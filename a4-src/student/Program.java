package student;

import java.util.ArrayList;
import java.util.Random;

import mutations.Critter;
import mutations.Mutation;
import mutations.RuleSetMutation;

/**
 * A representation of a critter program.
 * 
 */
public class Program extends AbstractNode {

	ArrayList<Rule> rules;
	public Critter critter;

	// Random rand = new Random();

	// assuming that you call the Critter constructor first,
	// and then the Critter constructor assigns program.critter
	// you should not mutate if you only initialize a Program

	public Program() {
		rules = new ArrayList<Rule>();
	}

	@Override
	public int size() {
		int numRules = 0;
		for (int i = 0; i < rules.size(); i++) {
			numRules += rules.get(i).size();
			// mutationNum ++;
		}
		return numRules + 1; // add one to include the Program in the count
	}

	/**
	 * Returns the list of rules that are in this program
	 * 
	 * @return
	 */
	public ArrayList<Rule> getRules() {
		return rules;
	}

	// when we call mutate, then we will return a mutated Program
	// this works because Program extends Node

	@Override
	public Node mutate() {
		// probability weights depend on the size
		// there is a (1/program.size() chance the mutation will happen
		// on Program, and a (program.size() - 1)/program.size() chance that
		// the mutation will happen on one of the rest of the Rules.

		int thatNode = rand.nextInt(this.size());
		if (thatNode == 0) {
			RuleSetMutation rm = new RuleSetMutation(this);
			//the RuleSetMutation class takes care of probability
			return rm.ruleMutation(); // calls mutation on the Program only
		} else {
			thatNode = thatNode - 1; //narrowing down our probability
			for (int i = 0; i < rules.size(); i++) {
				if (rules.get(i).size() < thatNode){
					thatNode = thatNode - rules.get(i).size();
					//narrowing down our probability
				} else { // if rules.get(i).size(0 >= thatNode
					rules.get(i).mutate();
					return this;
					//we found the Rule that gets that mutation
				}
			}
		}
		System.out.println("Something went wrong - this shouldn't be called");
		return this;
	}

	@Override
	public Node remove() {
		return invalidMutationHandler(); // not supported for type Program
	}

	@Override
	public Node swapOrder() {
		int pos1 = rand.nextInt(rules.size());
		int offset = rand.nextInt(rules.size() - 1) + 1;
		int pos2 = (pos1 + offset) % rules.size();
		// added one to guarantee at least +1 offset
		// we don't want to swap the same rule with itself, after all

		Rule temp = new Rule();
		Rule rule1 = rules.get(pos1);
		Rule rule2 = rules.get(pos2);

		// Swapping
		temp = rule1; // keeping it safe
		rules.add(pos1, rule2); // at position 1 we put Rule 2
		rules.add(pos2, temp); // at position 2 we put Rule 1

		return this;
	}

	@Override
	public Node cloneSubtree() {
		return invalidMutationHandler();
		// can't replace a Program with another Program;
		// there is only one Program
	}

	@Override
	public Node randomReplace() {
		return invalidMutationHandler(); // can't replace a Program
	}

	@Override
	public Node newParent() {
		return invalidMutationHandler(); // Program doesn't have a parent
	}

	@Override
	public Node cloneKid() {
		int kidIndex = rand.nextInt(this.rules.size());
		Rule kid = this.rules.get(kidIndex);
		this.rules.add((Rule)kid.deepCopy()); //we copy the kid and add it
		return this; //returns the Program with the cloned Kid
	}

	/**
	 * if the called mutation is not valid for that type of Node then this is
	 * called
	 * 
	 * @return a mutated Node
	 */
	private Node invalidMutationHandler() {
		//reshuffle probability, due to invalid mutations
		int randMutation = rand.nextInt(2);
		if (randMutation == 0) {
			return swapOrder();
		} else {
			return cloneKid();
		}
	}

	@Override
	public Node deepCopy() {
		ArrayList<Rule> newRuleList = new ArrayList<Rule>();
		for (int i = 0; i < rules.size(); i++) {
			newRuleList.add((Rule) rules.get(i).deepCopy());
		}
		rules = newRuleList;
		return this; // returns the Program, now with a new copy of Rules
	}

	@Override
	public void prettyPrint(StringBuffer sb) {
		for (Rule i : rules)
			i.prettyPrint(sb);
	}

}
