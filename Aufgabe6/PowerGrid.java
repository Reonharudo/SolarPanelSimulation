public class PowerGrid implements Port{
    private double stored = 0; // in kWh
    private double taken = 0; // in kWh

    /**
     * VORBEDINGUNG: kWh is positiv.
     * INVARIANTE: taken stays unaffected.
     * NACHBEDINGUNG: stored goes up by kWh.
     */
    @Override
    public void store(double kWh) {
        stored += kWh;
    }

    /**
     * VORBEDINGUNG: kWh is positiv.
     * INVARIANTE: stored stays unaffected.
     * NACHBEDINGUNG: taken goes up by kWh.
     */
    @Override
    public void take(double kWh) {
        taken += kWh;
    }

    /**
     * VORBEDINGUNG: -
     * INVARIANTE: Does not effect any field.
     * NACHBEDINGUNG: Returnes sum of all store() calls.
     */
    @Override
    public double stored() {
        return stored;
    }

    /**
     * VORBEDINGUNG: -
     * INVARIANTE: Does not affect any field.
     * NACHBEDINGUNG: Returns sum of all take() calls.
     */
    @Override
    public double taken() {
        return taken;
    }

//    @Override
//    public PortType type() {
//        return PortType.PowerGrid;
//    }
}
