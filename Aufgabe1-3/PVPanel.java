import java.time.LocalDateTime;

/* 
 *
 * PRECON: 0 < panelInclination < 180, area > 0, 0 < sunlightAtRightAngle < 180, quality != null, heightTreshold != null
 * BAD: this class has a lot of repetetive and redundant code that could be compromised by using maths, a lot of things
 * is also hard coded and therefore not applicable to similar problems
 *
 * However, it is made very intuitiv and therefore easier to understand for the reader of the code
 *
 */
public class PVPanel {
    private int panelInclination;
    private int area;
    private DIRECTIONS sunlightAtRightAngle;
    //private int sunDirection;
    private PANELMODEL quality;
    private double dewFormation;
    private final double heightTreshold;

    /* 
     *
     * PRECON:  panelInclination != null, area != null, sunlightAtRightAngle != null, quality != null, heightTreshold != null
     * POSTCON: Object is valid.
     * INVAR:   -
     */
    public PVPanel(int panelInclination, int area, DIRECTIONS SunlightAtRightAngle, PANELMODEL quality, double heightTreshold){
        this.panelInclination = panelInclination;
        this.area = area;
        this.sunlightAtRightAngle = SunlightAtRightAngle;
        this.quality = quality;
        this.heightTreshold = heightTreshold;


        /*if (SunlightAtRightAngle.equals(DIRECTIONS.EAST)){
            sunDirection = 0;
        } if (SunlightAtRightAngle.equals(DIRECTIONS.SOUTHEAST)){
            sunDirection = 45;
        } if (SunlightAtRightAngle.equals(DIRECTIONS.SOUTH)){
            sunDirection = 90;
        } if (SunlightAtRightAngle.equals(DIRECTIONS.SOUTHWEST)){
            sunDirection = 135;
        } if (SunlightAtRightAngle.equals(DIRECTIONS.WEST)){
            sunDirection = 180;
        }*/
    }


    /* 
     *
     * PRECON:  env != null and the timespan between from and to is 1 hour
     * POSTCON: The ouput is the production of one panel
     * INVAR:   getOutput <= getPeak
     * GOOD:    class context is strong due to close work of the methods of one class
     */
    public double getOutput(Environment env, LocalDateTime from, LocalDateTime to){
        double power = 0.2 * this.area;


        power = panelPos(power);

        if(env.getShort_desc(from, to).equals(WEATHER_DESC.RAINY)){
            power *= Math.abs(Math.random() - 0.4);
        }if(env.getShort_desc(from, to).equals(WEATHER_DESC.CLOUDY)){
            power *= Math.abs(Math.random() - 0.2);
        }

        if(env.isSnowy(from, to)){
            power = 0;
        }

        power *= this.sunIntensity(env, quality, from, to) * airTemperature(env, from, to) * (100 - env.getShading(from, to, heightTreshold))/100
                * dewFormation(env, from, to) * ((100 - env.getSand(from, to))/100);
        return power;
    }


    /* 
     *
     * PRECON:  env != null and the timespan between from and to is 1 hour
     * POSTCON: The ouput is the peak production of one panel
     * INVAR:   getOutput <= getPeak
     */
    public double getPeak(Environment env, LocalDateTime from, LocalDateTime to){
        double pow = 0.2 * this.area;
        pow = panelPos(pow);
        pow *= sunIntensity(env, PANELMODEL.STEEP, from, to);
        return pow;
    }

    /* 
     *
     * PRECON:  power != null, power > 0
     * POSTCON: panelPos returns the power production depending on the panel's position
     * INVAR:   panelInclination, sunLightAtRightAngle
     */
    private double panelPos(double power){
        if (panelInclination == 45) {
            if (sunlightAtRightAngle.equals(DIRECTIONS.SOUTHWEST) || sunlightAtRightAngle.equals(DIRECTIONS.SOUTHEAST)) {
                power *= 0.95;
            }
            if (sunlightAtRightAngle.equals(DIRECTIONS.EAST) || sunlightAtRightAngle.equals(DIRECTIONS.WEST)) {
                power *= 0.75;
            }
        }
        if (panelInclination == 180) {
            power *= 0.9;
        }
        if (panelInclination == 90) {
            if (sunlightAtRightAngle.equals(DIRECTIONS.SOUTHWEST) || sunlightAtRightAngle.equals(DIRECTIONS.SOUTHEAST)) {
                power *= 0.65;
            }
            if (sunlightAtRightAngle.equals(DIRECTIONS.SOUTH) || sunlightAtRightAngle.equals(DIRECTIONS.WEST)) {
                power *= 0.5;
            }
        }

        if (panelInclination == 90) {
            if (sunlightAtRightAngle.equals(DIRECTIONS.SOUTH)) {
                power *= 0.5;
            }
            if (sunlightAtRightAngle.equals(DIRECTIONS.SOUTHWEST) || sunlightAtRightAngle.equals(DIRECTIONS.SOUTHEAST)) {
                power *= 0.65;
            }
            if (sunlightAtRightAngle.equals(DIRECTIONS.WEST) || sunlightAtRightAngle.equals(DIRECTIONS.EAST)) {
                power *= 0.8;
            }
        }
        return power;
    }

