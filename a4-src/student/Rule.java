package student;

import java.util.ArrayList;

import java.util.ArrayList;

/**
 * A representation of a critter rule.
 */
public class Rule implements Node {
	
    private Condition condition;
    // how to represent the command?
    private ArrayList<Update> updates;
    private Action action;
    
    public ArrayList<Token> tokens;
    
    public Rule(ArrayList<Token> tokens) {
    	this.tokens = tokens;
    }

	@Override
	public int size() {
		// TODO Auto-generated method stub
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
