package student;

/**
 * A factory that gives access to instances of parser.
 */
public class ParserFactory {

	/**
	 * Return a parser object for parsing a critter program.
	 * @return
	 */
	public static Parser getParser() {
		Parser over9000 = new ParserImpl();
		return over9000;
	}
}
