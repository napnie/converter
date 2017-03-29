package ku.util;

/**
 * Convert from one unit to another unit.
 * @author Nitith Chayakul
 * @version 1.0
 */
public class UnitConverter {
	
	/**
	 * Convert from one unit to another.
	 * @param amount - amount from unit that convert from.
	 * @param fromUnit - unit that convert from
	 * @param toUnit - unit that convert into
	 * @return amount in unit that convert into
	 */
	public double convert(double amount, Unit fromUnit, Unit toUnit) {
		double result = amount * ( fromUnit.getValue()/toUnit.getValue() ) ;
		return result;
	}
	
	/**
	 * Return an array of all unit in Length.
	 * @return array of all Length unit
	 */
	public Unit[] getUnits() {
		return Length.values();
	}
}
