import java.util.ArrayList;
import java.util.LinkedList;

public class Help {
    public static void main(String[] args) {
        int temp = (int) (Math.random() * 10);
        ArrayList<String> list = new ArrayList<>();
        list.add("DBS Musterlösung");
        list.add("Stats");
        list.add("DBS VO");
        list.add("Programming (own project)");

        while(!(temp < 4)){
            temp = (int) (Math.random() * 10);
        }
        System.out.println(list.get(temp));

    }
}
