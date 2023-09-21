public class DoublyLinkedList <T> implements Itrable <T> {
	private int size = 0;
	private Node <T> head = null;	// head node
	private Node <T> tail = null;	// tail node

	// Internal node class to represent	data
	private class Node <T> {
		T data;
		Node <T> prev, next;
		public Node(T data, Node <T> prev, Node <T> next){
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
		
		@Override
		public String toString(){
			return data.toString();
		}
	}

	// Empty this linked list, O(n)
	public void clear(){
		Node <T> trav = head;	// 当前节点，会遍历所有节点
		while(trav != null){
			Node <T> next = trav.next;
			trav.prev = trav.next = null;
			trav.data = null;
		}
		head = tail = trav = null;
		size = 0;
	}	
	
	// Return the size of this linked list
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
	
	// Add an element to the head of this linked list,O(1)
	public void addFirst(T elem){
		// The linked list is empty
		if(isEmpty()){
			head = tail = new Node(T elem, null, null);
		} else {
			head.prev = new Node(T elem, null, head);
			head = head.prev;
		}
		size++;
	}

	// Add an element to the tail of this linked list,O(1)
	public void addLast(T elem){
		// The linked list is empty
		if(isEmpty()){
			head = tail = new Node(elem, null, null);
		} else {
			tail.next = new Node(elem, tail, null);
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
		// The linked list is empty
		if(isEmpty()) throw new RuntimeException("Empty list");
		
		// Extract the data at the head and move
		// the head pointer forwards one node
		T data = head.data;
		head = head.next;
		--size;

		// If the list is empty set the tail to null as well	
		if(isEmpty()){
			tail = null;
		} else {
			// Do a memory clean of the previous node
			head.prev = null;
		}
		
		// Return the first value at the head of the linked list
		return data;
	}

	// Remove the last value at the tail of the linked list, O(1)
	public T removeLast(){
		// Can't remove empty data from empty list -_-
		if(isEmpty()) throw new RuntimeException("Empty list");

		// Extract the data at the tail and move
		// the tail pointer backwards one node
		T data = tail.data;
		tail = tail.prev;
		--size;
		
		// If the list is empty set the head to null as well
		if(isEmpty()){
			head = null;
		} else {
			// Do a memory clean of the previous node
			tail.next = null;
		}

		// Return the last value that was at the last node we just remove
		return data;
	}
	
	// Remove an arbitrary node from the linked list,O(1) 
	public T remove(Node <T> node){
		// If the node to remove is somewhere either at the
		// head or the tail handle those independently
		if(node.prev == null) return removeFirst();
		if(node.next == null) return removeLast();

		// Make the pointers of adjcent nodes skip over 'node'
		node.prev.next = node.next;
		node.next.prev = node.prev;

		// Temporary store the data we want to return
		T data = node.data;
	
		// Memory clearup
		node.data = null;
		node = node.prev = node.next = null;

		--size;

		// Return the data at the node we just removed
		return data;
	}

	// Remove a node at a particular index,O(n)
	public T removeAt(int index){
		// Make sure the index provided is valid
		if(index < 0 || index >= size) throw new IllegalArgumentException();

		Node <T> trav;
		// Search from the front of the list
		if(index < size/2){
			for(int i = 0, trav = head; i != index; i++){
				trav = trav.next;
			}
		// Search from the back of the lise
		} else {
			for(int i = size-1, trav = tail; i != index; i--){
				trav = trav.prev;
			}
		}

		return remove(trav);
	}

	// Remove a particular value in the linked list,O(n)
	public boolean remove(Object obj){
		Node <T> trav;

		// Support searching for null
		if(obj == null){
			for(trav = head; trav != null; trav = trav.next){
				if(trav.data == null){
					remove(trav);
					return true;
				}
			}
		// Search for non null object
		} else {
			for(trav = head; trav != null; trav = trav.next){
				if(obj.equals(trav.data)){
					remove(trav);
					return true;
				}
			}
		}
		return false;
	}	
	
	// Find the index of a particular value in the linked list,O(n)
	public int indexOf(Object obj){
		Node <T> trav;

		// Supprot searching for null
		if(obj == null){
			for(trav = head, int index = 0; trav != null; trav = trav.next, index++){
				if(trav.data == null){
					return index;
				}
			}
		// Search for non null object
		} else {
			for(int index = 0, trav = head; trav != null; trav = trav.next, index++){
				if(obj.equals(trav.data){
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
	public java.util.Itrator <T> itrator(){
		return java.util.Itrator <T>(){
			private Node <T> trav = head;
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
		Node <T> trav = head;
		StringBuilder sb = StringBuilder();
		sb.append("[");
		while(trav != null){
			sb.append(trav.data);
			if(trav.next != null){
				sb.append(",");
			}
			trav = trav.next;
		}
		sb.append("]");
		return sb.toString();
	}	
}

