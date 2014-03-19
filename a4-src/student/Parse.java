package student;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.StringBuffer;

import mutations.*;

public class Parse {
	
	/**
	 * Print out a pretty-print display of the parse tree generated by the provided program
	 * The program is found in the input file. Syntax errors will be thrown if
	 * you are mean to this program.
	 * 
	 * The --mutate n tag allows the user to print out a version of
	 * the program contained in the input file that has been mutated
	 * n times. 
	 * 
	 * @param args
	 */
	public static void main(String[] args){

		try{
			if(args[0].equals("--mutate") || args[0].equals("-m")){
				
				int n = Integer.valueOf(args[1]);
				
				BufferedReader reader = new BufferedReader(new FileReader(args[2]));
				Parser godaddy = new ParserImpl();
				Program whatt = godaddy.parse(reader);
				for(int i = 0; i < n; i++){
					
				}
				RuleSetMutation r = new RuleSetMutation(whatt);
				r.ruleMutation();
				StringBuffer sb = new StringBuffer();
				whatt.prettyPrint(sb);
				sugarMomma(sb);
				whiteSpaceMaster(sb);
				System.out.println(sb.toString());
				
			}
			else{
				
				BufferedReader reader = new BufferedReader(new FileReader(args[0]));
				Parser godaddy = new ParserImpl();
				Program whatt = godaddy.parse(reader);
				StringBuffer sb = new StringBuffer();
				whatt.prettyPrint(sb);
				sugarMomma(sb);
				whiteSpaceMaster(sb);
				sb.append(Walrus.kookookachoo);
				System.out.println(sb.toString());
			}	
		}catch(FileNotFoundException e){
			System.out.println("File not found: " + args[0] + "\n");
			System.out.println("Usage: parse.jar [--mutate n] [FILE]\n" +
					"Print out a pretty-print display of the parse tree generated by the provided program\n" +
					"\n" +
					"  -m, --mutate n   Print out a copy of the program mutated n times.");
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("You must provide a file to be parsed.\n\n" +
						"Usage: parse.jar [--mutate n] [FILE]\n" +
						"Prettily print the parse tree generated from the provided program\n" +
						"\n" +
						"  -m, --mutate n   Print out a copy of the program mutated n times.");
		}catch(NumberFormatException e){
			System.out.println("n must be an integer value greater than 0\n\n" +
					"Usage: parse.jar [--mutate n] [FILE]\n" +
					"Prettily print the parse tree generated from the provided program\n" +
					"\n" +
					"  -m, --mutate n   Print out a copy of the program mutated n times.");
		}catch(NullPointerException e){
			System.out.println("We had an oopsies somewhere. NullPointerException \n\n" +
					"Usage: parse.jar [--mutate n] [FILE]\n" +
					"Prettily print the parse tree generated from the provided program\n" +
					"\n" +
					"  -m, --mutate n   Print out a copy of the program mutated n times.");
		}catch(SyntaxError e){
			System.out.println("SYNTAX ERROR\n\n" +
					"Usage: parse.jar [--mutate n] [FILE]\n" +
					"Prettily print the parse tree generated from the provided program\n" +
					"\n" +
					"  -m, --mutate n   Print out a copy of the program mutated n times.");
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
	public static void sugarMomma(StringBuffer sb){
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
	
	/**
	 * Goes through StringBuffer sb and makes everything all lined up
	 * and pretty looking and stuff.
	 * @param sb
	 */
	public static void whiteSpaceMaster(StringBuffer sb){
		int rule = sb.indexOf("-->", 0);
		int longest = rule;
		while(true){
			int lnbreak = sb.indexOf("\n", rule);
			if(lnbreak == -1) break;
			rule = sb.indexOf("-->", lnbreak);
			if(rule == -1) break;
			if(rule - lnbreak >=  45){
				int newbreak = sb.indexOf("and", lnbreak);
				if(newbreak == -1) newbreak = sb.indexOf("or", lnbreak);
				else if(newbreak == -1) newbreak = sb.indexOf("+", lnbreak);
				else if(newbreak == -1) newbreak = sb.indexOf("-", lnbreak);
				else if(newbreak == -1) newbreak = sb.indexOf("*", lnbreak);
				else if(newbreak == -1) newbreak = sb.indexOf("/", lnbreak);
				else if(newbreak == -1) newbreak = sb.indexOf("mod", lnbreak);
				//it has to be one of these. there is no else statement because
				//there is no reason for the string to be long if it doesn't
				//have one of these bastards in them.
				sb.insert(sb.indexOf(" ", newbreak) + 1, "#\n");
				rule = newbreak;
				continue;
			}
			if(rule - lnbreak > longest){
				longest = rule - lnbreak;
			}
		}
		int lnbreak = 0;
		while(true){
			rule = sb.indexOf("-->", lnbreak);
			int hash = sb.indexOf("#", lnbreak);
			if(rule == -1 && hash == -1) break;
			if(hash < rule && hash != -1) rule = hash;
			int leftlength = rule - lnbreak;
			if(leftlength < longest){
				sb.insert(lnbreak + 1, " ");
				continue;
			}
			lnbreak = sb.indexOf("\n", rule);
			if(lnbreak == -1) break;
		}
		while(sb.indexOf("#") != -1){
			sb.deleteCharAt(sb.indexOf("#"));
		}
		
		int george = sb.indexOf("$");
		while(george != -1){
			sb.replace(george, george + 1, "\n");
			for(int i = 0; i < longest + 2; i++)
				sb.insert(george + 1, " ");
			george = sb.indexOf("$");
		}
		
		int spacey = sb.indexOf(";");
		while(spacey != -1){
			sb.insert(spacey + 1, "\n");
			int last = spacey + 2;
			spacey = sb.indexOf(";", last);
		}
	}
}
