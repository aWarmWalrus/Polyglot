package student;

import java.util.Random;

public enum Action { //13 types
	WAIT(10), FORWARD(11), BACKWARD(12), LEFT(13), RIGHT(14), EAT(15), 
	ATTACK(16), GROW(17), BUD(18), MATE(19), TAG(20), SERVE(21), NONE(0);
	
	protected int actionValue;
	private Expression expr;
	Random rand = new Random();
	
	Action(int value){
		actionValue = value;		
	}
	
	public int getValue(){
		return actionValue;
	}
	
	public void setExpr(Expression expression){
		expr = expression;
	}
	
	public Action randomchoice() {
		int choice = rand.nextInt(13);
		int newAction = choice % 9;
		return getAct(newAction);
	}
	
	public static Action getAct(int token){
		if(token == 10) return Action.WAIT;
		else if(token == 11) return Action.FORWARD;
		else if(token == 12) return Action.BACKWARD;
		else if(token == 13) return Action.LEFT;
		else if(token == 14) return Action.RIGHT;
		else if(token == 15) return Action.EAT;
		else if(token == 16) return Action.ATTACK;
		else if(token == 17) return Action.GROW;
		else if(token == 18) return Action.BUD;
		else if(token == 19) return Action.MATE;
		else if(token == 20) return Action.TAG;
		else if(token == 21) return Action.SERVE;
		else return Action.NONE;
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
