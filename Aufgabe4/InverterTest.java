import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class InverterTest {


 static Series s1 = new Series() {
     @Override
     public double kWp() {
         return  30;
     }
     @Override
     public boolean addToSeries(GleichstromModule... modules) {
         return super.addToSeries(modules);
     }
 };

 static Series s2 = new Series() {

     @Override
     public double kWp() {
         return 30;
     }

     @Override
     public boolean addToSeries(GleichstromModule... modules) {
         return super.addToSeries(modules);
     }
 };



 static Series s3 = new Series() {
     @Override
     public double kWp() {
         return 30;
     }
     @Override
     public boolean addToSeries(GleichstromModule... modules) {
         return super.addToSeries(modules);
     }
 };

    static LinkedList<FurtherFunctions> f = new LinkedList<>();
    static LinkedList<Series> seriesList = new LinkedList<Series>(Arrays.asList(s1, s2));





    static Inverter i = new Inverter(seriesList, f) {
        @Override
        public double size() {
            return 4;
        }

        @Override
        public double kWp() {
            return 5;
        }

        @Override
        public boolean decouplingRequired() {
            return false;
        }


        @Override
        public int kwp() {
            return super.kwp();
        }
    };

    public static void test(){
        i.addToString(s1);
        i.addToString(s2);
        i.addToString(s3);
        System.out.println("kWpLimit: " + i.kWpLimit());

    }


}
