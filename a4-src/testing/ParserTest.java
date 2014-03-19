package testing;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringReader;
import java.util.ArrayList;

import mutations.*;
import org.junit.*;
import student.*;
import student.Rule;

@SuppressWarnings("unused")
public class ParserTest {
	
	/*
	 * This test class tests from the following file:
	 * J:\\KINGSTON\\CS2112\\Polyglot1\\a4-src\\student\\example-rules.txt
	 * 
	 */
	
	BufferedReader reader;
	Parser t;
	Program x;
	ArrayList<Rule> rules;
	
	@Before
	public void beforeTests() throws FileNotFoundException, SyntaxError{
		reader = new BufferedReader(new FileReader("J:\\KINGSTON\\CS2112\\Polyglot1\\testfile.txt"));
		t = ParserFactory.getParser();
		x = t.parse(reader);
	}
	
	/*
	 * Supposed to test the parser. but can't really think of any tests.
	 * We will just test that all the connections are made, even though
	 * the prettyprinter kind of does that anyway.
	 */
	@Test
	public void testParser(){
		//Program y = t.parse(new BufferedReader(new StringReader("5 --> eat;")));
		rules = x.getRules();
		assertTrue("WHY IS RULES NULL?", rules != null);
		assertTrue("Links were not made", rules.get(0) != null);	//there are two rules. check to make sure
		assertTrue("Links were not made", rules.get(1) != null);	//that they were assigned.
		
	}
	
	@Test
	public void testMutations(){
		
		System.out.println("5 < 3 --> eat " + x.getRules().get(0).size());
		assertTrue("rule.size is not working", x.getRules().get(0).size() == 5);
		System.out.println("Program.size() = " + x.size());
		assertTrue("program.size is not working", x.size() == 21);
		Critter butt = new Critter(x);
		RuleSetMutation r = new RuleSetMutation(butt, x);
		r.ruleMutation();
		StringBuffer sb = new StringBuffer();
		x.prettyPrint(sb);
		System.out.println(sb.toString());
	}
}
