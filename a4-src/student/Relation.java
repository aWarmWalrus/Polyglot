package student;

public class Relation extends AbstractNode implements Condition{
	
	public Expression left;
	public RelOperator rel;
	public Expression right;
	public Condition condition;
	public boolean isCondition = false;
	

	public void setCondition(Condition cond) throws SyntaxError {
		
		if(!isCondition){
			condition = cond;
			isCondition = true;
		}
		else throw new SyntaxError("something weird happened with Relations brooo");
	}
	
	public void setLeft(Expression l) throws SyntaxError {
		if(!isCondition){
			left = l;
		}
		else throw new SyntaxError("something weird happened with Relations brooo");
	}
	
	public void setRight(Expression r) throws SyntaxError {
		if(!isCondition){
			right = r;
		}
		else throw new SyntaxError("something weird happened with Relations brooo");
	}
	
	public void setRel(RelOperator rel) throws SyntaxError {
		if(!isCondition){
			this.rel = rel;
		}
		else throw new SyntaxError("something weird happened with Relations brooo");
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
		
		if(isCondition){
			sb.append("{");
			condition.prettyPrint(sb);
			sb.append("}");
		}
		else{
			left.prettyPrint(sb);
			Token temp = new Token(rel.getValue(), 0);
			sb.append(temp.toString());
			right.prettyPrint(sb);
		}
	}

	@Override
	public boolean evaluate() {
		// TODO Auto-generated method stub
		return false;
	}

}
