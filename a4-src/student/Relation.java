package student;

public class Relation extends AbstractNode implements Condition{
	
	public Expression left;
	public RelOperator rel;
	public Expression right;
	public Condition condition;
	
	public Relation(){
		
	}
	
	public void setCondition(Condition cond) {
		condition = cond;
	}
	
	public void setLeft(Expression l) {
		left = l;
	}
	
	public void setRight(Expression r) {
		right = r;
	}
	
	public void setRel(RelOperator rel) {
		this.rel = rel;
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 1;
	}

//	@Override
//	public Node mutate() {
//		// Auto-generated method stub
//		return null;
//	}

	@Override
	public Node remove() {
		//TODO 
		return null;
	} 
	
	@Override
	public Node swapOrder() {
		return null;
		//TODO
	}
	
	@Override
	public Node cloneSubtree() {
		return null ;
		// TODO
	}
	
	@Override
	public Node randomReplace() {
		return invalidMutationHandler(); 
		// Relation cannot be mutated while leaving its children the same
	}
	
	@Override
	public Node newParent() {
		return null;
		// TODO
	}
	
	@Override
	public Node cloneKid() {
		return invalidMutationHandler(); 
		// Relation does not have a variable amount of Children
	}
	
	/**
	 * if the called mutation is not valid for that type of Node
	 * then this is called
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
