import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static java.lang.Math.max;

public class Simulation {

    private PVFacility pv;
    private ElectricityUsage eu;
    private LocalDateTime start;

    private Environment env;

    /* 
     *
     * PRECON:  Valid start, pv, env, and eu.
     * POSTCON: The Object is in a valid state.
     * INVAR:   -
     */
    public Simulation(LocalDateTime start, PVFacility pv,Environment env, ElectricityUsage eu) {
        this.start = start;
        this.pv = pv;
        this.env = env;
        this.eu = eu;
    }

    /* 
     *
     * PRECON:  The setting for the Simulation are valid (start, pv, env, ue, sr)
     * POSTCON: sr holds the hourly result for the Simulation.
     * INVAR:   State of Simulation.
     */
    public void run(SimulationRun sr) {
        LocalDateTime dt = start;
        LocalDateTime end = start.plusYears(1);

        Weather weather = null;
        double curGen = 0;
        double curUse = 0;
        double curPeak = 0;

        for (; dt.isBefore(end); dt = dt.plusHours(1)) {

            //weather = wc.getCurrentWetter(dt, dt.plusHours(1));

            curGen = pv.getOutput(env, dt, dt.plusHours(1));
            curUse = eu.getElectricityUsage(dt);
            curPeak = pv.getPeak(env, dt, dt.plusHours(1));

            sr.add(curUse, curGen, curPeak);
        }
    }
}
