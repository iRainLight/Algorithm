package com.rain.datastructure;

public class LinkedQueue<T> implements Itrable<T>, Queue<T> {
	private java.util.LinkedList<T> list = new java.util.LinkedList<>();
	

	public LinkedQueue(){}

	public LinkedQueue(T firstElem){
		offer(firstElem);
	}

	// Return the size of the queue
	public int size(){
		return list.size();
	}

	// Return whether or not the queue is empty
	public boolean isEmpty(){
		return size() == 0;
	}

	// Add an element to the back of the queue
	public void offer(T elem){
		list.addLast(elem);
	}
	// Peek the element at the front of the queue
	// The method throws an error is the queue is empty
	public T peek(){
		if(isEmpty()) throw new RuntimeException("Queue Empty");
		return list.peekFirst();
	}

	// Poll an element from the front of the queue
	// The method throws an error is the empty is queue
	public T poll(){
		if(isEmpty()) throw new RuntimeException("Queue Empty");
		return list.removeFirst();
	}

	// Return an iterator to alow the user to traverse
	// through the elements found the queue
	public java.util.Itretor<T> iterator(){
		return list.iterator();
	}
}
