package student;

/** A literal numeric constant, such as 2.
 */
public class Num extends Expression {		//WHY IS THIS ABSTRACT?
    public final int value;

    public Num(int v) {
        value = v;
    }
    
    @Override
	public Node remove() {
		return null;
		//TODO
	} 
	
	@Override
	public Node swapOrder() {
		return invalidMutationHandler(); 
		// Num has no children to swap
	}
	
	@Override
	public Node cloneSubtree() {
		return invalidMutationHandler();
		// Num has no children to clone
	}
	
	@Override
	public Node randomReplace() {
		return null; 
		// TODO adjust the integer
	}
	
	@Override
	public Node newParent() {
		return null;
		// can insert an expression as a parent
	}
	
	@Override
	public Node cloneKid() {
		return invalidMutationHandler(); 
		// has no children
	}
	
	/**
	 * if the called mutation is not valid for that type of Node
	 * then this is called
	 * @return a mutated Node
	 */
	private Node invalidMutationHandler() {
		int randMutation = rand.nextInt(2);
		if (randMutation == 0) {
			return remove();
		} else {
			return randomReplace();
		}
	}
    
    public void prettyPrint(StringBuffer sb){
    	sb.append(Integer.toString(value));
    }
}
