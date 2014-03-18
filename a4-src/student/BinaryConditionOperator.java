package student;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * An enumeration of all possible binary condition operators.
 */
public enum BinaryConditionOperator {

	OR (30),
	AND (31);

	public int opvalue;
	
	BinaryConditionOperator(int value){
		opvalue = value;
	}
	
	/**
	 * The list of operators.
	 */
	public static final List<BinaryConditionOperator> VALUES =
			Collections.unmodifiableList(Arrays.asList(values()));
	/**
	 * The number of operators.
	 */
	public static final int NUM_OPS = VALUES.size();

}
