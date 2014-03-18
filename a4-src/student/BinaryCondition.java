package student;

/**
 * A representation of a binary Boolean condition: 'and' or 'or'
 * 
 */
public class BinaryCondition extends AbstractNode implements Condition {

	Condition left;
	BinaryConditionOperator op;
	Condition right;

	public boolean isTrue = true;

	/**
	 * Create an AST representation of l op r.
	 * 
	 * @param l
	 * @param op
	 * @param r
	 */
	public BinaryCondition(Condition l, BinaryConditionOperator op, Condition r) {
		left = l;
		this.op = op;
		right = r;
	}

	public BinaryCondition() {

	}

	public void setLeft(Condition cond) {
		left = cond;
	}

	public void setRight(Condition cond) {
		right = cond;
	}

	public void setOp(BinaryConditionOperator op) {
		this.op = op;
	}

	/**
	 * Create a boolean node for conditions must be true (for mutations to
	 * occur)
	 */
	public BinaryCondition(boolean bool) {

	}

	@Override
	public int size() {
		return 1;
	}

	// @Override
	// public Node mutate() {
	// Auto-generated method stub
	// return null;
	// }

	@Override
	public Node remove() {
		// TODO
		return null;
	}

	@Override
	public Node swapOrder() {
		return null;
		// TODO
	}

	@Override
	public Node cloneSubtree() {
		return null;
		// TODO
	}

	@Override
	public Node randomReplace() {
		return invalidMutationHandler();
		// BinaryCondition cannot be mutated
		// while leaving its children the same
	}

	@Override
	public Node newParent() {
		return null;
		// TODO
	}

	@Override
	public Node cloneKid() {
		return invalidMutationHandler();
		// BinaryCondition does not have a variable amount of Children
	}

	/**
	 * if the called mutation is not valid for that type of Node then this is
	 * called
	 * 
	 * @return a mutated Node
	 */
	private Node invalidMutationHandler() {
		int randMutation = rand.nextInt(4);
		if (randMutation == 0)
			return remove();
		else if (randMutation == 1) 
			return swapOrder();
		else if (randMutation == 2) 
			return cloneSubtree();
		else 
			return newParent();
		
	}

	@Override
	public void prettyPrint(StringBuffer sb) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean evaluate() {
		// TODO Auto-generated method stub
		return false;
	}

}
