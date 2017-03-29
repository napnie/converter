package ku.util;

/**
 * Main class of Length Converter.
 * @author Nitith Chayakul
 * @version 1.0
 */
public class Main {
	
	/**
	 * Create and start LengthConverter.
	 * @param arg
	 */
	public static void main(String[]arg) {
		UnitConverter ucon = new UnitConverter();
		ConverterUI ui = new ConverterUI(ucon);
		ui.run();
	}
}
