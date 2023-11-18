/**
 * Leonhard: MovementStrategy, CircularMovementStrategy, Tests, Konzeption
 * Noel: Anlage, Quit, Robot, Simulation, Konzeption, Direction
 * Jaz: HorizontalMovementStrategy, RandomMovementStrategy, Tests, Konzeption
 */
public class Test {
    public static void main(String[] args) {
        //Test Klassen zu Strategies
        CircularMovementStrategyTest.test();
        HorizontalMovementStrategyTest.test();
        RandomMovementStrategyTest.test();

        //Test Anlage
        AnlageTest.test();

        //Simulation Run
        int[][] panles = new int[][] {
                {1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1},
                {1, 1, 0, 0, 1, 1},
                {1, 1, 0, 0, 1, 1},
                {1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1}
        };
        Anlage anlage = new Anlage(panles);
        Robot[] robots = new Robot[2];

        MovementStrategy m1 = new RandomMovementStrategy();
        MovementStrategy m2 = new RandomMovementStrategy();

        robots[0] = new Robot(0,0,Direction.East, m1, anlage);
        robots[1] = new Robot(4, 4, Direction.West, m2, anlage);

        Simulation sim = new Simulation(anlage, robots);
        sim.run();
    }
}
