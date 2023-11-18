public class SunState {
    private int sunInclination; //altitude
    private int sunDirection; //azimuth
    private DIRECTIONS directions;

    /**
     Invariante:
     Vorbedingung: sunInclination in Degrees, sunDirection in m und >=0 , directions != null
     Nachbedingung: Erstellt eine Instanz der Klasse SunState mit den Parameter Werten die den korrespondierenden
     Instanzvariablen zugeordnet werdne
     */
    public SunState(int sunInclination, int sunDirection, DIRECTIONS directions) {
        this.sunInclination = sunInclination;
        this.sunDirection = sunDirection;
        this.directions = directions;
    }

    /**
     Invariante: direction bleibt unverändet
     Vorbedingung:
     Nachbedingung: gibt direction zurück
     */
    public DIRECTIONS getDirections() {
        return directions;
    }
}
