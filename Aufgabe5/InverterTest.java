public class InverterTest {
    public static void main(String... args){
        InverterTest inverterTest = new InverterTest();
        inverterTest.test();
    }

    public void test(){
        testCompatible();
        testConstructor();
        testCompatible();
    }

    public void testCompatible(){
        Inverter inverter1 = new Inverter(100, "DC european", "AC european");
        Inverter inverter2 = new Inverter(150, "DC america", "AC european");

        if(inverter1.compatible(inverter2)){ System.out.println("SUCCESS: Inverter 1 and Inverter 2 are compatible"); }
        else{ System.out.println("FAILED: Inverter 1 and Inverter 2 are NOT compatible"); }

        int expectedCall1 = 1;
        int actualCall1 = inverter1.rate();

        if(expectedCall1 == actualCall1){
            System.out.println("SUCCESS: rate() runs correctly");
        }else{
            System.out.println("FAILED: rate() failed");
        }

        int expectedCall2 = 0;
        int actualCall2 = inverter2.rate();

        if(expectedCall1 == actualCall1){
            System.out.println("SUCCESS: rate() runs correctly");
        }else{
            System.out.println("FAILED: rate() failed");
        }

        for(String s : inverter1.certified()){
            if(s.equals("DC european") || s.equals("AC european")){
                System.out.println("SUCCESS: certified() runs correctly");
            }else{
                System.out.println("FAILED: certified() runs faulty");
            }
        }
    }

    public void testkWp(){
        Inverter inverter2 = new Inverter(150, "DC america", "AC european");
        if(inverter2.kWp() == 150){
            System.out.println("SUCCESS: kWp() runs correctly");
        }else{
            System.out.println("FAILED: kWp() failed");
        }
    }

    public void testConstructor(){
        try{
            PVComponent pvComponent = new Inverter(-3, "DC european");
        }catch (RuntimeException e){
            System.out.println("SUCCESS: Exception successfully outputted: "+e.getMessage());
        }

        try{
            PVComponent pvComponent2 = new Inverter(100, "DC european");
        }catch (RuntimeException e){
            System.out.println("SUCCESS: Exception successfully outputted: "+e.getMessage());
        }
    }
}
