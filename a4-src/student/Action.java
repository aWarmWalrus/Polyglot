package student;

public enum Action {
	WAIT(10), FORWARD(11), BACKWARD(12), LEFT(13), RIGHT(14), EAT(15), 
	ATTACK(16), GROW(17), BUD(18), MATE(19), TAG(20), SERVE(21), NONE(0);
	
	private int actionValue;
	private Expression expr;
	
	Action(int value){
		actionValue = value;		
	}
	
	public int getValue(){
		return actionValue;
	}
	
	public void setExpr(Expression expression){
		expr = expression;
	}
	
	public void prettyPrint(StringBuffer sb){
		if(actionValue != 0){
			Token temp = new Token(actionValue, 0);
			sb.append(temp.toString());
			if(expr != null) {
				sb.append("[");
				expr.prettyPrint(sb);
				sb.append("]");
			}
		}
		sb.append(";");
	}
}
