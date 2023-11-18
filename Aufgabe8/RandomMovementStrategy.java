
public class RandomMovementStrategy extends MovementStrategy{

    private Direction dir;
    private Direction last;


    /**
     * NACHBEDINGUNG: es wird eine random Zahl zwischen 0-3 regeneriert und dementsprechend eine bestimmte Richtung
     * zurückgegeben und diese Richtung wird bei der Objektvariable "last" gespeichert.
     **/
    @Override
    public Direction retrieveStrategy() {
            int decider = ((int) (Math.random() * 100)) % 4;
            if(decider == 0) {
                last = Direction.North;
                return Direction.North; //go top
            }
            if(decider == 1){
                last = Direction.West;
                return Direction.West; //go left
            }
            if(decider == 2){
                last = Direction.East;
                return Direction.East; //go right
            }
            if(decider == 3){
                last = Direction.South;
                return Direction.South; //go bottom
            }
        return null;
    }

    /**
     * VORBEDINGUNG: retrieveStrategy() wurde vor dem Aufruf dieser Methode aufgerufen
     * NACHBEDINGUNG: wenn ok, merkt sich die Objektvariable "dir" die letzte Bewegung, sonst wird die Objektvariable "last"
     * auf die letzte mögliche Bewegung gesetzt
     */
    @Override
    public void lastRunWas(boolean ok) {
        if(ok){
            dir = last;
        }last = dir;
    }
}
