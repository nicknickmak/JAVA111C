package LABF;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Stack;

public class BinarySearchTreeWithDups<T extends Comparable<? super T>> extends BinarySearchTree<T>
		implements SearchTreeInterface<T>, java.io.Serializable {

	public BinarySearchTreeWithDups() {
		super();
	}

	public BinarySearchTreeWithDups(T rootEntry) {
		super(rootEntry);
		setRootNode(new BinaryNode<T>(rootEntry));
	}

	@Override
	public T add(T newEntry) {
		T result = null;
		if (isEmpty())
			setRootNode(new BinaryNode<T>(newEntry));
		else
			result = addEntryHelperIterative(newEntry);
		return result;
	}

	// ??? IMPLEMENT THIS METHOD
	private T addEntryHelperIterative(T newEntry) {

		BinaryNodeInterface<T> currentNode = getRootNode();
		assert currentNode != null;
		T result = null;
		boolean found = false;

		while (!found) {
			T currentEntry = currentNode.getData();
			int comparison = newEntry.compareTo(currentEntry);

			if (comparison == 0) { // newEntry matches currentEntry;
				if (currentNode.hasRightChild())
					currentNode = currentNode.getRightChild();
				else {
					found = true;
					currentNode.setRightChild(new BinaryNode<T>(newEntry));
				}
				result = currentEntry;
			} else if (comparison < 0) {
				if (currentNode.hasLeftChild())
					currentNode = currentNode.getLeftChild();
				else {
					found = true;
					currentNode.setLeftChild(new BinaryNode<T>(newEntry));
				} // end if
			} else {
				assert comparison > 0;

				if (currentNode.hasRightChild())
					currentNode = currentNode.getRightChild();
				else {
					found = true;
					currentNode.setRightChild(new BinaryNode<T>(newEntry));
				}
			}
		}

		return result;
	}

	
	// ??? IMPLEMENT THIS METHOD
	public ArrayList<T> getAllEntriesIterative(T searchVal) {
		// this initial code is meant as a suggestion to get your started- feel
		// free to use it or delete it!
		BinaryNodeInterface<T> currentNode = getRootNode();
		ArrayList<T> entryList = new ArrayList<T>();

		boolean found = false;
		
		while (!found && (currentNode != null)) {
			T currentEntry = currentNode.getData();

			if (searchVal.equals(currentEntry)) {
				entryList.add(currentEntry);
				found = true;
			} else if (searchVal.compareTo(currentEntry) < 0)
				currentNode = currentNode.getLeftChild();
			else
				currentNode = currentNode.getRightChild();
		}
		
		return entryList;
	}

	// ??? IMPLEMENT THIS METHOD
	public ArrayList<T> getAllEntriesRecursive(T searchVal) {
		// this initial code is meant as a suggestion to get your started- feel
		// free to use it or delete it!
		BinaryNodeInterface<T> rootNode = getRootNode();
		ArrayList<T> entryList = new ArrayList<T>();
		getAllEntriesHelper(searchVal, rootNode, entryList);
		
		return entryList;
	}

	private void getAllEntriesHelper(T searchVal, BinaryNodeInterface<T> rootNode, ArrayList<T> entryList) {
		if (rootNode != null) {
			T currentData = rootNode.getData();
			if (searchVal.equals(currentData)) {
				entryList.add(currentData);
				getAllEntriesHelper(searchVal, rootNode.getRightChild(), entryList);
			} else if (searchVal.compareTo(currentData) > 0) {
				getAllEntriesHelper(searchVal, rootNode.getLeftChild(), entryList);
			} else {
				getAllEntriesHelper(searchVal, rootNode.getRightChild(), entryList);
			}
		} 
			
	}

	// ??? IMPLEMENT THIS METHOD
	public ArrayList<T> getAllEntriesLessThanIterative(T searchVal) {
		// this initial code is meant as a suggestion to get your started- feel
		// free to use it or delete it!
		ArrayList<T> entryList = new ArrayList<T>();

		// Hint: consider using a stack to mimic recursion!
		// Stack<BinaryNodeInterface<T>> nodeStack = new
		// Stack<BinaryNodeInterface<T>>();
		// nodeStack.push(getRootNode());

		// while(!nodeStack.isEmpty()) {
		// }
		
		return entryList;
	}

	// ??? IMPLEMENT THIS METHOD
	public ArrayList<T> getAllEntriesLessThanRecursive(T searchVal) {
		// this initial code is meant as a suggestion to get your started- feel
		// free to use it or delete it!
		BinaryNodeInterface<T> rootNode = getRootNode();
		ArrayList<T> entryList = new ArrayList<T>();
		// getAllEntriesLessThanHelper(searchVal, rootNode, entryList);
		
		return entryList;
	}



}