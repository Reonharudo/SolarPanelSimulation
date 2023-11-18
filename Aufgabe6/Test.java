/**
 * Leonhard: PVPanel, Facade, Roof
 * Jaz: Commmunity, Street
 * Noel: Battery, Port, Powergrid
 * Alle: Klassen- und Implementierungskonzeption
 */
public class Test {

    public static void main(String[] args) {
        System.out.println("===========PVPANEL==========");
        TestPVPanel.test();

        System.out.println();
        System.out.println("===========TESTING==========");

        Street s1 = new Street("s1");
        Street s2 = new Street("s2");
        Street s3 = new Street("s3");


        PVPanel p1 = new Facade(7, 67, 89 , CardinalPoint.EAST, 80);

        PVPanel p2 = new Roof(9, 67, 89 , CardinalPoint.SOUTH, 90);

        PVPanel p3 = new Roof(10, 67, 89 , CardinalPoint.SOUTH, 90);


        s1.add(p1);
        s1.add(p2);
        s1.add(p3);

        s2.add(p2);
        s2.add(p3);

        s3.add(p1);

        //dürfen gleiche Panels in unterschiedlichen Straßen sein?
        Community c = new Community("c1");
        c.add(s1);
        c.add(s2);
        c.add(s1);
        c.add(s3);



        System.out.println("Street1: \n" + s1);
        s1.remove(p2);
        System.out.println("Street1 after remove: \n" + s1);
        s1.add(p1);
        System.out.println("Street1 after adding an already existing street: \n" + s1);

        System.out.println("Street2: \n" + s2);
        s2.add(p3);
        System.out.println("Street1 after add: \n" + s1);

        System.out.println("Community:\n"+c);
        c.remove("s1");
        System.out.println("Community after remove:\n"+c);

        s1.setPortToBattery(78, 3, 7);

        System.out.println("Battery discharge: "+s1.batteryDischargeTime(7));
        s1.BatteryLoad(67, 7);
        System.out.println("Battery discharge after load: "+s1.batteryDischargeTime(7));

        s1.setPortToPowerGrid(7);
        System.out.println("After changing Port to Powergrid: "+s1.storedToPort(7));
        s1.setPortToBattery(78, 3, 7);
        System.out.println("Stored power after changing back to battery: "+s1.storedToPort(7));
        s1.setPortToBattery(0, 0, 7);
        s1.storeToPort(78, 7);
        System.out.println("After divison by 0: "+s1.storedToPort(7));

        System.out.println();
        //doesnt work properly
        System.out.println("Static methods:");
        System.out.println("Average incline: " + s1.staticMethods(StaticMethods.avgInclination));
        System.out.println("Average MaxPower: " + s1.staticMethods(StaticMethods.avgMaxPow));
        System.out.println("Average Direction: " + s1.staticMethods(StaticMethods.avgInclination));
        System.out.println("Average Energy: " + s1.staticMethods(StaticMethods.avgPower));
        System.out.println("Average Energy with Roofport: " + s1.staticMethods(StaticMethods.avgPowerRoof));
        System.out.println("Average Energy with Facadeport: " + s1.staticMethods(StaticMethods.avgPowerFacade));
        System.out.println("Taken Energy: " + s1.staticMethods(StaticMethods.avgTakenPow));

    }

}
