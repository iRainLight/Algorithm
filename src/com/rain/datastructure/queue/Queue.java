package com.rain.datastructure;

public interface Queue<T>{
	public int size();

	public boolean isEmpty();

	public void offer(T elem);

	public T poll();

	public T peek();
}
