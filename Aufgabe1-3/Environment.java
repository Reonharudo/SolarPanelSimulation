import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class Environment {
    private List<EnvironmentFactor> factors;
    private WeatherCourse weatherCourse;

    /**
     * 
     Invariante:
     Vorbedingung: weathercours nicht null
     Nachbedingung: Erstellt eine Instanz der Klasse mit den Parameter Werten die den korrespondierenden
     Instanzvariablen zugeordnet werden
     */
    public Environment(WeatherCourse weatherCourse, EnvironmentFactor... factors){
        this.weatherCourse = weatherCourse;
        this.factors = Arrays.asList(factors);
    }


    /**
     * 
     Invariante: weathercourse unverändert
     Vorbedingung: from < to und from != null und to != null
     Nachbedingung: gibt die AirTemperature in Celsius zurück
     */
    public double getAirTemperature(LocalDateTime from, LocalDateTime to){
        Weather weather = weatherCourse.getCurrentWetter(from, to);
        double airTemp = weather.getTemperature();



        for(EnvironmentFactor factor : factors){
            if(factor instanceof Building){
                if(from.getHour() <= 11){
                    airTemp += 0.5;
                }else{
                    if(from.getHour() >= 17){
                        airTemp -= 0.5;
                    }
                }
            }
        }


        for(EnvironmentFactor factor : factors){
            if(factor instanceof LightMaterial){
                if(from.getHour() <= 11){
                    airTemp -= 0.5;
                }else{
                    if(from.getHour() >= 17){
                        airTemp += 0.5;
                    }
                }
            }
        }


        if(airTemp > 25){
            final double absoluteReductionPerTree = 0.25;
            final double absoluteReductionPerSmallPlant = 0.25;

            double absoluteReduction = 0;

            for(EnvironmentFactor factor : factors){
                if(factor instanceof Tree){
                    Tree tree = (Tree)factor;
                    tree.growTo(to);
                    absoluteReduction += absoluteReductionPerTree;
                }else if(factor instanceof SmallPlant){
                    absoluteReduction += absoluteReductionPerSmallPlant;
                }
            }

            airTemp -= absoluteReduction;
        }
        return airTemp;
    }


    /**
     * 
     Invariante: wind bleibt unverändert
     Vorbedingung: from < to und from != null und to != null
     Nachbedingung: gibt Wind in m/s zurück,
     wenn [from,to] nicht gilt, so wird eine Exception geworfen
     */
    public double getWind(LocalDateTime from, LocalDateTime to){
        if(from != null && to != null){
            return weatherCourse.getCurrentWetter(from, to).getWind();
        }else{
            throw new RuntimeException("from and to may not be null");
        }
    }

    /**
     * 
     Invariante: wind bleibt unverändert
     Vorbedingung: from < to und from != null und to != null
     Nachbedingung: gibt Wind in m/s zurück
     */
    public WEATHER_DESC getShort_desc(LocalDateTime from, LocalDateTime to) {
        return weatherCourse.getCurrentWetter(from, to).getShort_desc();
    }
    /**
     * 
     Invariante: humdity bleibt unverändert
     Vorbedingung: from < to und from != null und to != null
     Nachbedingung: gibt humdity in % zurück
     */
    public double getHumidity(LocalDateTime from, LocalDateTime to){
        return weatherCourse.getCurrentWetter(from, to).getHumidity();
    }

    /**
     * 
     Invariante:
     Vorbedingung: from < to und from != null und to != null
     Nachbedingung: gibt SunState zurück
     */
    public SunState getSunState(LocalDateTime from, LocalDateTime to){
        Weather weather = weatherCourse.getCurrentWetter(from, to);
        return new SunState(weather.getSunInclination(), weather.getSunDirection(), weather.getDirection());
    }

    /**
     * 
     Invariante:
     Vorbedingung: from < to und from != null und to != null
     Nachbedingung: gibt Sand in % zurück
     */
    public double getSand(LocalDateTime from, LocalDateTime to){
        return weatherCourse.getCurrentWetter(from, to).getSand();
    }

    /**
     * 
     Invariante: environmentfactors bleibt unverändert
     Vorbedingung: from < to und from != null und to != null
     Nachbedingung: gibt Beschattung in % zurück
     */
    public double getShading(LocalDateTime from, LocalDateTime to, double heightTreshold){
        double accHeight = 0;
        for(EnvironmentFactor factor : factors){
            if(factor.getHeight() >= heightTreshold){
                double diffHeight = factor.getHeight() - heightTreshold;
                double diffHeightNormalized = normalise(diffHeight, 0, 100);
                accHeight += diffHeightNormalized;
            }
        }
        normalise(accHeight, 0, 100);
        return accHeight;
    }

    /**
     * 
     Invariante: environmentfactors bleibt unverändert
     Vorbedingung: from < to und from != null und to != null
     Nachbedingung: gibt logischen binären Zustand zurück ob es schneit
     */
    public boolean isSnowy(LocalDateTime from, LocalDateTime to){
        Weather weather = weatherCourse.getCurrentWetter(from, to);
        if(weather.getShort_desc() == WEATHER_DESC.SNOWY){
            return true;
        }else{
            return false;
        }
    }


    /**
     * 
     Invariante: objekt bleibt unverändert
     Vorbedingung: min < max
     Nachbedingung: gibt die Normalisierungsform im gegeben Interval der gegebenen Zahlen zurück,
     fehler wenn min < max soll geworfen werden
     */
    private double normalise(double inValue, float min, float max) {
        return (inValue - min)/(max - min);
    }
}
