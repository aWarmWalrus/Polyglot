package mutations;

import student.AbstractNode;
import student.Node;

public class RuleMutation extends Mutation {

	public RuleMutation(Critter crit, Node node) {
		super(crit, node);
		// TODO Auto-generated constructor stub
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
		if (mutationType == 1) {
			((AbstractNode)nodeToMutate).swapOrder();
		}
		if (mutationType == 2) {
			((AbstractNode)nodeToMutate).cloneSubtree();
		}
		if (mutationType == 3) {
			((AbstractNode)nodeToMutate).randomReplace();
		}
		if (mutationType == 4) {
			((AbstractNode)nodeToMutate).newParent();
		}
		if (mutationType == 5) {
			((AbstractNode)nodeToMutate).cloneKid();
		}

		nodeToMutate.mutate();

		// after the mutation there is another 1/4 chance that another
		// mutation will happen
		return makeMutation();
	}
	
	
}
