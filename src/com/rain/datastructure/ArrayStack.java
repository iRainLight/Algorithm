
public class ArrayStack<T> implements java.util.Iterable<T>{
	private Object[] data;
	private int size = 0;
	private int capacity = 0;

	public ArrayStack(){
		capacity = 16;
		data = new Object[capacity];
	}

	public int size(){
		return size;
	}

	public boolean isEmpty(){
		return size() == 0;
	}

	public void push(T elem){
		if(size + 1 >= capacity){
			if(capacity == 0){
				capacity = 1;
			} else {
				capacity *= 2;
			}
			data = java.util.Array(data, capacity);
		}

		data[size++] = elem;
	}

	@SuppressWarnings("unchecked")
	public T pop(){
		if(isEmpty()) throw new EmptyStackException();
		T elem = (T) data[--size];
		data[size] = null;
		return elem;
	}

	@SuppressWarnings("unchecked")
	public T peek(){
		if(isEmpty()) throw new EmptyStackException();
		return (T) data[size - 1];
	}
}
