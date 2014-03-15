package student;

import java.util.ArrayList;
import java.util.Random;

//Purpose: to more easily share code that implements mutation for Fault Injection

public class AbstractNode implements Node{
	
	//the children of this particular Node are stored here
	ArrayList<?> children; 
	
	//random object for mutations
	Random rand;
	
	//for mutation: the size accumulator
	int mutationNum = 0;
	
	//this should never actually be called
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	//Mutation Algorithm:
	//We call size() again and when the size accumulator reaches the randomNumber, we
	//then mutate at that position
	@Override
	public Node mutate() {
		
		return null;
	}
	
	public Node mutateHelper() {
		int randNum = 0;
		randNum = rand.nextInt(size());
		
		//next we walk down the tree 
		return null;
	}

	@Override
	public void prettyPrint(StringBuffer sb) {
		// TODO Auto-generated method stub
		
	}

}
