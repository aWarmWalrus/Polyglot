package student;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.StringBuffer;

public class Parse {
	
	/**
	 * 
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		
		try {
//			BufferedReader reader = new BufferedReader(new FileReader(
//					"I:\\KINGSTON\\CS2112\\Polyglot1\\a4-src\\student\\example-rules.txt"));
			
			BufferedReader reader = new BufferedReader(new FileReader(
					"C:\\Users\\Kelly\\Documents\\GitHub\\Polyglot\\a4-src\\student\\example-rules.txt"));
			
			Tokenizer tk = new Tokenizer(reader);
			Parser godaddy = new ParserImpl();
			godaddy.parse(reader);
//			while(tk.hasNext()){
//				System.out.print(tk.next() + " ");
//			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found: ");// + args[0]);
			System.out.println();
			//e.printStackTrace();
		}
		
	}
}
