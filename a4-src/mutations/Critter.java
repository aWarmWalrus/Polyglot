package mutations;

import java.util.ArrayList;
import java.util.Random;

import student.Program;

public class Critter {

	ArrayList<Integer> mem;
	Random r;
	Program program;
	
	/**
	 * Creates a new Critter object.
	 */
	public Critter(Program p) {
		program = p; //each Critter needs a program
		mem.add(0, 8); // assuming we start with 8
		mem.add(1, 1);
		mem.add(2, 1);
		mem.add(3, 1);
		mem.add(4, 1);
		mem.add(5, 1);
		mem.add(6,r.nextInt(100));
		mem.add(7, r.nextInt(100));
		
	}
	
	/**
	 * Adds a new memory location.
	 */
	public void addMem() {
		mem.add(mem.size(), 0);
	}
	
	/**
	 * Takes off the most recently added memory location.
	 * Condition: the size of memeory can not go below 8.
	 */
	public void trashMem() {
		mem.add(mem.size(), -1); //sentinel values yay
		
	}
	
	
	// Bryce says ArrayList will offer faster performance
	// initialize to -1
	
	// about mutations
	// when you remove a Condition - just make up a new Condition to replace it

	// when you remove the last Update - generate random Update?
	// when you remove an Action - generate a random Action 
	// guarantee there is only Update or Action
	
	// For Swapping
	// Rule swapping - obvious
	// condition swapping - swapping within ArrayList
	
	// Replacement:
	//replaced with the same type somewhere else in the Program
	// copy over that subtree
	
	// Replacement v2
	// children not affected, only replace that single node
	// copy over a node somewhere else in the tree
	
	// Insertion
	// 
	
	// Nodes with an unfixed number of children
	// copy one of its children and add to the list
	// aka have duplicate rules
	// aka have duplicate updates
	
	
}
