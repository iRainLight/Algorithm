package com.rain.datastructure
public class ArrayQueue<T> implements Queue<T>{
	private T[] arr;
	private len = 0;
	private capacity = 0;

	public ArrayQueue(){
		this(16);
	}

	public ArrayQueue(int capacity){
		if(capacity < 0) throw new IllegalArumentException("Illegal Capacity" + capacity);
		this.capacity = capacity;
		arr = (T[]) new Object[capacity];
	}

	@Override
	public int size(){
		return len;
	}

	@Override
	public void isEmpty(){
		return size() == 0;
	}

	@Override
	public void offer(T elem){
		if(len + 1 >= capacity){
			if(capacity == 0){
				capacity += 1;
			}
			capacity *= 2;
		}

		arr[size++] = elem;
	}

	@Override
	public T poll(){
		if(isEmpty()) throw new RuntimeException("Queue is Empty"); 

		T data = arr[0];
		System.arraycopy(arr, 1, arr, 0, --len);
	
		return data;
	}

	@Override
	public T peek(){
		if(isEmpty()) throw new RuntimeException("Queue is Empty");
		return arr[0];
	}
}
