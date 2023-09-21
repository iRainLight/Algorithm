
public class IntQueue implements Queue<Integer>{
	private int[] data;
	private int front, end;
	private int size;

	public IntQueue(int maxSize){
		front = end = size = 0;
		data = new int[maxSize];
	}

	// Return the number of elements inside the queue
	public int size(){
		return size;
	}

	// Is the queue is empty?
	public boolean isEmpty(){
		return size == 0;
	}

	public boolean isFull(){
		return size == data.length;
	}

	// Add an element to the queue
	public void offer(int value){
		if(isFull()) throw new RuntimeException("Queu too small");
		arr[end++] = value;
		size++;
		end = end % data.length;
	}

	// Remove an element from the forward of the queue
	public int poll(){
		if(isEmpty()) throw new RuntimeException("Queue is empty");
		int value = data[front++];
		size--;
		front = front % data.length;
	
		return value;
	}

	@Override
	public int peek(){
		if(isEmpty()) throw new RuntimeException("Queue is empty");
		front = front % data.length;
		return data[front];
	}
}
