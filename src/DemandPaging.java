public class DemandPaging { 

/** 
This problem studies the effect of changing page sizes in a demand paging system. 
The following sequence of requests for program words is taken from a 
460&#8208;word program: 10, 11, 104, 170, 73, 309, 185, 245, 246, 434, 458, 364. 
Main memory can hold a total of 200 words for this program and 
the page frame size will match the size of the pages 
into which the program has been divided. 
1. Find the success frequency for the request list using a FIFO replacement algorithm and a page size of 
100 words (there are two page frames). 

* Request for Word comes in 
* Work out which Page this Word is on 
* Check if this Page is in the Cache 
* If it's not then load the Page into the Cache, potentially dropping something out 
* If it is in the Cache then move it to the top of the Cache 
* Load the Word from the Page 
* Return the Word 

So, for your example numbers: 
10. This is Page #1. This isn't in the Cache so load it and store it into the Cache. 
11. This is Page #1. This is in the Cache to load it and store it into the cache. 
104. This is Page #2. This isn't in the Cache so load it and store it into the Cache. 
170. This is Page #2. This is in the Cache to load it and store it into the cache. 
73. This is Page #1. This is in the Cache, but isn't the top of the cache so move it to the top. 
309. This is page #3. This isn't in the Cache so load it and store it into the Cache. At this point, the Cache would store three items and can only store two, so drop the bottom one (Page #2) out of the cache. 
185. This is page #2. This isn't in the Cache any more so load it and store it into the Cache. At this point, the Cache would store three items and can only store two, so drop the bottom one (Page #1) out of the cache. 
etc, etc, etc... 
*/ 
static int[] cache;



public static void main(String[] args){ 
int programSize = 460; 
int memorySize = 200; 
int pageSize = 200; 
int pageFrameSize = (int) Math.ceil((double) programSize / pageSize); 
int counter = 0 ; 

int currentPage = 0; 

cache = new int[memorySize/pageSize]; 
/*
int[] wordRequest = new int[12]; 

wordRequest[0] = 10; 
wordRequest[1] = 11; 
wordRequest[2] = 104; 
wordRequest[3] = 170; 
wordRequest[4] = 73; 
wordRequest[5] = 309; 
wordRequest[6] = 185; 
wordRequest[7] = 245; 
wordRequest[8] = 246; 
wordRequest[9] = 434; 
wordRequest[10] = 458; 
wordRequest[11] = 364; 
*/

int[] wordRequest = {10, 11, 104, 170, 73, 309, 185, 245, 246, 434, 458, 364};


for(int i = 0 ; i < wordRequest.length; i++){ 
	currentPage = whichPage(wordRequest[i], pageSize); 
	System.out.println(wordRequest[i] + " is page #" + currentPage); 
	
	for(int r : cache){
		System.out.print(r + " ");
	}
	System.out.println();
	int p;
	
	for(p =0; p < cache.length; p++){ 
		if( cache[p] == currentPage){ 
			// Cache hit 
			System.out.println(wordRequest[i] + " Hit"); 
			break; 
		} 	
	} 
	
	if (p == cache.length) { 
		// Cache miss 
		System.out.println (wordRequest[i] + " Miss."); 
		swap(currentPage); 
		counter++; 
	} 
} 

System.out.println(counter + " misses"); 
} 


public static void swap(int currentPage){ 
	System.out.println ("Loading page #" + currentPage + ". Unloading page #" + cache[0] + "."); 
	
	for(int x = 0; x < cache.length - 1; x++){ 
		cache[x + 1] = cache[x]; 
	} 
	
	cache[0] = currentPage; 
	//System.out.println(cache.length); 
} 

public static int whichPage(int word, int pageSize){ 
	int page = 0; 
	
	if( word < pageSize){ 
		page = 0; 
	} 
	else if( word > pageSize){ 
		page = Math.round(word/(float)pageSize); 
	} 
	
	return page; 
} 

} 
