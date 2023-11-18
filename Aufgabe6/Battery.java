//how do you make sure that info is deleted when removed from
//PV-Panel
public class Battery implements Port{
    private double U; // in Volt
    private double I; // in Ampere
    private int loadingTime; // in Minutes
    private int dischargingTime; // in Minutes

    /**
     * VORBEDINGUNG: Voltage and amperage must be positive (>0)
     * INVARIANTE: -
     * NACHBEDINGUNG: Valid Object.
     */
    public Battery(double voltage, double amperage) {
        assert(0 < voltage && 0 < amperage); // would lead to devision by 0
        this.U = voltage;
        this.I = amperage;
        this.loadingTime = 0;
        this.dischargingTime = 0;
    }

    /**
     * VORBEDINGUNG: -
     * INVARIANTE: Does not affect any field.
     * NACHBEDINGUNG: Returnes the set Voltage.
     */
    public double voltage() {return U;}

    /**
     * VORBEDINGUNG: -
     * INVARIANTE: Does not affect any field.
     * NACHBEDINGUNG: Returnes the set Amperage.
     */
    public double amperage() {return I;}

    /**
     * VORBEDINGUNG: -
     * INVARIANTE: Does not affect any field.
     * NACHBEDINGUNG: Returns the accumulated loadingTime.
     */
    public int loadingTime() {return loadingTime;}

    /**
     * VORBEDINGUNG: -
     * INVARIANTE: Does not affect any field.
     * NACHBEDINGUNG: Returns the accumulated discargingTime.
     */
    public int dischargingTime() {return dischargingTime;}

    /**
     * VORBEDINGUNG: Time is not negative.
     * INVARIANTE: Only loadingTime is increased. (shorter than listing everything else)
     * NACHBEDINGUNG: loadingTime is increased by time.
     */
    public void load(int time) {
        loadingTime += time;
    }

    /**
     * VORBEDINGUNG: Time is not negative.
     * INVARIANTE: Only discargingTime is increased. (shorter than listing everything else)
     * NACHBEDINGUNG: discargingTime is increased by time.
     */
    public void discharge(int time) {
        dischargingTime += time;
    }

    /**
     * VORBEDINGUNG: kWh is not negative.
     * INVARIANTE: Does not affect any field.
     * NACHBEDINGUNG: Returns the equivalent loading time for the kWh and this Battery.
     */
    private int timeFromkWh(double kWh) {
        // P = I * U
        // ((P * lT) / 60) / 1000 = (P * lT) / 60000 = kWh

        // P * lT = kWh * 60000
        // lT = (kWh * 60000) / P

        return (int)((kWh * 60000.0) / (U * I));
    }

    /**
     * VORBEDINGUNG: time is not negative.
     * INVARIANTE: Does not affect any field.
     * NACHBEDINGUNG: Returns the equivalent kWh for the provided time and this Battery.
     */
    private double kWhFromTime(int time) {
        // P = I * U
        // ((P * lT) / 60) / 1000 = (P * lT) / 60000 = kWh

        return (I * U * (double)time) / 60000.0;
    }

    /**
     * VORBEDINGUNG: kWh is not negative.
     * INVARIANTE: Only loading time is increased. All other fields are unaffected.
     * NACHBEDINGUNG: loadingTime is increased by the appropriate amount of time.
     */
    @Override
    public void store(double kWh) {
        loadingTime += timeFromkWh(kWh);
    }

    /**
     * VORBEDINGUNG: kWh is not negative.
     * INVARIANTE: Only discharge time is increased. All other fields are unaffected.
     * NACHBEDINGUNG: dischargeTime is increased by the appropriate amount of time.
     */
    @Override
    public void take(double kWh) {
        dischargingTime += timeFromkWh(kWh);
    }

    /**
     * VORBEDINGUNG: -
     * INVARIANTE: Does not affect any field.
     * NACHBEDINGUNG: Returns the accumulated loadingTime.
     */
    @Override
    public double stored() {
        return kWhFromTime(loadingTime);
    }

    /**
     * VORBEDINGUNG:
     * INVARIANTE: Does not affect any field.
     * NACHBEDINGUNG: Returns the accumulated dischargingTime.
     */
    @Override
    public double taken() {
        return kWhFromTime(dischargingTime);
    }

//    @Override
//    public PortType type() {
//        return PortType.Battery;
//    }
}
