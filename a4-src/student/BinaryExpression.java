package student;

/**
 * 
 * 
 * @author aWarmWalrus
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Node mutate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void prettyPrint(StringBuffer sb) {
		left.prettyPrint(sb);
		Token tool = new Token(op.value, 0);
		sb.append(tool.toString());
		right.prettyPrint(sb);
		
	}
}
