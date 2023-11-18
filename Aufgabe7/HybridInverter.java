/**
 * INVARIANTE: kWh >= 0 Und falls eine Battery angeschlossen ist, ist deren Amperage <= maxA.
 */
public class HybridInverter extends Inverter {

    private Battery battery;
    private final double maxA;

    /**
     * VORBEDINGUNG: maxA >= 0 und wenn eine battery != null, muss diese weniger als maxA ampere haben.
     * NACHBEDINGUNG: erzeugt eine Instanz der Klasse
     */
    public HybridInverter(boolean small, Battery battery, double maxA) {
        super(small);

        this.battery = battery;
        this.maxA = maxA;

        assert battery == null || (battery.getAmperage() <= maxA);
    }

    /**
     * VORBEDINGUNG: Wenn b nicht null ist muss b.getAmpere() kleiner als maxA
     * NACHBEDINGUNG: Die boolean Rückgabe gibt an ob die batterie hinzugefügt/entfernt wurde.
     */
    public boolean setBattery(Battery b) {
        if (b == null) {
            battery = null;
            return true;
        }
        if (b.getAmperage() <= maxA) {
            battery = b;
            return true;
        }
        return false;
    }

    /**
     * VORBEDINGUNG: kWh >= 0.
     * NACHBEDINGUNG: Die Rückgabe gibt an ob this mit den übergebenen Parametern kompatibel ist. Kompatibel heißt wenn
     * er eine Battery hat und wenn kWh <= 5 ist, und this small ist, oder wenn kWh > 5 ist und this dafür !small ist.
     * Wenn kompatibel, dann true als Rückgabe, ansonsten false
     */
    @Override
    public boolean check(double kWh, boolean battery) {
        return battery && underkWhlimit(kWh);
    }

    /**
     * VORBEDINGUNG: -
     * NACHBEDINGUNG: returns string representation of this object
     */
    @Override
    public String toString() {
        return "HybridInverter { count = " + this.getkWh() + ", is_small = " + this.small + ", Battery = " + this.battery + "}";
    }
}
