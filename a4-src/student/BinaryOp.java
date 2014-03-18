package student;

// Represents +, -, *, /, mod
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
