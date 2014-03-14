package student;

import java.util.ArrayList;

/**
 * A representation of a critter program.
 *
 */
public class Program implements Node {

	ArrayList<Rule> rules;
	
	public Program(){
		rules = new ArrayList<Rule>();
	}
	
	@Override
	public int size() {
		return 0;
	}

	@Override
	public Node mutate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void prettyPrint(StringBuffer sb) {
		// TODO Auto-generated method stub

	}

}
