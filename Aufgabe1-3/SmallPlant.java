import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ThreadLocalRandom;

public class SmallPlant extends Nature{

    /**
     
     Invariante: plantedTime nicht null
     Vorbedingung: height in m & >= 0, maxheight >= height & maxheight in m
     Nachbedingung: Erstellt eine Instanz der Klasse mit den Parameter Werten die den korrespondierenden
     Instanzvariablen zugeordnet werdne
     */
    public SmallPlant(LocalDateTime currentTime, double height) {
        super(currentTime, height, height);
    }

    /**
     
     Invariante: currentTime != null
     Vorbedingung:  toLocalDateTime != null
     Nachbedingung: lässt diesen SmallPlant in Richtung maxHeight wachsen

     GOOD: hoher Klassenzusammenhalt, niedrige Objektkopplung durch Vererbung. Niederige
     Anzahl an Parametern wird dadurch erreicht das bestimmte Instanzvariablen bereits
     in der Elternklasse Nature initialisiert werden
     */
    @Override
    public void growTo(LocalDateTime toLocalDateTime) {
        long diffDays = ChronoUnit.DAYS.between(super.getCurrentTime(), toLocalDateTime);

        double randomGrowAbsolute = ThreadLocalRandom.current().nextDouble(0,0.45 + 1);

        for(int i = 0; i < diffDays; i++){
            this.setHeight(this.getHeight()+randomGrowAbsolute);
        }
    }
}
