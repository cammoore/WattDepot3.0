/**
 * 
 */
package org.wattdepot.datamodel;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import javax.xml.datatype.XMLGregorianCalendar;

import org.junit.Test;
import org.wattdepot.util.tstamp.Tstamp;

/**
 * @author Cam Moore
 *
 */
public class TestMeasurement {
  /** Making PMD happy, at the expense of readability. */
  private static final String DEFAULT_TIMESTAMP = "2013-09-14T08:00:00.000-10:00";

  @Test
  public void testMeasurement() throws Exception {
    String key1 = "some-key", value1 = "some-value", key2 = "foo-key", value2 = "foo-value";
    // Make two pairs of Property objects that have same keys &values
    Property prop1 = new Property(key1, value1);
    Property prop2 = new Property(key2, value2);
    Property prop3 = new Property(key1, value1);
    Property prop4 = new Property(key2, value2);
    Properties props1 = new Properties(), props2 = new Properties();
    props1.getProperty().add(prop1);
    props1.getProperty().add(prop2);
    props2.getProperty().add(prop3);
    props2.getProperty().add(prop4);

    XMLGregorianCalendar timestamp1 = Tstamp.makeTimestamp(DEFAULT_TIMESTAMP);
    XMLGregorianCalendar timestamp2 = Tstamp.makeTimestamp(DEFAULT_TIMESTAMP);
    BigDecimal val1 = new BigDecimal(1.0);
    BigDecimal val2 = new BigDecimal(1.0);
    Unit kWh = new Unit("Kilowatt Hour", "kWh");
    Measurement meas1 = new Measurement(timestamp1, val1, kWh, props1);
    Measurement meas2 = new Measurement(timestamp2, val2, kWh, props2);
    assertNotSame("Two newly created Measurment are the same.", meas1, meas2);
    assertEquals("Two Measurement objects with identical values are not equal", meas1, meas2);
    assertEquals("Two Measurement objects with identical values have different hashCodes", 
        meas1.hashCode(), meas2.hashCode());
    Measurement meas3 = new Measurement(Tstamp.incrementHours(timestamp1, 1), val1, kWh, props1);
    assertTrue("Measurement with later timestamp doesn't have positive compareTo", 
        meas3.compareTo(meas2) > 0);
    assertTrue("Measurement with later timestamp doesn't have negative compareTo", 
        meas1.compareTo(meas3) < 0);
  }

  /**
   * Tests that the isInterpolated and setInterpolated methods work properly.
   * 
   * @throws Exception If there are problems creating the timestamp.
   */
  @Test
  public void testInterpolation() throws Exception {
    String key1 = "foo-key", value1 = "foo";
    XMLGregorianCalendar timestamp1 = Tstamp.makeTimestamp(DEFAULT_TIMESTAMP);
    BigDecimal val1 = new BigDecimal(1.0);
    Unit kWh = new Unit("Kilowatt Hour", "kWh");
    Property prop1 = new Property(key1, value1);
    Measurement meas1 = new Measurement(timestamp1, val1, kWh, prop1);
    assertFalse("Measurement incorrectly reports interpolation", meas1.isInterpolated());
    meas1.setInterpolated(true);
    assertTrue("Measurement incorrectly reports interpolation", meas1.isInterpolated());
    meas1.setInterpolated(false);
    assertFalse("Measurement incorrectly reports interpolation", meas1.isInterpolated());
  
  }
}