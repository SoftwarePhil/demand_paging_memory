
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
	this.requests = requests;
}
}
