public class Roof extends PVPanel{

    /**
     * VORBEDINGUNG: valid houseNr && producedPower < maxPower && maxPower > producedPower && port != null && direction != null && excessPower > 0
     * INVARIANTE:  -
     * NACHBEDINGUNG: creates instance with given parameters
     */
    public Roof(int houseNr, double maxPower, double producedPower, CardinalPoint direction, int inclinationDirection) {
        super(houseNr, maxPower, producedPower, direction, inclinationDirection);
    }

    /**
     * VORBEDINGUNG: direction != null && inclinationDirection >= 0 && inclinationDirection <= 90
     * INVARIANTE: -
     * NACHBEDINGUNG: if direction equals SOUTH then inclinationdirection is set to 0 otherwise parameter value
     *      * is set for instance variable inclinationdirection
     */
    @Override
    protected void setInclinationDirection(double inclinationDirection){
        if(inclinationDirection >= 0 && inclinationDirection <= 90){
            this.inclinationDirection = inclinationDirection;
        }else{
            throw new RuntimeException("Inclination from 0 to 90 only.");
        }
    }
}
