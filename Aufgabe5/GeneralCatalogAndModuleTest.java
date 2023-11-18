import java.util.Iterator;

public class GeneralCatalogAndModuleTest {
    /**
     * VORBEDINGUNG: -
     * INVARIANTE: -
     * NACHBEDINGUNG: -
     */
    public static void main(String[] args) {
        test();
    }

    /**
     * VORBEDINGUNG: -
     * INVARIANTE: -
     * NACHBEDINGUNG: -
     */
    public static void test() {
        Module mod1 = new Module(1,"DC european", "AC european");
        Module mod2 = new Module(2,"DC european");
        Module mod3 = new Module(3,"AC european");
        Module mod4 = new Module(4, "DC decoupled");

        System.out.println("=== TEST MODULE ===");
        check(mod1.compatible(mod2), true);
        check(mod1.compatible(mod2), true);
        check(mod2.compatible(mod3), false);
        check(mod1.compatible(mod4), false);

        mod1.compatible(mod4);
        mod1.compatible(mod4);
        mod1.compatible(mod4);

        check(mod1.rate(), 2);
        check(mod3.rate(), 0);
        check(mod4.rate(), 0);

        check(mod1.toString(), "Module");
        check(mod2.toString(), "Module");
        check(mod3.toString(), "Module");
        check(mod4.toString(), "Module");

        check(mod1.certified(), new String[] {"DC european", "AC european"});
        check(mod2.certified(), new String[] {"DC european"});

        System.out.println("=== TEST GENERALCATALOG ===");
        Multiple mul1 = new Multiple(2);
        Multiple mul2 = new Multiple(4);
        Multiple mul3 = new Multiple(7);
        Multiple mul4 = new Multiple(5);
        Multiple mul5 = new Multiple(6);

        System.out.println("-- addX, iterator --");
        GeneralCatalog<Multiple> muls = new GeneralCatalog<Multiple>();
        muls.addX(mul1);
        check(count(muls), 1);
        muls.addX(mul2);
        muls.addX(mul3);
        muls.addX(mul4);
        muls.addX(mul5);
        check(count(muls), 5);
        muls.addX(mul2);
        muls.addX(mul2);
        muls.addX(mul4);
        muls.addX(mul5);
        check(count(muls), 5);
        Iterator<Multiple> mulsi = muls.iterator();
        while(mulsi.hasNext()) {
            mulsi.next();
            mulsi.remove();
        }
        check(count(muls), 0);
        System.out.println("-- addY, iterator(x) --");
        muls.addY(mul1);
        muls.addY(mul2);
        muls.addY(mul3);
        muls.addY(mul4);
        muls.addY(mul5);

        int c = 0;
        mulsi = muls.iterator(mul1);
        while(mulsi.hasNext()) {
            mulsi.next();
            c++;
        }

        check(c, 3);
        mulsi = muls.iterator(mul1);
        while(mulsi.hasNext()) {
            mulsi.next();
            mulsi.remove();
        }

        c = 0;
        mulsi = muls.iterator(mul1);
        while(mulsi.hasNext()) {
            mulsi.next();
            c++;
        }
        check(c, 0);

        System.out.println("-- generalIterator --");
        c = 0;
        mulsi = muls.generalIterator();
        while(mulsi.hasNext()) {
            mulsi.next();
            c++;
        }
        check(c, 2);

        muls.addX(mul1);
        muls.addX(mul2);
        muls.addX(mul3);
        muls.addX(mul4);
        muls.addX(mul5);
        muls.addY(mul1);
        muls.addY(mul2);
        muls.addY(mul3);
        muls.addY(mul4);
        muls.addY(mul5);

        c = 0;
        mulsi = muls.generalIterator();
        while(mulsi.hasNext()) {
            mulsi.next();
            c++;
        }
        check(c, 5);

        // other variants
        GeneralCatalog<Module> mods = new GeneralCatalog<Module>();
        mods.addX(mod1);
        mods.addY(mod2);
        mods.addY(mod3);

        GeneralCatalog<Inverter> invs = new GeneralCatalog<Inverter>();
        Inverter inverter1 = new Inverter(100, "DC european", "AC european");
        Inverter inverter2 = new Inverter(150, "DC america", "AC european");

        invs.addX(inverter1);
        invs.addY(inverter2);

        GeneralCatalog<PVComponent> pvs = new GeneralCatalog<PVComponent>();
        pvs.addX(mod1);
        pvs.addX(mod2);
        pvs.addX(inverter1);

        pvs.addY(mod3);
        pvs.addY(inverter2);

    }

    public static <T extends Iterable<?>> int count(T l) {
        int i = 0;
        for(Object o : l) {
            i++;
        }
        return i;
    }

    public static void check(boolean expr, boolean expect) {
        if (expr == expect) {
            System.out.println("Test: OK");
        } else {
            System.out.println("Test: FAILED");
        }
    }

    public static void check(int expr, int expect) {
        if (expr == expect) {
            System.out.println("Test: OK");
        } else {
            System.out.println("Test: FAILED");
        }
    }

    public static void check(String s, String expect) {
        if (s.equals(expect)) {
            System.out.println("Test: OK");
        } else {
            System.out.println("Test: FAILED");
        }
    }

    public static void check(String[] ls, String[] expect) {
        boolean eq = ls.length == expect.length;

        if (eq) {
            for (int i = 0; i < ls.length; i++) {
                if (!ls[i].equals(expect[i])) {
                    eq = false;
                    break;
                }
            }
        }

        if (eq) {
            System.out.println("Test: OK");
        } else {
            System.out.println("Test: FAILED");
        }
    }
}
