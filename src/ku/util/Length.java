package ku.util;

/**
 * Contain a name and value of distance measurement unit as a constant with meter as a standard unit.
 * @author Nitith Chayakul
 * @version 1.0
 */
public enum Length implements Unit {
	METER("meter",1.00),
	KILOMETER("kilometer",1000.0),
	CENTIMETER("centimeter",0.01),
	MILE("mile",1609.344),
	FOOT("foot",0.30480),
	WA("wa",2.0),
	LUNAR("lunar distance",384402000.0),
	CHAIN("chain",20.1168);
	
	/** attribute for unit's name */
	private final String name;
	/** attribute for unit's value */
	private final double value;
	
	/** private constructor for store unit's name and value in attribute */
	private Length(String name, double value) {
		this.name = name;
		this.value = value;
	}
	
	/**
	 * Return a Length unit's name.
	 * @see ku.util.Unit#toString()
	 */
	@Override
	public String toString() {
		return name;
	}
	
	/**
	 * Return a Length unit's value with meter as standard unit.
	 * @see ku.util.Unit#getValue()
	 */
	public double getValue() { 
		return value; 
	}
	
}
