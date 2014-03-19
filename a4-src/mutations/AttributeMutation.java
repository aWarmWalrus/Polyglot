package mutations;

import student.Node;
import student.Program;

public class AttributeMutation extends Mutation {

	public AttributeMutation(Critter crit, Program node) {
		super(crit, node);
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * Changes an attribute of the critter
	 * 
	 * @return a Program
	 */
	public Node attributeMutation() {
		// 3 kinds of memory entries
		// 1) length of critter's memory (at least 8)
		// 2) offense (at least 1)
		// 3) defense (at least 1)
		// This mutation will either increase/decrease (with equal probability)
		// the values of these entries

		if (rand.nextInt(2) == 0) { // this will decrease attributes
			int i = rand.nextInt(3);
			if (i == 0) {
				if (critter.mem.size() > 8) { // needs to be at least 8

					critter.mem.remove(critter.mem.size() - 1);
					// remove the last element that was put into the memory
				}
			} else if (i == 1) { // decreasing defense
				if (critter.mem.get(1) > 1) { // needs to be greater than 1
					critter.mem.add(1, critter.mem.get(1) - 1);
					// we take off 1 from previous def value

				}
			} else if (i == 2) { // decreasing offense
				if (critter.mem.get(2) > 1) { // needs to be greater than 1
					critter.mem.add(2, critter.mem.get(2) - 1);
					// we take off 1 from old offense value
				}
			}

		} else { // nextInt was 1 this will increase attributes

			// index = size() + 1 because we are adding a new memory spot

			int p = rand.nextInt(3);
			if (p == 0) { // increasing memory
				critter.mem.add(critter.mem.size(), 0);

			} else if (p == 1) { // increasing defense
				critter.mem.add(1, critter.mem.get(1) + 1);
			} else if (p == 2) { // increasing offense
				critter.mem.add(2, critter.mem.get(2) + 1);
			}

		}
		// after the mutation there is another 1/4 chance that another
		// mutation will happen
		return makeMutation();

	}
	
	
}
