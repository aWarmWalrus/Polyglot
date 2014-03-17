package student;

public class Relation implements Condition{
	
	public Expression left;
	public RelOperator rel;
	public Expression right;
	public Condition condition;
	
	public Relation(){
		
	}
	
	public void setCondition(Condition cond){
		condition = cond;
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
