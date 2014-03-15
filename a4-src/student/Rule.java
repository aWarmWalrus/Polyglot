package student;

import java.util.ArrayList;

import java.util.ArrayList;

/**
 * A representation of a critter rule.
 */
public class Rule extends AbstractNode {
	
    private Condition condition;
    // how to represent the command?
    private ArrayList<Update> updates;
    private Action action;

    public void setCondition(Condition cond){
    	condition = cond;
    }
    
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
