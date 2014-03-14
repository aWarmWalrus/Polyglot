package student;

import java.util.ArrayList;

/**
 * An interface representing a Boolean condition in a critter program.
 *
 */
public class Condition implements Node {		//IM CHANGING THIS TO A REAL CLASS. LORD HELP ME.
	
	ArrayList<Token> tokens;
	
	public Condition(ArrayList<Token> tokens){
		this.tokens = tokens;
	}
	
	/**
	 * Evaluates the Condition
	 * 
	 * @return true or false
	 */
	public boolean evaluate(){
		return true;
	}

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
