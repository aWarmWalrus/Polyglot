package student;

/**
 * A representation of a binary Boolean condition: 'and' or 'or'
 *
 */
public class BinaryCondition extends Condition {
	
	/**
	 * Create an AST representation of l op r.
	 * @param l
	 * @param op
	 * @param r
	 */
	public BinaryCondition(Condition l, BinaryConditionOperator op, Condition r) {
		//TODO
		super(null);
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

	@Override
	public boolean evaluate() {
		// TODO Auto-generated method stub
		return false;
	}

}
