package mutations;

import java.util.Random;

import student.AbstractNode;
import student.Node;
import student.Program;

/**
 * Handles mutations for Fault Injection.
 * 
 * @methods will return a Program object.
 * 
 */
public class Mutation {

	// random number generator
	protected Random rand;
	protected Critter critter;
	protected Program nodeToMutate; // should I make this final?
	public int chosenNodeID;

	/**
	 * Constructor - will produce a new Mutation object.
	 * @param Critter object.
	 * @param Node object
	 */
	public Mutation(Critter crit, Program node) {
		critter = crit; // needs this for purposes of editing memory
		nodeToMutate = node;

	}
	
	public Mutation(Program node) {
		nodeToMutate = node;
	}

	/**
	 * This has at least 1/4 probability of mutating the program that was given
	 * in the constructor.
	 * @return A new Program.
	 */
	public Node makeMutation() {
		// there is 3/4 chance that we will just return the original program
		if (rand.nextInt(4) != 0) {
			return noMutation();
		} else { // if the number == 0
			return isMutation();
		}
	}

	
	/**
	 * Mutates the Program given in the constructor. Calls isMutation() for a
	 * possible follow-up mutation.
	 * 
	 * @return a Mutated Program
	 */
	private Node isMutation() {
		// helper methods will have 1/4 chance of calling another mutation
		if (rand.nextInt(2) == 0) {
			
			AttributeMutation attr = new AttributeMutation(critter, nodeToMutate);
			return attr.attributeMutation();

		} else { // if the number == 1
			
			RuleSetMutation rule = new RuleSetMutation(critter, nodeToMutate);
			return rule.ruleMutation();
		}

	}
	

	/**
	 * Does not mutate the Program
	 * 
	 * @return a copy of the same Program that was given in the constructor
	 */
	private Node noMutation() {
		return nodeToMutate;

	}

}
