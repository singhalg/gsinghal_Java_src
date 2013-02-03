package lab9;

import java.util.NoSuchElementException;

/**
 * Implements a Map as an ordered list of (key,value) pairs.
 * CSE131 Lab 9
 * @author
 * @version 1.0
 * Date:
 */

public class OrderedListMap<K extends Comparable<K>,V> implements Map<K,V> {

	Listitem<K, V> node;


	public void put(K key, V value) {


		Listitem<K, V> newnode = new Listitem<K, V> (key, value);
		newnode.next = null;
		Listitem <K,V> current = node;  // current is the pointer to the current listitem
		if (node==null){ 			// case for beginning a new OrderedListMap
			this.node = newnode;
		}
		else{
			if (newnode.key.compareTo(current.key)<0){	// case when newnode's key is smaller than the first Listitem of the existing node
				newnode.next = node;
				node = newnode;
			}
			else if(newnode.key.compareTo(current.key)==0){ // case when newnode's key is equal to that of the first Listitem of the node 
				current.Value = value;
			}

			else{
				while(current.next != null){
					if(newnode.key.compareTo(current.next.key)==0){ // case when newnode's key is equal to that of the subsequent Listitems (next to current) of the node 
						current.next.Value = value;
						return;
					}else if (newnode.key.compareTo(current.next.key)<0){ // case when the newnode has to be inserted
						newnode.next = current.next;
						current.next = newnode;
						return;
					}

					else{ //if (newnode.key.compareTo(current.next.key)>0)
						current = current.next;
					}

				}
				current.next = newnode; // case when newnode will go at the end of the existing list
			}
		}


	}

	public V get(K key) {
		Listitem <K,V> current = node;
		while(current != null){
			if(key.compareTo(current.key)<0){ // for comparison with the first node
				throw new NoSuchElementException(); // terminate the method
			}else if(current.key.compareTo(key)==0){
				return current.Value;
			}else{
				current = current.next;
			}
		}				// if while loop terminates w/o finding any similar key
		throw new NoSuchElementException();

	}

	public boolean contains(K key) {
		Listitem <K,V> current = node;
		while(current != null){
			if(key.compareTo(current.key)<0){
				return false; // terminate the method if the key of first Listitem is smaller than that of newnode 
			}
			else if(key.compareTo(current.key)==0){
				return true;
			}
			else{

				if(((key.compareTo(current.key)>0)&&(key.compareTo(current.next.key)<0))){ // stop if further keys are greater (in order)
					return false;
				}
				else{
					current = current.next;
				}
			}
		}
		return false;
	}

	public boolean remove(K key) {
		boolean ans = false;
		Listitem<K,V> pointer = node; // same as current
		if (this.contains(key)){ // start loop only if this list contains the key
			ans = true;
			if(key.compareTo(pointer.key)==0){
				node = node.next;/// if the first node is to be removed
			}
			else{
				while(pointer.next != null){
					if(key.compareTo(pointer.next.key)==0){
						pointer.next = pointer.next.next;
					}
					else{
						pointer = pointer.next;
					}
				}
			}
		}

		// TODO 
		return ans;
	}

	public String toString() {
		if(node == null){
			return "";
		}else{
			return node.toString(); // toString is defined in ListItem class
		}
	}
}