    /* 
     *
     * PRECON:  env != null and the timespan between from and to is 1 hour.
     * POSTCON: airTemperature returns a percentage that takes part to calc the output
     * INVAR:   0 <= airTemperature <= 100
     */
    private double airTemperature(Environment env, LocalDateTime from, LocalDateTime to){
        double percentage = 100;
        if(!(env.getAirTemperature(from, to) - humidity(env, from, to) > 0 && env.getAirTemperature(from, to) - humidity(env, from, to) < 0))
            percentage = 100 - (env.getAirTemperature(from, to) + humidity(env, from, to) + wind(env, from, to));
        if(env.getAirTemperature(from, to) > 100) percentage = 0;
        if(env.getAirTemperature(from, to) < 100) percentage = 100;
        return percentage/100;
    }

    /* 
     *
     * PRECON:  env != null and the timespan between from and to is 1 hour.
     * POSTCON: humidity returns a degree that takes part in the calc in airTemperature,
     *          it is portraying the cooling effect through humidity and also dewFormation
     *          through humidity
     * INVAR:   humidity >= 0
     */
    private double humidity(Environment env, LocalDateTime from, LocalDateTime to){
        double degree = 0;
        if(env.getHumidity(from, to) > 35)  degree = (env.getHumidity(from, to) - 35)/6;
        dewFormation = env.getHumidity(from, to);
        return degree;
    }

    //Note: http://gyre.umeoce.maine.edu/data/gomoos/buoy/php/variable_description.php?variable=wind_2_speed
    /* 
     *
     * PRECON:  env != null and the timespan between from and to is 1 hour.
     * POSTCON: wind returns a degree that takes part in the calc in airTemperature,
     *          it is portraying the cooling effect through humidity
     * INVAR:   0 <= wind <= 12, this
     */
    private double wind(Environment env, LocalDateTime from, LocalDateTime to){
        double degree = 0;
        if(env.getWind(from, to) < 1.6) degree = 1;
        else if(env.getWind(from, to) < 4) degree = 2;
        else if(env.getWind(from, to) < 6) degree = 3;
        else if(env.getWind(from, to) < 9) degree = 4;
        else if(env.getWind(from, to) < 11) degree = 5;
        else if(env.getWind(from, to) < 13.5) degree = 6;
        else if(env.getWind(from, to) < 16.5) degree = 7;
        else if(env.getWind(from, to) < 21) degree = 8;
        else if(env.getWind(from, to) < 23.5) degree = 9;
        else if(env.getWind(from, to) < 27.5) degree = 10;
        else if(env.getWind(from, to) < 31.5) degree = 11;
        else if(env.getWind(from, to) < 32) degree = 12;

        return degree;
    }

    /* 
     *
     * PRECON:  env != null and the timespan between from and to is 1 hour.
     * POSTCON: dewFormation returns a percentage of how strong the dewFormation is
     *          this is also influenced through wind (which reduces dewFormation)
     * INVAR:   0 <= dewFormation <= 100
     */
    private double dewFormation(Environment env, LocalDateTime from, LocalDateTime to){
        double percentage = 100 - dewFormation + env.getWind(from, to);
        if(percentage > 100) percentage = 100;
        if(percentage < 0) percentage = 0;
        return percentage/100;
    }


