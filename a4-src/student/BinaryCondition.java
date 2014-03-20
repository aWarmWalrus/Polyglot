package student;

import java.util.ArrayList;

import mutations.RuleSetMutation;

/**
 * A representation of a binary Boolean condition: 'and' or 'or'
 * 
 */
public class BinaryCondition extends AbstractNode implements Condition {

	Condition left;
	BinaryConditionOperator op;
	Condition right;

	public boolean isTrue = true;

	/**
	 * Create an AST representation of l op r.
	 * 
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

	public void setLeft(Condition cond) {
		left = cond;
	}

	public void setRight(Condition cond) {
		right = cond;
	}

	public void setOp(BinaryConditionOperator op) {
		this.op = op;
	}

	/**
	 * Create a boolean node for conditions must be true (for mutations to
	 * occur)
	 */
	public BinaryCondition(boolean bool) {
		isTrue = bool;
	}

	@Override
	public int size() {
		return left.size() + right.size() + 1;
		//we add 1 to include the current node in the size count
	}

	 @Override
	 public Node mutate(StringBuffer sb) {
		 int thatNode = rand.nextInt(this.size());
			if (thatNode == 0) {
				RuleSetMutation rm = new RuleSetMutation(this);
				// the RuleSetMutation class takes care of probability
				return rm.ruleMutation(sb); // calls mutation on the BinCondition
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
		sb.append("Mutation: removed a binary condition node");
		Node n = stackOfNodes.getLast(); //the parent
		if (n instanceof Rule) {
			if (rand.nextInt(1) == 0)
				((Rule) n).condition = this.left;
			else 
				((Rule) n).condition = this.right;
		}
		else if (n instanceof BinaryCondition) {
			int randNum = rand.nextInt(1);
			if (((BinaryCondition) n).left.equals(this)) {
				if (randNum == 0) ((BinaryCondition) n).left = this.left;
				else ((BinaryCondition) n).left = this.right;
			}
			else if (((BinaryCondition) n).right.equals(this)) {
				if (randNum == 0) ((BinaryCondition) n).right = this.left;
				else ((BinaryCondition) n).right = this.right;
			}
			else System.out.println("Error: This should never happen");
		}
		else if (n instanceof Relation) {
			if (rand.nextInt(1) == 0)
				((Relation) n).condition = this.left;
			else
				((Relation) n).condition = this.right;
		}
		return null;
	}

	@Override
	public Node swapOrder(StringBuffer sb) {
		sb.append("Mutation: the order of two children of a binary condition were switched");
		Condition temp = new BinaryCondition();
		temp = this.left;
		this.left = this.right;
		this.right = temp;
		return this;
	}

	@Override
	public Node cloneSubtree(StringBuffer sb) {
		sb.append("Mutation: cloned a subtree of a binary condition");
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
		sb.append("Mutation: randomly replaced a binary condition with a new one");
		if (op.opvalue == 30) op = BinaryConditionOperator.AND;
		else op = BinaryConditionOperator.OR;
		return this;
	}

	@Override
	public Node newParent(StringBuffer sb) { //inserts a new BinaryCondition as a parent
		//just as a convention we have decided to insert make the current
		//BinaryCondition the left child of the new parent as we see 
		//little to no benefit in randomizing whether it becomes the left
		//vs the right child.
		

		sb.append("Mutation: inserted a new parent for a condition node");

		Program p = (Program) stackOfNodes.getFirst(); //should be a program
		ArrayList<Condition> conditionList = new ArrayList<Condition>();
		for (int i = 0; i < p.rules.size(); i++) {
			Condition c = p.rules.get(i).condition;
			conditionList.add(c);
			//we get a list of the conditions from the list of rules
		}
		int randInt = rand.nextInt(conditionList.size());
		Condition randCond = conditionList.get(randInt).deepCopy();
		BinaryCondition newParent = new BinaryCondition(this, op, randCond); 
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
		// TODO
	}

	@Override
	public Node cloneKid(StringBuffer sb) {
		return invalidMutationHandler(sb);
		// BinaryCondition does not have a variable amount of Children
	}

	/**
	 * if the called mutation is not valid for that type of Node then this is
	 * called
	 * 
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
		else if (randMutation == 3)
			return randomReplace(sb);
		else
			return newParent(sb);
		
	}
	
	
	@Override
	public Condition deepCopy() {
		Condition newCondition = new BinaryCondition(left.deepCopy(), this.op, right.deepCopy());
		return newCondition;
	}


	@Override
	public void prettyPrint(StringBuffer sb) {
		left.prettyPrint(sb);
		if(op.opvalue == 30) sb.append(" or ");
		else sb.append(" and ");
		right.prettyPrint(sb);
	}

	@Override
	public boolean evaluate() {
		// TODO Auto-generated method stub
		return false;
	}

}
