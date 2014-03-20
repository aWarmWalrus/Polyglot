package student;

@SuppressWarnings("serial")
public class SyntaxError extends Exception {

	public SyntaxError() {
		System.out.println("SYNTAX ERROR");
	}
	
	public SyntaxError(String string) {
		System.out.println(string);
	}
}
