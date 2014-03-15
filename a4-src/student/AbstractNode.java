package student;

import java.util.ArrayList;

//Purpose: to more easily share code that implements mutation for Fault Injection

public class AbstractNode implements Node{
	
	//the children of this particular Node are stored here
	ArrayList<?> children; 
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
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
