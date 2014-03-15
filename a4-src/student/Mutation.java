package student;

import java.util.Random;

/**
 * Handles mutations for Fault Injection.
 * 
 * @methods will return a Program object.
 * 
 */
public class Mutation {

	// random number generator
	private Random rand;
	private Program originalProgram; // should I make this final?
	private Program mutatedProgram;

	
	/**
	 * Constructor - will produce a new Mutation object.
	 * @param a Program object.
	 */
	public Mutation(Program program) {
		originalProgram = program;
	}

	/**
	 * This has at least 1/4 probability of mutating the program that was given
	 * in the constructor.
	 * 
	 * @return A new Program.
	 */
	public Program makeMutation() {
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
	private Program isMutation() {
		// helper methods will have 1/4 chance of calling another mutation
		if (rand.nextInt(2) == 0) {
			return attributeMutation();
		} else { // if the number == 1
			return ruleMutation();
		}

	}

	
	/**
	 * Changes the rule set of the Program given in the constructor.
	 * 
	 * @return a Program
	 */
	private Program ruleMutation() {
//		Rule newrule = Rule()
//		originalProgram.rules.add(Rule newrule);
		
		
		// after the mutation there is another 1/4 chance that another
		// mutation will happen
		return makeMutation();
	}

	
	/**
	 * Changes an attribute of the critter
	 * @return a Program
	 */
	private Program attributeMutation() {

		// after the mutation there is another 1/4 chance that another
		// mutation will happen
		return makeMutation();
	}

	/**
	 * Does not mutate the Program
	 * 
	 * @return a copy of the same Program that was given in the constructor
	 */
	private Program noMutation() {
		mutatedProgram = originalProgram;
		return mutatedProgram;
	}
}
