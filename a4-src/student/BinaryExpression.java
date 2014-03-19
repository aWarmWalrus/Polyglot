package student;

/**
 * @author aWarmWalrus
 * @author Kelly
 *
 */
public class BinaryExpression extends Expression implements Node {

	public enum BinaryOp{
		
		PLUS(50), MINUS(51), MUL(60), DIV(61), MOD(62);
		
		int value;
		
		BinaryOp(int newvalue){
			value = newvalue;
		}
		
		static BinaryOp getBinaryOp(int token){
			
			for(BinaryOp i : BinaryOp.values()){
				if (i.value == token) return i;
			}
			
			return null;
		}
	}
	
	Expression left;
	BinaryOp op;
	Expression right;
	boolean parentheses;
	
	public BinaryExpression(boolean isParentheses){
		parentheses = isParentheses;
	}
	
	public void setExpression(BinaryExpression expr){
		this.left = expr.left;
		this.op = expr.op;
		this.right = expr.right;
	}
	
	public BinaryExpression(){
		parentheses = false;
	}
	
	
	public BinaryExpression(Expression left, BinaryOp op, Expression right) {
		this.left = left;
		this.op = op;
		this.right = right;
	}
	
	public void setLeft(Expression l){
		left = l;
	}
	
	public void setOp(int token){
		op = BinaryOp.getBinaryOp(token);
	}
	
	public void setRight(Expression r){
		right = r;
	}

	@Override
	public int size() {
		return left.size() + right.size() + 1;
	}

	@Override
	public Node mutate() {
		// TODO Auto-generated method stub
		return null;
	}

	public Expression deepCopy() {
		Expression newExp = new BinaryExpression(left.deepCopy(), op, right.deepCopy());
		return newExp;
	}
	
	@Override
	public void prettyPrint(StringBuffer sb) {
		if(parentheses) sb.append("(");
		left.prettyPrint(sb);
		Token tool = new Token(op.value, 0);
		sb.append(" ");
		sb.append(tool.toString());
		sb.append(" ");
		right.prettyPrint(sb);
		if(parentheses) sb.append(")");
		
	}
}
