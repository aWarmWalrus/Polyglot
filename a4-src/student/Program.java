package student;

import java.util.ArrayList;

import mutations.Critter;
import mutations.Mutation;

/**
 * A representation of a critter program.
 *
 */
public class Program extends AbstractNode {

	ArrayList<Rule> rules;
	public Critter critter;
	// assuming that you call the Critter constructor first,
	// and then the Critter constructor assigns program.critter
	// you should not mutate if you only initialize a Program
	
	public Program(){
		rules = new ArrayList<Rule>();
	}
	
	@Override
	public int size() {
		int accumulator = 0;
		for (int i = 0; i < rules.size(); i++) {
			accumulator += rules.get(i).size();
//			mutationNum ++;
		}
		return accumulator;
	}
	
	/**
	 * Returns the list of rules that are in this program
	 * @return
	 */
	public ArrayList<Rule> getRules(){
		return rules;
	}
	
	//when we call mutate, then we will return a mutated Program
	//this works because Program extends Node

	@Override
	public Node mutate() {
		if (critter == null) {
			critter = new Critter(this); 
			//making a new Critter 
		}
		Mutation mutt = new Mutation(critter, this);
		return mutt.makeMutation();
		
	}

	@Override
	public void prettyPrint(StringBuffer sb) {
		// TODO Auto-generated method stub

	}

}
