public class TestStockList {
    public static void main(String... args){
        test();
    }


    public static void test(){
        /*
         * TEST STORAGE
         */
        StockList stockList = new StockList();
        System.out.println("SUCCESS: StocksList NoArgsConstructor() creates instance");

        Inverter inverterHybrid1Large = new HybridInverter(false, new Battery(40), 11);
        Inverter inverterHybrid2Large = new HybridInverter(false, new Battery(11), 23);
        Inverter inverterHybrid3Small = new HybridInverter(true, null, 3);
        Inverter inverterHybrid4Small = new HybridInverter(true, new Battery(40), 2);

        Inverter stringInverter1Large = new StringInverter(false);
        Inverter stringInverter2Large = new StringInverter(false);
        Inverter stringInverter3Small = new StringInverter(true);
        Inverter stringInverter4Small = new StringInverter(true);

        stockList.addInverter(inverterHybrid1Large);
        stockList.addInverter(inverterHybrid2Large);
        stockList.addInverter(inverterHybrid3Small);
        stockList.addInverter(inverterHybrid4Small);

        stockList.addInverter(stringInverter1Large);
        stockList.addInverter(stringInverter2Large);
        stockList.addInverter(stringInverter3Small);
        stockList.addInverter(stringInverter4Small);

        if(stockList.kWhStore() == inverterHybrid1Large.getkWh() + inverterHybrid2Large.getkWh() + inverterHybrid3Small.getkWh() + inverterHybrid4Small.getkWh()
        + stringInverter1Large.getkWh() + stringInverter2Large.getkWh() + stringInverter3Small.getkWh() + stringInverter4Small.getkWh()){
            System.out.println("SUCCESS: addInverter() works as expected");
            System.out.println("SUCCESS: kWhStore() works as expected");
        }else{
            System.out.println("FAILED: addInverter() does not work as expected");
            System.out.println("FAILED: kWhStore() does not work as expected");
        }

        boolean successDel = stockList.delInverter(inverterHybrid1Large);

        if(successDel && stockList.kWhStore() == inverterHybrid2Large.getkWh() + inverterHybrid3Small.getkWh() + inverterHybrid4Small.getkWh()
                + stringInverter1Large.getkWh() + stringInverter2Large.getkWh() + stringInverter3Small.getkWh() + stringInverter4Small.getkWh()){
            System.out.println("SUCCESS: delInverter() works as expected");
        }else{
            System.out.println("FAILED: delInverter() does not work as expected");
        }

        stockList.addInverter(inverterHybrid1Large);

        /*
         * TEST CLIENT STORAGE assignInverter() with Battery
         */
        Client client = new Client( true, 6);

        Inverter assignedInverterHybridLarge = stockList.assignInverter(client);
        if(assignedInverterHybridLarge != null){
            System.out.println("SUCCESS: assignInverter() works as expected for HybridInverter Large");
        }else{
            System.out.println("FAILED: assignInverter() does not work as expected for HybridInverter Large");
        }

        Client tooBigclient = new Client(true, 200);

        Inverter assignedInverterHybridSmall = stockList.assignInverter(tooBigclient);
        if(assignedInverterHybridSmall == null){
            System.out.println("SUCCESS: assignInverter() works as expected as small hybrid inverters were not assigned to client" +
                    "with kw of 200");
        }else{
            System.out.println("FAILED: assignInverter() does not work expected as small hybrid inverters was assigned assigned to client" +
                "with kw of 200");
        }

        Client client1 = new Client(false, 1);
        Inverter assignedStringInverterSmall = stockList.assignInverter(client1);
        if(assignedStringInverterSmall != null){
            System.out.println("SUCCESS: assignInverter() works as expected for assignedInverterSmall");
        }else{
            System.out.println("FAILED: assignInverter() does not work as expected for assignedInverterSmall");
        }



        /*
         * TEST CLIENT STORAGE kWhClient()
         */
        if(assignedInverterHybridLarge != null && assignedStringInverterSmall != null){
            if(stockList.kWhClient() == assignedInverterHybridLarge.getkWh() + assignedStringInverterSmall.getkWh()){
                System.out.println("SUCCESS: kWhClient() works as expected");
            }else{
                System.out.println("FAILED: kWhClient() does not work as expected");
            }
        }else{
            System.out.println("UNKNOWN: Could not test kWhClient() as assignInverter() does not work");
        }



        /*
         * TEST CLIENT STORAGE returnInverter()
         */

        Inverter returnedInverter = stockList.returnInverter(client, assignedInverterHybridLarge);
        if(returnedInverter != null && stockList.kWhStore() == inverterHybrid2Large.getkWh() + inverterHybrid3Small.getkWh() + inverterHybrid4Small.getkWh()){
            System.out.println("SUCCESS: returnInverter() works as expected and is successfully located in storageList");
        }else {
            System.out.println("FAILED: returnInverter() does not work as expected. No association between provided client found");
        }


        /*
         * TEST CLIENT STORAGE assignInverter() with no Battery
         */
        Client client2 = new Client( false, 6);
        Inverter inverter = stockList.assignInverter(client2);
        if(inverter != null && stockList.kWhStore() == inverterHybrid2Large.getkWh() + inverterHybrid3Small.getkWh() + inverterHybrid4Small.getkWh()){
            System.out.println("SUCCESS: returnInverter() works as expected and is successfully located in storageList");
        }else {
            System.out.println("FAILED: returnInverter() does not work as expected. No association between provided client found");
        }

        /*
         * TEST SHOW METHODS
         */
        stockList.clientsShow();
        System.out.println("SUCCESS: clientsShow() successfully called");

        /*
         * TEST CLIENT STORAGE returnInverter()
         */

        Inverter returnedInverter1 = stockList.returnInverter(client, assignedStringInverterSmall);
        if(returnedInverter1 != null && stockList.kWhStore() == inverterHybrid2Large.getkWh() + inverterHybrid3Small.getkWh() + inverterHybrid4Small.getkWh()){
            System.out.println("SUCCESS: returnInverter() works as expected and is successfully located in storageList");
        }else {
            System.out.println("FAILED: returnInverter() does not work as expected. No association between provided client found");
        }

        /*
         * TEST SHOW METHODS
         */
        stockList.clientsShow();
        System.out.println("SUCCESS: clientsShow() successfully called");
        stockList.inverterShow();
        System.out.println("SUCCESS: inverterShow() successfully called");
    }
}
