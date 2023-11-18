public class Module implements PVComponent {
    private double size;
    private int rate;
    private String certificates[];

    /**
     * VORBEDINGUNG: size ist positiv und certificates ist nicht null
     * INVARIANTE: -
     * NACHBEDINGUNG: Objekt ist Valide.
     */
    Module(double size, String... certificates) {
        this.size = size;
        this.rate = 0;
        this.certificates = certificates;
    }

    /**
     * VORBEDINGUNG: -
     * INVARIANTE: -
     * NACHBEDINGUNG: gibt "Module" zurück
     */
    @Override
    public String toString() {
        return "Module";
    }

    /**
     * VORBEDINGUNG: Module ist nicht null.
     * INVARIANTE: -
     * NACHBEDINGUNG: Gibt zurück ob die Komponenten ein gemeinsames zertifikat haben.
     */
    @Override
    public boolean compatible(PVComponent module) {
        if (match(module.certified())) {
            rate++;
            return true;
        }
        return false;
    }

    /**
     * VORBEDINGUNG:
     * INVARIANTE: Objekt bleibt gleich.
     * NACHBEDINGUNG: Gibt die anzahle (>= 0) zurüch wieoft compatible aufgerufen wurde un true zurückgab.
     */
    @Override
    public int rate() {
        return this.rate;
    }

    /**
     * VORBEDINGUNG: -
     * INVARIANTE: Objekt bleibt gleich.
     * NACHBEDINGUNG: Gibt alle Zertificate zurück.
     */
    @Override
    public String[] certified() {
        return this.certificates;
    }

    /**
     * VORBEDINGUNG: -
     * INVARIANTE: Objekt bleibt gleich.
     * NACHBEDINGUNG: Gibt die größe (> 0) des Modules zurück.
     */
    public double size() {
        return this.size;
    }

    /**
     * VORBEDINGUNG: other ist nicht null
     * INVARIANTE: -
     * NACHBEDINGUNG: Gibt zurück ob other und this gemeinsame Zertifikate haben.
     */
    private boolean match(String[] other) {
        for (String i : this.certificates) {
            for (String j : other) {
                if (i.equals(j)) {
                    return true;
                }
            }
        }
        return false;
    }
}
