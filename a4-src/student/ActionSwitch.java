package student;

public class ActionSwitch extends AbstractNode {
	Action action;

	public ActionSwitch(Action action) {
		this.action = action;
	}

	// only one action per Rule
	// public int size() {
	//
	// return 1;
	// }

	public void takingAction() {
		switch (action) {
		case WAIT:
			// executes code corresponding to wait
		case FORWARD:

		case BACKWARD:

		case LEFT:

		case RIGHT:

		case EAT:

		case ATTACK:

		case GROW:

		case BUD:

		case MATE:

		case TAG:
			// executes code corresponding to tag [ expr ]
		case SERVE:

		case NONE:

		}
		
	}
	@Override
	public Node remove(StringBuffer sb) {
		return null;
		//TODO
	}
	
	@Override
	public Node swapOrder(StringBuffer sb) {
		return invalidMutationHandler(sb); 
		// can't swap Actions - there is at most one Action
	}
	
	@Override
	public Node cloneSubtree(StringBuffer sb) {
		return invalidMutationHandler(sb) ;
		// we don't have Subtrees after Actions
	}
	
	@Override
	public Node randomReplace(StringBuffer sb) {
		return null;
		//TODO
	}
	
	@Override
	public Node newParent(StringBuffer sb) {
		return invalidMutationHandler(sb); 
		// can't insert a new Rule between a Rule and an Action
	}
	
	@Override
	public Node cloneKid(StringBuffer sb) {
		return invalidMutationHandler(sb); 
		// Actions don't have kids
	}
	
	/**
	 * if the called mutation is not valid for that type of Node
	 * then this is called
	 * @return a mutated Node
	 */
	private Node invalidMutationHandler(StringBuffer sb) {
		int randMutation = rand.nextInt(2);
		if (randMutation == 0) {
			return remove(sb);
		} else {
			return randomReplace(sb);
		}
	}
	

	

}
