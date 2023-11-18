public class TestPort {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        Battery bat1    = new Battery(1.5, 0.02); // AA-Battery
        PowerGrid grid1 = new PowerGrid();

        Port p1 = bat1;
        Port p2 = grid1;

        p1.store(1);
        p2.store(1);

        p1.store(2);
        p2.store(2);

        p1.store(4);
        p2.store(4);

        check(p1.stored(), 7);
        check(p2.stored(), 7);
        check(p1.taken(), 0);
        check(p2.taken(), 0);
        check(bat1.dischargingTime(), 0);
        check(bat1.loadingTime(), 14000000);

        p1.take(0.5);
        p2.take(0.5);

        p1.take(2);
        p2.take(2);

        p1.take(3);
        p2.take(3);

        check(p1.taken(), 5.5);
        check(p2.taken(), 5.5);

        Battery batemp = new Battery(bat1.voltage(), bat1.amperage());
        batemp.load(bat1.loadingTime());
        batemp.discharge(bat1.dischargingTime());

        check(bat1.loadingTime(), batemp.loadingTime());
        check(bat1.dischargingTime(), batemp.dischargingTime());
        check(bat1.stored(), batemp.stored());
        check(bat1.taken(), batemp.taken());
    }

    public static void check(double a, double b) {
        double theta = 0.0001;
        if (Math.abs(a - b) <= theta) {
            System.out.println("Check SUCCEDED");
        } else {
            System.out.println("Check FAILED (" + a + ", " + b + ")");
        }
    }

    public static void check(int a, int b) {
        if (a == b) {
            System.out.println("Check SUCCEDED");
        } else {
            System.out.println("Check FAILED (" + a + ", " + b + ")");
        }
    }
}
