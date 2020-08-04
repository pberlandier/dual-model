package odm.duality;

import java.util.BitSet;
import java.util.Hashtable;

/**
 * This class represent a sequence of elements (used here for time period, e.g.
 * hours, days, months, ...) with an dynamically defined set of boolean
 * properties. It allows to perform efficient batch computations on boolean
 * expressions involving the different properties through the use of bit sets.
 * 
 * @author PIERREBerlandier
 *
 */
public class DualTimePeriod {
	private int size;
	private Hashtable<String, Properties> propsTable;

	public DualTimePeriod(int size) {
		this.size = size;
		this.propsTable = new Hashtable<String, Properties>();
	}

	/**
	 * Returns the number of elements with the given property.
	 * 
	 * @param propName
	 * @return
	 */
	public int getCardinality(String propName) {
		return getProperties(propName).cardinality();
	}

	/**
	 * Returns the value of a given property for the time period at a given index.
	 * 
	 * @param index
	 * @param propName
	 * @return
	 */
	public boolean getProperty(int index, String propName) {
		return getProperties(propName).get(index);
	}

	/**
	 * Sets the value of a given property for the time period at a given index.
	 * 
	 * @param index
	 * @param propName
	 */
	public void setProperty(int index, String propName) {
		getProperties(propName).set(index);
	}

	/**
	 * Returns the properties for all the time periods in the sequence.
	 * 
	 * @param propName
	 * @return
	 */
	public Properties getProperties(String propName) {
		Properties props = propsTable.get(propName);
		if (props == null) {
			props = new Properties(size);
			propsTable.put(propName, props);
		}
		return props;
	}

	/**
	 * Sets the properties for all the time periods in the sequence.
	 * 
	 * @param propName
	 * @param props
	 */
	public void setProperties(String propName, Properties props) {
		propsTable.put(propName, props);
	}

	public static DualTimePeriod createSampleInstance(int count) {
		DualTimePeriod instance = new DualTimePeriod(count);
		for (int i = 0; i < count; i++) {
			if (i % 7 == 5) {
				instance.setProperty(i, "saturday");
			} else if (i % 7 == 6) {
				instance.setProperty(i, "sunday");
			}
			//
			// Every 50 days is a holiday!
			//
			if (i % 50 == 0) {
				instance.setProperty(i, "holiday");
			}
			//
			// Worker takes a day off every 42 days.
			//
			if (i % 42 != 0) {
				instance.setProperty(i, "worked day");
			}
		}
		return instance;
	}

	/**
	 * This class is used to perform batch logical operations on properties using a
	 * bit set implementation.
	 * 
	 * @author PIERREBerlandier
	 *
	 */
	public class Properties {
		private int size;
		private BitSet values;

		private Properties(int size) {
			this.size = size;
			this.values = new BitSet(size);
		}

		private Properties(Properties props) {
			this.size = props.size;
			this.values = (BitSet) props.values.clone();
		}

		private boolean get(int index) {
			return values.get(index);
		}

		private void set(int index) {
			values.set(index);
		}

		public int cardinality() {
			return values.cardinality();
		}

		public Properties and(Properties props) {
			Properties result = new Properties(this);
			result.values.and(props.values);
			return result;
		}

		public Properties or(Properties props) {
			Properties result = new Properties(this);
			result.values.or(props.values);
			return result;
		}

		public Properties not() {
			Properties result = new Properties(this);
			result.values.flip(0, result.size);
			return result;
		}
	}
}
