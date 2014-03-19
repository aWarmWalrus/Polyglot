package mutations;

import student.AbstractNode;
import student.Node;
import student.Program;

public class RuleMutation extends Mutation {

	public RuleMutation(Critter crit, Program program) {
		super(crit, program);
	}
	
	public RuleMutation(Program program) {
		super(program);
	}
	
	
	
	/**
	 * Changes the rule set of the Program given in the constructor.
	 * 
	 * @return a Node
	 */
	public Node ruleMutation() {

		// total number of notes to choose from:
		int programSize = nodeToMutate.size();

		// we choose a random number that represents one Node
		chosenNodeID = rand.nextInt(programSize);
		
		int mutationType = rand.nextInt(5);
		if (mutationType == 0) {
			((AbstractNode)nodeToMutate).remove();
			//justify this design decision
		}
		else if (mutationType == 1) {
			((AbstractNode)nodeToMutate).swapOrder();
		}
		else if (mutationType == 2) {
			((AbstractNode)nodeToMutate).cloneSubtree();
		}
		else if (mutationType == 3) {
			((AbstractNode)nodeToMutate).randomReplace();
		}
		else if (mutationType == 4) {
			((AbstractNode)nodeToMutate).newParent();
		}
		else  { //(mutationType == 5)
			((AbstractNode)nodeToMutate).cloneKid();
		}

		//TODO //DELETE THIS?
		nodeToMutate.mutate();

		// after the mutation there is another 1/4 chance that another
		// mutation will happen
		return makeMutation();
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
