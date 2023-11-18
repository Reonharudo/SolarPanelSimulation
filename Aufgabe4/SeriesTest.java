public class SeriesTest {

    public static void main(String... args){
        SeriesTest seriesTest = new SeriesTest();
        seriesTest.testAddToSeries();
    }

    public void test(){
        SeriesTest seriesTest = new SeriesTest();
        seriesTest.testAddToSeries();
        seriesTest.testKWp();
    }

    public void testConstructor(){
        System.out.println(new ConcreteSeries());
    }

    public void testAddToSeries(){
        ConcreteSeries series1 = new ConcreteSeries();

        series1.addToSeries(new GleichstromModule() {
            @Override
            public double size() {
                return 5;
            }

            @Override
            public double kWp() {
                return 10;
            }

            @Override
            public boolean decouplingRequired() {
                return false;
            }

            @Override
            public String toString() {
                return "Module { size: "+size()+" "+"kWp: "+kWp()+" "+"decouplingRequired: "+decouplingRequired()+"  }";
            }
        });

        String actualToString = series1.toString();
        String expectedToString = "Series { kWp: 10.0 Module { size: 5.0 kWp: 10.0 decouplingRequired: false  }}";
        System.out.println(actualToString.equals(expectedToString) ? "AddToSeries: SUCCESSFULL": "AddToSeries: FAILED");
    }

    public void testKWp(){
        ConcreteSeries series1 = new ConcreteSeries();

        series1.addToSeries(new GleichstromModule() {
            @Override
            public double size() {
                return 5;
            }

            @Override
            public double kWp() {
                return 10;
            }

            @Override
            public boolean decouplingRequired() {
                return false;
            }

            @Override
            public String toString() {
                return "Module { size: "+size()+" "+"kWp: "+kWp()+" "+"decouplingRequired: "+decouplingRequired()+"  }";
            }
        });

        series1.addToSeries(new GleichstromModule() {
            @Override
            public double size() {
                return 5;
            }

            @Override
            public double kWp() {
                return 8;
            }

            @Override
            public boolean decouplingRequired() {
                return false;
            }
            @Override
            public String toString() {
                return "Module { size: "+size()+" "+"kWp: "+kWp()+" "+"decouplingRequired: "+decouplingRequired()+"  }";
            }
        });

        double actualKwp = series1.kWp();
        double expectedKwp = 18;
        System.out.println(actualKwp == expectedKwp ? "KWP: SUCESSFULL": "KWP: FAILED");
    }


    public class ConcreteSeries extends Series{

    }
}
