import java.security.InvalidParameterException;

public class Weather {
    private int temperature;
    private WEATHER_DESC short_desc;

    private int sunInclination;
    private int sunDirection;
    private DIRECTIONS directions;

    private double wind;
    private double humidity;
    private double sand;

    /**
     * 
     Invariante:
     Vorbedingung: temperature in degrees, short_desc != null, sunInclination in degrees, sunDirection in m, directions != null, wind in m & wind >= 0, humidity in % & humidity >= 0, sand in % and sand >= 0
     Nachbedingung: Erstellt eine Instanz der Klasse mit den Parameter Werten
     */
    public Weather(int temperature, WEATHER_DESC short_desc, int sunInclination, int sunDirection, DIRECTIONS directions, double wind, double humidity, double sand) {
        this.temperature = temperature;
        this.short_desc = short_desc;
        this.sunDirection = sunDirection;
        this.directions = directions;

        this.setHumidity(humidity);
        this.setSand(sand);
        this.setWind(wind);
        this.setSunInclination(sunInclination);
    }

    /**
     *  
     Invariante: wind ist in m/s
     Vorbedingung:
     Nachbedingung: gibt wind zurück
     */
    public double getWind() {
        return wind;
    }

    /**
     *  
     Invariante:
     Vorbedingung: wind ist in m/s
     Nachbedingung: wind parameter unverändert
     */
    public void setWind(double wind) {
        this.wind = wind;
    }

    /**
     *  
     Invariante: humidity ist in %
     Vorbedingung:
     Nachbedingung: gibt humidity zurück
     */
    public double getHumidity() {
        return humidity;
    }

    /**
     *  
     Invariante:
     Vorbedingung: humidity ist in m/s
     Nachbedingung: instanzvariable humidity wird der Wert vom Parameter humidity zugewiesen
     */
    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    /**
     *  
     Invariante: sand ist in %
     Vorbedingung:
     Nachbedingung: gibt sand zurück
     */
    public double getSand() {
        return sand;
    }

    /**
     *  
     Invariante:
     Vorbedingung: sand ist in %
     Nachbedingung: instanzvariable sand wird der Wert vom Parameter sand zugewiesen
     */
    public void setSand(double sand) {
        this.sand = sand;
    }

    /**
     *  
     Invariante: temperature is in celsius
     Vorbedingung:
     Nachbedingung: gibt temperatur zurück
     */
    public int getTemperature() {
        return temperature;
    }

    /**
     *  
     Invariante: short_desc is not null
     Vorbedingung:
     Nachbedingung: gibt short_desc zurück
     */
    public WEATHER_DESC getShort_desc() {
        return short_desc;
    }

    /**
     *  
     Invariante: suninclination ist in degree
     Vorbedingung:
     Nachbedingung: gibt sunInclination zurück
     */
    public int getSunInclination() {
        return this.sunInclination;
    }

    /**
     *  
     Invariante:
     Vorbedingung: suninclination ist in Degree
     Nachbedingung:
     instanzvariable sunInclination wird der Wert vom Parameter sunInclination zugewiesen,
     wirft eine Exception wenn inclination negativ ist
     */
    public void setSunInclination(int sunInclination){
        if(sunInclination >= 0){
            this.sunInclination = sunInclination;
        }else{
            throw new InvalidParameterException("SunInclination must be equal or larger than 0");
        }
    }

    /**
     *  
     Invariante: direction of sun in m
     Vorbedingung:
     Nachbedingung: gibt sunDiretion zurück
     */
    public int getSunDirection() {
        return sunDirection;
    }

    /**
     *  
     Invariante: direction is not null
     Vorbedingung:
     Nachbedingung: gibt direction zurück
     */
    public DIRECTIONS getDirection() {
        return directions;
    }
}
