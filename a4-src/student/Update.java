package student;

public class Update extends AbstractNode{
	
	Expression memIndex;
	Expression assigned;
	
	public void setMemIndex(Expression expr){
		memIndex = expr;
	}
	
	public void setAssignment(Expression expr){
		assigned = expr;
	}
	
	@Override
	public int size() {
		//end of the tree; update will only assign to a mem[] location
//		mutationNum++;
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
