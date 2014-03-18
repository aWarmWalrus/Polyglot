package student;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * An enumeration of all possible equality comparators
 * @author Walrus
 *
 */
public enum RelOperator {

	LT(32), LE(33), EQ(34), GE(35), GT(36), NE(37);
	
	
	RelOperator(int value){
		relValue = value;		
	}
	
	private int relValue;
	
	public int getValue(){
		return relValue;
	}
	
	/**
	 * The list of operators.
	 */
	public static final List<RelOperator> VALUES =
			Collections.unmodifiableList(Arrays.asList(values()));
	/**
	 * The number of operators.
	 */
	public static final int NUM_OPS = VALUES.size();
	
	public static RelOperator getRel(int token){
		if(token == 32) return RelOperator.LT;
		else if(token == 33) return RelOperator.LE;
		else if(token == 34) return RelOperator.EQ;
		else if(token == 35) return RelOperator.GE;
		else if(token == 36) return RelOperator.GT;
		else if(token == 37) return RelOperator.NE;
		return null;
	}
	
}
