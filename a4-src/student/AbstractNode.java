package student;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import mutations.Mutation;

//Purpose: to more easily share code that implements mutation for Fault Injection

public class AbstractNode implements Node {

	protected LinkedList<Node> stackOfNodes = new LinkedList<Node>();
	public Random rand = new Random();
	
	// for mutation: the size accumulator
	// int mutationNum = 0;

	
	// this should never actually be called
	@Override
	public int size() {
		return 1;
	}
	
	
	// Mutation Algorithm????:
	// We call size() again and when the size accumulator reaches the
	// randomNumber, we
	// then mutate at that position
	
	@Override
	public Node mutate(StringBuffer sb) {
//		stackOfNodes.add(this); //add the Node to the Linked List
//		Mutation mutt = new Mutation(this);
//		return mutt.makeMutation();
		return null;
	}
	

	/**
	 * Removes the node.
	 * @return Returns the Parent Node.
	 */
	public Node remove(StringBuffer sb) {
		return null;
	}

	/**
	 * Switches the order of the children of the Node.
	 * 
	 * @return
	 */
	public Node swapOrder(StringBuffer sb) {
		return null;
	}

	/**
	 * Replaces the Node and its children.
	 */
	public Node cloneSubtree(StringBuffer sb) {
		return null;
	}

	/**
	 * Only the Node is replaced.
	 */
	public Node randomReplace(StringBuffer sb) {
		return null;
	}

	/**
	 * Inserts a new parent for the Node.
	 */
	public Node newParent(StringBuffer sb) {
		return null;
	}

	/**
	 * Adds a child for the Node.
	 */
	public Node cloneKid(StringBuffer sb) {
		return null;
	}

	
	/**
	 * Returns a separate and identical copy of the Node
	 * @return a new copy of the Node
	 * 
	 */
	public Node deepCopy() {
		return null;
	}
	
	// public Node mutateHelper() {
	// int randNum = 0;
	// randNum = rand.nextInt(size());
	//
	// //next we walk down the tree
	// return null;
	// }

	@Override
	public void prettyPrint(StringBuffer sb) {
		// TODO Auto-generated method stub

	}

}
