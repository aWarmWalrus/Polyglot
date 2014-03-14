package student;

import java.util.ArrayList;

/**
 * An interface representing a Boolean condition in a critter program.
 *
 */
public interface Condition extends Node {
	
	/**
	 * Evaluates the Condition
	 * 
	 * @return true or false
	 */
	public boolean evaluate();

}
