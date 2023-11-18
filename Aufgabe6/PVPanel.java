
abstract class PVPanel {
    private final int houseNr;
    private double maxPower;
    private double producedPower;
    private Port port;
    private CardinalPoint direction;

    protected double inclinationDirection;

    /**
     * VORBEDINGUNG: valid houseNr && producedPower < maxPower && maxPower > producedPower && port != null && direction != null
     * INVARIANTE:  -
     * NACHBEDINGUNG: creates instance with given parameters
     */
    public PVPanel(int houseNr, double maxPower, double producedPower, CardinalPoint direction, double inclinationDirection) {
        this.houseNr = houseNr;
        this.maxPower = maxPower;
        this.producedPower = producedPower;
        this.direction = direction;
        setInclinationDirection(inclinationDirection);
    }

    public int getHouseNr() {
        return houseNr;
    }

    /**
     * VORBEDINGUNG voltage > 0 & amperage > 0
     * INVARIANTE: -
     * NACHBEDINGUNG: instance variable port is assigned an instance of battery with given parameters
     */
    public void setPortToBattery(double voltage, double amperage) {
        port = new Battery(voltage, amperage);
    }

    /**
     * VORBEDINGUNG -
     * INVARIANTE: -
     * NACHBEDINGUNG: instance variable port is assigned an instance of PowerGrid
     */
    public void setPortToPowerGrid() {
        port = new PowerGrid();
    }

    /**
     * VORBEDINGUNG port is assigned an instance of class that implemented Port e.g Battery or PowerGrid
     * INVARIANTE: -
     * NACHBEDINGUNG: returns associated PortType Value
     */
    public PortType getPortType() {
        if (port == null) {
            throw new RuntimeException("Trying to probe for PortType on null Object!");
        } else if (port instanceof Battery) {
            return PortType.Battery;
        } else if (port instanceof PowerGrid) {
            return PortType.PowerGrid;
        } else {
            throw new RuntimeException("Port is neither Battery nor PowerGrid!");
        }
    }

    /**
     * VORBEDINGUNG kWh > 0,  port is assigned an instance of class that implemented Port e.g Battery or PowerGrid
     * INVARIANTE: -
     * NACHBEDINGUNG: stores given excess power to port
     */
    public void storeToPort(double kWh) {
        port.store(kWh);
    }

    /**
     * VORBEDINGUNG voltage > 0 & amperage > 0,  port is assigned an instance of class that implemented Port e.g Battery or PowerGrid
     * INVARIANTE: -
     * NACHBEDINGUNG: returns stored excess power of port
     */
    public double storedToPort() {
        return port.stored();
    }

    /**
     * VORBEDINGUNG port is assigned an instance of class that implemented Port e.g Battery or PowerGrid
     * INVARIANTE: -
     * NACHBEDINGUNG: instance variable port is assigned an instance of battery with given parameters
     */
    public void takeFromPort(double kWh) {
        port.take(kWh);
    }

    /**
     * VORBEDINGUNG voltage > 0 & amperage > 0,  port is assigned an instance of class that implemented Port e.g Battery or PowerGrid
     * INVARIANTE: -
     * NACHBEDINGUNG: instance variable port is assigned an instance of battery with given parameters
     */
    public double takenFromPort() {
        return port.taken();
    }


    /**
     * VORBEDINGUNG port is assigned a Battery class
     * INVARIANTE: -
     * NACHBEDINGUNG: returns voltage of connected battery (over port)
     */
    public double batteryVoltage() {
        if (port instanceof Battery) {
            return ((Battery) port).voltage();
        } else {
            throw new RuntimeException("Cannot get Attribute of Battery from something that is not a Battery!");
        }
    }

    /**
     * VORBEDINGUNG port is assigned a Battery class
     * INVARIANTE: -
     * NACHBEDINGUNG: returns amperage of connected battery (over port)
     */
    public double batteryAmperage() {
        if (port instanceof Battery) {
            return ((Battery) port).amperage();
        } else {
            throw new RuntimeException("Cannot get Attribute of Battery from something that is not a Battery!");
        }
    }

    /**
     * VORBEDINGUNG port is assigned a Battery class
     * INVARIANTE: -
     * NACHBEDINGUNG: returns loadingTime of connected battery (over port)
     */
    public int batteryLoadingTime() {
        if (port instanceof Battery) {
            return ((Battery) port).loadingTime();
        } else {
            throw new RuntimeException("Cannot get Attribute of Battery from something that is not a Battery!");
        }
    }

    /**
     * VORBEDINGUNG port is assigned a Battery class
     * INVARIANTE: -
     * NACHBEDINGUNG: returns dischargingTime of connected battery (over port)
     */
    public int batteryDischargeTime() {
        if (port instanceof Battery) {
            return ((Battery) port).dischargingTime();
        } else {
            throw new RuntimeException("Cannot get Attribute of Battery from something that is not a Battery!");
        }
    }

    /**
     * VORBEDINGUNG port is assigned a Battery class
     * INVARIANTE: -
     * NACHBEDINGUNG: changes load time by specified time
     */
    public void BatteryLoad(int time) {
        if (port instanceof Battery) {
            ((Battery) port).load(time);
        } else {
            throw new RuntimeException("Cannot change Battery Attribute from something that is not a Battery!");
        }
    }

    /**
     * VORBEDINGUNG port is assigned a Battery class
     * INVARIANTE: -
     * NACHBEDINGUNG: changes discharge time by specified time
     */
    public void BatteryDischarge(int time) {
        if (port instanceof Battery) {
            ((Battery) port).discharge(time);
        } else {
            throw new RuntimeException("Cannot change Battery Attribute from something that is not a Battery!");
        }
    }

    /**
     * VORBEDINGUNG: maxPower > producedPower
     * INVARIANTE:maxPower > producedPower
     * NACHBEDINGUNG: returns instance variable maxPower
     */
    public double getMaxPower() {
        return maxPower;
    }

    /**
     * VORBEDINGUNG: -
     * INVARIANTE:
     * NACHBEDINGUNG: returns instance member inclinationDirection
     */
    public double getIncline() {
        return inclinationDirection;
    }

    /**
     * VORBEDINGUNG: maxPower < producedPower
     * INVARIANTE:maxPower < producedPower
     * NACHBEDINGUNG: returns produced power
     */
    public double getProducedPower() {
        return producedPower;
    }

    /**
     * VORBEDINGUNG: direction != null
     * INVARIANTE: direction != null
     * NACHBEDINGUNG: returns instance variable direction
     */
    protected CardinalPoint getDirection() {
        return direction;
    }

    /**
     * VORBEDINGUNG: maxPower >= this.producedPower + producedPower
     * INVARIANTE: -
     * NACHBEDINGUNG: sets sum of this.producedPower + given parameter producedPower
     */
    public double riseProducedPower(double producedPower){
        double newProducedPower = this.producedPower + producedPower;
        if(newProducedPower <= maxPower){
            return this.producedPower = newProducedPower;
        }else{
            throw new RuntimeException("ProducedPower may not exceed maxPower");
        }
    }

    /**
     * VORBEDINGUNG: direction != null
     * INVARIANTE: -
     * NACHBEDINGUNG: -
     */
    abstract protected void setInclinationDirection(double inclinationDirection);

    @Override
    public String toString() {
        return "PVPanel{" +
                "houseNr=" + houseNr +
                ", maxPower=" + maxPower +
                ", producedPower=" + producedPower +
                ", port=" + port +
                ", direction=" + direction +
                ", inclinationDirection=" + inclinationDirection +
                '}';
    }
}
