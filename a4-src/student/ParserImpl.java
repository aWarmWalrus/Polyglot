package student;

import java.io.Reader;
import java.util.ArrayList;

public class ParserImpl implements Parser {

	/**
	 * Vestigial code that we do not need right now.
	 */
	/** The tokenizer from which input is read. */
	Tokenizer tokenizer;
	//ArrayList<Token> tokens;
	Program program;
	Reader reader;

	// public ParserImpl(Tokenizer tk) {
	// throw new UnsupportedOperationException();
	// }

	/**
	 * Not really sure what to do with both parse and parseProgram, so parse()
	 * will call parseProgram(). 
	 * 
	 * @return program 
	 * 
	 */
	public Program parse(Reader r) {
		System.out.println("called parse(), calling parseProgram()");
		reader = r;
		tokenizer = new Tokenizer(reader);
		ArrayList<Token> allTokens = new ArrayList<Token>();
		while (tokenizer.hasNext())
			allTokens.add(tokenizer.next());	
		try {
			parseProgram(allTokens);
			for(Rule rule : program.rules) parseRule(rule);
		} catch (SyntaxError e) {
			e.printStackTrace();
		}
		return program;
	}

	/**
	 * Parses a program from the stream of tokens provided by the Tokenizer,
	 * consuming tokens representing the program. All following methods with a
	 * name "parseX" have the same spec except that they parse syntactic form X.
	 * 
	 * @return the created AST
	 * @throws SyntaxError
	 *             if there the input tokens have invalid syntax
	 */
	public Program parseProgram(ArrayList<Token> tokens) throws SyntaxError {
		System.out.println("called parseProgram()");
		program = new Program();
		ArrayList<Token> ruleTokens = new ArrayList<Token>();
		for(Token i : tokens){
			if(i.getType() != Token.SEMICOLON){
				ruleTokens.add(i);
			} else {
				Rule aRule = new Rule(ruleTokens);
				program.rules.add(aRule);
				System.out.println(ruleTokens.toString());
				ruleTokens = new ArrayList<Token>();
			}
		}
		return program;
	}
	
	/**
	 * So the idea right now is that all our parsers know what
	 * they're parsing because we will pass an array
	 * of tokens to each node. I guess this is a bad idea?
	 * @return
	 * @throws SyntaxError
	 */
	public Rule parseRule(Rule rule) throws SyntaxError {
		ArrayList<Token> left = new ArrayList<Token>();
		ArrayList<Token> right = new ArrayList<Token>();
		int arrows = 0;
		for(Token i : rule.tokens){
			if(i.getType() != Token.ARR){
				if (arrows == 0) left.add(i);
				if (arrows == 1) right.add(i);
			} else if (i.getType() == Token.ARR){
				rule.condition = new Relation();
				System.out.println(left.toString());
				arrows++;
				if (arrows == 2) throw new SyntaxError();
			}
		}
		
		return rule;
	}

	public Condition parseCondition() throws SyntaxError {
		throw new UnsupportedOperationException();
	}

	public Expression parseExpression() throws SyntaxError {
		return parseTerm();
	}

	public Expression parseTerm() throws SyntaxError {
		throw new UnsupportedOperationException();
	}

	public Expression parseFactor() throws SyntaxError {
		throw new UnsupportedOperationException();
	}

	public Expression parseAtom() throws SyntaxError {
		throw new UnsupportedOperationException();
	}

	// add more as necessary...

	/**
	 * Consumes a token of the expected type. Throws a SyntaxError if the wrong
	 * kind of token is encountered.
	 */
	public void consume(int tokenType) throws SyntaxError {
		throw new UnsupportedOperationException();
	}
}
