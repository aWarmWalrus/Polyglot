package student;

/** A literal numeric constant, such as 2.
 */
public class Num extends Expression {		//WHY IS THIS ABSTRACT?
    public final int value;

    public Num(int v) {
        value = v;
    }
    
    public void prettyPrint(StringBuffer sb){
    	sb.append(Integer.toString(value));
    }
}
