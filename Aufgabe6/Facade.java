public class Facade extends PVPanel{

    /**
     * VORBEDINGUNG: valid houseNr && producedPower < maxPower && maxPower > producedPower && port != null && direction != null && excessPower > 0
     * INVARIANTE:  -
     * NACHBEDINGUNG: creates instance with given parameters
     */
    public Facade(int houseNr, double maxPower, double producedPower, CardinalPoint direction, int inclinationDirection) {
        super(houseNr, maxPower, producedPower, direction, inclinationDirection);
    }

    /**
     * VORBEDINGUNG: direction != null
     * INVARIANTE: -
     * NACHBEDINGUNG: if direction equals SOUTH then inclinationdirection is set to 0 otherwise parameter value
     *      * is set for instance variable inclinationdirection
     */
    @Override
    protected void setInclinationDirection(double inclinationDirection){
        if(inclinationDirection >= -180 && inclinationDirection <= 180){
            if(getDirection().equals(CardinalPoint.SOUTH)){
                this.inclinationDirection = 0;
            }else{
                this.inclinationDirection = inclinationDirection;
            }
        }else{
            throw new RuntimeException("Inclination from -180 to 180 only");
        }
    }
}
