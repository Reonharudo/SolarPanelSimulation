/**
 * INVARIANTE: amperage >= 0
 */
public class Battery{

    private final int amperage;

    public Battery(int amperage) {
        this.amperage = amperage;
    }

    /**
     * VORBEDINGUNG: -
     * NACHBEDINGUNG: returns amperage
     */
    public int getAmperage() {
        return amperage;
    }

    /**
     * VORBEDINGUNG: -
     * NACHBEDINGUNG: returns string representation of this object
     */
    @Override
    public String toString() {
        return "Battery { amperage = " + this.amperage + "}";
    }

}
