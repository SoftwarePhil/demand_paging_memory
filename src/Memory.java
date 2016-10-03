//Philip DiMarco
public class Memory {

private int memorySize; 
private int pageSize; 
private int missCount = 0;
private int[] requests;
private Page[] memory;

public Memory(int memorySize, int pageSize, int[] requests){
	this.memorySize = memorySize;
	this.pageSize = pageSize;
	this.requests = requests;
	
	int amountOfPages = this.memorySize/this.pageSize;
	Page.setAmountOfPages(amountOfPages);
	memory = new Page[amountOfPages];
	
	for(int i = 0; i < memory.length; i++){
		memory[i] = new Page();
	}
}

public void runProgram(){
	int currentPage;
	for(int word : requests){
		currentPage = findPage(word);
		
		boolean hit = false;
		for(Page page : memory){
			if(page.getPage() == currentPage){
				System.out.println("Hit  :: page " + page.getPage() + ":: word " + word);
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
	System.out.print("There was a success rate of  " + (100 * (1 - (missCount/(float)requests.length))) + "%");
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
	int swapPageIndex = Page.swapPage();
	System.out.print("\tLoading page " + currentPage + " :: Unloading page " + memory[swapPageIndex].getPage() + "\n"); 
	
	memory[swapPageIndex].setPage(currentPage);; 
}

private void printMemory(){
	System.out.print("\tcurrent memory is ");

	for(Page c : memory){
		System.out.print(c.getPage() + " ");
	}
	
	System.out.println();
}

}
