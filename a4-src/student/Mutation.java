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
	public int chosenNodeID;

	/**
	 * Constructor - will produce a new Mutation object.
	 * 
	 * @param a
	 *            Program object.
	 */
	public Mutation(Program program) {
		originalProgram = program;
		mutatedProgram = originalProgram; 
		//it starts off as an exact copy
		
		//TODO
		//how to make it so that it's not an alias?
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
	 * Changes an attribute of the critter
	 * 
	 * @return a Program
	 */
	private Program attributeMutation() {
		// 3 kinds of memory entries
		// 1) length of critter's memory (at least 8)
		// 2) offense (at least 1)
		// 3) defense (at least 1)
		// This mutation will either increase/decrease (with equal probability)
		// the values of these entries

		// ideas: use a HashMap to keep track of the values of the memory
		// can add and remove keys like we add and remove memory
		// but where do we put this memory? We have no Critter Class
		
		if (rand.nextInt(2) == 0){ //this will decrease attributes
			int i = rand.nextInt(3);
			if (i == 0){
				//if (Mem.size > 8) { //needs to be at least 8
				
				//Mem.remove(mem.size()-1) 
				//this will remove the last element that was put into the memory
				
			}
			else if (i == 1) { //decreasing defense
				//if (Mem.get(1) > 1) { //needs to be greater than 1
				//Mem.put(1, Mem.get(1) - 1); //we take off 1 from previous def value
			}
			else if (i == 2) { //decreasing offense
				//if (Mem.get(2) > 1) { //needs to be greater than 1
				//Mem.put(2, Mem.get(2) - 1); //we take off 1 from old offense value
			}
			
		} else { //nextInt was 1 this will increase attributes
			//Mem.put(int index, V value) 
			//index = size() + 1 because we are adding a new memory location
			//that is the type of the memory value?
			int i = rand.nextInt(3);
			if (i == 0) { //increasing memory
				//Mem.put(size(), V value);
				
			}
			else if (i == 1) { //increasing defense
				//Mem.put(1, Mem.get(1) + 1);
			}
			else if (i == 2) { //increasing offense
				//Mem.put(2, Mem.get(2) + 1);
			}
		}
		
		
		// after the mutation there is another 1/4 chance that another
		// mutation will happen
		return makeMutation();
	}

	
	
	/**
	 * Changes the rule set of the Program given in the constructor.
	 * 
	 * @return a Program
	 */
	private Program ruleMutation() {
		mutatedProgram = originalProgram;
		
		//total number of notes to choose from:
		int programSize = mutatedProgram.size(); 
		
		//we choose a random number that represents one Node
		chosenNodeID = rand.nextInt(programSize);
		
		originalProgram.mutate();
		
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
		
		return mutatedProgram;
		
	}
	
}
