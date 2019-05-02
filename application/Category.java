package application;
/**
 * Category Class
 * 
 * @author erica, ben, aj, callan
 *
 */

//
public class Category{
	private String name;
	
	//inner class node
	private class Node{
		private User user;
		private Node next;
		
		private Node(User user) {
			this.user = user;
			next = null;
		}
		
		private User getUser() {
			return user;
		}
	}
	
	private class linkedList{
		private Node head;
		private int size;
		
		private linkedList() {
			head = null;
			size = 0;
		}
		
		private void insert(User user) {
			if(head == null) {
				head = new Node(user);
				size++;
			}
			else {
				Node temp = head;
				while(temp.next != null) {
					temp = temp.next;
				}
				temp.next = new Node (user);
				size++;
			}
		}
		
		private boolean remove(User user) {
			if(!contains(user)) {
				return false;
			}
			else {
				if(head.user.getEmail().equals(user.getEmail())) {
					head = head.next;
					size--;
					return true;
				}
				else {
					Node temp = head;
					while(!(temp.next.user.getEmail().equals(user.getEmail()))) {
						temp = temp.next;
					}
					temp.next = temp.next.next;
					size--;
					return true;
				}
			}
		}
		
		private boolean contains(User user) {
			Node temp = head;
			while(temp != null) {
				if(temp.user.getEmail().equals(user.getEmail())) {
					return true;
				}
				temp = temp.next;
			}
			return false;
		}
		
		private String get(User user) {
			if(!contains(user)) {
				return null;
			}
			Node temp = head;
			while(!(temp.user.getEmail().equals(user.getEmail()))) {
				temp = temp.next;
			}
			return temp.user.getEmail();
		}
		
		private User getHead() {
			return head.user;
		}
		
	}
	
	private int capacity; //table size
	private double loadFactorThreshold; //loadfactor value that starts resizing
	int numKeys; //number of keys in HashTable
	private linkedList[]table; //the HashTable of type linkedList
	
	public Category(String name) {
		table = new linkedList[11]; //creates table with initial size of 11
		capacity = 11;
		loadFactorThreshold = 0.75; //sets LoadFactor threshold to 0.75
		this.name = name;
	}
	
	public Category(int initialCapacity, double loadFactorThreshold, String name) {
		this.name = name;
		capacity = initialCapacity;
		table = new linkedList[initialCapacity];
		this.loadFactorThreshold = loadFactorThreshold;

	}
	
	public void insert(User user) {
		
		if(table[Math.abs(user.hashCode())%capacity] == null) { //if hashIndex of Hashtable is null, create new linkedList at index
			table[Math.abs(user.hashCode())%capacity] = new linkedList();
		}
		table[Math.abs(user.hashCode())%capacity].insert(user); //call linkedList insert method
		numKeys++; //increment numKeys
		
		if(getLoadFactor() >= loadFactorThreshold) { //if loadFactor is greater than or equal to the loadFactorThreshold
			linkedList[]temp = new linkedList[(2*capacity) + 1]; //create temp hashTable with 2 time the size of the original hashTable + 1
			int capTemp = 2*capacity + 1;
			
			for(int count = 0; count < table.length; count++) { //loops through original hashTable
				if(table[count] != null) {  //rehasheshes the non-null values from the original hashTable and places them in new table
					temp[Math.abs(table[count].getHead().hashCode())%capTemp] = table[count];
				}
			}
			table = temp; //sets HashTable to temp
			capacity = capTemp; //reestablishes the capacity 
		} 
	}
	
	public boolean remove(User user) {
		if(table[Math.abs(user.hashCode())%capacity] != null && table[Math.abs(user.hashCode())%capacity].contains(user)) {
			//if hashIndex of key in hashTable isn't empty and linkedList bucket at index contains the key, call linkedList remove method
			numKeys--; //decrease numKeys
			return table[Math.abs(user.hashCode())%capacity].remove(user); 
		}
		return false; //returns false if key doesn't exist
		
	}
	
	public User get(String username) {
		for(int count = 0; count < table.length; count++) {
			if(table[count].size > 0) {
				Node temp = table[count].head;
				while(temp != null) {
					if(temp.user.getEmail().equals(username)) {
						return temp.user;
					}
					temp = temp.next;
				}
			}
		}
		return null;
	}
	
	//@return numKeys
	public int numKeys() {
		return numKeys;
	}

	//@return loadFactorThreshold
	public double getLoadFactorThreshold() {
		return loadFactorThreshold;
	}

	//@return loadFactor (numKeys/capacity)
	public double getLoadFactor() {
		return ((double)numKeys/capacity);
	}

	//@return capacity
	public int getCapacity() {
		return capacity;
	}
	
	//returns the hashtable
	public linkedList[] getTable() {
		return table;
	}
}
