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
	public Node remove() {
		return null;
		//TODO
	}
	
	@Override
	public Node swapOrder() {
		return invalidMutationHandler(); 
		// can't swap Actions - there is at most one Action
	}
	
	@Override
	public Node cloneSubtree() {
		return invalidMutationHandler() ;
		// we don't have Subtrees after Actions
	}
	
	@Override
	public Node randomReplace() {
		return null;
		//TODO
	}
	
	@Override
	public Node newParent() {
		return invalidMutationHandler(); 
		// can't insert a new Rule between a Rule and an Action
	}
	
	@Override
	public Node cloneKid() {
		return invalidMutationHandler(); 
		// Actions don't have kids
	}
	
	/**
	 * if the called mutation is not valid for that type of Node
	 * then this is called
	 * @return a mutated Node
	 */
	private Node invalidMutationHandler() {
		int randMutation = rand.nextInt(2);
		if (randMutation == 0) {
			return remove();
		} else {
			return randomReplace();
		}
	}
	

	

}
