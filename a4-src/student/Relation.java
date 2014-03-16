package student;

public class Relation implements Condition{
	
	public Expression left;
	public RelOperator rel;
	public Expression right;

	
	/**
	 * A Relation is only ever going to be a comparison of two expr across a 
	 * rel operator or it can be a Condition enclosed in brackets. This class 
	 * keeps track of both types.
	 * 
	 */
	public Relation(Expression left, RelOperator rel, Expression right){
		this.left = left;
		this.rel = rel;
		this.right = right;
	}
	
	public Relation(){
		
	}
	
	public void setLeft(Expression l){
		left = l;
	}
	
	public void setRight(Expression r){
		right = r;
	}
	
	public void setRel(RelOperator rel){
		this.rel = rel;
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
