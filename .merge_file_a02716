package student;

import java.util.ArrayList;

import java.util.ArrayList;

/**
 * A representation of a critter rule.
 */
public class Rule extends AbstractNode {
	
    protected Condition condition;
    // how to represent the command?
    private ArrayList<Update> updates;
    private ActionSwitch actionSwitch;
    
    public ArrayList<Token> tokens;
    
    public Rule(ArrayList<Token> tokens) {
    	this.tokens = tokens;
    }

	@Override
	public int size() {
		//this iterates through the ArrayList of Update objects, calling size on each.
		int accumulator = 0;
		//number of Update Nodes
		for (int i = 0; i < updates.size(); i++) {
			accumulator += updates.get(i).size();
		}
		//number of Action Nodes
		accumulator += actionSwitch.size();
		return accumulator;
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
