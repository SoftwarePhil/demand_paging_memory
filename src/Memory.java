
public class Memory {

private int memorySize; 
private int pageSize; 
private int missCount = 0;
private int[] requests;
private int[] cache;

public Memory(int memorySize, int pageSize, int[] requests){
	this.memorySize = memorySize;
	this.pageSize = pageSize;
	this.requests = requests;
	
	cache = new int[this.memorySize/this.pageSize];
	
	for(int i = 0; i < cache.length; i++){
		cache[i] = -1;
	}
}

public void runProgram(){
	int currentPage;
	for(int word : requests){
		currentPage = findPage(word);
		
		boolean hit = false;
		for(int page : cache){
			if(page == currentPage){
				System.out.println("Hit  :: page " + page + ":: word " + word);
				hit = true;
				break;
			}
		}
		
		if(hit == false){
			System.out.println("Miss .. word "+ word +":: page "+ currentPage  +" swapping .. ..");
			swap(currentPage);
			missCount++;
		}
		
		printMemory();
		System.out.print("\n");
	}
	
	System.out.println("\nThis program missed " + missCount + " times out of " + requests.length);
	System.out.print("There was a swap rate of  " + (100 * (missCount/(double)requests.length)));
}

private int findPage(int currentWord){
	int page = 0; 
	
	if(currentWord < pageSize){ 
		page = 0; 
	} 
	else{ 
		page = (int) Math.ceil((double) currentWord/pageSize); 
	} 
	
	return page; 
}

private void swap(int currentPage){ 
	System.out.print("\tLoading page " + currentPage + " :: Unloading page " + cache[0]); 
	
	
	for(int x = 0; x < cache.length - 1; x++){ 
		cache[x] = cache[x + 1]; 
	} 
	
	cache[cache.length - 1] = currentPage; 
	System.out.println();
}

private void printMemory(){
	System.out.print("\tcurrent memory is ");

	for(int c : cache){
		System.out.print(c + " ");
	}
	
	System.out.println();
}

}
