package student;

/** A literal numeric constant, such as 2.
 */
public class Num extends Expression {		//WHY IS THIS ABSTRACT?
    public final int value;

    public Num(int v) {
        value = v;
    }

    @Override
    public int size() {
    	return 1;
    }
    
    
    @Override
	public Node remove(StringBuffer sb) {
		return null;
		//TODO
	} 
	
	@Override
	public Node swapOrder(StringBuffer sb) {
		return invalidMutationHandler(sb); 
		// Num has no children to swap
	}
	
	@Override
	public Node cloneSubtree(StringBuffer sb) {
		return invalidMutationHandler(sb);
		// Num has no children to clone
	}
	
	@Override
	public Node randomReplace(StringBuffer sb) {
		return null; 
		// TODO adjust the integer
	}
	
	@Override
	public Node newParent(StringBuffer sb) {
		return null;
		// can insert an expression as a parent
	}
	
	@Override
	public Node cloneKid(StringBuffer sb) {
		return invalidMutationHandler(sb); 
		// has no children
	}
	
	/**
	 * if the called mutation is not valid for that type of Node
	 * then this is called
	 * @return a mutated Node
	 */
	private Node invalidMutationHandler(StringBuffer sb) {
		int randMutation = rand.nextInt(2);
		if (randMutation == 0) {
			return remove(sb);
		} else {
			return randomReplace(sb);
		}
	}
	
	public Expression deepCopy() {
		Expression newExp = new Num(value);
		return newExp;
	}
    
    public void prettyPrint(StringBuffer sb){
    	sb.append(Integer.toString(value));
    }
}
