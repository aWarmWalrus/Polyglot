package student;

public class Update extends AbstractNode{
	
	SensorMem mem;
	Expression memIndex;
	Expression assigned;
	
	public Update(){
		mem = new SensorMem();
		mem.setOption(Token.MEM);
	}
	
	public void setMemIndex(Expression expr){
		mem.setExpression(expr);
	}
	
	public void setAssignment(Expression expr){
		assigned = expr;
	}
	
	@Override
	public int size() {
		return 1;
	}

	@Override
	public Node mutate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void prettyPrint(StringBuffer sb) {
		sb.append("mem[");
		memIndex.prettyPrint(sb);
		sb.append("] := ");
		assigned.prettyPrint(sb);
	}

}
