package student;

/**
 * A representation of a binary Boolean condition: 'and' or 'or'
 *
 */
public class BinaryCondition implements Condition {
	

	Condition left;
	BinaryConditionOperator op;
	Condition right;

	public boolean isTrue = true;

	
	/**
	 * Create an AST representation of l op r.
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
	
	public void setLeft(Condition cond){
		left = cond;
	}
	
	public void setRight(Condition cond){
		right = cond;
	}
	
	public void setOp(BinaryConditionOperator op){
		this.op = op;
	}

	
	/**
	 * Create a boolean node for conditions must be true (for mutations to occur)
	 */
	public BinaryCondition(boolean bool){
		
	}
	
	@Override
	public int size() {
		return 1;
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
