package student;

import java.util.ArrayList;
import java.util.Random;

import mutations.Mutation;

//Purpose: to more easily share code that implements mutation for Fault Injection

public class AbstractNode implements Node {

	// the children of this particular Node are stored here
	// ArrayList<?> children;

	// random object for mutations
	// Random rand;

	// for mutation: the size accumulator
	// int mutationNum = 0;

	// this should never actually be called
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	// Mutation Algorithm:
	// We call size() again and when the size accumulator reaches the
	// randomNumber, we
	// then mutate at that position
	@Override
	public Node mutate() {
		Mutation mutt = new Mutation(this);
		return mutt.makeMutation();
	}

	/**
	 * Removes the node.
	 */
	public Node remove() {
		return null;
	}

	/**
	 * Switches the order of the children of the Node.
	 * 
	 * @return
	 */
	public Node swapOrder() {
		return null;
	}

	/**
	 * Replaces the Node and its children.
	 */
	public Node cloneSubtree() {
		return null;
	}

	/**
	 * Only the Node is replaced.
	 */
	public Node randomReplace() {
		return null;
	}

	/**
	 * Inserts a new parent for the Node.
	 */
	public Node newParent() {
		return null;
	}

	/**
	 * Adds a child for the Node.
	 */
	public Node cloneKid() {
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
