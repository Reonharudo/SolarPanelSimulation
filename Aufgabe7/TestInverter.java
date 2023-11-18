public class TestInverter {
    public static void main(String... args){
        test();
    }


    public static void test() {
        HybridInverter hil_nob = new HybridInverter(false,null,10);
        HybridInverter hil_b = new HybridInverter(false, new Battery(8), 10);
        HybridInverter his_nob = new HybridInverter(true, null, 5);
        HybridInverter his_b = new HybridInverter(true, new Battery(5), 5);

        StringInverter sil = new StringInverter(false);
        StringInverter sis = new StringInverter(true);

        boolean[] res = new boolean[6];
        boolean[] exp;
        Inverter[] invs = {hil_nob, hil_b, his_nob, his_b, sil, sis};

        exp = new boolean[] {true, true, true, true, false, false};
        for(int i = 0; i < invs.length; i++) {
            res[i] = invs[i].check(0,true);
        }
        check(exp, res, "check battery");

        exp = new boolean[] {false, false, false, false, true, true};
        for(int i = 0; i < invs.length; i++) {
            res[i] = invs[i].check(0,false);
        }
        check(exp, res, "check no battery");

        exp = new boolean[] {true, true, false, false, true, false};
        for(int i = 0; i < invs.length; i++) {
            res[i] = invs[i].check(6,false) || invs[i].check(6, true);
        }
        check(exp, res, "check large");

        exp = new boolean[] {true, true, true, true, true, true};
        for(int i = 0; i < invs.length; i++) {
            res[i] = invs[i].check(2,false) || invs[i].check(2, true);
        }
        check(exp, res, "check small");

        double c = 0;
        for (Inverter inv : invs) c += inv.getkWh();
        check(c, 0.0, "initial counter");


        c = 0;
        for(int i = 0; i < invs.length; i++) invs[0].raisekWh(4.0);
        for (Inverter inv : invs) c += inv.getkWh();
        check(c, 6 * 4.0, "counter raise and read");

        HybridInverter[] hivs = {hil_nob, hil_b, his_nob, his_b};
        res = new boolean[4];

        exp = new boolean[] {true, true, true, true};
        for(int i = 0; i < hivs.length; i++) res[i] = hivs[i].setBattery(null);
        check(res, exp, "assign null to battery");

        exp = new boolean[] {false, false, false, false};
        for(int i = 0; i < hivs.length; i++) res[i] = hivs[i].setBattery(new Battery(99));
        check(res, exp, "battery over amp protection");

        exp = new boolean[] {true, true, false, false};
        for(int i = 0; i < hivs.length; i++) res[i] = hivs[i].setBattery(new Battery(7));
        check(res, exp, "battery over amp protection");

        exp = new boolean[] {true, true, true, true};
        for(int i = 0; i < hivs.length; i++) res[i] = hivs[i].setBattery(new Battery(1));
        check(res, exp, "set small battery");
    }

    private static void check(double a, double b, String msg) {
        double theta = 0.00001;
        if (Math.abs(a - b) <= theta) {
            System.out.println("SUCCESS TEST: " + msg);
        } else {
            System.out.println("FAILED TEST: " + msg);
        }
    }
    private static void check(boolean[] a, boolean[] b, String msg) {
        if (a.length != b.length) {
            System.out.println("FAILED TEST: " + msg);
            return;
        }

        for (int i = 0; i < a.length; i++) {
            if (a[i] ^ b[i]) {
                System.out.println("FAILED TEST: " + msg);
                return;
            }
        }
        System.out.println("SUCCESS TEST: " + msg);
    }
}
