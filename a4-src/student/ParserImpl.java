package student;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

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
			//for(Rule rule : program.rules) parseRule(rule);
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
				program.rules.add(parseRule(ruleTokens));
				System.out.println("From parseProgram(): " + ruleTokens.toString());
				ruleTokens.clear();
			}
		}
		return program;
	}
	
	/**
	 * The game plan: parseRule accepts some tokens from the boss. This class
	 * will make a call down to parseCondition with a given set of tokens.
	 * 
	 * @return
	 * @throws SyntaxError
	 */
	public Rule parseRule(ArrayList<Token> tokens) throws SyntaxError {
		ArrayList<Token> bucket = new ArrayList<Token>();
		Rule thisRule = new Rule();
		int arrows = 0;
		for(Token i : tokens){
			if(i.getType() != Token.ARR){
				bucket.add(i);
			} else if (i.getType() == Token.ARR){
				arrows++;
				if (arrows == 2) throw new SyntaxError();
				thisRule.setCondition(parseCondition(bucket));
				System.out.println("From parseRule(): " + bucket.toString());
				bucket.clear();
			}
		}
		thisRule.setAction(parseAction(bucket));
		
		//this is the complicated algorithm to parse the updates and figure out
		//where one update ends and where another begins.
		int walk = tokens.indexOf(Token.ARR);
		int mem;
		ArrayList<Token> bin = new ArrayList<Token>();
		while(tokens.subList(walk, tokens.size() - 1).contains(Token.ASSIGN)){
			mem = tokens.indexOf(Token.MEM);
			
			
			thisRule.addUpdates(parseUpdate(bin));
		}
		thisRule.checkSemantic();
		return thisRule;
	}

	/**
	 * This is called by parseRule. It will take an ArrayList as an argument
	 * and do the hard work of figuring out what goes where. This is really
	 * just a helper function.
	 * 
	 * @return
	 * @throws SyntaxError
	 */
	public Condition parseCondition(ArrayList<Token> tokens) 
			throws SyntaxError {
		//case 1: there is no AND and no OR
		if(!tokens.contains(Token.AND) && 
				!tokens.contains(Token.OR)){
			//the entire condition is one relation
			
		}
		return null;
		//return new Condition();
	}
	
	public Update parseUpdate(List<Token> tokens){
		return null;
		
	}
	
	/**
	 * Determines if there is an action in the set of tokens passed to it
	 * 
	 * @param tokens
	 * @return NONE if there is no action.
	 */
	public Action parseAction(ArrayList<Token> tokens){
		for(Action action : Action.values()){
			if(tokens.contains(action)) return action;
		}
		return Action.NONE;
	}
	
	//When the Condition is true, then this action may be triggered.
	public void triggerAction(Action act){
		ActionSwitch aswitch = new ActionSwitch(act);
		aswitch.takingAction();
	}

	public Expression parseExpression(ArrayList<Token> tokens) throws SyntaxError {
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
