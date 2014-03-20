package student;

import java.util.ArrayList;

import mutations.RuleSetMutation;

public class Relation extends AbstractNode implements Condition{
	
	public Expression left;
	public RelOperator rel;
	public Expression right;
	public Condition condition;
	public boolean isCondition = false;
	
	/**
	 * Constructs a new Relation object
	 */
	public Relation() {
		//allows you to set class variables later if wanted
	}
	
	
	/**
	 * Constructs a new Relation object
	 * @param left
	 * @param rel
	 * @param right
	 */
	public Relation(Expression left, RelOperator rel, Expression right) {
		this.left = left;
		this.rel = rel;
		this.right = right;
	}
	
	/**
	 * Constructs a new Relation object
	 * @param condition
	 */
	public Relation(Condition condition) {
		this.condition = condition;
		isCondition = true;
	}
	

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
		if(left == null || rel == null) throw new SyntaxError("SYNTAX ERROR: not a complete condition");
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
		int count;
		if(isCondition){
			count = condition.size(); 
		} else {
			count = left.size() + right.size();
		}
		return count + 1; //add 1 to include the Relation in the count
	}

	@Override
	public Node mutate(StringBuffer sb) {
		int thatNode = rand.nextInt(this.size());
		if (thatNode == 0) {
			RuleSetMutation rm = new RuleSetMutation(this);
			// the RuleSetMutation class takes care of probability
			return rm.ruleMutation(sb); // calls mutation on the R
		} else if (thatNode >= 1 && thatNode < left.size() + 1){
			stackOfNodes.add(this);
			left.mutate(sb);
		} else if (thatNode >= left.size() + 1 && thatNode < size()) {
			stackOfNodes.add(this);
			right.mutate(sb);
		} else {
			System.out.println("This should not be reached");
		}
		return this;
	}

	@Override
	public Node remove(StringBuffer sb) {
		Node n = stackOfNodes.getLast(); //the parent
		if (n instanceof Rule) {
			if (rand.nextInt(1) == 0)
				((Rule) n).condition = (Condition) this.left;
			else 
				((Rule) n).condition = (Condition) this.right;
		}
		else if (n instanceof BinaryCondition) {
			int randNum = rand.nextInt(1);
			if (((BinaryCondition) n).left.equals(this)) {
				if (randNum == 0) ((BinaryCondition) n).left = (Condition) this.left;
				else ((BinaryCondition) n).left = (Condition) this.right;
			}
			else if (((BinaryCondition) n).right.equals(this)) {
				if (randNum == 0) ((BinaryCondition) n).right = (Condition) this.left;
				else ((BinaryCondition) n).right = (Condition) this.right;
			}
			else System.out.println("Error: This should never happen");
		}
		else if (n instanceof Relation) { //the Relation inherits both children
			((Relation) n).left = this.left;
			((Relation) n).right = this.right;
		}
		else { 
			System.out.println("shouldn't ever happen");
			return null; 
		}
		return this; 
	}

	
	@Override
	public Node swapOrder(StringBuffer sb) {
		if (!isCondition) {
			Expression temp = new BinaryExpression();
			temp = this.left;
			this.left = this.right;
			this.right = temp;
		}
		
		return this;
	}
	
	@Override
	public Node cloneSubtree(StringBuffer sb) {
		Program p = (Program) stackOfNodes.getFirst(); //should be a program
		ArrayList<Condition> conditionList = new ArrayList<Condition>();
		for (int i = 0; i < p.rules.size(); i++) {
			Condition c = p.rules.get(i).condition;
			conditionList.add(c);
			//we get a list of the conditions from the list of rules
		}
		int randInt = rand.nextInt(conditionList.size());
		Condition randCond = conditionList.get(randInt).deepCopy();
		Node parent = stackOfNodes.getLast();
		if (parent instanceof BinaryCondition) {
			((Rule) parent).condition = randCond;
		}
		else if (parent instanceof BinaryCondition) {
			int randNum = rand.nextInt(1);
			if (((BinaryCondition) parent).left.equals(this)) {
				if (randNum == 0) ((BinaryCondition) parent).left = randCond;
			}
			else if (((BinaryCondition) parent).right.equals(this)) {
				if (randNum == 0) ((BinaryCondition) parent).right = randCond;
			}
			else System.out.println("Error: This should never happen");
		}
		else if (parent instanceof Relation) {
			((Relation) parent).condition = randCond;
		}
		return null;
	}
	
	@Override
	public Node randomReplace(StringBuffer sb) {
		int randrel = rand.nextInt(6);
		rel = RelOperator.getRel(randrel + 32);
		return this;
	}
	
	@Override
	public Node newParent(StringBuffer sb) {
		//inserts a new BinaryCondition as a parent
				//just as a convention we have decided to insert make the current
				//BinaryCondition the left child of the new parent as we see 
				//little to no benefit in randomizing whether it becomes the left
				//vs the right child.
				
				Program p = (Program) stackOfNodes.getFirst(); //should be a program
				ArrayList<Condition> conditionList = new ArrayList<Condition>();
				for (int i = 0; i < p.rules.size(); i++) {
					Condition c = p.rules.get(i).condition;
					conditionList.add(c);
					//we get a list of the conditions from the list of rules
				}
				int randInt = rand.nextInt(conditionList.size());
				Condition randCond = conditionList.get(randInt).deepCopy();
				BinaryCondition newParent;
				if (rand.nextInt(2) == 0) 
					newParent = new BinaryCondition(this, BinaryConditionOperator.AND, randCond); 
				else
					newParent = new BinaryCondition(this, BinaryConditionOperator.OR, randCond);
				//this is the new parent
				
				//link it back to the tree
				Node parent = stackOfNodes.getLast();
				if (parent instanceof Rule) {
					((Rule) parent).condition = newParent;
				}
				else if (parent instanceof BinaryCondition) {
					int randNum = rand.nextInt(1);
					if (((BinaryCondition) parent).left.equals(this)) {
						if (randNum == 0) ((BinaryCondition) parent).left = newParent;
					}
					else if (((BinaryCondition) parent).right.equals(this)) {
						if (randNum == 0) ((BinaryCondition) parent).right = newParent;
					}
					else System.out.println("Error: This should never happen");
				}
				else if (parent instanceof Relation) {
					((Relation) parent).condition = newParent;
				}
				return null;
	}
				
	
	
	@Override
	public Node cloneKid(StringBuffer sb) {
		return invalidMutationHandler(sb); 
		// Relation does not have a variable amount of Children
	}
	
	/**
	 * if the called mutation is not valid for that type of Node
	 * then this is called
	 * @return a mutated Node
	 */
	private Node invalidMutationHandler(StringBuffer sb) {
		int randMutation = rand.nextInt(4);
		if (randMutation == 0)
			return remove(sb);
		else if (randMutation == 1) 
			return swapOrder(sb);
		else if (randMutation == 2) 
			return cloneSubtree(sb);
		else 
			return newParent(sb);
	}
	
	
	@Override
	public Condition deepCopy() {
		Condition newCondition;
		Condition newCondition2;
		if (isCondition) {
			newCondition =  new Relation(condition.deepCopy());
			return newCondition;
		} else {
			newCondition2 = new Relation((Expression)left.deepCopy(),
					rel, (Expression)right.deepCopy());
			return newCondition2;
		}
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
			sb.append(" ");
			sb.append(temp.toString());
			sb.append(" ");
			right.prettyPrint(sb);
		}
	}

	@Override
	public boolean evaluate() {
		// TODO Auto-generated method stub
		return false;
	}

}
