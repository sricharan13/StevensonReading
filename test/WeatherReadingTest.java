import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import weather.StevensonReading;
import weather.WeatherReading;


/**
 * A Test class for testing WeatherReading.
 */
public class WeatherReadingTest {

  private WeatherReading reading;
  private StevensonReading s_reading;

  protected WeatherReading initializeReading(double t, double d, double w, int r) {
    return new StevensonReading(t, d, w, r);
  }

  /**
   * Testcase to check dew temperature is not greater than air temperature.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalDewTemperatureCondition1() {
    reading = initializeReading(34.5, 35.5, 5.4, 1);
    fail("dew point temperature should not be greater than air temperature");
  }

  /**
   * Testcase to check dew temperature is not less than air temperature - 20.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalDewTemperatureCondition2() {
    reading = initializeReading(15, 35.5, 5.4, 1);
    fail("constructor should not initialize values to illegal values");
  }


  /**
   * Testcase to check wind speed is non-negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalWindSpeed() {
    reading = initializeReading(34.5, 31.5, -5.4, 1);
    fail("wind speed should be non-negative");
  }

  /**
   * Testcase to check wind speed is non-negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalTotalRain() {
    reading = initializeReading(34.5, 31.5, 5.4, -1);
    fail("total rain received should be non-negative");
  }

  /**
   * Testcase to check if getTemperature() returns expected value.
   */
  @Test
  public void testGetTemperature() {
    reading = initializeReading(23.432, 12.123, 3.3, 12);
    assertEquals(23, reading.getTemperature());
  }

  /**
   * Testcase to check if getDewPoint() returns expected value.
   */
  @Test
  public void testGetDewPoint() {
    reading = initializeReading(23.432, 12.123, 3.3, 12);
    assertEquals(12, reading.getDewPoint());
  }

  /**
   * Testcase to check if the getWindChill() returns expected value.
   */
  @Test
  public void testGetWindSpeed() {
    reading = initializeReading(23.432, 12.123, 3.3, 12);
    assertEquals(3, reading.getWindSpeed());
  }

  /**
   * Testcase to check if getTotalRain() returns expected value.
   */
  @Test
  public void testGetTotalRain() {
    reading = initializeReading(23.432, 12.123, 3.3, 12);
    assertEquals(12, reading.getTotalRain());
  }

  /**
   * Testcase to check if getRelativeHumidity() returns expected value.
   */
  @Test
  public void testGetRelativeHumidity() {
    reading = initializeReading(23.432, 12.123, 3.3, 12);
    assertEquals(43, reading.getRelativeHumidity());
  }

  /**
   * Testcase to check if getWindChill() returns expected value.
   */
  @Test
  public void testGetHeatIndex() {
    reading = initializeReading(23.432, 12.123, 3.3, 12);
    assertEquals(77, reading.getWindChill());
  }

  /**
   * Testcase to check if getWindChill() returns expected value.
   */
  @Test
  public void testGetWindChill() {
    reading = initializeReading(22.6, 12.1, 3, 12);
    assertEquals(75, reading.getWindChill());
  }

  /**
   * Testcase to check if toString() is returning the output in expected format.
   */
  @Test
  public void testToString() {
    reading = initializeReading(23.432, 12.123, 3.3, 12);
    assertEquals("Reading: T = 23, D = 12, v = 3, rain = 12", reading.toString());
  }

  /**
   * Testcase to check if equals() is working correctly.
   */
  @Test
  public void testEquals() {
    reading = initializeReading(23.42, 12.23, 3.3, 12);
    s_reading = new StevensonReading(23.42, 12.23, 3.3, 12);
    assertEquals(true, s_reading.equals(reading));
    assertEquals(true, s_reading.equals(s_reading));
    WeatherReading other_reading = initializeReading(25.48, 16.23, 5.3, 12);
    assertEquals(false, s_reading.equals(other_reading));
  }

  /**
   * Testcase to check if hashCode is working correctly.
   */
  @Test
  public void testHashCode() {
    reading = initializeReading(23.42, 12.23, 3.3, 12);
    s_reading = new StevensonReading(23.42, 12.23, 3.3, 12);
    WeatherReading other_reading = initializeReading(25.48, 16.23, 5.3, 12);
    assertEquals(reading.hashCode(), s_reading.hashCode());
    assertNotEquals(reading.hashCode(), other_reading.hashCode());
  }
}