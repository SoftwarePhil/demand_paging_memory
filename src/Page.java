//Philip DiMarco
public class Page {

private int pageNumber = -1;
private static int size;
private static int counter;

static int nextLocation = 0;


public void setPage(int pageNumber){
	this.pageNumber = pageNumber;
}

public int getPage(){
	return pageNumber;
}

public static void setAmountOfPages(int size){
	Page.size = size;
}

public static int swapPage(){
	int temp = counter % size;
	counter++;
	return temp;
}
}
