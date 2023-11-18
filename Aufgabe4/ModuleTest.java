public class ModuleTest {
    public static void test() {
        MyCrisMod mycrismod = new MyCrisMod(2, 1, true);
        MyWechselMod mywechselmod = new MyWechselMod(3,2, true);

        CristallineModule cristall = mycrismod;
        GleichstromModule gleichstrom = mycrismod;
        Module mod = mycrismod;

        WechselstromModule wechselstrom = mywechselmod;

        if ((mod.size() == 2 && mod.kWp() == 1) && mod.decouplingRequired()) {
            System.out.println("SUCCESSFULL: Test funcions.");
        } else {
            System.out.println("FAILED: Functions are not the same.");
        }

        mod = mywechselmod;
        if ((mod.size() == 3 && mod.kWp() == 2) && mod.decouplingRequired()) {
            System.out.println("SUCCESSFULL: Test funcions.");
        } else {
            System.out.println("FAILED: Functions are not the same.");
        }

        //gleichstrom = mywechselmod;
        //wechselstrom = mywechselmod;

        System.out.println("Test Successfull!");
    }
}

class MyWechselMod implements WechselstromModule {
    double size;
    double kWp;
    boolean decoupling;

    MyWechselMod(double size, double kWp, boolean decoupling) {
        this.size = size;
        this.kWp = kWp;
        this.decoupling = decoupling;
    }

    @Override
    public double size() {
        return this.size;
    }

    @Override
    public double kWp() {
        return this.kWp;
    }

    @Override
    public boolean decouplingRequired() {
        return this.decoupling;
    }
}

class MyCrisMod implements CristallineModule {
    double size;
    double kWp;
    boolean decoupling;

    MyCrisMod(double size, double kWp, boolean decoupling) {
        this.size = size;
        this.kWp = kWp;
        this.decoupling = decoupling;
    }

    @Override
    public double size() {
        return this.size;
    }

    @Override
    public double kWp() {
        return this.kWp;
    }

    @Override
    public boolean decouplingRequired() {
        return this.decoupling;
    }
}