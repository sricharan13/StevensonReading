package weather;

import java.util.Objects;

/**
 * This class represents a StevensonReading. It contains the data collected from
 * a Stevenson Station.
 */
public final class StevensonReading implements WeatherReading {

  private static final double c1 = -8.78469475556;
  private static final double c2 = 1.61139411;
  private static final double c3 = 2.33854883889;
  private static final double c4 = -0.14611605;
  private static final double c5 = -0.012308094;
  private static final double c6 = -0.0164248277778;
  private static final double c7 = 0.002211732;
  private static final double c8 = 0.00072546;
  private static final double c9 = -0.000003582;

  private final double temperature;
  private final double dewPoint;
  private final double windSpeed;
  private final double relativeHumidity;
  private final double heatIndex;
  private final double windChill;

  private final int totalRain;

  /**
   * Converts Celsius to Fahrenheit.
   *
   * @param celsius temperature in Celsius.
   * @return temperature in Fahrenheit.
   */
  private static double covertToFahrenheit(double celsius) {
    return 9 * (celsius / 5) + 32;
  }

  /**
   * Constructs a StevensonReading object and initializes it to the given readings.
   *
   * @param temperature air temperature in Celsius.
   * @param dewPoint    dew point temperature (never greater than air temperature) in Celsius.
   * @param windSpeed   wind speed (non-negative) in miles per hour.
   * @param totalRain   total rain received (non-negative) in the last 24 hrs in millimeters.
   */
  public StevensonReading(double temperature, double dewPoint, double windSpeed, int totalRain) {

    if (dewPoint > temperature) {
      throw new IllegalArgumentException("dew temperature cannot be greater than air temperature");
    }

    if (temperature - 20 > dewPoint) {
      throw new IllegalArgumentException();
    }

    if (windSpeed < 0) {
      throw new IllegalArgumentException("wind speed cannot be negative");
    }

    if (totalRain < 0) {
      throw new IllegalArgumentException("rain measure cannot be negative");
    }

    this.temperature = temperature;
    this.dewPoint = dewPoint;
    this.windSpeed = windSpeed;
    this.totalRain = totalRain;
    this.relativeHumidity = 100 + 5 * (dewPoint - temperature);

    this.heatIndex = c1 + c2 * temperature + c3 * relativeHumidity
            + c4 * temperature * relativeHumidity + c5 * temperature * temperature
            + c6 * relativeHumidity * relativeHumidity
            + c7 * temperature * temperature * relativeHumidity
            + c8 * temperature * relativeHumidity * relativeHumidity
            + c9 * temperature * temperature * relativeHumidity * relativeHumidity;

    this.windChill = 35.74 + 0.6215 * covertToFahrenheit(temperature)
            - 35.75 * Math.pow(windSpeed, 0.16)
            + 0.4275 * covertToFahrenheit(temperature) * Math.pow(windSpeed, 0.16);
  }

  @Override
  public String toString() {
    return "Reading: " + "T = " + (int) Math.round(temperature)
            + ", D = " + (int) Math.round(dewPoint)
            + ", v = " + (int) Math.round(windSpeed)
            + ", rain = " + totalRain;
  }

  @Override
  public boolean equals(Object o) {

    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    WeatherReading that = (WeatherReading) o;
    return Double.compare(that.getTemperature(), getTemperature()) == 0
            && Double.compare(that.getDewPoint(), getDewPoint()) == 0
            && Double.compare(that.getWindSpeed(), getWindSpeed()) == 0
            && Double.compare(that.getRelativeHumidity(), getRelativeHumidity()) == 0
            && Double.compare(that.getHeatIndex(), getHeatIndex()) == 0
            && Double.compare(that.getWindChill(), getWindChill()) == 0
            && getTotalRain() == that.getTotalRain();
  }

  @Override
  public int hashCode() {
    return Objects.hash(getTemperature(), getDewPoint(), getWindSpeed(), getRelativeHumidity(),
            getHeatIndex(), getWindChill(), getTotalRain());
  }

  @Override
  public int getTemperature() {
    return (int) Math.round(temperature);
  }

  @Override
  public int getDewPoint() {
    return (int) Math.round(dewPoint);
  }

  @Override
  public int getWindSpeed() {
    return (int) Math.round(windSpeed);
  }

  @Override
  public int getTotalRain() {
    return totalRain;
  }

  @Override
  public int getRelativeHumidity() {
    return (int) Math.round(relativeHumidity);
  }

  @Override
  public int getHeatIndex() {
    return (int) Math.round(heatIndex);
  }

  @Override
  public int getWindChill() {
    return (int) Math.round(windChill);
  }
}
