public class HeavyMaterial extends Material{
    /**
     * 
     Invariante:
     Vorbedingung: maxHeight muss kleiner als height sein
     Nachbedingung: Erstellt eine Instanz der Klasse HeavyMaterial mit den Parameter Wert height
     */
    public HeavyMaterial(double height) {
        super.setHeight(height);
        super.setMaxHeight(height);
    }
}
