package mutations;

import java.util.Random;

import student.AbstractNode;
import student.Node;
import student.Program;

public class RuleSetMutation extends Mutation {

	Random rand = new Random();

	public RuleSetMutation(Critter crit, Node node) {
		super(crit, node);
	}
	
	public RuleSetMutation(Node node) {
		super(node);
	}
	
	
	
	/**
	 * Changes the rule set of the Program given in the constructor.
	 * 
	 * @return a Node
	 */
	public Node ruleMutation(StringBuffer sb) {

		int mutationType = rand.nextInt(5);
		if (mutationType == 0) {
			((AbstractNode)nodeToMutate).remove(sb);
			//justify this design decision
		}
		else if (mutationType == 1) {
			((AbstractNode)nodeToMutate).swapOrder(sb);
		}
		else if (mutationType == 2) {
			((AbstractNode)nodeToMutate).cloneSubtree(sb);
		}
		else if (mutationType == 3) {
			((AbstractNode)nodeToMutate).randomReplace(sb);
		}
		else if (mutationType == 4) {
			((AbstractNode)nodeToMutate).newParent(sb);
		}
		else  { //(mutationType == 5)
			((AbstractNode)nodeToMutate).cloneKid(sb);
		}

//		nodeToMutate.mutate();

		// after the mutation there is another 1/4 chance that another
		// mutation will happen
		return makeMutation(sb);
	}
	
//	//When the Condition is true, then this action may be triggered.
//		public void triggerAction(Action act){
//			ActionSwitch aswitch = new ActionSwitch(act);
//			aswitch.takingAction();
//		}
//		
	//For switching actions:
		public void switchAction() {
			
		}
}
