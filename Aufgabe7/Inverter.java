/**
 * INVARIANTE: kWh >= 0
 */
public abstract class Inverter {
    private double kWh;
    protected final boolean small;

    public Inverter(boolean small) {
        this.kWh = 0;
        this.small = small;
    }

    /**
     * VORBEDINGUNG: kWh >= 0
     * NACHBEDINGUNG: Rückgabe gibt an ob der Inverter kWh aushält.
     */
    protected boolean underkWhlimit(double kWh) {
        assert(0 <= kWh);
        if (small) {
            return kWh <= 5;
        } else {
            return kWh <= 10;
        }
    }

    /**
     * VORBEDINGUNG: -
     * NACHBEDINGUNG: gibt zurück ob this mit den übergebenen Parametern kompatibel ist
     */
    public abstract boolean check(double kW, boolean battery);

    /**
     * VORBEDINGUNG: kWh sind nicht negativ
     * NACHBEDINGUNG: Der Zählerstand steigt um kWh
     */
    public void raisekWh(double kWh) {this.kWh += kWh;}

    /**
     * VORBEDINGUNG: -
     * NACHBEDINGUNG: Der gespeicherte Zählerstand wird zurückgegeben.
     */
    public double getkWh() {
        return kWh;
    }
}
