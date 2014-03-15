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
	Random rand;
	Program originalProgram;
	Program mutatedProgram;

	/**
	 * Constructor - will produce a new Mutation object.
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
		} else { //if the number == 0
			return isMutation();
		}

	}

	/**
	 * Mutates the Program given in the constructor.
	 * Calls isMutation() for a possible follow-up mutation.
	 * @return a Mutated Program
	 */
	public Program isMutation() {
		//after the mutation there is another 1/4 chance that another
		//mutation will happen
		return makeMutation();
	}

	
	/**
	 * Does not mutate the Program
	 * 
	 * @return a copy of the same Program that was given in the constructor
	 */
	public Program noMutation() {
		mutatedProgram = originalProgram;
		return mutatedProgram;
	}
}
