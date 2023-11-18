public class LightMaterial extends Material{
    /**
     * 
     Invariante:
     Vorbedingung: maxHeight muss kleiner als height sein
     Nachbedingung: Erstellt eine Instanz der Klasse LightMaterial mit dem Parameter Wert height
     */
    public LightMaterial(double height) {
        super.setHeight(height);
        super.setMaxHeight(height);
    }
}
