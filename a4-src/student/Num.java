package student;

/** A literal numeric constant, such as 2.
 */
public abstract class Num extends Expression {
    public final int value;

    public Num(int v) {
        value = v;
    }
}
