import java.util.ArrayList;

public class OopA4XB16Test {

    static Storagebattery s1 = new Storagebattery() {
        @Override
        public double size() {
            return 5;
        }

        @Override
        public double kWp() {
            return 6;
        }

        @Override
        public boolean decouplingRequired() {
            return false;
        }

        @Override
        public int hashCode() {

            return super.hashCode();
        }
    };

    static ArrayList<Series> m = new ArrayList<Series>();
    static ArrayList<Storagebattery> s = new ArrayList<Storagebattery>();

    static Series series = new Series() {

        @Override
        public boolean addToSeries(GleichstromModule... modules) {
            return super.addToSeries(modules);
        }
    };


    public static void test(){
        OopA4XB16 test1 = new OopA4(m, s);
        test1.add(series);
        test1.addStoragebattery(s1);
        System.out.println(test1.toString());
    }
}
class OopA4 extends OopA4XB16{
    private ArrayList<Series> m;
    private ArrayList<Storagebattery> s;
    public OopA4(ArrayList<Series> m, ArrayList<Storagebattery> s) {
        super(m, s);
        this.m = m;
        this.s = s;
    }
}