    /* 
     *
     * PRECON:  env != null, quality != null, and the timespan between from and to is 1 hour.
     * POSTCON: sunIntensity returns a percentage that takes part to calculate the output
     *          it portrays the power production depending on the quality of the panel and
     *          the position of the sun
     * INVAR:   0 <= intensity <= 1, this
     */
    private double sunIntensity(Environment env, PANELMODEL quality, LocalDateTime from, LocalDateTime to){
        double intensity = 1;
        //if(!(env.getSunState(from, to).getSunDirection() + sunDirection <= 180)) intensity = 0;
            if (quality.equals(PANELMODEL.STEEP)) {
                if (!env.getSunState(from, to).getDirections().equals(sunlightAtRightAngle)) {
                    if (sunlightAtRightAngle.equals(DIRECTIONS.SOUTH)) {
                        if (env.getSunState(from, to).getDirections().equals(DIRECTIONS.SOUTHEAST) || env.getSunState(from, to).getDirections().equals(DIRECTIONS.SOUTHWEST))
                            intensity *= 0.5;
                        if (env.getSunState(from, to).getDirections().equals(DIRECTIONS.WEST) || env.getSunState(from, to).getDirections().equals(DIRECTIONS.EAST))
                            intensity *= 0.1;
                    }
                }
                if (sunlightAtRightAngle.equals(DIRECTIONS.WEST)) {
                    if (env.getSunState(from, to).getDirections().equals(DIRECTIONS.SOUTHWEST)) intensity *= 0.5;
                    if (env.getSunState(from, to).getDirections().equals(DIRECTIONS.SOUTH)) intensity *= 0.1;
                    else intensity = 0;
                }
                if (sunlightAtRightAngle.equals(DIRECTIONS.SOUTHWEST)) {
                    switch (env.getSunState(from, to).getDirections()) {
                        case WEST, SOUTH -> intensity *= 0.5;
                        case SOUTHEAST -> intensity *= 0.1;
                        default -> intensity = 0;
                    }
                }
                if (sunlightAtRightAngle.equals(DIRECTIONS.EAST)) {
                    switch (env.getSunState(from, to).getDirections()) {
                        case SOUTHEAST -> intensity *= 0.5;
                        case SOUTH -> intensity *= 0.1;
                        default -> intensity = 0;
                    }
                }
                if (sunlightAtRightAngle.equals(DIRECTIONS.SOUTHEAST)) {
                    switch (env.getSunState(from, to).getDirections()) {
                        case EAST, SOUTH -> intensity *= 0.5;
                        case SOUTHWEST -> intensity *= 0.1;
                        default -> intensity = 0;
                    }
                }
            }
            if (quality.equals(PANELMODEL.FLAT)) {
                if (env.getSunState(from, to).getDirections().equals(sunlightAtRightAngle)) {
                    intensity *= 0.6;
                } else if (sunlightAtRightAngle.equals(DIRECTIONS.SOUTH)) {
                    if (env.getSunState(from, to).getDirections().equals(DIRECTIONS.SOUTHWEST) || env.getSunState(from, to).getDirections().equals(DIRECTIONS.SOUTHEAST))
                        intensity *= 0.95;
                    if (env.getSunState(from, to).getDirections().equals(DIRECTIONS.WEST) || env.getSunState(from, to).getDirections().equals(DIRECTIONS.EAST))
                        intensity *= 0.1;
                    else intensity = 0;
                } else if (sunlightAtRightAngle.equals(DIRECTIONS.WEST)) {
                    if (env.getSunState(from, to).getDirections().equals(DIRECTIONS.SOUTHWEST))
                        intensity *= 0.95;
                    if (env.getSunState(from, to).getDirections().equals(DIRECTIONS.SOUTH))
                        intensity *= 0.1;
                    else intensity = 0;
                } else if (sunlightAtRightAngle.equals(DIRECTIONS.SOUTHWEST)) {
                    if (env.getSunState(from, to).getDirections().equals(DIRECTIONS.WEST) || env.getSunState(from, to).getDirections().equals(DIRECTIONS.SOUTH))
                        intensity *= 0.95;
                    if (env.getSunState(from, to).getDirections().equals(DIRECTIONS.SOUTHEAST)) intensity *= 0.1;
                    else intensity = 0;
                } else if (sunlightAtRightAngle.equals(DIRECTIONS.SOUTHEAST)) {
                    if (env.getSunState(from, to).getDirections().equals(DIRECTIONS.EAST) || env.getSunState(from, to).getDirections().equals(DIRECTIONS.SOUTH))
                        intensity *= 0.95;
                    if (env.getSunState(from, to).getDirections().equals(DIRECTIONS.SOUTHWEST)) intensity *= 0.1;
                    else intensity = 0;
                } else if (sunlightAtRightAngle.equals(DIRECTIONS.EAST)) {
                    if (env.getSunState(from, to).getDirections().equals(DIRECTIONS.SOUTHEAST)) intensity *= 0.95;
                    if (env.getSunState(from, to).getDirections().equals(DIRECTIONS.SOUTH)) intensity *= 0.1;
                    else intensity = 0;
                }
            }


        return intensity;
    }
}