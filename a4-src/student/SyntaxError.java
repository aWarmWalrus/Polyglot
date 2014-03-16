package student;

public class SyntaxError extends Exception {

	public SyntaxError() {
		System.out.println("Unknown");
	}
	
	public SyntaxError(String string) {
		System.out.println(string);
	}
}
