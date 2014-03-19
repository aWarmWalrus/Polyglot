package student;

public class SensorMem extends Expression{
	
	public enum option{
		MEM(0), NEARBY(80), AHEAD(81), RANDOM(82);
		
		int value;
		
		option(int value){
			this.value = value;
		}
		
		static option getOption(int token){
			for(option i : option.values()){
				if(i.value == token) return i;
			}
			return null;
		}
	}
	
	Expression index;
	option o;
	
	void setOption(int token){
		o = option.getOption(token);
	}
	
	void setExpression(Expression expr){
		index = expr;
	}
	
	public void prettyPrint(StringBuffer sb){
		Token tool = new Token(o.value, 0);
		sb.append(tool.toString());
		sb.append("[");
		index.prettyPrint(sb);
		sb.append("]");
	}
}
