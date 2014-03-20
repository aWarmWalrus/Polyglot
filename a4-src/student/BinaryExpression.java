package student;

import java.util.ArrayList;

import mutations.RuleSetMutation;

/**
 * @author aWarmWalrus
 * @author Kelly
 *
 */
public class BinaryExpression extends Expression implements Node {

	public enum BinaryOp{
		
		PLUS(50), MINUS(51), MUL(60), DIV(61), MOD(62);
		
		int value;
		
		BinaryOp(int newvalue){
			value = newvalue;
		}
		
		static BinaryOp getBinaryOp(int token){
			
			for(BinaryOp i : BinaryOp.values()){
				if (i.value == token) return i;
			}
			
			return null;
		}
	}
	
	Expression left;
	BinaryOp op;
	Expression right;
	boolean parentheses;
	
	public BinaryExpression(boolean isParentheses){
		parentheses = isParentheses;
	}
	
	public void setExpression(BinaryExpression expr){
		this.left = expr.left;
		this.op = expr.op;
		this.right = expr.right;
	}
	
	public BinaryExpression(){
		parentheses = false;
	}
	
	
	public BinaryExpression(Expression left, BinaryOp op, Expression right) {
		this.left = left;
		this.op = op;
		this.right = right;
	}
	
	public void setLeft(Expression l){
		left = l;
	}
	
	public void setOp(int token){
		op = BinaryOp.getBinaryOp(token);
	}
	
	public void setRight(Expression r){
		right = r;
	}

	@Override
	public int size() {
		return left.size() + right.size() + 1;
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
		sb.append("Mutation: removed a binary expression node");
//		Node n = stackOfNodes.getLast(); //the parent
//		if (n instanceof Rule) {
//			if (rand.nextInt(1) == 0)
//				((Rule) n).condition = this.left;
//			else 
//				((Rule) n).condition = this.right;
//		}
//		else if (n instanceof BinaryCondition) {
//			int randNum = rand.nextInt(1);
//			if (((BinaryCondition) n).left.equals(this)) {
//				if (randNum == 0) ((BinaryCondition) n).left = this.left;
//				else ((BinaryCondition) n).left = this.right;
//			}
//			else if (((BinaryCondition) n).right.equals(this)) {
//				if (randNum == 0) ((BinaryCondition) n).right = this.left;
//				else ((BinaryCondition) n).right = this.right;
//			}
//			else System.out.println("Error: This should never happen");
//		}
//		else if (n instanceof Relation) {
//			if (rand.nextInt(1) == 0)
//				((Relation) n).condition = this.left;
//			else
//				((Relation) n).condition = this.right;
//		}
		return null;
	}

	@Override
	public Node swapOrder(StringBuffer sb) {
		sb.append("Mutation: the order of two children of a binary expression were switched");
//		Condition temp = new BinaryCondition();
//		temp = this.left;
//		this.left = this.right;
//		this.right = temp;
		return this;
	}

	@Override
	public Node cloneSubtree(StringBuffer sb) {
		sb.append("Mutation: cloned a subtree of a binary expression");
//		Program p = (Program) stackOfNodes.getFirst(); //should be a program
//		ArrayList<Condition> conditionList = new ArrayList<Condition>();
//		for (int i = 0; i < p.rules.size(); i++) {
//			Condition c = p.rules.get(i).condition;
//			conditionList.add(c);
//			//we get a list of the conditions from the list of rules
//		}
//		int randInt = rand.nextInt(conditionList.size());
//		Condition randCond = conditionList.get(randInt).deepCopy();
//		Node parent = stackOfNodes.getLast();
//		if (parent instanceof BinaryCondition) {
//			((Rule) parent).condition = randCond;
//		}
//		else if (parent instanceof BinaryCondition) {
//			int randNum = rand.nextInt(1);
//			if (((BinaryCondition) parent).left.equals(this)) {
//				if (randNum == 0) ((BinaryCondition) parent).left = randCond;
//			}
//			else if (((BinaryCondition) parent).right.equals(this)) {
//				if (randNum == 0) ((BinaryCondition) parent).right = randCond;
//			}
//			else System.out.println("Error: This should never happen");
//		}
//		else if (parent instanceof Relation) {
//			((Relation) parent).condition = randCond;
//		}
		return null;
		
	}

	@Override
	public Node randomReplace(StringBuffer sb) {
		sb.append("Mutation: randomly replaced a binary expression with a new one");
//		if (op.opvalue == 30) op = BinaryConditionOperator.AND;
//		else op = BinaryConditionOperator.OR;
		return this;
	}

	@Override
	public Node newParent(StringBuffer sb) { //inserts a new BinaryCondition as a parent
		//just as a convention we have decided to insert make the current
		//BinaryCondition the left child of the new parent as we see 
		//little to no benefit in randomizing whether it becomes the left
		//vs the right child.
		
		sb.append("Mutation: inserted a new parent for an expression node");
		
//		Expression otherchild = new BinaryExpression();
//		Program p = (Program) stackOfNodes.getFirst(); //should be a program
//		ArrayList<Expression> expressionList = new ArrayList<Expression>();
//		for (int i = 0; i < p.rules.size(); i++) {
//			Expression c = p.rules.get(i).;
//			expressionList.add(c);
//			//we get a list of the conditions from the list of rules
//		}
//		int randInt = rand.nextInt(expressionList.size());
//		Expression randCond = expressionList.get(randInt).deepCopy();
//		BinaryExpression newParent = new BinaryExpression(this, op, randCond); 
//		//this is the new parent
//		
//		//link it back to the tree
//		Node parent = stackOfNodes.getLast();
//		if (parent instanceof BinaryExpression) {
//			((Rule) parent).condition = newParent;
//		}
//		else if (parent instanceof BinaryCondition) {
//			int randNum = rand.nextInt(1);
//			if (((BinaryCondition) parent).left.equals(this)) {
//				if (randNum == 0) ((BinaryCondition) parent).left = newParent;
//			}
//			else if (((BinaryCondition) parent).right.equals(this)) {
//				if (randNum == 0) ((BinaryCondition) parent).right = newParent;
//			}
//			else System.out.println("Error: This should never happen");
//		}
//		else if (parent instanceof Relation) {
//			((Relation) parent).condition = newParent;
//		}
		return null;
		// TODO
	}

	@Override
	public Node cloneKid(StringBuffer sb) {
		return invalidMutationHandler(sb);
		// BinaryExpression does not have a variable amount of Children
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

	public Expression deepCopy() {
		Expression newExp = new BinaryExpression(left.deepCopy(), op, right.deepCopy());
		return newExp;
	}
	
	@Override
	public void prettyPrint(StringBuffer sb) {
		if(parentheses) sb.append("(");
		left.prettyPrint(sb);
		Token tool = new Token(op.value, 0);
		sb.append(" ");
		sb.append(tool.toString());
		sb.append(" ");
		right.prettyPrint(sb);
		if(parentheses) sb.append(")");
		
	}
}
