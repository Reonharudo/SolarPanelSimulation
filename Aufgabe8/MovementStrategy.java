/**
 * INVARIANTE:
 */
public abstract class MovementStrategy {

    /**
     * VORBEDINGUNG: -
     * NACHBEDINGUNG: gibt eine Direction zurück, die in einer Bewegungsstrategie-Run bisher OK war.
     */
    public abstract Direction retrieveStrategy();

    /**
     * VORBEDINGUNG: retrieveStrategy() was called
     * NACHBEDINGUNG: assoziiert die Direction von retrieveStrategy() mit das es nicht ok ist (=fehlgeschlagen)
     */
    public abstract void lastRunWas(boolean ok);
}
