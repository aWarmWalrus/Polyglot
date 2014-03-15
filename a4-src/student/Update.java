package student;

public class Update extends AbstractNode{
	
	
	
	@Override
	public int size() {
		//end of the tree; update will only assign to a mem[] location
		mutationNum++;
		return 1;
	}

	@Override
	public Node mutate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void prettyPrint(StringBuffer sb) {
		// TODO Auto-generated method stub
		
	}

}
