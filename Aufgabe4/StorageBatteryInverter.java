import java.util.LinkedList;

public abstract class StorageBatteryInverter extends Inverter {
    public StorageBatteryInverter(LinkedList<Series> modules, LinkedList<FurtherFunctions> f) {
        super(modules, f);
    }
}
