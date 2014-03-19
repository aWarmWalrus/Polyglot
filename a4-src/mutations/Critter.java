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
		program = p; // each Critter needs a program
		program.critter = this;
		mem = new ArrayList<Integer>();
		r = new Random();
		mem.add(0, 8); // assuming we start with 8
		mem.add(1, 1);
		mem.add(2, 1);
		mem.add(3, 1);
		mem.add(4, 1);
		mem.add(5, 1);
		mem.add(6, r.nextInt(100));
		mem.add(7, r.nextInt(100));
		mem.add(0, mem.size());

	}

	/**
	 * Adds a new memory location.
	 */
	public void addMem() {
		mem.add(mem.size(), 0);
	}

	/**
	 * Takes off the most recently added memory location. Condition: the size of
	 * memory can not go below 8.
	 */
	public void trashMem() {
		mem.add(mem.size(), -1); // sentinel values yay

	}

	// about mutations
	// when you remove a Condition - just make up a new Condition to replace it

	// when you remove the last Update - generate random Update?
	// when you remove an Action - generate a random Action
	// guarantee there is only Update or Action

	// For Swapping
	// Rule swapping - obvious
	// condition swapping - swapping within ArrayList





	// 6 types of Mutations:
	// 1. Remove
	// Take away a rule
	// Take away a child; replace with left/right (for Condiitons)
	//
	// 2. SwapOrder
	// Switch the children (for addops and mulops)
	//
	// 3. CloneSubtree
	// Replace with a subtree from a different rule
	//
	// 4. RandomReplace
	// Find a random and legal Node of the same type
	//
	// 5. NewParent
	// Get a new parent (as for Conditions this could work)
	// if you need kids copy from other rules
	//
	// 6. CloneKid
	// add a rule or add an update
	// Nodes with an unfixed number of children
	// copy one of its children and add to the list
}
