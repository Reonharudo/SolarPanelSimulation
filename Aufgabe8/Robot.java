/**
 * INVARIANTE: row > 0 && col > 0 && dir != null && strategy != null && anlage != null
 */
public class Robot extends Thread /*implements runnable*/{
    private int cleaned;
    private int waited;

    private int row;
    private int col;
    private Direction dir;
    private final MovementStrategy strategy;
    private final Anlage anlage;
    private Quit quit;

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Direction getDir() { return dir; }

    /**
     * VORBEDINGUNG: row > 0 && col > 0 && dir != null && strategy != null && anlage != null
     * NACHBEDINGUNG: Erzeugt Instanz der Klasse Robot mit den übergebenen Parametern
     */
    public Robot(int row, int col, Direction dir, MovementStrategy strategy, Anlage anlage) {
        this.cleaned = 0;
        this.waited = 0;
        this.row = row;
        this.col = col;
        this.dir = dir;
        this.strategy = strategy;
        this.anlage = anlage;
    }

    /**
     * VORBEDINGUNG: -
     * NACHBEDINGUNG: gibt true zurück wenn 100 cleaned ist mind. 25 mal gewartet worden ist, ansonsten false
     */
    public boolean finnished() {
        return (waited >= 25) || (cleaned >= 100);
    }

    /**
     * VORBEDINGUNG: d != null
     * NACHBEDINGUNG: Bewegt den Roboter in die Direction. Wenn erfolgreich wird true ausgegeben, wenn der
     * Roboter sich nicht bewegen konnte, schläft der Thread für 25ms und Instanzvariable waited wird um 1 erhöht.
     */
    private boolean trymove(Direction d) {
        boolean moved = false;

        for(int i = 0; i < 3 && !moved && !finnished(); i++) {
            moved = anlage.move(row, col, d);
            if (!moved) {
                try {
                    //noinspection BusyWait
                    sleep(25); // busy waiting but that's kinda what we have to do.
                } catch (InterruptedException ignored) {}

                waited++;
            }
        }

        return moved;
    }

    /**
     * VORBEDINGUNG: -
     * NACHBEDINGUNG: Setzt ein Quit Objekt
     */
    public void setQuit(Quit q) {
        quit = q;
    }

    /**
     * VORBEDINGUNG: -
     * NACHBEDINGUNG: Bewegt den Roboter in die nächste Bewegungsstrategie-Richtung. Wenn erfolgreich bewegt
     * werden die Instanzvariablen row und col auf die neue Position aktualisiert und true ausgegeben, ansonsten
     * false. Strategy bekommt die Information, ob die Richtung erfolgreich war oder nicht mit lastRunWas(erfolgreich)
     */
    public boolean move() {
        Direction d;
        boolean moved;

        d = strategy.retrieveStrategy();
        moved = trymove(d);
        if (moved) {
            switch (d) {
                case North:
                    row--;
                    break;
                case East:
                    col++;
                    break;
                case South:
                    row++;
                    break;
                case West:
                    col--;
                    break;
            }
            cleaned += 2;
            dir = d;
        }
        strategy.lastRunWas(moved);
        return moved;
    }

    /**
     * VORBEDINGUNG:  row < map.length  && col < map[0].length dh derzeitige Position ist in der Map enthalten
     * NACHBEDINGUNG: Zeichnet den Roboter abhängig von seiner Ausrichtung in den übergebenen Paramter map
     */
    public void draw(char[][] map) {
        int rows = map.length;
        int cols = map[0].length; // must have rows

        assert(row < rows);
        assert(col < cols);

        switch (dir) {
            case North:
                // LR
                // TT
                map[row][col]     = 'L';
                map[row][col+1]   = 'R';
                map[row+1][col]   = 'T';
                map[row+1][col+1] = 'T';
                break;
            case East:
                // TL
                // TR
                map[row][col]     = 'T';
                map[row][col+1]   = 'L';
                map[row+1][col]   = 'T';
                map[row+1][col+1] = 'R';
                break;
            case South:
                // TT
                // RL
                map[row][col]     = 'T';
                map[row][col+1]   = 'T';
                map[row+1][col]   = 'R';
                map[row+1][col+1] = 'L';
                break;
            case West:
                // RT
                // LT
                map[row][col]     = 'R';
                map[row][col+1]   = 'T';
                map[row+1][col]   = 'L';
                map[row+1][col+1] = 'T';
                break;
        }
    }

    /**
     * VORBEDINGUNG: -
     * NACHBEDINGUNG: Gibt die aus wieoft gewarted und wieviele panele geputzt wurden.
     */
    public void status() {
        System.out.println("Cleaned: " + cleaned);
        System.out.println("Waited:  " + waited);
    }

    /**
     * VORBEDINGUNG: -
     * NACHBEDINGUNG: Lösst den Roboter reinigen. Wenn ein Robot Thread beendet wird, so wird auch dieser
     * Thread beendet.
     */
    @Override
    public void run() {
        assert(quit != null);

        while(!(finnished() || quit.get())) {
            move();
            if (finnished()) {
                quit.set(true);
            }
        }
    }
}
