public class RandomMovementStrategyTest {
    public static void main(String[] args) {
        test();
    }

    public static void test(){
        RandomMovementStrategy r = new RandomMovementStrategy();

        Direction movement = r.retrieveStrategy();

        if(movement == Direction.East){
            System.out.println("SUCCESS: RandomMovementStrategy() move is East");
        }else if(movement == Direction.North){
            System.out.println("SUCCESS: RandomMovementStrategy() move is North");
        }else if(movement == Direction.South){
            System.out.println("SUCCESS: RandomMovementStrategy() move is South");
        }else if(movement == Direction.West){
            System.out.println("SUCCESS: RandomMovementStrategy() move is West");
        }

        movement = r.retrieveStrategy();

        if(movement == Direction.East){
            System.out.println("SUCCESS: RandomMovementStrategy() move is East");
        }else if(movement == Direction.North){
            System.out.println("SUCCESS: RandomMovementStrategy() move is North");
        }else if(movement == Direction.South){
            System.out.println("SUCCESS: RandomMovementStrategy() move is South");
        }else if(movement == Direction.West){
            System.out.println("SUCCESS: RandomMovementStrategy() move is West");
        }

        movement = r.retrieveStrategy();

        if(movement == Direction.East){
            System.out.println("SUCCESS: RandomMovementStrategy() move is East");
        }else if(movement == Direction.North){
            System.out.println("SUCCESS: RandomMovementStrategy() move is North");
        }else if(movement == Direction.South){
            System.out.println("SUCCESS: RandomMovementStrategy() move is South");
        }else if(movement == Direction.West){
            System.out.println("SUCCESS: RandomMovementStrategy() move is West");
        }

    }
}
