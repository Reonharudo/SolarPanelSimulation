/**
 * INVARIANTE:  panels != null && panels.length == rows && panels[0].length == cols &&
 * rows > 0 &&
 * cols > 0
 */
public class Anlage {
    private final int[][] panels;
    private final int rows;
    private final int cols;

    /**
     * VORBEDINGUNG: Alle Werte des zweidimensionalen Arrays müssen 0 sein
     * NACHBEDINGUNG: Erzeugt Instanz der Klasse Anlage mit dem übergebenen Panel Parameter.
     */
    public Anlage(int[][] panels) {
        assert(panels.length % 2 == 0 && 2 <= panels.length);
        assert(panels[0].length % 2 == 0 && 2 <= panels[0].length);

        this.rows = panels.length;
        this.cols = panels[0].length;

        this.panels = panels;
    }

    /**
     * VORBEDINGUNG: dir != null && put(row, col) wurde initial aufgerufen
     * NACHBEDINGUNG: in Abhängigkeit von der Direction wird geschaut, ob Platz verfügbar ist. Ein verfügbarer
     * Platz ist mit 1 oder 0 definiert. Wenn der Platz verfügbar ist, wird true zurückgegeben und die assoziierten Bereichs
     * felder werden auf -1 gesetzt, ansonsten wenn kein Platz verfügbar ist, wird false ausgegeben.
     */
    public synchronized boolean move(int row, int col, Direction dir) {
        // edges
        if (dir == Direction.North && row == 0) {
            return false;
        }
        if (dir == Direction.East && col == cols - 2) {
            return false;
        }
        if (dir == Direction.South && row == rows - 2) {
            return false;
        }
        if (dir == Direction.West && col == 0) {
            return false;
        }

        // non edge movement
        if (dir == Direction.North) {
            if (panels[row-1][col] == 1 && panels[row-1][col+1] == 1) {
                // freeing old
                panels[row+1][col] = 1;
                panels[row+1][col+1] = 1;

                // occupying new
                panels[row-1][col] = -1;
                panels[row-1][col+1] = -1;
                return true;
            }
            return false;
        }
        if (dir == Direction.East) {
            if (panels[row][col+2] == 1 && panels[row+1][col+2] == 1) {
                // freeing old
                panels[row][col] = 1;
                panels[row+1][col] = 1;

                // occupying new
                panels[row][col+2] = -1;
                panels[row+1][col+2] = -1;
                return true;
            }
            return false;
        }
        if (dir == Direction.South) {
            if (panels[row+2][col] == 1 && panels[row+2][col+1] == 1) {
                // freeing old
                panels[row][col] = 1;
                panels[row][col+1] = 1;

                // occupying new
                panels[row+2][col] = -1;
                panels[row+2][col+1] = -1;
                return true;
            }
            return false;
        }
        if (dir == Direction.West) {
            if (panels[row][col-1] == 1 && panels[row+1][col-1] == 1) {
                // freeing old
                panels[row][col+1] = 1;
                panels[row+1][col+1] = 1;

                // occupying new
                panels[row][col-1] = -1;
                panels[row+1][col-1] = -1;
                return true;
            }
            return false;
        }
        assert(false); // unreachable
        return false;
    }

    /**
     * VORBEDINGUNG: row >= 0 && col >= 0 && panels alle Werte == -1 oder panels alle Werte == 0
     * NACHBEDINGUNG:
     *  ein Bereich ist definiert mit [row][col], panels[row][col+1], panels[row+1][col][row+1][col+1]
     *  wenn in panels der Bereich == 0 dann wird der Wert auf -1 gesetzt und true ausgegeben,
     *  wenn in panels der Bereich == -1 dann wird false ausgegeben,
     */
    public synchronized boolean put(int row, int col) {
        // in field
        if (0 > row || row > rows - 2) {
            return false;
        }
        if (0 > col || col > cols - 2) {
            return false;
        }

        // try to put robot at row,col
        boolean free = panels[row][col] == 1   && panels[row][col+1] == 1
                    && panels[row+1][col] == 1 && panels[row+1][col+1] == 1;

        if (free) {
            panels[row][col] = -1;
            panels[row][col+1] = -1;
            panels[row+1][col] = -1;
            panels[row+1][col+1] = -1;
        }
        return free;
    }

    /**
     * VORBEDINGUNG: -
     * NACHBEDINGUNG: derzeitiger Stand von der Anlage wird auf die Konsole ausgegeben.
     * Ein Abstand ist ein Leerzeichen, ein Roboter ist mit # markiert, ein Panel wo keine Roboter sind,
     * ist mit + markiert.
     */
    public synchronized void draw(Robot[] robots) {
        char[][] m = new char[rows][cols];

        for(int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                switch (panels[r][c]) {
                    case -1:
                        m[r][c] = '#';
                        break;
                    case 0:
                        m[r][c] = ' ';
                        break;
                    case 1:
                        m[r][c] = '+';
                        break;
                    default:
                        m[r][c] = '!';
                        break;
                }
            }
        }

        for(Robot robot : robots) {
            robot.draw(m);
        }

        for(char[] row : m) {
            System.out.println(row);
        }
    }
}
