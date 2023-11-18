import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class WeatherCourse {
    private Map<LocalDateTime, Weather> history;

    public WeatherCourse(){
        history = new HashMap<>();
    }

    /**
     FEHLER: als Nachbedingung muss für datetime bei == null auch eine InvalidParameterException
     geworfen werden
     */
    /**
     *  
     Invariante: history ist nicht null
     Vorbedingung: datetime und weather sind nicht null
     Nachbedingung: parameter werden in history hinzugefügt,
     wirft eine exception wenn weather null ist.
     */
    public void addWeather(LocalDateTime dateTime, Weather weather){
        if(weather != null){
            history.put(dateTime, weather);
        }else{
            throw new InvalidParameterException("Weather may not be null");
        }
    }


    /**
     *  
     Invariante: history bleibt unverändert
     Vorbedingung: from und to sind nicht null und sind in einem Intervall [from,to]
     Nachbedingung: Gibt den entsprechenden Wetter Eintrag zwischen dem Intervall [from,to] zurück
     */
    public Weather getCurrentWetter(LocalDateTime from, LocalDateTime to){
        int accTemp = 0;
        int accSunInclination = 0;
        int accSunDirection = 0;
        double accWind = 0;
        double accHumidity = 0;
        double accSand = 0;

        List<String> weatherDescList = new ArrayList<>();
        List<String> directionList = new ArrayList<>();

        if(history.keySet().size() == 0){
            throw new RuntimeException("history is empty, cannot run correct calculations");
        }

        int length = 0;
        for(LocalDateTime dateTime : history.keySet()){
            length++;
            if((dateTime.equals(from) || dateTime.isAfter((from))) && (dateTime.equals(to) || dateTime.isBefore(to))){
                Weather weather = history.get(dateTime);
                accTemp += weather.getTemperature();
                accSunInclination += weather.getSunInclination();
                accSunDirection += weather.getSunDirection();
                accWind += weather.getWind();
                accHumidity += weather.getHumidity();
                accSand += weather.getSand();

                weatherDescList.add(weather.getShort_desc().toString());
                directionList.add(weather.getDirection().toString());
            }
        }

        int meanTemp = accTemp / length;
        int meanSunInclination = accSunInclination / length;
        int meanSunDirection = accSunDirection / length;

        double meanWind = accWind / length;
        double meanHumidity = accHumidity / length;
        double meanSand = accSand / length;

        if(weatherDescList.size() == 0 || directionList.size() == 0){ //equals that no weather object was found within time interval from to parameter
            throw new InvalidParameterException("Check your time interval, it does not match with internal history");
        }

        WEATHER_DESC meanWeatherDesc = WEATHER_DESC.valueOf(highestOccurence(weatherDescList));
        DIRECTIONS meanDirections = DIRECTIONS.valueOf(highestOccurence(directionList));

        return new Weather(meanTemp, meanWeatherDesc, meanSunInclination, meanSunDirection, meanDirections, meanWind, meanHumidity, meanSand);
    }

    /**
     *  
     Invariante:
     Vorbedingung: strings ist nicht null
     Nachbedingung: Retourniert den String mit der höchsten Häufigkeit in der Liste
     */
    private String highestOccurence(List<String> strings){
        List<String> values = new ArrayList<>(strings);

        Map<String, Integer> countMap = new HashMap<>();

        //calculate occurence of each string stored in map
        for(String s : values){
            if(countMap.containsKey(s)){//string already in map
                countMap.put(s, countMap.get(s) + 1);
            }else{
                countMap.put(s, 0);
            }
        }

        //analyze occurences of each string
        int highestOccurenceNr = -1;
        String highestOccuredString = ;

        for(String string : countMap.keySet()){
            if(countMap.get(string) > highestOccurenceNr){
                highestOccurenceNr = countMap.get(string);
                highestOccuredString = string;
            }
        }

        return highestOccuredString;
    }

    /**
     * 
     Invariante:
     Vorbedingung: tempLowerBound < tempHigherBound & year is valid year not negative
     Nachbedingung: Retourniert ein WeatherCourse Objekt das die Properties in Abhängigkeit von den Parametern
     erstellt hat.
     */
    public static WeatherCourse randomWeatherCourse(int year, int tempLowerBound, int tempHigherBound){
        WeatherCourse weatherCourseScenario = new WeatherCourse();
        Random random = new Random();

        for(int month = 1; month <= 12; month++){
            YearMonth yearMonthObject = YearMonth.of(year, month);
            int daysInMonth = yearMonthObject.lengthOfMonth(); //28
            for(int day = 1; day <= daysInMonth; day++){
                for(int hour = 0; hour <= 23; hour++){
                    int randomTemp = (tempHigherBound-tempLowerBound)+tempLowerBound;
                    String weatherDesc = ;
                    if(month == 1 || month == 2 || month == 10 || month == 11 || month == 12){
                        weatherDesc = pickRandomValue(WEATHER_DESC.class, SEASON.WINTER);
                    }else{
                        weatherDesc = pickRandomValue(WEATHER_DESC.class, null);
                    }
                    int sunInclination = random.nextInt((180 - 0) + 0);
                    int sunDirection = random.nextInt((180 - 0) + 0);
                    int randomWind = ThreadLocalRandom.current().nextInt(0, 32 + 1);
                    int randomHumidity = ThreadLocalRandom.current().nextInt(0, 100 + 1);
                    int randomSand = ThreadLocalRandom.current().nextInt(0, 100 + 1);

                    String direction = pickRandomValue(DIRECTIONS.class, null);

                    Weather weatherScenario = new Weather(randomTemp, WEATHER_DESC.valueOf(weatherDesc), sunInclination, sunDirection, DIRECTIONS.valueOf(direction), randomWind, randomHumidity, randomSand);
                    weatherCourseScenario.addWeather(LocalDateTime.of(year, month, day, hour, 0), weatherScenario);
                }
            }
        }
        return weatherCourseScenario;
    }

    /**
     *  
     Invariante: e bleibt unverändert
     Vorbedingung: e nicht null
     Nachbedingung: Returns random value of an enum class. If season is not null, passed enum class is further filtered
     * so that it adjust its Winter Occurence
     */
    public static String pickRandomValue(Class<? extends Enum<?>> e, SEASON season){
        String[] enumValues = Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);

        List<String> filteredEnumValues = new ArrayList<>(Arrays.asList(enumValues));

        if(season != null){
            if(season.toString().equals("WINTER")){
                for(String s : enumValues){
                    if(s.equals("SNOWY")){
                        filteredEnumValues.remove(s);
                    }
                }
            }
        }

        int lowerBound = 0;
        int higherBound = filteredEnumValues.size();
        Random random = new Random();
        int randomIndex = random.nextInt( (higherBound+lowerBound)+lowerBound);
        return filteredEnumValues.get(randomIndex);
    }
}
