public class TestPVPanel {
    public static void main(String... args){
        testPVPanelConstructor();
    }

    public static void test(){
        testRiseProducedPower();
        testPVPanelConstructor();
    }

    public static void testRiseProducedPower(){
        PVPanel panelWithFacade = new Facade(
                1,
                1000,
                100,
                CardinalPoint.EAST,
                180
        );
        panelWithFacade.riseProducedPower(120);
        double expected = 220;
        double actual = panelWithFacade.getProducedPower();
        if(expected == actual){
            System.out.println("SUCCESS: riseProducedPower value increased correctly");
        }else{
            System.out.println("FAILED: riseProducedPower value increased not as expected");
        }
    }

    public static void testPVPanelConstructor(){
        PVPanel panelWithFacade = new Facade(
                1,
                1000,
                100,
                CardinalPoint.EAST,
                180
        );
        System.out.println("SUCCESS: created FacadePVPanel instance");
        System.out.println(panelWithFacade);

        PVPanel panelWithRoof = new Roof(
                1,
                1000,
                100,
                CardinalPoint.EAST,
                90
        );
        System.out.println("SUCCESS: created RoofPVPanel instance");
        System.out.println(panelWithRoof);

        try{
            PVPanel panelWithFacadeWithError = new Facade(
                    1,
                    1000,
                    100,
                    CardinalPoint.EAST,
                    188
            );
            System.out.println("FAILED: exception was not thrown for 188 degree inclination");
        }catch (RuntimeException e){
            System.out.println("SUCCESS: thrown exception for invalid inclination 188° for Facade: "+e.getMessage());
        }

        try{
            PVPanel panelWithFacadeWithError = new Facade(
                    1,
                    1000,
                    100,
                    CardinalPoint.EAST,
                    -188
            );
            System.out.println("FAILED: exception was not thrown for -188 degree inclination");
        }catch (RuntimeException e){
            System.out.println("SUCCESS: thrown exception for invalid inclination 188° for Facade: "+e.getMessage());
        }

        try{
            PVPanel panelWithFacadeWithError = new Roof(
                    1,
                    1000,
                    100,
                    CardinalPoint.EAST,
                    91
            );
            System.out.println("FAILED: exception was not thrown for 91 degree inclination");
        }catch (RuntimeException e){
            System.out.println("SUCCESS: thrown exception for invalid inclination 91° for Roof: "+e.getMessage());
        }

        try{
            PVPanel panelWithFacadeWithError = new Roof(
                    1,
                    1000,
                    100,
                    CardinalPoint.EAST,
                    -1
            );
            System.out.println("FAILED: exception was not thrown for -1 degree inclination");
        }catch (RuntimeException e){
            System.out.println("SUCCESS: thrown exception for invalid inclination 91° for Roof: "+e.getMessage());
        }
    }
}
