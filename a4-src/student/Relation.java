package student;

public class Relation implements Condition{
	
	final BinaryCondition condition;
	final Expression left;
	final BinaryOp rel;
	final Expression right;
	final boolean isCondition;
	
	/**
	 * A Relation can either be a comparison of two expr across a rel operator
	 * or it can be a Condition enclosed in brackets. This class keeps track of
	 * both types.
	 * 
	 */
	public Relation(Expression left, BinaryOp rel, Expression right){
		isCondition = false;
		this.left = left;
		this.rel = rel;
		this.right = right;
		condition = null;
	}
	
	/*
	 * This Constructor should ONLY be called if the condition has an AND
	 * or an OR operator within brackets. 
	 */
	public Relation(BinaryCondition cond){
		isCondition = true;
		condition = cond;
		this.left = null;
		this.rel = null;
		this.right = null;
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
