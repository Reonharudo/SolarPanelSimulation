public class HorizontalMovementStrategy extends MovementStrategy{

    private boolean right = true;

    /**
    * NACHBEDINGUNG: solange right auf true gesetzt ist wird die direction East zurückgegeben, sonst wird West zurückgegeben
    **/
    @Override
    public Direction retrieveStrategy() {
        if(right){
            return Direction.East;
        }
        return Direction.West;
    }

    /**
     * VORBEDINGUNG: retrieveStrategy() wurde vor dem Aufruf dieser Methode aufgerufen
     * NACHBEDINGUNG: wenn nicht ok und right wird right = false, sonst right = true
     */
    @Override
    public void lastRunWas(boolean ok) {
        if(!ok && right){
            right = false;
        }else if(!ok){
            right = true;
        }
    }
}
