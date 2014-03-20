package student;

import mutations.RuleSetMutation;

public class Update extends AbstractNode{
	
	SensorMem mem;
	Expression assigned;
	
	public Update(SensorMem mem, Expression assigned) {
		this.mem = mem;
		this.assigned = assigned;
	}

	public Update(){
		mem = new SensorMem();
		mem.setOption(Token.MEM);
	}
	
	public SensorMem getMem(){
		return mem;
	}
	
	public Expression getAssignment(){
		return assigned;
	}
	
	public void setMemIndex(Expression expr){
		mem.setExpression(expr);
	}
	
	public void setAssignment(Expression expr){
		assigned = expr;
	}
	
	@Override
	public int size() {
		return mem.size() + assigned.size() + 1;
	}

	@Override
	public Node mutate(StringBuffer sb) {
		int thatNode = rand.nextInt(this.size());
		if (thatNode == 0) {
			RuleSetMutation rm = new RuleSetMutation(this);
			
			return rm.ruleMutation(sb);
		} else if (thatNode >= 1 && thatNode < assigned.size() + 1) {
			assigned.mutate(sb);
			//weight of probability depends on size of expression
		} else {
			//shouldn't reach this point
		}
		return this;
	}

	
	@Override
	public Node remove(StringBuffer sb) {
		return null;
		//TODO
	} 
	
	@Override
	public Node swapOrder(StringBuffer sb) {
		return null;
		//TODO
	}
	
	@Override
	public Node cloneSubtree(StringBuffer sb) {
		return null ;
		// TODO
	}
	
	@Override
	public Node randomReplace(StringBuffer sb) {
		return null;
		// TODO
	}
	
	@Override
	public Node newParent(StringBuffer sb) {
		return invalidMutationHandler(sb); 
		// cannot insert a new Rule between a Rule and an Update
	}
	
	@Override
	public Node cloneKid(StringBuffer sb) {
		return invalidMutationHandler(sb); 
		// Update does not have a variable amount of Children
	}
	
	/**
	 * if the called mutation is not valid for that type of Node
	 * then this is called
	 * @return a mutated Node
	 */
	private Node invalidMutationHandler(StringBuffer sb) {
		int randMutation = rand.nextInt(5);
		if (randMutation == 0)
			return remove(sb);
		else if (randMutation == 1) 
			return swapOrder(sb);
		else if (randMutation == 2) 
			return cloneSubtree(sb);
		else 
			return randomReplace(sb);
	}

	@Override
	public Node deepCopy() {
		Update newUpdate = new Update((SensorMem) mem.deepCopy(), assigned.deepCopy());
		return newUpdate;
	}
	
	@Override
	public void prettyPrint(StringBuffer sb) {
		sb.append("mem[");
		mem.index.prettyPrint(sb);
		sb.append("] := ");
		assigned.prettyPrint(sb);
	}

}
