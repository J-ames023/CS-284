/**
 * HW4 Submission by James Fong.
 * I pledge my honor that I have abided by the Stevens Honor System.
 */

package main;

import java.util.*;
import java.util.Stack;
import java.util.Random;

public class Treap<E extends Comparable> {
	
	private class Node<E> {
		/**
		 * Data Fields
		 */
		
		public E data; // key for the search
		public int priority; // random heap priority
		public Node <E> left;
		public Node <E> right;
		
		/**
		 * @param data
		 * @param priority
		 * Constructor node
		 */
	
		public Node (E data, int priority) {
			if (data == null) {
				throw new IllegalArgumentException("No nodes exist");
			}
			
			this.data = data;
			this.priority = priority;
			this.left = null;
			this.right = null;
		}

		/**
		 * performs a right rotation, returning a reference to the root
		 * of the result; updates data and priority attributes
		 * of the involved nodes accordingly.
		 * @return
		 */
		
		Node <E> rotateRight (){
			if(left == null) {
				return this;
			}
			
			else {
				Node<E> newRoot = this.left;
				left = left.right;
				newRoot.right = this;
				return newRoot;
			}
		}
		
		/**
		 * performs a left rotation, returning a reference to the root
		 * of the result; updates data and priority attributes
		 * of the involved nodes accordingly.
		 * @return
		 */
		
		Node <E> rotateLeft (){
			if(right == null) {
				return this;
			}
			
			else {
				Node<E> newRoot = this.right;
				right = right.left;
				newRoot.left = this;
				return newRoot;
			}
		}
		
		public String toString() {
			return "key = " + data + ", priority = " + priority;
		}
	}
		
	/**
	 * Data fields
	 */
	private Random priorityGenerator;
	private Node<E> root;

	/**
	 * Constructors; Treap() creates an empty treap. Initialize priorityGenerator using new Random().
	 */
	
	public Treap() {
		priorityGenerator = new Random();
		root = null;
	}

	public Treap(long seed) {
		priorityGenerator = new Random(seed);
		root = null;
	}
	
	/**
	 * 1. Insert new node as a leaf of the tree at appropriate position
	 * according to the ordering on E.
	 * 2. If priority of parent node is less than priority of new node,
	 * bubble up the new node in the tree towards the root such that
	 * the treap is a heap according to the priorities of each node.
	 * @param key
	 * @return
	 */
	
	boolean add (E key) {
		if (key == null) {
			throw new IllegalArgumentException("No value inputted!");
		}
		
		int priority = priorityGenerator.nextInt(); //
		
		return add(key, priority);
	}
		
	boolean add (E key, int priority) {
		Stack<Node<E>> stack = new Stack<Node<E>>();
		Node<E> temp = new Node<E>(key, priority);
		
		if (root == null) {
		    root = temp;
		    return true;
		}
		
		Node<E> parent = null;
		Node<E> child = root;
		
		while (child != null) {
		    parent = child;           
		    if (key.compareTo(child.data) > 0) {
		    	child = child.right;
		        stack.push(parent);
		    }
		    
		    else if (key.compareTo(child.data) < 0) {
		    	child = child.left;
		        stack.push(parent);
		    }
		    
		    else {
		    	return false;
		    }
		}
		
		if (key.compareTo(parent.data) > 0) {
			parent.right = temp;
		}
		
		else {
			parent.left = temp;
		}
		
		reheap(stack, temp);
		return true;
	}
	
	/**
	 * Use a helper function reheap (with appropriate parameters that should include the stack)
	 * to restore the heap invariant.
	 * @param stack
	 * @param current
	 */
	
	private void reheap(Stack<Node<E>> stack, Node<E> current) {
		if (current.priority < stack.peek().priority) {
			return;
		}
		
		Node<E> parent = stack.pop();
		
		while((parent.priority > current.priority)) {
        	if (parent.left == current) {
        		if (stack.isEmpty()) {
        			root = parent.rotateRight();
        		}
        		else {
        			Node<E> CurrNode = stack.peek();
        			if (CurrNode.left == parent) {
        				CurrNode.left = parent.rotateRight();
        			}
        			else if (CurrNode.right == parent) {
        				CurrNode.right = parent.rotateRight();
        			}
        		}
        	}
        	if (parent.right == current) {
        		if (stack.isEmpty()) {
        			root = parent.rotateLeft();
        		}
        		else {
        			Node<E> CurrNode = stack.peek();
        			if (CurrNode.left == parent) {
        				CurrNode.left = parent.rotateLeft();
        			}
        			else if (CurrNode.right == parent) {
        				CurrNode.right = parent.rotateLeft();
        			}
        		}
        	}
        	
        	if(!stack.empty()) {
        		parent = stack.pop();
        	}
        	
        	else {
        		break;
        	}
        }
	}
	
	/**
	 * Deletes the node with the given key from the treap and returns true.
	 * If the key was not found, the method does not modify the
	 * treap and returns false. If the node to erase has both left and right subtrees,
	 * look at priorities of children, and consider highest one to determine rotating left or right.
	 * @param root
	 * @param key
	 * @return
	 */
	boolean delete (E key) {
		if(key == null)
			throw new IllegalArgumentException("Called delete() with null key");
		root = delete(root, key);
		return true;
	}
	
	private Node<E> delete(Node<E> root, E key) {
    	
		if (root != null) {
            if (key.compareTo(root.data) > 0) {
                root.right = delete(root.right, key);
                
            } else if (key.compareTo(root.data) < 0) {
                root.left = delete(root.left, key); 
                
            } else {
                if (root.right == null) {
                    return root.left;
                    
                } else if (root.left == null) {
                    return root.right;
                    
                } else {
                    Node<E> replace = root.right;
                    while(replace.left != null) {
                    	replace = replace.left;
                    }
                    
                	root.data = replace.data;
                    root.right = delete(root.right, root.data);
                }
            }
        }
        return root;
    }
	
	/**
	 * Finds a node with the given key in the treap rooted at root and returns true if it finds it and false otherwise.
	 * @param root
	 * @param key
	 * @return
	 */
	
	private boolean find (Node <E> root, E key) {
		if(root == null) {
			throw new IllegalArgumentException("Called with null key to find");
		} else {
			
			if(root.data.compareTo(key) == 0) {
				return true;
			}
			if(root.data.compareTo(key) < 0) {
				return find(root.right, key);
			}

			return find(root.left, key);
		}
    }
	
	/**
	 * Find a node with a given key in the treap and returns true if found;
	 * otherwise false.
	 * @param key
	 * @return
	 */
	public boolean find (E key) {
		if (key == null)
			throw new IllegalArgumentException("Called with null key to find");
		
		return find(root, key);
	}
	
	private String toString(Node<E> n, int depth) {
		StringBuilder str = new StringBuilder();

		for (int i = 0; i < depth; i++) {
			str.append("  ");
		}
		if (n==null) {
			str.append("null");
			
		} 
		
		else {
			str.append(n.toString());
			str.append("\n");
			str.append(toString(n.left, depth + 1));
			str.append("\n");
			str.append(toString(n.right, depth + 1));
		}
		return str.toString();
	}
	
	public String toString () {
			return toString(root, 0);
	}
	
	/*
	public static void main(String[] args) {
		
		Treap<Integer> t = new Treap<Integer>();
		
		t.add(4,19);
		
		System.out.println(t.toString());
		
		t.delete(4);
		
		System.out.println(t.toString());
		
		t.add(2,31);
		t.add(6,70);
		t.add(1,84);
		t.add(3,12); 
		t.add(5,83);
		t.add(7,26);
		System.out.println(t.toString());
		
	}
	*/

}
