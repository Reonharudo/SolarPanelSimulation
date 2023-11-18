public class CircularMovementStrategyTest {
    public static void main(String... args){
        test();
    }

    public static void test(){
        CircularMovementStrategy circularMovementStrategy = new CircularMovementStrategy();

        //### Simulation Robot starts moving (first to North as specificed in CircularMovementStrategy ###
        // ### NEW RUN ###
        if(circularMovementStrategy.retrieveStrategy() == Direction.West){
            System.out.println("SUCCESS: CircularMovementStrategy() suggested move is West");
        }else{
            System.out.println("FAILED: CircularMovementStrategy() suggested first move was not West");
        }

        circularMovementStrategy.lastRunWas(true);

        // ### NEW RUN ###
        Direction movement = circularMovementStrategy.retrieveStrategy();
        if(movement == Direction.West){
            System.out.println("SUCCESS: CircularMovementStrategy() suggested move is West");
        }else{
            System.out.println("FAILED: CircularMovementStrategy() suggested first move was not West");
        }

        circularMovementStrategy.lastRunWas(false);

        // ### NEW RUN ###
        System.out.println("INFO: lastRunWas not OK of "+movement+" changing strategy");

        Direction movement2 = circularMovementStrategy.retrieveStrategy();
        if(movement2 == Direction.North){
            System.out.println("SUCCESS: CircularMovementStrategy() suggested move is North");
        }else{
            System.out.println("FAILED: CircularMovementStrategy() suggested first move was not North");
        }

        circularMovementStrategy.lastRunWas(false);

        // ### NEW RUN ###
        System.out.println("INFO: lastRunWas not OK of "+movement2+" changing strategy");

        if(circularMovementStrategy.retrieveStrategy() == Direction.East){
            System.out.println("SUCCESS: CircularMovementStrategy() suggested move is East");
        }else{
            System.out.println("FAILED: CircularMovementStrategy() suggested first move was not East");
        }
        circularMovementStrategy.lastRunWas(true);

        // ### NEW RUN ###
        if(circularMovementStrategy.retrieveStrategy() == Direction.East){
            System.out.println("SUCCESS: CircularMovementStrategy() suggested move is East");
        }else{
            System.out.println("FAILED: CircularMovementStrategy() suggested first move was not East");
        }
        circularMovementStrategy.lastRunWas(true);

        // ### NEW RUN ###
        Direction movement3 = circularMovementStrategy.retrieveStrategy();
        if(movement3 == Direction.East){
            System.out.println("SUCCESS: CircularMovementStrategy() suggested move is East");
        }else{
            System.out.println("FAILED: CircularMovementStrategy() suggested first move was not East");
        }

        circularMovementStrategy.lastRunWas(false);

        // ### NEW RUN ###
        System.out.println("INFO: lastRunWas not OK of "+movement3+" changing strategy");

        if(circularMovementStrategy.retrieveStrategy() == Direction.South){
            System.out.println("SUCCESS: CircularMovementStrategy() suggested move is South");
        }else{
            System.out.println("FAILED: CircularMovementStrategy() suggested first move was not South");
        }
        circularMovementStrategy.lastRunWas(true);

        // ### NEW RUN ###
        if(circularMovementStrategy.retrieveStrategy() == Direction.South){
            System.out.println("SUCCESS: CircularMovementStrategy() suggested move is South");
        }else{
            System.out.println("FAILED: CircularMovementStrategy() suggested first move was not South");
        }

        circularMovementStrategy.lastRunWas(false);
        System.out.println("INFO: lastRunWas not OK of "+movement3+" changing strategy");

        // ### NEW RUN ###
        if(circularMovementStrategy.retrieveStrategy() == Direction.West){
            System.out.println("SUCCESS: CircularMovementStrategy() suggested move is West");
            System.out.println("SUCCESS: Circular Structure works, as Direction is now resetted from going with West");
        }else{
            System.out.println("FAILED: CircularMovementStrategy() suggested first move was not West");
            System.out.println("FAILED: Circular Structure is not working as expected");
        }
    }
}
