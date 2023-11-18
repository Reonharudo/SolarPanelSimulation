public class Simulation implements Runnable {
    private final Anlage anlage;
    private final Robot[] robots;
    private final Quit quit;

    /**
     * NACHBEDINGUNG: a != null, rs != null
     */
    public Simulation(Anlage a, Robot ... rs) {
        this.anlage = a;
        this.robots = rs;
        this.quit = new Quit();
    }

    /**
     * NACHBEDINGUNG: es wird ein mainrobot deklariert, dann werden die einzelnen Threads gestartet und sobald ein robot
     * entweder mind. 25 mal warten musst bzw 100 Anlagen gereinigt hat oder die quit zeit überschritten
     * wurde, dann werden alle threads geschlossen
     */
    @Override
    public void run() {
        Robot mainRobot = robots[0];
        Timeout timeout = new Timeout(quit);

        for(Robot robot : robots) {
            robot.setQuit(quit);
            if (!anlage.put(robot.getRow(), robot.getCol())) {
                System.out.println("Could not place robot at (" + robot.getRow() + ", " + robot.getCol() + ")!");
            }
        }

        int s = 1;
        System.out.println("--- Run: " + String.format("%03d", s++) +
                " -------------------------------------------------------------------");
        anlage.draw(robots);

        timeout.start();
        for(int i = 1; i < robots.length; i++) {
            robots[i].start();
        }

        while(!(mainRobot.finnished() || quit.get())) {
            if(!mainRobot.move()) continue;
            System.out.println("\n--- Run: " + String.format("%03d", s++) +
                    " -------------------------------------------------------------------");
            anlage.draw(robots);
            if (mainRobot.finnished()) {
                quit.set(true);
            }
        }

        for(Robot robot : robots) {
            robot.interrupt();
        }
        timeout.interrupt();

        for(Robot robot : robots) {
            System.out.println("\n--- Robot at (" + robot.getRow() + ", " + robot.getCol() + ") facing " +
                    robot.getDir() + " ---------------");
            robot.status();
        }
    }
}

class Timeout extends Thread {
    private final Quit quit;

    public Timeout(Quit quit) {
        this.quit = quit;
    }


    /**
     * NACHBEDINGUNG: thread schläft für 10000 millisecond und dann wird quit auf true gesetzt
     */
    @Override
    public void run() {
        try {
            sleep(10000);
        } catch (InterruptedException ignored) {}
        quit.set(true);
    }
}