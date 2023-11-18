/**
 * INVARIANTE: kW >= 0
 */
public class Client{
   private final boolean battery;
   private final double kW;

    public Client(boolean battery, double kW) {
        this.battery = battery;
        this.kW = kW;
    }

    /**
     * VORBEDINGUNG: -
     * NACHBEDINGUNG: returns battery boolean
     */
    public boolean getBattery(){
        return battery;
    }

    /**
     * VORBEDINGUNG: -
     * NACHBEDINGUNG: returns kw in kilowatt per hour
     */
    public double getkW() {
        return kW;
    }

    /**
     * VORBEDINGUNG: -
     * NACHBEDINGUNG: returns string representation of this object
     */
    @Override
    public String toString() {
        return "Client{" +
                "battery=" + battery +
                ", kW=" + kW +
                '}';
    }
}
