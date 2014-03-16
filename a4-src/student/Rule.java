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
    private Action action;
    private ActionSwitch actionSwitch;
    
    /**
     * Constructor for a Rule Object
     */
    public Rule() {
    	//requires no arguments
    }
    
    
//    /**
//     * Constructor for a Rule Object
//     * @param cond
//     */
//    public Rule(Condition condition, ArrayList<Update> updateList) {
//    	this.condition = condition; //another way to set the Condition
//    	updates = updateList;
//    }
    
    public void setCondition(Condition cond){
    	condition = cond;
    }

    public void setAction(Action action){
    	this.action = action;
    }
    
    public void addUpdates(ArrayList<Update> updates){
    	this.updates = updates;
    }
    
    
    /**
     * Will be called after all the set methods are called.
     * There are not that many cases when this will return false.
     * 		1) When there are no updates and no actions.
     * 		2) When there is 
     * @return
     */
    public boolean checkSemantic(){
    	if(action == Action.NONE && 
    			(updates == null || updates.isEmpty())) return false;
    	else return true;
    }
    
    
	@Override
	public int size() {
		//this iterates through the ArrayList of Update objects, calling size on each.
		int accumulator = 0;
		//number of Update Nodes
		for (int i = 0; i < updates.size(); i++) {
			accumulator += updates.get(i).size();
//			mutationNum ++;
		}
		//number of Action Nodes
//		accumulator += actionSwitch.size();
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
