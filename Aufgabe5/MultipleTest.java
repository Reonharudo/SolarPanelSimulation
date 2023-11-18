public class MultipleTest {

    public static void main(String... args){
        MultipleTest multipleTest = new MultipleTest();
        multipleTest.test();
    }
    public void test(){
        System.out.println("======="+this.getClass().getSimpleName()+"=======");
        testUntertypbeziehung();
        testCompatibleMethods();
    }

    public void testUntertypbeziehung(){
        /* Es wird vom Compiler richtig ein Fehler erkannt */
        Multiple multiple = new Multiple(3);
        Compatible<Multiple> c = new Multiple(5);
        System.out.println(multiple+" "+c+" "+"wurden richtig instanziert");
        //Compatible<PVComponent> c2 = new Multiple(4); //Compile Error wird richtig ausgegeben
        //Compatible<Inverter> c2 = new Multiple(4); //Compile Error wird richtig ausgegeben
        //Compatible<Module> c2 = new Multiple(4); //Compile Error wird richtig ausgegeben
    }

    public void testCompatibleMethods(){
        Compatible<Multiple> compatible1 = new Multiple(5);
        Compatible<Multiple> compatible2 = new Multiple(5);
        Compatible<Multiple> compatible3 = new Multiple(2);

        compatible1.compatible( (Multiple) compatible2);
        compatible1.compatible( (Multiple) compatible3);
        compatible1.compatible( (Multiple) compatible2);

        int actualRate = compatible1.rate();
        int expectedRate = 2;

        if(actualRate == expectedRate){
            System.out.println("Test compatible() and rate() was successfull");
        }else{
            System.out.println("Test compatible() and rate() failed");
        }
    }
}
