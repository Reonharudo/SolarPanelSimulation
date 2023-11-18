import java.time.LocalDateTime;
import java.util.Random;


public class ElectricityUsage {
    private double kWperday;
    private Random rng;

    /* 
     *
     * PRECON:  Valid rng and area, people and bigAppliances are non-negative.
     * POSTCON: Valid ElectricityUsage
     * INVAR:   -
     */
    public ElectricityUsage(Random rng, boolean elektrikHeating, int area, int people, int bigAppliances) {
        this.rng = rng;
        double perperson = 200.0f;
        if (elektrikHeating) perperson += 550.0f;
        this.kWperday = ( area * 9.0f + people * perperson + bigAppliances * 200.0f) / 365.0f;
    }

    /* 
     *
     * PRECON:  Valid rng and non-negative kWperday
     * POSTCON: Valid ElectricityUsage Object.
     * INVAR:   -
     */
    public ElectricityUsage(Random rng, double kWperday) {
        this.rng = rng;
        this.kWperday = kWperday;
    }

    /* 
     *
     * PRECON:  Hour is between 0 and 24.
     * POSTCON: Output is fraction of daily Electicity usage.
     * INVAR:   Sum of all hours per day is 1. State of ElectricityUsage.
     */
    private static double hourOfDayMul(int hour) {
        // https://www.energie-lexikon.info/lastprofil.html
        double n = 44.;
        if      (hour <= 4)  return 1   / n;
        else if (hour == 5)  return 1.5 / n;
        else if (hour <= 17) return 2   / n;
        else if (hour <= 21) return 2.5 / n;
        else if (hour == 22) return 2   / n;
        else if (hour == 23) return 1.5 / n;
        else return 0; // unreachable
    }

    /* 
     *
     * PRECON:  Month is between 0 and 11 (incl.)
     * POSTCON: Output is power multiplier for each month.
     * INVAR:   The average of all multipliers is 1. State of ElectricityUsage.
     */
    private static double monthOfYearMul(int month) {
        // https://www.musterhaushalt.de/durchschnitt/stromverbrauch/
        double[] m2m = {
                1.1178451178451179,
                1.027684249906472,
                1.0699588477366255,
                0.9667040778151887,
                0.9397680508791619,
                0.9068462401795735,
                0.9319117096894874,
                0.912832023943135,
                0.9509913954358398,
                1.0216984661429107,
                1.0658436213991769,
                1.0849233071455293
        };

        return m2m[month - 1];
    }

    /* 
     *
     * PRECON:  Valid now.
     * POSTCON: Output is kWusage in the hour of now. Between 0 and kWperday.
     * INVAR:   State of ElectricityUsage.
     */
    public double getElectricityUsage(LocalDateTime now){
        int hour = now.getHour(); // 0 -> 23
        int month = now.getMonthValue(); // 1 - 12
        double r = 1.0f + (rng.nextGaussian() / 100.0f); // 1% std. deviation
        //System.out.println("    " + kWperday + " * " + hourOfDayMul(hour) + " * " + monthOfYearMul(month) + " * " + r);
        return kWperday * hourOfDayMul(hour) * monthOfYearMul(month) * r;
    }
}
