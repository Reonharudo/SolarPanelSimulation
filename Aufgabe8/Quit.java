/**
 * INVARIANTE:
 */
public class Quit {
    private boolean quit;

    /**
     * VORBEDINGUNG: -
     * NACHBEDINGUNG: Erzeugt Instanz der Klasse quit wobei instanzvariable quit = false
     */
    public Quit() {
        this.quit = false;
    }

    /**
     * VORBEDINGUNG:
     * NACHBEDINGUNG: gibt boolean wert zurück in Abhängigkeit ob this quit ist
     */
    public synchronized boolean get() {
        return quit;
    }

    /**
     * VORBEDINGUNG: -
     * NACHBEDINGUNG: Setzt this quit auf den übergebenen boolean Parameter
     */
    public synchronized void set(boolean v) {
        quit = v;
    }
}
