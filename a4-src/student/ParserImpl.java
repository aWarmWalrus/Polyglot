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
				System.out.println();
				System.out.println("+The Rule: " + ruleTokens.toString());
				program.rules.add(parseRule(ruleTokens));
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
//		System.out.println("called parseRule()");
		ArrayList<Token> bucket = new ArrayList<Token>();
		Rule thisRule = new Rule();
		int arrows = 0;
		for(Token i : tokens){
			if(i.getType() != Token.ARR){
				bucket.add(i);
			} else if (i.getType() == Token.ARR){
				arrows++;
				if (arrows == 2) throw new SyntaxError("");
				System.out.println("    -The Condition: " + bucket.toString());
				thisRule.setCondition(parseCondition(bucket));
				bucket.clear();
			}
		}
		System.out.println("    -The Command: " + bucket.toString());
		thisRule.setAction(parseAction(bucket));
		thisRule.addUpdates(parseUpdate(bucket));
		thisRule.checkSemantic();
		return thisRule;
	}

	/**
	 * This is called by parseRule. It will take an arraylist of tokens 
	 * as an argument and do the hard work of figuring out what goes 
	 * where. This is really just a helper function.
	 * 
	 * @return
	 * @throws SyntaxError
	 */
	public Condition parseCondition(ArrayList<Token> tokens) 
			throws SyntaxError {
//		System.out.println("called parseCondition()");
		int status = 0;
		ArrayList<Token> bucket = new ArrayList<Token>();
		
		//if there is no AND or OR outside brackets, pass EVERYTHING as a relation.
		if(!containsToken(tokens, Token.OR) && 
				!containsToken(tokens, Token.AND)){
			//there is no AND or OR operators here. Must be a relation.
			System.out.println("          >Relation: " + tokens.toString());
			return parseRelation(tokens);
		}
		
		BinaryCondition condition = new BinaryCondition();
		
		for(Token i : tokens){
			
			//Scan through tokens normally.
			if (status == 0){
				if(i.getType() == Token.LBRACE){
					//this should only happen at beginning or right after an op
					status = -1;
					if(containsToken(tokens, Token.OR)) bucket.add(i);
					continue;
				} else if (i.getType() == Token.OR){
					System.out.println("       >Left: " + bucket.toString());
					condition.setOp(BinaryConditionOperator.OR);
					System.out.println("       >Operator: " + BinaryConditionOperator.OR);
					condition.setLeft(parseCondition(bucket));
					bucket.clear();
					continue;
				} else if (i.getType() == Token.AND){
					if(containsToken(tokens, Token.OR)) {
						bucket.add(i);
						continue;
					}
					System.out.println("       >Left: " + bucket.toString());
					condition.setOp(BinaryConditionOperator.AND);
					System.out.println("       >Operator: " + BinaryConditionOperator.AND);
					condition.setLeft(parseCondition(bucket));
					bucket.clear();
					continue;
				}
				//this should never happen if just ended a brace. but whatever. will fix later.
				bucket.add(i);
			}
			
			//Scan through things in a brace, ignoring ADD and OR, exclude [, ]
			else if (status < 0){
				if(i.getType() == Token.LBRACE){
					status--;
				}
				else if(i.getType() == Token.RBRACE){
					status++;
					if(status != 0) bucket.add(i);
					if(containsToken(tokens, Token.OR)) bucket.add(i);
					continue;
				}
				bucket.add(i);
			}
			
			//run through to end.
			else if (status == 2){
				bucket.add(i);
			}
		}
		
		if(!bucket.isEmpty()){
			System.out.println("       >Right: " + bucket.toString());
			condition.setRight(parseCondition(bucket));

			return condition;
			
		}
		System.out.println("THIS NEVER REALLY HAPPENS DOES IT?");
		return condition;
	}
	
	/**
	 * Essentially parses everything after the --> token. Determines what is an
	 * update and passes that as a token array into parseExpression
	 * 
	 * @param tokens
	 * @return
	 * @throws SyntaxError
	 */
	public ArrayList<Update> parseUpdate(List<Token> tokens) throws SyntaxError{
//		System.out.println("called parseUpdate()");
		int status = 0;
		ArrayList<Token> expr = new ArrayList<Token>();
		ArrayList<Update> updates = new ArrayList<Update>();
		Update thisUpdate = new Update();
		for (Token i : tokens) {
			
			if (status == 0) {
				if (i.getType() == Token.MEM)
					status = 1;
				continue;
			} 
			
			else if (status == 1) {
				if (i.getType() != Token.LBRACKET)
					throw new SyntaxError("");
				status = 2;
				continue;
			} 
			
			//ensnare the expression inside the brackets
			else if (status == 2) {
				if (i.getType() != Token.RBRACKET)
					expr.add(i);
				else{
					System.out.println("        >Update Left: mem" + expr.toString());
					thisUpdate.setMemIndex(parseExpression(expr));
					expr.clear();
					status = 3;
					continue;
				}
			} 
			
			//Just checking syntax. If there's no assign, then everything we know is wrong
			else if (status == 3) {
				if (i.getType() != Token.ASSIGN)
					throw new SyntaxError("Missing an := token");
				status = 4;
				continue;
			}
			
			//The big one. Checks if the token is an action,
			//if the token is a mem or sensor token, in which case it expects
			//something inside a pair of brackets.
			//if the token is a number, in which case it looks for an op
			else if (status == 4) {
				if (i.isAction()){
					//the current token is an action
					System.out.println("THIS CASE NEVER REALLY HAPPENS RIGHT?");
					if(expr.isEmpty()) throw new SyntaxError("Missing arguments");
					thisUpdate.setAssignment(parseExpression(expr));
				} else if (i.getType() == Token.MEM
						|| i.isSensor()){
					expr.add(i);
					status = 5;
					continue;
				} else if (i.isNum()){
					expr.add(i);
					status = 7;
					continue;
				} else if (i.isAddOp() || i.isMulOp()) //two ops in a row 
					throw new SyntaxError("You have two operators in a row " +
							"or an operator directly behind an assign token");
				else{
					System.out.println("THIS CASE NEVER REALLY HAPPENS RIGHT?");
					expr.add(i);
				}
			}
			
			//Jump to the end of the mem or sensor bracket
			else if (status == 5) {
				expr.add(i);
				if(i.getType() == Token.RBRACKET){
					status = 7;
					continue;
				}
			}
			
			//Endgame. If there is no operator, then it is the end of the update.
			else if (status == 7){
				if (i.isAddOp() || i.isMulOp()){
					expr.add(i);
					status = 4;
				} else if(i.getType() == Token.MEM){
					//in the event that there is still another update
					System.out.println("        >Update Right: " + expr.toString());
					thisUpdate.setAssignment(parseExpression(expr));
					updates.add(thisUpdate);
					expr.clear();
					thisUpdate = new Update();
					status = 1;
					continue;
				} else {	//END OF THE UPDATE!!!! :o
					System.out.println("         >Update Right : " + expr.toString());
					thisUpdate.setAssignment(parseExpression(expr));
					updates.add(thisUpdate);
					thisUpdate = new Update();
					expr.clear();
				}
			}			
		}
		
		//The big loop upstairs won't return an assignment if there is no tokens
		//after the last token of the assignment. This catches that.
		if(!expr.isEmpty()){
			System.out.println("         >Update Right: " + expr.toString());
			thisUpdate.setAssignment(parseExpression(expr));
			updates.add(thisUpdate);
		}
		
		return updates;
	}
	
	/**
	 * Determines if there is an action in the set of tokens passed to it
	 * 
	 * @param tokens
	 * @return NONE if there is no action.
	 * @throws SyntaxError 
	 */
	public Action parseAction(ArrayList<Token> tokens) throws SyntaxError{
		
//		System.out.println("called parseAction");
		
		//check every token passed to it
		for(Token i : tokens){
			
			//check if i is an action
			if(i.isAction()){
				
				//check WHICH action it is
				for(Action action : Action.values()){
					
					//the action that matches the token's type
					if (action.getValue() == i.getType()){
						System.out.print("        >The Action: " + i);
						
						//special case if it is TAG or SERVE action type
						if (i.getType() == Token.TAG ||
								i.getType() == Token.SERVE){
							
							//create a new arraylist to iterate through
							ArrayList<Token> findExpr =
									arraySubList(
											tokens,
											tokens.indexOf(i) + 2, //start after the left bracket
											tokens.size());	   //end at the last token in tokens
							
							int thisToken = tokens.indexOf(i);
							//assume "[" right after mem token
							int lbracket = tokens.get(thisToken + 1).getType(); 
							if(Token.LBRACKET != lbracket) 
								throw new SyntaxError("");
							ArrayList<Token> expr = new ArrayList<Token>();
							for(Token k : findExpr){
								if(k.getType() == Token.RBRACKET){
									action.setExpr(parseExpression(expr));
									break;
								}
								else
								expr.add(k);
							}
							
							System.out.print(expr);
						}
						
						System.out.println();
						return action;
					}	
				}
				
				System.out.println("There is a bug here");
				return Action.NONE;
			}
		}
		return Action.NONE;
	}
	
	/**
	 * Deals with conditions without AND or OR, or conditions expressed inside brackets
	 * 
	 * @param tokens
	 * @return
	 * @throws SyntaxError
	 */
	public Relation parseRelation(ArrayList<Token> tokens) throws SyntaxError{
		
		ArrayList<Token> bucket = new ArrayList<Token>();
		Relation relation = new Relation();
		
		int status = 0;
		
		for(Token i : tokens) {
			
			if (status == 0){
				if (i.getType() == Token.LBRACE){
					//this is to cover the case when the entire condition is inside braces
					//assumes that the last token is a right brace.
					status = 1;
					continue;
				}
				if (i.isComp()){ 		//there is a possibility of there being two equality signs here. syntax error here!
					System.out.println("             ~LeftExpr: " + bucket.toString());
					relation.setLeft(parseExpression(bucket));
					System.out.println("             ~RelOpera: " + RelOperator.getRel(i.getType()));
					relation.setRel(RelOperator.getRel(i.getType()));
					bucket.clear();
					continue;
				}
				bucket.add(i);
			}
			
			else if(status == 1){
				if(i.getType() == Token.RBRACE){
					System.out.println("  :o         ~A Nested Condition!: " + bucket.toString());
					relation.setCondition(parseCondition(bucket));
					return relation;
				}
				bucket.add(i);
			}
		}
		if(bucket.isEmpty()) throw new SyntaxError("Missing a righthand expression!");
		System.out.println("             ~RightExp: " + bucket.toString());
		relation.setRight(parseExpression(bucket));
		return relation;
	}
	
	public Expression parseExpression(ArrayList<Token> tokens) throws SyntaxError {
		
		if(tokens.isEmpty()) throw new SyntaxError("Got an empty set of tokens");
		
		int status = 0;
		boolean reallybadstyle = false;
		ArrayList<Token> bucket = new ArrayList<Token>();
		ArrayList<Token> expr = new ArrayList<Token>();
		ArrayList<Token> wasteofmemory = new ArrayList<Token>();
		BinaryExpression expression = new BinaryExpression();
		SensorMem thisSM = new SensorMem();
		
		//just catch cases when it's a lone number. this will happen a lot.
		if (tokens.size() == 1){
			if (tokens.get(0).getType() != Token.NUM)
				throw new SyntaxError("I don't know what do with this one token. it's not a number");
			NumToken num = (NumToken) tokens.get(0);
			System.out.println("                 @Num: " + num.value);
			return new Num(num.value);
		}
		
		//need to catch cases when everything is inside parenthases.
		
		
		for(Token i : tokens){
			
			//
			if(status == 0){
				
				if (i.getType() == Token.LPAREN){
					status--;
					if(!bucket.isEmpty()){
						bucket.add(i);
						reallybadstyle = true;
					}
					continue;
				}
				
				//will go into this if there is no other mulops. parses left then everything
				//else goes into the right bucket.
				else if (i.isAddOp()){
					System.out.println("                 @Left: " + bucket.toString());
					System.out.println("                 @Op  : " + i.toString());
					expression.setOp(i.getType());
					expression.setLeft(parseExpression(bucket));
					status = 4;
					bucket.clear();
					continue;
				}
				
				//only does this if there are no plus or minus ops
				else if (i.isMulOp()){
					if(containsToken(tokens, Token.PLUS) ||
							containsToken(tokens, Token.MINUS)){
						bucket.add(i);
						continue;
					}
					System.out.println("                 @Left: " + bucket.toString());
					System.out.println("                 @Op  : " + i.toString());
					expression.setOp(i.getType());
					expression.setLeft(parseExpression(bucket));
					status = 4;
					bucket.clear();
					continue;
				}
				
				else if((i.getType() == Token.MEM)||
						i.isSensor()){
					
					if(!bucket.isEmpty()){
						bucket.add(i);
						continue;
					}
					bucket.add(i);
					thisSM.setOption(i.getType());
					status = 10;
					continue;
				}
				bucket.add(i);
			}
			
			else if(status < 0){
				//skipping across a parenthetical statement
				//this assumes that the parentheses are on the left of the 
				//operator.
				if(i.getType() == Token.LPAREN) status--; 
				else if(i.getType() == Token.RPAREN){
					status++;
				}
				if (status == 0) {
					if(reallybadstyle)
						bucket.add(i);
					expression.setLeft(parseExpression(bucket));
					wasteofmemory = (ArrayList<Token>) bucket.clone();
					bucket.clear();
					status = 3;
					continue;
				}
				bucket.add(i);
			}
			
			else if(status == 3){
				if (!i.isMulOp() && !i.isAddOp()){
					throw new SyntaxError("yo expected a operator here bro");
				}
				System.out.println("                 @Op: " + i.toString());
				expression.setOp(i.getType());
				status = 4;
				continue;
			}
			
			else if(status == 4){
				//this assumes that the left and the operator have been found
				//now we're just putting everything into another binary expr
				bucket.add(i);
			}
			
			//last token was a sensormem. must be a lbracket
			else if(status == 10){
				if(i.getType() != Token.LBRACKET)
					throw new SyntaxError("Expect a bracket expression" + 
							"after a sensor or mem");
				status = 11;
				bucket.add(i);
				continue;
			}
			
			//last token was a sensormem. must be a lbracket
			else if(status > 10){
				bucket.add(i);
				if(i.getType() == Token.LBRACKET)
					status++;
				if(i.getType() == Token.RBRACKET){
					status--;
					if(status == 10){
						System.out.println("                    ^Sensormem: " + expr.toString());
						thisSM.setExpression(parseExpression(expr));
						wasteofmemory = (ArrayList<Token>) bucket.clone();
						bucket.clear();
						status = 3;
						continue;
					}
				}
				expr.add(i);
			}
		}
		
		if(bucket.isEmpty()){
			if(thisSM.index != null) return thisSM;
			System.out.println(bucket.toString());
			System.out.println(wasteofmemory.toString());
			return parseExpression(wasteofmemory);
		}
		
		else{
			if(!wasteofmemory.isEmpty()){
				System.out.println("                 @Left: " + wasteofmemory.toString());
				expression.setLeft(parseExpression(wasteofmemory));
			}
			System.out.println("                 @Right: " + bucket.toString());
			expression.setRight(parseExpression(bucket));
			return expression;
		}
	}

	public Expression parseTerm() throws SyntaxError {
		return null;
	}

	
	public Expression parseFactor(ArrayList<Token> tokens) throws SyntaxError {
		
		
		return null;
	}

	public Expression parseAtom() throws SyntaxError {
		throw new UnsupportedOperationException();
	}

	// add more as necessary...
	
	//When the Condition is true, then this action may be triggered.
	public void triggerAction(Action act){
		ActionSwitch aswitch = new ActionSwitch(act);
		aswitch.takingAction();
	}

	/**
	 * Consumes a token of the expected type. Throws a SyntaxError if the wrong
	 * kind of token is encountered.
	 */
	public void consume(int curTok, int tokenType) throws SyntaxError {
		//TODO Charlie I think we're supposed to implement this? maybe?
		throw new UnsupportedOperationException();
	}
	
	/*
	 * Helper function to create an array sublist of type ArrayList<Token>
	 */
	private ArrayList<Token> arraySubList(ArrayList<Token> tokens, int startInd, int endInd){
		ArrayList<Token> toReturn = new ArrayList<Token>();
		for(Token i : tokens){
			int index = tokens.indexOf(i);
			if(index >= startInd && index < endInd){
				toReturn.add(i);
			}
		}
		return toReturn;
	}
	
	/*
	 * Helper function to see if an arraylist of tokens contains a certain
	 * token value.
	 */
	private boolean containsToken(ArrayList<Token> tokens, int tokenValue){
		int status = 0;
		for(Token i : tokens){
			if (status == 0){
				if (tokenValue == i.getType()) return true;
				if (i.getType() == Token.LBRACE ||
						i.getType() == Token.LPAREN){
					status++;
					continue;
				}
			}
			
			//things in the brackets/parenthases are invisible
			else if (status > 0){
				if (i.getType() == Token.LBRACE||
						i.getType() == Token.LPAREN)
					status++;
				if (i.getType() == Token.RBRACE||
						i.getType() == Token.RPAREN) 
					status--;
				continue;
			}
		}
		return false;
	}
}
