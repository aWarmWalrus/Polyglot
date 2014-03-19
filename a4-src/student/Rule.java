package student;

import java.util.ArrayList;

import mutations.RuleSetMutation;

/**
 * A representation of a critter rule.
 */
public class Rule extends AbstractNode {

	protected Condition condition;
	// how to represent the command?
	private ArrayList<Update> updates;
	private Action action;
	private ActionSwitch actionSwitch;

	/**
	 * Constructor for a Rule Object
	 */
	public Rule() {
		// requires no arguments
	}

	/**
	 * Alternate constructor for a Rule Object
	 * 
	 * @param cond
	 */
	public Rule(Condition condition, ArrayList<Update> updateList, Action action) {
		this.condition = condition; // another way to set the Condition
		updates = updateList;
		this.action = action;
		actionSwitch = new ActionSwitch(action);
	}

	public void setCondition(Condition cond) {
		condition = cond;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public void addUpdates(ArrayList<Update> updates) {
		this.updates = updates;
	}

	public boolean isAction() {
		if (action.actionValue != 0)
			return true;
		else
			return false;
	}

	/**
	 * Will be called after all the set methods are called. There are not that
	 * many cases when this will return false. 1) When there are no updates and
	 * no actions. 2) When there is
	 * 
	 * @return
	 */
	public boolean checkSemantic() {
		if (action == Action.NONE && (updates == null || updates.isEmpty()))
			return false;
		else
			return true;
	}

	/**
	 * Gets the condition if there is one.
	 * 
	 * @return condition if it exists
	 */
	public Condition getCondition() {
		return condition;
	}

	/**
	 * Gets the action.
	 * 
	 * @return action if it exists
	 */
	public Action getAction() {
		return action;
	}

	/**
	 * Gets the updates
	 * 
	 * @return updates
	 */
	public ArrayList<Update> getUpdates() {
		return updates;
	}

	@Override
	public int size() {
		// this iterates through the ArrayList of Update objects, calling size
		// on each.
		int numUpdatesActions = 0;
		for (int i = 0; i < updates.size(); i++) {
			numUpdatesActions += updates.get(i).size();
			// mutationNum ++;
		}
		if (isAction()) { // if it is not a NONE type
			numUpdatesActions += 1; // we add the action to the count
		}
		return condition.size() + numUpdatesActions + 1; // include the Rule in
															// the size
	}

	@Override
	public Node mutate() {
		int thatNode = rand.nextInt(this.size());
		if (thatNode == 0) {
			RuleSetMutation rm = new RuleSetMutation(this);
			// the RuleSetMutation class takes care of probability
			return rm.ruleMutation(); // calls mutation on the Rule only
		} else if (thatNode >= 1 && thatNode < condition.size() + 1) {
			condition.mutate();
			//weight of probability depends on size of condition
		} else {
			stackOfNodes.add(this);
			// we add the Rule to the LinkedList to keep track of it

			thatNode = thatNode - 1;
			if (isAction())
				thatNode = thatNode - 1; // we discount the Action
			thatNode = thatNode - 1; //we discount the Condition

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
		Program p = (Program) stackOfNodes.getFirst(); // should be a Program
		// we added the Program that called mutate() to this LinkedList

		p.rules.remove(this); // removes this Rule from the Program
		return p;
	}

	@Override
	public Node swapOrder() {
		return invalidMutationHandler();
		// you can't swap a condition with a command
	}

	@Override
	public Node cloneSubtree() {
		Program p = (Program) stackOfNodes.getFirst();
		int randRuleIndex = rand.nextInt(p.rules.size());
		int originalIndex = p.rules.indexOf(this); // index of the current Rule
		Rule copiedRule = (Rule) p.rules.get(randRuleIndex).deepCopy();
		p.rules.add(originalIndex, copiedRule);
		return copiedRule;
	}

	@Override
	public Node randomReplace() {
		// because we can't randomlyReplace the Node instead we will
		// randomly replace the Action
		// we will make sure there is at least one Action or one Update left
		action = action.randomchoice(); // changes action
		while (!isAction() && updates.size() == 0) {
			action = action.randomchoice();
		}
		return this;

	}

	@Override
	public Node newParent() {
		return invalidMutationHandler();
		// cannot insert a Program as the parent of this rule
	}

	@Override
	public Node cloneKid() {
		return invalidMutationHandler();
		// Rule does not have a variable amount of Children
	}

	/**
	 * if the called mutation is not valid for that type of Node then this is
	 * called
	 * 
	 * @return a mutated Node
	 */
	private Node invalidMutationHandler() {
		int randMutation = rand.nextInt(3);
		if (randMutation == 0)
			return remove();
		else if (randMutation == 1)
			return cloneSubtree();
		else
			return randomReplace();
	}

	@Override
	public Node deepCopy() {
		ArrayList<Update> newUpdates = new ArrayList<Update>();
		for (int i = 0; i < updates.size(); i++) {
			newUpdates.add((Update) updates.get(i).deepCopy());
		}
		Rule newRule = new Rule(condition.deepCopy(), newUpdates, action);
		return newRule;
	}

	@Override
	public void prettyPrint(StringBuffer sb) {
		sb.append("\n");
		condition.prettyPrint(sb);
		sb.append(" --> ");
		if (updates != null)
			for (int i = 0; i < updates.size(); i++) {
				updates.get(i).prettyPrint(sb);
				if (i + 1 != updates.size())
					sb.append("\n");
				if (i + 1 == updates.size() && action.getValue() != 0)
					sb.append(", ");
			}
		action.prettyPrint(sb);
	}

}
