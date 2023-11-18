import java.time.LocalDateTime;

public abstract class Nature extends EnvironmentFactor{
    private final SEASON seasons[] = {
            SEASON.WINTER, SEASON.WINTER, SEASON.SPRING, SEASON.SPRING, SEASON.SUMMER, SEASON.SUMMER,
            SEASON.SUMMER, SEASON.SUMMER, SEASON.FALL, SEASON.FALL, SEASON.WINTER, SEASON.WINTER
    };
    private LocalDateTime currentTime;
    private double height;

    /**
     * 
     Invariante:
     Vorbedingung: maxHeight muss kleiner als height sein
     Nachbedingung: Erstellt eine Instanz der Klasse Nature mit den Parameter Werten
     */
    public Nature(LocalDateTime currentTime, double height, double maxHeight) {
        this.currentTime = currentTime;
        this.height = height;
        super.setMaxHeight(maxHeight);
    }

    /**
     * 
     Invariante:
     Vorbedingung: toLocalDateTime ist nicht null
     Nachbedingung: Lässt die Nature Klasse von currentTime bis toLocalDateTime wachsen. Instanzvariable height
     wird auf ein Wert <= maxHeight gesetzt, in Abhängigkeit vom Zeitinterval [currentTime, toLocalDateTime]

     GOOD: dynamische Bindung wird erreicht durch die abstract methode; vereinfacht die
     Wartbarkeit und Skalierbarkeit des Programmcodes auf neue Nature-Objekte.
     Guter Klassenzusammenhalt durch beschreibende Namen der Instanzvariablen und Parameter wie
     currentTime oder toLocalDateTime und length und maxLength
     */
    public abstract void growTo(LocalDateTime toLocalDateTime);

    /**
     * 
     Invariante: currenttime is nicht null
     Vorbedingung:
     Nachbedingung: gibt currentTime zurück
     */
    public LocalDateTime getCurrentTime() {
        return currentTime;
    }

    /**
     * 
     Invariante: height in m & height <= maxHeight
     Vorbedingung:
     Nachbedingung: gibt height zurück
     */
    public double getHeight() {
        return height;
    }

    /**
     * 
     Invariante: season is not null and has all entries
     Vorbedingung:
     Nachbedingung: gibt seasons zurück
     */
    public SEASON[] getSeasons() {
        return seasons;
    }

    /**
     * 
    Invariante:
    Vorbedingung: currentTime != null
    Nachbedingung: gibt currenttime zurück
     */
    public void setCurrentTime(LocalDateTime currentTime) {
        this.currentTime = currentTime;
    }

    /**
     * 
    Invariante:
    Vorbedingung: height >= 0 & height <= maxheight
    Nachbedingung: gibt height zurück
     */
    public void setHeight(double height) {
        this.height = height;
    }
}
