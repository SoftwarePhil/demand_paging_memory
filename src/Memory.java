
public class Memory {

private int programSize = 460; 
private int memorySize = 400; 
private int pageSize = 200; 
private int[] requests;
private int[] cache;

public Memory(int programSize, int memorySize, int pageSize, int[] requests){
	this.programSize = programSize;
	this.memorySize = memorySize;
	this.pageSize = pageSize;
	
	cache = new int[memorySize/pageSize];
}

public void runProgram(int[] requests){
	this.requests = requests;
	
	

}

public int findPage(int currentWord){
	int page = 0; 
	
	if(currentWord < pageSize){ 
		page = 0; 
	} 
	else{ 
		page = (int) Math.ceil((double) currentWord/pageSize); 
	} 
	
	return page; 
}

public void swap(int currentPage){ 
	System.out.println ("Loading page #" + currentPage + ". Unloading page #" + cache[0]); 
	
	for(int x = 0; x < cache.length - 1; x++){ 
		cache[x + 1] = cache[x]; 
	} 
	
	cache[0] = currentPage;  
} 
}
