package student;

public class BinaryExpression extends Expression{

	Expression left;
	BinaryOp op;
	Expression right;
	
	public void setLeft(Expression l){
		left = l;
	}
	
	public void setOp(BinaryOp op){
		this.op = op;
	}
	
	public void setRight(Expression r){
		right = r;
	}
}
