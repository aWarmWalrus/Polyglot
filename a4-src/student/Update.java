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
	public Node mutate() {
		int thatNode = rand.nextInt(this.size());
		if (thatNode == 0) {
			RuleSetMutation rm = new RuleSetMutation(this);
			
			return rm.ruleMutation();
		} else if (thatNode >= 1 && thatNode < assigned.size() + 1) {
			assigned.mutate();
			//weight of probability depends on size of expression
		} else {
			stackOfNodes.add(this);
			// we add the Update to the LinkedList to keep track of it

			thatNode = thatNode - 2; //we discount the 

			for (int i = 0; i < updates.size(); i++) {
				if (updates.get(i).size() < thatNode) {
					thatNode = thatNode - updates.get(i).size();
					// narrowing down our probability
				} else { // if rules.get(i).size(0 >= thatNode
					updates.get(i).mutate();
					return this;
					// we found the Rule that gets that mutation
				}
			}

		}
		return this;
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
