package student;

public class Update extends AbstractNode{
	
	Expression memIndex;
	Expression assigned;
	
	public void setMemIndex(Expression expr){
		memIndex = expr;
	}
	
	public void setAssignment(Expression expr){
		assigned = expr;
	}
	
	@Override
	public int size() {
		//end of the tree; update will only assign to a mem[] location
//		mutationNum++;
		return 1;
	}

	@Override
	public Node mutate() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Node remove() {
		return null;
		//TODO
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
		return null;
		// TODO
	}
	
	@Override
	public Node newParent() {
		return invalidMutationHandler(); 
		// cannot insert a new Rule between a Rule and an Update
	}
	
	@Override
	public Node cloneKid() {
		return invalidMutationHandler(); 
		// Update does not have a variable amount of Children
	}
	
	/**
	 * if the called mutation is not valid for that type of Node
	 * then this is called
	 * @return a mutated Node
	 */
	private Node invalidMutationHandler() {
		int randMutation = rand.nextInt(5);
		if (randMutation == 0)
			return remove();
		else if (randMutation == 1) 
			return swapOrder();
		else if (randMutation == 2) 
			return cloneSubtree();
		else 
			return randomReplace();
	}

	@Override
	public void prettyPrint(StringBuffer sb) {
		sb.append("mem[");
		memIndex.prettyPrint(sb);
		sb.append("] := ");
		assigned.prettyPrint(sb);
	}

}
