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
			Num e = new Num(3);
			
//			BufferedReader reader = new BufferedReader(new FileReader(
//					"J:\\KINGSTON\\CS2112\\Polyglot1\\a4-src\\student\\example-rules.txt"));
			
			BufferedReader reader = new BufferedReader(new FileReader(
					"C:\\Users\\Kelly\\Documents\\GitHub\\Polyglot\\a4-src\\student\\example-rules.txt"));
			Token x = new Token(35, 2);
			System.out.println(x.toString());
			Parser godaddy = new ParserImpl();
			Program whatt = godaddy.parse(reader);
			System.out.println("done!");
			StringBuffer sb = new StringBuffer();
			whatt.prettyPrint(sb);
			System.out.println(sb.toString());
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
