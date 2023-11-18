import java.time.LocalDateTime;
import java.util.Random;

/*
Aufgabe 3 -
Leo, Noel, Jaz: Zusicherungen "Design-by-Contract" e.g Kommentare hinzugefügt

Aufgabe 2 - Arbeitsaufteilung
Leo - Environment (Environmentfactors and its children classes)
Noel - Database , Simulation
Jaz - PVPanel, Facility
All - Test Simulation Objects, Discussion with Implementation
Note: Our simulation takes quite long to run, but thanks to our caching with the database our calculated
scenarios are instantly logged into console! Therefore; as for the initial run its execute based on the database

Aufgabe 1 - Arbeitsaufteilung
* Leo : Witterung (WeatherCourse, Weather)
* Noel: Stromverbrauch (ElecticalUsage, Simulation)
* Jaz : PV-Panel (PVPanel, PVFacility)
* all : Test
*/

public class Test {
    /* @author  all
     *
     * PRECON:  -
     * POSTCON: ./simdb.txt holds the Database of the Simulations.
     * INVAR:   -
     */
    public static void main(String[] args) {
        Environment[] evs = new Environment[3];
        PVFacility[] pvs = new PVFacility[3];
        ElectricityUsage[] eus = new ElectricityUsage[3];

        LocalDateTime start = LocalDateTime.of(2022,1,1,0,0);
        LocalDateTime end = start.plusYears(1);

        //// WeatherCourse

        String[] EVNames = {"Environment Scenario 1", "Environment Scenario 2", "Environment Scenario 3"};

        //precision of simulation grows with the amount of weather objects (as history) in WeatherCourse
        //as for this test simulation run we decided on one weather object for each day which equals 365 Weather Objects
        WeatherCourse weatherCourseScenario1 = WeatherCourse.randomWeatherCourse(start.getYear(), -12,41);
        WeatherCourse weatherCourseScenario2 = WeatherCourse.randomWeatherCourse(start.getYear(), 0, 20);
        WeatherCourse weatherCourseScenario3 = WeatherCourse.randomWeatherCourse(start.getYear(), 6, 36);

        //Sceneraio 1

        //Some trees
        EnvironmentFactor factor1 = new Tree(start, 20, 920);
        EnvironmentFactor factor2 = new Tree(start, 0, 1014);
        EnvironmentFactor factor3 = new Tree(start, 1, 840);
        EnvironmentFactor factor4 = new Tree(start, 10, 810);

        //SmallPlants
        EnvironmentFactor factor5 = new SmallPlant(start, 20);
        EnvironmentFactor factor6 = new SmallPlant(start, 6);
        EnvironmentFactor factor7 = new SmallPlant(start, 7);
        EnvironmentFactor factor8 = new SmallPlant(start, 9);

        //Building
        EnvironmentFactor factor9 = new Building(630);
        EnvironmentFactor factor10 = new Building(630);
        EnvironmentFactor factor11 = new Building(630);
        EnvironmentFactor factor12 = new Building(600);

        evs[0] = new Environment(weatherCourseScenario1, factor1, factor2, factor3, factor4, factor5, factor6,
                factor7, factor8, factor9, factor10, factor11, factor12);

        //Sceneraio 2

        //Some trees
        EnvironmentFactor scenario2_factor1 = new Tree(start, 20, 920);
        EnvironmentFactor scenario2_factor011 = new Tree(start, 20, 920);
        EnvironmentFactor scenario2_factor2 = new Tree(start, 0, 1014);

        //SmallPlants
        EnvironmentFactor scenario2_factor5 = new SmallPlant(start, 20);
        EnvironmentFactor scenario2_factor6 = new SmallPlant(start, 6);
        EnvironmentFactor scenario2_factor7 = new SmallPlant(start, 7);
        EnvironmentFactor scenario2_factor13 = new SmallPlant(start, 7);
        EnvironmentFactor scenario2_factor14= new SmallPlant(start, 7);
        EnvironmentFactor scenario2_factor15 = new SmallPlant(start, 7);
        EnvironmentFactor scenario2_factor16 = new SmallPlant(start, 7);
        EnvironmentFactor scenario2_factor8 = new SmallPlant(start, 9);

        //Light Material
        EnvironmentFactor scenario2_factor20 = new LightMaterial(10);
        EnvironmentFactor scenario2_factor21 = new LightMaterial(10);
        EnvironmentFactor scenario2_factor22 = new LightMaterial(10);
        EnvironmentFactor scenario2_factor23 = new LightMaterial(10);
        EnvironmentFactor scenario2_factor24 = new LightMaterial(10);

        //Heavy Materials
        EnvironmentFactor scenario2_factor30 = new HeavyMaterial(10);
        EnvironmentFactor scenario2_factor31 = new HeavyMaterial(10);
        EnvironmentFactor scenario2_factor32 = new HeavyMaterial(10);
        EnvironmentFactor scenario2_factor33 = new HeavyMaterial(10);
        EnvironmentFactor scenario2_factor34 = new HeavyMaterial(10);

        //Building
        EnvironmentFactor scenario2_factor9 = new Building(630);

        evs[1] = new Environment(weatherCourseScenario2, scenario2_factor1, scenario2_factor011, scenario2_factor2,
                scenario2_factor5, scenario2_factor6, scenario2_factor7, scenario2_factor13, scenario2_factor14,
                scenario2_factor15, scenario2_factor16, scenario2_factor8, scenario2_factor20, scenario2_factor21,
                scenario2_factor22, scenario2_factor23, scenario2_factor24, scenario2_factor30, scenario2_factor31,
                scenario2_factor32, scenario2_factor33, scenario2_factor34, scenario2_factor9);

        // Scenario 3

        //Some trees
        EnvironmentFactor scenario3_factor1 = new Tree(start, 30, 800);
        EnvironmentFactor scenario3_factor2 = new Tree(start, 50, 584);
        EnvironmentFactor scenario3_factor3 = new Tree(start, 3, 840);
        EnvironmentFactor scenario3_factor4 = new Tree(start, 10, 815);

        //SmallPlants
        EnvironmentFactor scenario3_factor5 = new SmallPlant(start, 19);
        EnvironmentFactor scenario3_factor6 = new SmallPlant(start, 14);
        EnvironmentFactor scenario3_factor7 = new SmallPlant(start, 5);
        EnvironmentFactor scenario3_factor8 = new SmallPlant(start, 10);

        //Building
        EnvironmentFactor scenario3_factor9 = new Building(630);
        EnvironmentFactor scenario3_factor10 = new Building(600);
        EnvironmentFactor scenario3_factor11 = new Building(400);
        EnvironmentFactor scenario3_factor12 = new Building(650);

        //Light Material
        EnvironmentFactor scenario3_factor13 = new LightMaterial(9);
        EnvironmentFactor scenario3_factor14 = new LightMaterial(9);
        EnvironmentFactor scenario3_factor15 = new LightMaterial(9);

        //Heavy Material
        EnvironmentFactor scenario3_factor16 = new HeavyMaterial(9);
        EnvironmentFactor scenario3_factor17 = new HeavyMaterial(9);


        evs[2] = new Environment(weatherCourseScenario3, scenario3_factor1, scenario3_factor2, scenario3_factor3, scenario3_factor4, scenario3_factor5, scenario3_factor6,
                scenario3_factor7, scenario3_factor8, scenario3_factor9, scenario3_factor10, scenario3_factor11, scenario3_factor12, scenario3_factor13, scenario3_factor14, scenario3_factor15, scenario3_factor16, scenario3_factor17);

        //// PVFacility

        String[] PVNames = {"PV-Facility 1", "PV-Facility 2", "PV-Facility 3"};

        PVPanel p1 = new PVPanel(45, 7, DIRECTIONS.SOUTH, PANELMODEL.FLAT, 690);
        PVPanel p2 = new PVPanel(90, 5, DIRECTIONS.WEST, PANELMODEL.FLAT, 700);
        PVPanel p3 = new PVPanel(180, 3, DIRECTIONS.SOUTHEAST, PANELMODEL.FLAT, 650);

        pvs[0] = new PVFacility(p1, p2, p3);

        PVPanel p4 = new PVPanel(45, 8, DIRECTIONS.SOUTHWEST, PANELMODEL.STEEP, 710);
        PVPanel p5 = new PVPanel(90, 2, DIRECTIONS.WEST, PANELMODEL.STEEP, 730);
        PVPanel p6 = new PVPanel(180, 6, DIRECTIONS.SOUTHEAST, PANELMODEL.STEEP, 715);

        pvs[1] = new PVFacility(p4, p5, p6);

        PVPanel p7 = new PVPanel(45, 10, DIRECTIONS.SOUTH, PANELMODEL.FLAT, 700);
        PVPanel p8 = new PVPanel(90, 7, DIRECTIONS.WEST, PANELMODEL.STEEP, 680);
        PVPanel p9 = new PVPanel(180, 8, DIRECTIONS.SOUTHWEST, PANELMODEL.STEEP, 750);

        pvs[2] = new PVFacility(p7, p8, p9);

        //// ElectricityUsage

        String[] EUNames = {"Electricity Scenario 1", "Electricity Scenario 2", "Electricity Scenario 3"};

        // average 1-person house with electric water heating
        eus[0] = new ElectricityUsage(new Random(2022), 2500.0 / 365.0);
        // 70m^2 home with two persons and 8 big electiracal appliances
        eus[1] = new ElectricityUsage(new Random(2022), false, 70, 2, 8);
        // big house?
        eus[2] = new ElectricityUsage(new Random(2022), true, 100, 4, 12);

        String dbfile = "./simdb.txt";
        Database db = new Database(dbfile);

        // Run different Simulations
        Simulation sim;
        SimulationRun run;
        for (int evi = 0; evi < 3; evi++) {
            for (int pvi = 0; pvi < 3; pvi++) {
                for (int eui = 0; eui < 3; eui++) {
                    String name = EVNames[evi] + " - " + PVNames[pvi] + " - " + EUNames[eui];

                    SimulationRun[] q = db.query(name);
                    if (0 < q.length) {
                        System.out.println("######################################################################################");
                        System.out.println(q[0]);
                    }
                    else {

                        run = new SimulationRun(start, end, name, EVNames[evi], EUNames[eui], PVNames[pvi]);

                        sim = new Simulation(start, pvs[pvi], evs[evi], eus[eui]);
                        sim.run(run);

                        System.out.println("######################################################################################");
                        System.out.println(run);
                        db.add(run);
                    }
                }
            }
        }
    }
}