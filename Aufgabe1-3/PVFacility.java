import java.time.LocalDateTime;
import java.util.ArrayList;

public class PVFacility {
    private PVPanel[] panels;

    /* 
     *
     * PRECON:  Valid panels.
     * POSTCON: Object is valid.
     * INVAR:   -
     */
    public PVFacility(PVPanel... panels) {
        this.panels = panels;
    }

    /* 
     *
     * PRECON:  Env is valid and the timespan between from and to is 1 hour.
     * POSTCON: The output is the accumulated production of all panels.
     * INVAR:   getOutput <= getPeak
     */
    public double getOutput(Environment env, LocalDateTime from, LocalDateTime to) {
        double acc = 0;
        for (PVPanel panel: panels) {
            acc += panel.getOutput(env, from, to);
        }
        return acc;
    }

    /* 
     *
     * PRECON:  Env is valid and the timespan between from and to is 1 hour.
     * POSTCON: The output is the accumulated peak production of all panels.
     * INVAR:   getOutput <= getPeak
     */
    public double getPeak(Environment env, LocalDateTime from, LocalDateTime to) {
        double acc = 0;
        //Weather optimal = new Weather(weather.getTemperature(), WEATHER_DESC.SUNNY, weather.getSunInclination(), weather.getSunDirection(), weather.getDirection());
        for (PVPanel panel: panels) {
            acc += panel.getPeak(env, from, to);
        }
        return acc;
    }
}
