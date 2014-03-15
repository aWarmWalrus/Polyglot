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
<<<<<<< HEAD
    private Action action;

    public void setCondition(Condition cond){
    	condition = cond;
    }
=======
    private ActionSwitch actionSwitch;
>>>>>>> 0e40e5851a491322c7032185942ed7da97630798
    
    public void setAction(Action action){
    	this.action = action;
    }
    
    public void addUpdates(Update update){
    	updates.add(update);
    }
    /**
     * Will be called after all the set methods are called.
     * There are not that many cases when this will return false.
     * 		1) When there are no updates and no actions.
     * 		2) When there is 
     * @return
     */
    public boolean checkSemantic(){
    	if(action == Action.NONE && updates.isEmpty()) return false;
    	else return true;
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
