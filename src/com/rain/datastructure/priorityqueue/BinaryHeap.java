// A min priority queue implementation using a binary tree

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BinaryHeap<T extends Comparable<T>>{
	// A dynamic list to track the elements inside heap
	private List<T> heap = null;

	// Construct and initially empty priority queue
	public BinaryHeap(){
		this(1);
	}

	// Construct a priority queue with an inital capacity
	public BinaryHeap(int sz){
		head = new ArrayList<>(sz);
	}

	// Construct a priority queue using heapify in O(n) time
	public BinaryHeap(T[] elems){
		int heapSize = elems.length;
		heap = new ArrayList<>(heapSize);

		// Place all elements in heap
		for(int i = 0; i < heapSize; i++) heap.add(elems[i]);

		// Heapify process,O(n)
		for(int i = Math.max(0, (heapSize / 2) - 1); i >= 0; i--) sink(i);
	}	

	// Priority queue construction,O(n)
	public BinaryHeap(Collection<T> elems){
		int heapSize = size();
		heap = new ArrayList<>();

		// Add all elements of the given collection to the heap
		heap.addAll(elems);
		
		// Heapify process, O(n)
		for(int i = Math.max(0, (heapSize / 2) - 1); i >= 0; i--) sink(i);
	}
	
	// Return ture/false depending on if the priority queue is empty
	public boolean isEmpty(){
		return size() == 0;
	}

	// Return the size of the heap
	public int size(){
		return heap.size();
	}

	// Clears everything inside	the heap,O(n)
	public void clear(){
		heap.clear();
	}

	// Return the value of the element with the lowest
	// priority in this priority queue. If the priority
	// queue is empty null is returned.
	public T peek (){
		if(isEmpty()) return null;
		return heap.get(0);
	}

	// Remove the root of the heap,O(log(n))
	public T poll(){
		return removeAt(0);
	}

	// Test if an element is in the heap,O(n)
	public boolean contains(T elem){
		// Linear scan to check containment
		for(int i = 0; i < size(); i++){
			if(heap.get(i).equals(elem))
				return true;
		}
		return false;
	}

	// Adds an element to the priority queue, the
	// element must not be null,O(log(n))
	public void add(T elem){
		if(elem == null) throw new IllegalArgumentException();
		heap.add(elem);
		
		int	indexOfLastElem = size() - 1;
		swim(indexOfLastElem);
	}

	// Tests if the value of node i <= node j
	// This method assume i & j are valid indices,O(1)
	private boolean less(int i, int j){
		T node1 = heap.get(i);
		T node2 = heap.get(j);
		return node1.compareTo(node2) <= 0;
	}

	// Perform bottom up nodes swim,O(log(n))
	private void swim(int k){
		// Grab the index of the next parent node WRT to k
		int parent = (k - 1) / 2;

		// Keep swimming while we have not reached the 
		// root and while we're less than our parent.
		while(k > 0 && less(k, parent)){
			// Exchange k with the parent
			swap(k, parent);
			k = parent;

			// Grab the index of the next parent node WPT to k
			parent = (k - 1) / 2;
		}
	}

	// Top down node sink, O(log(n))
	// Swap elements from the lowest level to the top level
	private void sink(int k){
		int heapSize = size();
		while(true){
			int left = 2*k + 1;	// left node
			int right = 2*k + 2;	// right node
			int smallest = left;	// Assume left is the smallest node of the two children

			// Find which is smaller left or right
			// If right is smaller set smallest to be right
			if(right < heapSize && less(right, left) 
				smallest = right;

			// Stop if we're outside the bounds of the tree 
			// or stop early if we cannot sink k anymore
			if(left > heapSize || less(k, smallest)
				break;

			// Move down the tree following the smallest node
			swap(smallest, k);
			k = smallest;
		}
	} 

	// Swap two nodes. Assumes i & j are valid,O(1)
	private void swap(int i, int j){
		T elem_i = heap.get(i);
		T elem_j = heap.get(j);

		heap.set(i, elem_j);
		heap.set(j, elem_i);
	}

	// Removes a particular	element in the heap,O(n)
	public boolean remove(T element){
		if(element == null) return false;
		// Linear removal via search,O(n)
		for(int i = 0; i < size(); i++){
			if(heap.get(i).equals(element)){
				removeAt(i);
				return true;
			}
		}
		return false;
	}

	// Removes a node at particular index,O(log(n))
	private T removeAt(int i){
		if(isEmpty()) return null;

		int indexOfLastElem = size() - 1;
		T remove_data = heap.get(i);
		swap(i, indexOfLastElem);

		// Obliterate the value
		heap.remove(indexOfLastElem);

		// Check if the last element was removed
		if(i == indexOfLastElem) return remove_data;
		T elem = heap.get(i);

		// Try sinking element
		sink(i);

		// If sinking did not work try swimming
		if(heap.get(i).euqls(elem)	)	swim(i);
		return remove_data;
	}

}
