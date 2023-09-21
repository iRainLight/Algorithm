public class SinglyLinkedList<T> implements Iterable<T> {
	private int size = 0;
	private Node<T> head = null;
	private Node<T> tail = null;

	// Internal node class to represent data
	private class Node<T>{
		T data;
		Node<T> next;
		public Node(T data, Node<T> next){
			this.data = data;
			this.next = next;
		}

		@Override
		public String toString(){
			return data.toString;
		}
	}
		
	// Empty the linked list,O(n)
	public void clear(){
		Node<T> trav = head;
		while(trav != null){
			Node<T> temp = trav.next;
			trav.next = null;
			trav.data = null;
		}
		head = tail = trav = null;
		size = 0;
	}

	// Return the size of the linked list
	public int size(){
		return size;
	}

	// Is this linked list empty?
	public boolean isEmpty(){
		return size() == 0;
	}

	// Add an element to the tail of the linked list,O(1)
	public void add(T elem){
		addLast(elem);
	}

	// Add an element to the head of the linked list,O(1)
	public void addFirst(T elem){
		// The linked list is empty
		if(isEmpty()){
			head = tail = new Node(elem, null);
		} else {
			Node<T> temp = head;
			head = new Node(elem, temp);
		}

		size++;
	}

	// Add an element to the tail of the linked list,O(1)
	public void addLast(T elem){
		// The linked list is empty
		if(isEmpty()){
			head = tail = new Node(elem, null);
		} else {
			tail.next = new Node(elem, null);
			tail = tail.next;
		}
		size++;
	}

	// Check the value of the first node if it exists,O(1)
	public T peekFirst(){
		if(isEmpty()) throw new RuntimeException("Empty list");
		return head.data;
	}

	// Check the value of the last node if it exists,O(1)
	public T peekLast(){
		if(isEmpty()) throw new RuntimeException("Empty list");
		return tail.data;
	}

	// Remove the first value at the head of the linked list,O(1)
	public T removeFirst(){
		if(isEmpty()) throw new RuntimeException("Empty list");
		
		// Extract the data at the head and move
		// the head pointer forwards one node
		T data = head.data;
		Node<T> prev = head;	
		head = head.next;
		size--;

		// if the list is empty set the tail to null as well
		if(isEmpty()){
			tail = null;
			prev = null;
		} else {
			// Do a memory clear of the previous node
			prev = null;
		}
		
		// Return the head value at the head of the linked list
		return data;
	}

	// Remove the last value at the tail of the linked list,O(n)
	public T removeLast(){
		// Can't remove empty data from empty list
		if(isEmpty()) throw new RuntimeException("Empty list");

		// Extract the data at the tail and move
		// the tail pointer backwards one node
		T data = tail.data;
		Node<T> lastNode= tail;

		// Find a node that the tail's prevoius node and doing move
		Node<T> trav = head;
		while(trav.next != tail && head != tail){
			trav = trav.next;
		}
		tail = trav;
		size--;

		// If the list is empty set the head to null as well
		if(isEmpty()){
			head = null;
			tail = null;
		} else {
			// Do a memory clear of the last node
			tail.next = null;
		}
		return data;
	}

	// Ramove an arbitrary node from the linked list,O(n)
	public T remove(Node<T> node){
		// If the node to remove is somewhere either at the 
		// head or the tail handle those independently
		if(node == head) return removeFirst();
		if(node == tail) return removeLast();

		// Make the poniter of adjcent nodes skip over 'node'
		Node<T> trav = head;
		while(trav.next != node){
			trav = trav.next;
		}
		trav.next = node.next;

		// Temporary store the data we want to return
		T data = node.data;

		// Memory clearup
		node.data = null;
		node = node.next = null;

		--size;

		// Return the data at the node we just removed
		return data;
	}

	// Remove a node at a particular index,O(n)
	public T removeAt(int index){
		// Make sure the index provided is valid
		if(index < 0 || index >= size) throw new IllegalArgumentException();

		Node<T> trav = head;
		// Search from the front of the linked list
		for(int i = 0; i != index; i++){
			trav = trav.next;
		}

		return remove(trav);
	}

	// Find the index of a particular value at the linked list,O(n)
	public int indexOf(Object obj){
		Node<T> trav = head;

		// Support search for null
		if(obj == null){
			for(int index = 0; index < size; trav = trav.next, index++){
				if(trav.data == null){
					return index;
				}
			}
		// Search for non null object
		} else {
			for(int index = 0; index < size; trav = trav.next, index++){
				if(obj.equals(trav.data)){
					return index;
				}
			}
		}

		return -1;
	}

	// Check a value is contained with the linked list
	public boolean contained(Object obj){
		return indexOf(obj) != -1;
	}

	@Override
	public java.util.Itrator<T> itrator(){
		return java.util.Itrator<T>(){
			Node<T> trav = head;
			@Override
			public boolean hasNext(){
				return trav != null;
			}
			@Override
			public T next(){
				T data = trav.data;
				trav = trav.next;
				return data;
			}
		};
	}

	@Override
	public String toString(){
		Node<T> trav = head;
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		while(trav != null){
			sb.append(trav.data);
			if(trav.next != noll){
				sb.append(",");
			}
			trav = trav.next;
		}
		sb.append("]");

		return sb.toString();
	}
}
