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
		
		BinaryNodeInterface<T> currentNode = getRootNode();
		ArrayList<T> entryList = new ArrayList<T>();
		boolean found = false;
		
		while (currentNode != null) {
			T currentEntry = currentNode.getData();

			if (searchVal.equals(currentEntry)) {
				entryList.add(currentEntry);
				currentNode = currentNode.getRightChild();
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
			} else if (searchVal.compareTo(currentData) < 0) {
				getAllEntriesHelper(searchVal, rootNode.getLeftChild(), entryList);
			} else {
				getAllEntriesHelper(searchVal, rootNode.getRightChild(), entryList);
			}
		} 		
	}

	// ??? IMPLEMENT THIS METHOD
	public ArrayList<T> getAllEntriesLessThanIterative(T searchVal) {
		
		ArrayList<T> entryList = new ArrayList<T>();

		Stack<BinaryNodeInterface<T>> nodeStack = new Stack<BinaryNodeInterface<T>>();
		nodeStack.push(getRootNode());

		while(!nodeStack.isEmpty()) {
			BinaryNodeInterface<T> currentNode = nodeStack.pop();
			if (currentNode.getData().compareTo(searchVal) < 0) {
				entryList.add(currentNode.getData());
				if (currentNode.hasRightChild()) {
					nodeStack.push(currentNode.getRightChild());
				}
				if (currentNode.hasLeftChild()) {
					nodeStack.push(currentNode.getLeftChild());
				}
			} else {
				if (currentNode.hasLeftChild()) {
					nodeStack.push(currentNode.getLeftChild());
				}
			}
		}
		
		return entryList;
	}

	// ??? IMPLEMENT THIS METHOD
	public ArrayList<T> getAllEntriesLessThanRecursive(T searchVal) {
		BinaryNodeInterface<T> rootNode = getRootNode();
		ArrayList<T> entryList = new ArrayList<T>();
		getAllEntriesLessThanHelper(searchVal, rootNode, entryList);
		
		return entryList;
	}

	private void getAllEntriesLessThanHelper(T searchVal, BinaryNodeInterface<T> rootNode, ArrayList<T> entryList) {
		if (rootNode.getData().compareTo(searchVal) < 0) {
			if (rootNode.hasRightChild()) {
				getAllEntriesLessThanHelper(searchVal, rootNode.getRightChild(), entryList);
			}
			entryList.add(0, rootNode.getData());
			if (rootNode.hasLeftChild()) {
				getAllEntriesLessThanHelper(searchVal, rootNode.getLeftChild(), entryList);
			}
		} else {
			if (rootNode.hasLeftChild()) {
				getAllEntriesLessThanHelper(searchVal, rootNode.getLeftChild(), entryList);
			}
		}
	}


	//Extra Credit
	public int calculateLeftHeight() {
		return calcHeightHelper(getRootNode().getLeftChild());
	}
	
	public int calculateRightHeight() {
		return calcHeightHelper(getRootNode().getRightChild());
	}

	private int calcHeightHelper(BinaryNodeInterface<T> rootNode) {
		if (rootNode == null) {
			return 0;
		} else {
			int leftHeight = calcHeightHelper(rootNode.getLeftChild());
			int rightHeight = calcHeightHelper(rootNode.getLeftChild());
			
			return Math.max(leftHeight, rightHeight) + 1;
		}
	}
}