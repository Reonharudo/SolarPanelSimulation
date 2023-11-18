import java.util.HashMap;
import java.util.Map;

/**
 * INVARIANTE: currentFailedDirectionsMap != null und alle keys instance of Direction und alle values vom Typ boolean
 */
public class CircularMovementStrategy extends MovementStrategy{
    private Map<Direction, Boolean> currentFailedDirectionsMap;
    public CircularMovementStrategy(){
        initCurrentFailedDirectionsMap();
    }

    /**
     * VORBEDINGUNG: -
     * NACHBEDINGUNG: currentFailedDirectionsMap enthält keine Key-Value-Pairs mehr
     */
    private void initCurrentFailedDirectionsMap(){
        currentFailedDirectionsMap = new HashMap<>();
        this.currentFailedDirectionsMap.put(Direction.North, true);
        this.currentFailedDirectionsMap.put(Direction.South, true);
        this.currentFailedDirectionsMap.put(Direction.East, true);
        this.currentFailedDirectionsMap.put(Direction.West, true);
    }

    /**
     * VORBEDINGUNG: -
     * NACHBEDINGUNG: Gibt nächste Direction zurück.
     * Zuerst wird North zurückgegeben mit folgender chronologischer Strategie:
     * wenn der Value vom Key "North" in currentFailedDirectionsMap == false ist, wird North zurückgegben
     * wenn der Value vom Key "West" in currentFailedDirectionsMap == false ist, wird West zurückgegben
     * wenn der Value vom Key "South" in currentFailedDirectionsMap == false ist, wird South zurückgegben
     * wenn der Value vom Key "East" in currentFailedDirectionsMap == false ist, wird East zurückgegben
     */
    @Override
    public Direction retrieveStrategy() {
        if(currentFailedDirectionsMap.get(Direction.West)){
            return Direction.West;
        }else if(currentFailedDirectionsMap.get(Direction.North)) {
            return Direction.North;
        }else if(currentFailedDirectionsMap.get(Direction.East)) {
            return Direction.East;
        }else if(currentFailedDirectionsMap.get(Direction.South)){
            return Direction.South;
        }else{
            initCurrentFailedDirectionsMap();
            return Direction.West;
        }
    }

    /**
     * VORBEDINGUNG: retrieveStrategy() wurde vor dem Aufruf dieser Methode aufgerufen
     * NACHBEDINGUNG: Wenn ok, wird der Value vom ersten Key-Value-Pair von entweder North, West, South, East, dessen Value true war, auf false gesetzt.
     */
    @Override
    public void lastRunWas(boolean ok){
        if(currentFailedDirectionsMap.get(Direction.West)){
            currentFailedDirectionsMap.put(Direction.West, ok);
        }else if(currentFailedDirectionsMap.get(Direction.North)) {
            currentFailedDirectionsMap.put(Direction.North, ok);
        }else if(currentFailedDirectionsMap.get(Direction.East)) {
            currentFailedDirectionsMap.put(Direction.East, ok);
        }else if(currentFailedDirectionsMap.get(Direction.South)) {
            currentFailedDirectionsMap.put(Direction.South, ok);
        }
    }
}
