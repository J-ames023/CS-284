//Written by James Fong
//I pledge my honor that I have abided by the Stevens Honor System.

package main;

import java.util.ArrayList;
import java.util.StringJoiner; 

public class IDLList<E> { //Indexed DLL

	@SuppressWarnings("hiding")
	private class Node<E> { // write constructors
		E data;
		Node<E> next;
		Node<E> prev;
		
		@SuppressWarnings("unused")
		Node (E elem) { // constructor that creates a node holding elm
			elem = data;
			this.next = null;
			this.prev = null;
		}
		
		Node (E elem, Node<E> prev, Node<E> next){  //a constructor that creates a node holding elm, with net as next and prev as previous.
			this.data = elem;
			this.next = next;
			this.prev = prev;
		}
		//get(int i)
	}
	
	private Node<E> head; //data, next and prev (head.data, .next, .prev
	private Node<E>	tail; //data, next and prev (tail.data, .next, .prev
	private int size; //Size of DLL, not array
	private ArrayList<Node<E>> indices;

	// Creates an empty double linked list
	public IDLList() { 
		head = null; 
		tail = null; 
		size = 0;    
		indices = new ArrayList<Node<E>>();
	}
	
	 //that adds elem at position index (counting from wherever head is). It uses the index for fast access
	public boolean add(int index, E elem) {
		
		if (index < 0) { // Inputted index must be larger than 0.
			throw new IllegalStateException("No such index exists for the DLL.");
		}
		if (index == 0) { // index at 0 is head
			return this.add(elem);
		}
		
		//Finding existing node, inserting new node
		else if (index == size() - 1 && head !=null) {
			Node<E> Existing = indices.get(index);
			Node<E> Insert = new Node<E>(elem, Existing.prev, Existing);	
			size++;
			indices.add(index, Insert);
			tail = Existing;
			
		} else {
			Node<E> Existing = indices.get(index);
			Node<E> Insert = new Node<E>(elem, Existing.prev, Existing);	
			size++;
			indices.add(index, Insert);
		}
		//Increase DLL, add to index
		
		return true; // Always Returns True

	}
	
	// that adds elem at the head (i.e. it becomes the first element of the list). It always returns true
	public boolean add (E elem) { 
		
		//identifying newNode
		
		// Empty DLL
		if (head == null) {
			
			//identifying newNode
			Node<E> newNode = new Node<E>(elem, null, null);
			head = newNode;
			tail = head;
			size++;
			indices.add(0, head);
		}
		
		// If there exists only one node in DLL
		else if (size == 1) {
			Node<E> newNode = new Node<E>(elem, null, head);
//			head.prev = newNode;
			head = newNode;
			head.next = tail;
			tail.prev = head;
			size++;
			indices.add(0, newNode);
		}
		
		// All other cases
		else {
			Node<E> newNode = new Node<E>(elem, null, null);
			head.prev = newNode;
			head = newNode;
			size++;
			indices.add(0, head);
		}
		
		// Always return true
		return true;
	}
	
	//adds elem as the new last elemen of the list (at the tail). Always returns true.
	public boolean append (E elem) {
		
		Node<E> node = new Node<E>(elem, tail, null);
		
		// Empty DLL
		if (tail == null) {
			tail = node;
			head = new Node<E>(elem, null, tail);
			head = tail;
			size++;
			indices.add(tail);
		}
		
		// else if (tail == head) and else are the same case, all other cases
		else { 
			tail.next = node;
			tail = node;
			size++;
			indices.add(tail);
		}
		// Always return true
		return true;
	}
	
	public E get(int index) {// returns the object at position index from the head. It uses the index for fast access. Indexing starts from 0, thus get(0) returns the head element of the list.
			return indices.get(index).data;
	}
	
	public E getHead() { // that returns the object at the head
		
		if (head != null) { // Checks if head data is not empty
			return head.data;
		}
		else {
			throw new IllegalStateException("The Doubly Linked List is Empty.");
		}
		
	}
	
	public E getLast() { // returns the object at the tail.
		if (tail != null) { // Checks if tail data is not empty
			return tail.data;
		}
		else {
			throw new IllegalStateException("The Doubly Linked List is Empty.");
		}
	}
	
	public int size() { // returns the list size
		return size;
	}
	
	// removes and returns element at the head. Should throw an IllegalStateException if there is no such element
	public E remove() {

		if (head == null) { // empty DLL cannot 
			throw new IllegalStateException("Cannot remove; no such element exists at head.");
		}
		
		if (head == tail) { // one node in DLL
			E data = head.data;
			head = null;
			size--;
			indices.remove(0);
			return data;
		}
		
		else { // all other cases
			E data = head.data;
			head = head.next;
			size--;
			indices.remove(0);
			return data;
		}
	}
	
	// removes and returns the element at the tail. Should throw an IllegalStateException if there is no such element
	public E removeLast() { 
		if (tail == null) { // empty DLL
			throw new IllegalStateException("Cannot remove; no such element exists at head.");
		}
		
		if (tail == head) { // one node in DLL
			E data = tail.data;
			tail = null;
			size--;
			indices.remove(0);
			return data;
		}
		
		else { // All other cases
			E data = tail.data;
			tail = indices.get(indices.size() - 1);
	        indices.get(indices.size() - 2).next = null;
	        indices.remove(indices.size() - 1);
	        size--;
	        return data;
		}
	}
	
	//removes and returns the element at the given index. Use the index for fast access. Should throw an IlllegalStateException if no such element.
	public E removeAt (int index) { 
		if (index > size || index < 0) {
			throw new IllegalStateException("Cannot remove; no such element exists at selected index.");
		}
		if (index == 0) { // Remove at head.
			return this.remove();
		}
		
		if (index == size) { // Remove at tail
			return this.removeLast();
		}
		
		else { // Remove anywhere else at by index
			
			Node<E> RemoveNode = indices.get(index);
			
			if (RemoveNode.next == null) {
				RemoveNode.prev.next = null;
			}
			
			else {
				RemoveNode.prev.next = RemoveNode.prev;
			}
			indices.remove(index);
			size--;
			return RemoveNode.data;
		}
	}

	// removes first occurrence of elem in the list and returns true. Returns false if elem was not in the list.
	public boolean remove (E elem) {
			
		int index = 0; // starts at index 0
		if (elem != null) { // Ensures element exists
			while (index < size()) { 
				if(indices.get(index).data == elem ) {
					removeAt(index);
					return true; //Returns true when reaches first element that meets elem condition
				}
				index += 1;
			}
		}
		else {
			throw new IllegalStateException("The element does not exist in the DLL."); 
		}
		return false;
	}
	
	// Presents a string representation of the list
	public String toString() {
		
		//Setting beginning of string to record at head
        StringJoiner str = new StringJoiner(", ", "[ ", " ]");
        
        for(int i = 0; i < size(); i++) {
			str.add(indices.get(i).data.toString());
		}
		return str.toString();
	}
}
