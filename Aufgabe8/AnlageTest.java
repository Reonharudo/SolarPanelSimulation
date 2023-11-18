public class AnlageTest {

    public static void main(String[] args) {
        test();
    }

    public static void test(){
        int[][] panels = new int[][]{
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1}
        };

        Anlage a = new Anlage(panels);

        if(a.put(2, 1)){
            System.out.println("SUCCESS: put was successful");
        }else{
            System.out.println("FAILED: put was not successful");
        }

        if(a.put(1, 3)){
            System.out.println("SUCCESS: put was successful");
        }else{
            System.out.println("FAILED: put was not successful");
        }

        if(a.put(1, 3)){
            System.out.println("FAILED: put was successful");
        }else{
            System.out.println("SUCCESS: put was not successful");
        }

    }
}
