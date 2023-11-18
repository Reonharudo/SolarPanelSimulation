
//Container for the PVPanels
public class Street{
    private List houses;
    private final String name;

    public Street(String name) {
        this.name = name;
        this.houses = new List();
    }

    /**
     * VORBEDINGUNG: -
     * INVARIANTE:  -
     * NACHBEDINGUNG: gets the name of this
     */
    public String getName() {
        return name;
    }

    /**
     * VORBEDINGUNG: s != null
     * INVARIANTE:  -
     * NACHBEDINGUNG: adds a PVPanel to this Street if the given unique characteristic does
     * not exist yet
     */
    public void add(PVPanel s) {
        houses.add(s);
    }

    /**
     * VORBEDINGUNG: s != null
     * INVARIANTE:  -
     * NACHBEDINGUNG: removes a PVPanel from this Street by the unique int of the PVPanel
     */
    public void remove(PVPanel s) {
        houses.remove(s.getHouseNr());
    }

    /**
     * VORBEDINGUNG voltage > 0, amperage > 0, in this.houses must exist a PVPanel with id
     * INVARIANTE: -
     * NACHBEDINGUNG: -
     */
    public void setPortToBattery(double voltage, double amperage, int id) {
        get(id).setPortToBattery(voltage, amperage);
    }

    /**
     * VORBEDINGUNG in this.houses must exist a PVPanel with id
     * INVARIANTE: -
     * NACHBEDINGUNG: instance variable port is assigned an instance of PowerGrid
     */
    public void setPortToPowerGrid(int id) {
        get(id).setPortToPowerGrid();
    }

    /**
     * VORBEDINGUNG in this.houses must exist a PVPanel with id
     * INVARIANTE: -
     * NACHBEDINGUNG: returns associated PortType Value
     */
    public PortType getPortType(int id) {
       return get(id).getPortType();
    }

    /**
     * VORBEDINGUNG: in this.houses must exist a PVPanel with id
     * INVARIANTE:  -
     * NACHBEDINGUNG: find the PVPanel with a certain HouseNr
     */
    private PVPanel get(int id){
        for (Object p:houses) {
            if(p != null) {
                PVPanel panel = (PVPanel) p;
                if(panel.getHouseNr() == id){
                    return panel;
                }
            }
        }return null;
    }

    /**
     * VORBEDINGUNG in this.houses must exist a PVPanel with id
     * INVARIANTE: -
     * NACHBEDINGUNG: returns amperage of connected battery
     */
    public double batteryAmperage(int id) {
        return get(id).batteryAmperage();
    }

    /**
     * VORBEDINGUNG kWh > 0,  in this.houses must exist a PVPanel with id
     * INVARIANTE: -
     * NACHBEDINGUNG: stores given excess power to port
     */
    public void storeToPort(double kWh, int id) {
        get(id).storeToPort(kWh);
    }

    /**
     * VORBEDINGUNG in this.houses must exist a PVPanel with id
     * INVARIANTE: -
     * NACHBEDINGUNG: returns stored excess power of port
     */
    public double storedToPort(int id) {
        return get(id).storedToPort();
    }

    /**
     * VORBEDINGUNG in this.houses must exist a PVPanel with id, kWh > 0
     * INVARIANTE: -
     * NACHBEDINGUNG: takes energy from port if connected port is a battery in kWh
     */
    public void takeFromPort(double kWh, int id) {
        get(id).takeFromPort(kWh);
    }

    /**
     * VORBEDINGUNG in this.houses must exist a PVPanel with id
     * INVARIANTE: -
     * NACHBEDINGUNG: takes energy from port if connected port is a battery
     */
    public double takenFromPort(int id) {
        return get(id).takenFromPort();
    }

    /**
     * VORBEDINGUNG in this.houses must exist a PVPanel with id
     * INVARIANTE: -
     * NACHBEDINGUNG: returns voltage of connected battery
     */
    public double batteryVoltage(int id) {
        return get(id).batteryVoltage();
    }

    /**
     * VORBEDINGUNG in this.houses must exist a PVPanel with id
     * INVARIANTE: -
     * NACHBEDINGUNG: returns loadingTime of connected battery
     */
    public int batteryLoadingTime(int id) {
        return get(id).batteryLoadingTime();
    }

    /**
     * VORBEDINGUNG in this.houses must exist a PVPanel with id
     * INVARIANTE: -
     * NACHBEDINGUNG: returns dischargingTime of connected battery
     */
    public int batteryDischargeTime(int id) {
        return get(id).batteryLoadingTime();
    }

    /**
     * VORBEDINGUNG: in this.houses must exist a PVPanel with id, time > 0
     * INVARIANTE: -
     * NACHBEDINGUNG: changes load time by specified time
     */
    public void BatteryLoad(int time, int id) {
        get(id).BatteryLoad(time);
    }

    /**
     * VORBEDINGUNG port is assigned a Battery class, time > 0
     * INVARIANTE: -
     * NACHBEDINGUNG: changes discharge time by specified time
     */
    public void BatteryDischarge(int time, int id) {
        get(id).BatteryDischarge(time);
    }

    /**
     * VORBEDINGUNG: houses != null
     * INVARIANTE:  -
     * NACHBEDINGUNG: returns static mathods like average maxPower, average inclination,...
     */
    public double staticMethods(StaticMethods s){
        double result = 0;
        int i = 0;
        for (Object p:houses) {
            i++;
            PVPanel panel = (PVPanel) p;
            if(s.equals(StaticMethods.avgMaxPow)) result += panel.getMaxPower();
            if(s.equals(StaticMethods.avgInclination)) result += panel.getIncline();
            if(s.equals(StaticMethods.avgDirection)) result += panel.inclinationDirection;
            if(s.equals(StaticMethods.avgPower)) result += panel.getProducedPower();
            if(s.equals(StaticMethods.avgPowerRoof)){
                if(p instanceof Roof) result += panel.getProducedPower();
            }if(s.equals(StaticMethods.avgPowerFacade)){
                if(p instanceof Facade) result += panel.getProducedPower();
            }if(s.equals(StaticMethods.avgStoredPow)) result += panel.storedToPort();
            if(s.equals(StaticMethods.avgTakenPow)) result += panel.takenFromPort();
        }
        return result/i;
    }

    /**
     * VORBEDINGUNG: -
     * INVARIANTE:  -
     * NACHBEDINGUNG: returns all the PVPanels that are in the list house
     */
    public String toString() {
        return "Street name: "+name + ": \n"+ houses.toString();
    }
}
