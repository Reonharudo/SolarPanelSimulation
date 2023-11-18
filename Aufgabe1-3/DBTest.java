import java.time.LocalDateTime;
import java.util.Random;

public class DBTest {
    public static void main(String[] args) {
        Database db = new Database("runs.txt");
        LocalDateTime start = LocalDateTime.of(2022, 1, 1, 0, 0);
        LocalDateTime end = start.plusYears(1);

        Random rng = new Random();

        /*
        for (int r = 0; r < 50; r++) {
            int l = rng.nextInt(20);

            String name = "dummy test " + l;
            String env = "dummy enviroment " + l;
            String pv = "dummy pv-facility " + l;
            String eu = "dummy electricity usage " + l;

            SimulationRun simrun = new SimulationRun(start, end, name, env, eu, pv);

            for (int i = 0; i < 8760; i++) {
                double gen = rng.nextDouble(0.0, 4.0);
                double use = rng.nextDouble(0.0, 4.0);
                double peak = Math.max(gen, rng.nextDouble(0.0, 6.0));
                simrun.add(use, gen, peak);
            }

            db.add(simrun);
        }
        */

        SimulationRun runs[] = db.all();
        //SimulationRun runs[] = db.querry("dummy test 1");
        //SimulationRun run = db.querry(1);

        for(SimulationRun run : runs) {
            System.out.println("name: " + run.getName());
        }
    }
}
