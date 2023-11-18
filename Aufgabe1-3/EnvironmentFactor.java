public class EnvironmentFactor {
    private double height;

    private double maxHeight;


    /**
     * 
     Invariante:
     Vorbedingung: maxHeight muss größer als height sein und maxheight ist in m
     Nachbedingung: setzt maxheight auf den übergebenen maxheight parameter

     GOOD: durch protected wird die Objektkopplung reduziert.
     */
    protected void setMaxHeight(double maxHeight) {
        if(maxHeight >= height){
            this.maxHeight = maxHeight;
        }else{
            throw new RuntimeException("Max Height must be larger than height");
        }
    }

    /**
     * 
     Invariante: height ist in m und >=0
     Vorbedingung:
     Nachbedingung: gibt height zurück
     */
    public double getHeight() {
        return height;
    }

    /**
     * 
     Invariante:
     Vorbedingung: height >= 0 und height <= maxHeight
     Nachbedingung: instanzvariable height wird der Wert vom Parameter height zugewiesen

     GOOD: durch protected wird die Objektkopplung reduziert.
     */
    protected void setHeight(double height) {
        this.height = height;
    }


    /**
     * 
     Invariante: maxHeight ist in m und >=0
     Vorbedingung:
     Nachbedingung: gibt height zurück
     */
    public double getMaxHeight() {
        return maxHeight;
    }
}
