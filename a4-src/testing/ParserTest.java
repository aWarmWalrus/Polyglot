package testing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import org.junit.Test;

import student.Parser;
import student.ParserFactory;
import student.Program;
import student.Rule;
import student.Token;

@SuppressWarnings("unused")
public class ParserTest {
	
	/*
	 * This test class tests from the following file:
	 * J:\\KINGSTON\\CS2112\\Polyglot1\\a4-src\\student\\example-rules.txt
	 * 
	 */
	
	@Test
	public void somerandomtest(){
		try {
			BufferedReader reader = new BufferedReader(new FileReader("J:\\KINGSTON\\CS2112\\Polyglot1\\a4-src\\student\\example-rules.txt"));
			Parser t = ParserFactory.getParser();
			Program x = t.parse(reader);
			ArrayList<Rule> rules = x.getRules();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
