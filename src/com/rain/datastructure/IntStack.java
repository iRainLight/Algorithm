
public class IntStack{
	private int arr[];
	private int pos = 0;

	// maxSize is the maximum number of items
	// that can be in the queue at any given time
	public IntStack(Int maxSize){
		arr = new T[maxSize]; 

	// Return the number of elements insize the stack
	public int size(){
		return pos;
	}

	// Return true/false on whehter the stack is empty
	public boolean isEmpty(){
		return size() == 0;
	}
	
	// Add an element to the top of the stack
	public void push(Integer elem){
		arr[pos++] = elem;
	}

	// Make sure you check that the stack is not empty before calling pop
	public Integer pop(){
		if(isEmpty()) throw new EmptyStackException();
		return arr[--pos];
	}
	
	// Return the element at the top of the stack}
	public Integer peek(){
		if(isEmpty()) throw new EmptyStackException();
		return arr[pos];
	}
}
