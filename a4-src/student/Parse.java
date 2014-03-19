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
			sugarMomma(sb);
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
	
	/**
	 * Goes through the StringBuffer sb and replaces all mem[x] with
	 * their sugary sweetness pimpnames if they exist. only if they exist.
	 * 
	 * ;)
	 * 
	 * @param sb
	 */
	static public void sugarMomma(StringBuffer sb){
		char[] dst = new char[7];
		for(int i = 0; i < sb.length() - 5; i++){
			sb.getChars(i, i+6, dst, 0);
			if(dst[0] == 109 &&
					dst[1] == 101 &&
					dst[2] == 109 &&
					dst[5] == 93){
				//we expect dst[4] to be an integer.
				int mem = Character.getNumericValue(dst[4]);
				if(mem == 0){
					sb.replace(i, i+6, "MEMSIZE");
				}else if(mem == 1){
					sb.replace(i, i+6, "DEFENSE");
				}else if(mem == 2){
					sb.replace(i, i+6, "OFFENSE");
				}else if(mem == 3){
					sb.replace(i, i+6, "SIZE");
				}else if(mem == 4){
					sb.replace(i, i+6, "ENERGY");
				}else if(mem == 5){
					sb.replace(i, i+6, "PASS");
				}else if(mem == 6){
					sb.replace(i, i+6, "TAG");
				}else if(mem == 7){
					sb.replace(i, i+6, "POSTURE");
				}
			}
		}
	}
	
}
