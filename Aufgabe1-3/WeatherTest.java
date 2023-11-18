public class WeatherTest {
    /**
     *  
     * Invariante: this
     * Vorbedingung:
     * Nachbedingung: Assert ob Temperature, desc, sunInclination, sunDirection, wind, humidity und sand
     * den zugewiesenen Wert bekommen hat
     */
    public static void test() {
        int temperature = 10;
        String desc = "SUNNY";
        int sunInclination = 4;
        int sunDirection = 2;
        double wind = 15;
        double humidity = 10;
        double sand = 1;

        Weather weather = new Weather(temperature, WEATHER_DESC.valueOf(desc), sunInclination, sunDirection, DIRECTIONS.SOUTH, wind, humidity, sand);

        assert(temperature == weather.getTemperature());
        assert(WEATHER_DESC.valueOf(desc) == weather.getShort_desc());
        assert(sunInclination == weather.getSunInclination());
        assert(sunDirection == weather.getSunDirection());
        assert(wind == weather.getWind());
        assert(humidity == weather.getHumidity());
        assert(sand == weather.getSand());
    }
}
