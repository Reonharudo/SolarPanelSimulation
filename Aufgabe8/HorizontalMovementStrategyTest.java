public class HorizontalMovementStrategyTest {
    public static void main(String[] args) {
        test();
    }

    public static void test(){
        HorizontalMovementStrategy h = new HorizontalMovementStrategy();

        if(h.retrieveStrategy() == Direction.East){
            System.out.println("SUCCESS: HorizontalMovementStrategy() move is East");
        }else{
            System.out.println("FAILED: HorizontalMovementStrategy() first move was not East");
        }

        h.lastRunWas(false);

        Direction movement = h.retrieveStrategy();
        if(movement == Direction.West){
            System.out.println("SUCCESS: HorizontalMovementStrategy() move is West");
        }else{
            System.out.println("FAILED: HorizontalMovementStrategy() first move was not West");
        }

        h.lastRunWas(false);

        movement = h.retrieveStrategy();
        if(movement == Direction.West){
            System.out.println("FAILED: HorizontalMovementStrategy() first move is East");
        }else{
            System.out.println("SUCCESS: HorizontalMovementStrategy() first move was not West");
        }
    }
}
