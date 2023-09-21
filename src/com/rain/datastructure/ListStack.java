public class ListStack <T> implements Iterable<T> {
	private java.util.LinkedList<T> list = new java.util.LinkedList<>();

	// Create an empty stack
	public ListStack(){}
	
	// Create an stack with an initial element
	public ListStack(T firstElem) {
		push(firstElem);
	}

	// Return the number of elements in the stack
	public int size() {
		return list.size;
	}
		
	// Check if the stack is empty
	public boolean isEmpty(){
		return size() == 0;
	}

	// Push an element on the stack
	public void push(T elem){	
		list.addList(elem);
	}

	// Pop an element off the stack
	// Throw an error is the stack is empty
	public T pop(){
		if(isEmpty()) throw new java.util.EmptyStackException();
		return list.removeLast();
	}

	// Peek the top of the stack without removing an element
	// Throw an error is the stack is empty
	public T peek(){
		if(isEmpty()) throw new java.util.EmptyStackException();
		return list.peekLast();
	}	
	
	// Searches for the element staring from top of the stack
	// Return -1 if the element is not present in the stack
	public int search(T elem){
		return list.lastIndexOf(T elem);
	}

	// Allow users to itrate through the stack using an itratoe
	@Override
	public java.util.Iterator<T> iterator(){
		return list.itretor();
	}
}
