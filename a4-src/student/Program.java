package student;

import java.util.ArrayList;

/**
 * A representation of a critter program.
 *
 */
public class Program extends AbstractNode {

	ArrayList<Rule> rules;
	
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

	
	//when we call mutate, then we will return a mutated Program
	//this works because Program extends Node
	@Override
	public Node mutate() {
		
		// TODO Auto-generated method stub
		return new Program();
	}

	@Override
	public void prettyPrint(StringBuffer sb) {
		// TODO Auto-generated method stub

	}

}
