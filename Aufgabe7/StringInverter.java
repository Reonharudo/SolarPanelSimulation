/**
 * INVARIANTE: kWh ist nicht negativ.
 */
public class StringInverter extends Inverter {
    public StringInverter(boolean small) {
        super(small);
    }

    /**
     * VORBEDINGUNG: kWh ist nicht negativ.
     * NACHBEDINGUNG:
     * Die Rückgabe gibt an ob this mit den Parametern kompatibel ist. Kompatibel heißt wenn
     * er keine Battery hat und wenn kWh <= 5 ist, und this small ist, oder wenn kWh > 5 ist und this dafür !small ist.
     * Wenn kompatibel, dann true als Rückgabe, ansonsten false
     */
    @Override
    public boolean check(double kWh, boolean battery) {
        return !battery && underkWhlimit(kWh);
    }

    /**
     * VORBEDINGUNG:
     * NACHBEDINGUNG: Representation als String
     */
    @Override
    public String toString() {
        return "StringInverter { count = " + this.getkWh() + ", is_small = " + this.small + "}";
    }
}
