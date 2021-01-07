import java.io.FileNotFoundException;
import java.util.Scanner;

public class DecisionMapTest {
    public static void main(String[] args) throws FileNotFoundException {



        DecisionMap DM = new DecisionMap();

        navigateMap(DM);
    }

    public static void navigateMap(DecisionMap DM){
        Scanner input = new Scanner(System.in);
        DecisionNode node = DM.entryPoint();

        while(node != null) {

            System.out.println(node.getDescription());
            System.out.println(node.getQuestion());


            OptionCases();
            System.out.println(node.getOptions());
            int decision = input.nextInt();

            switch (decision){
                case 1:
                    node = node.getNoNode();
                    break;
                case 2:
                    node = node.getYesNode();
                    break;
            }
        }
    }

    public static void OptionCases(){

        System.out.println("Press 1 to go to the left option or 2 to go the right option !!:");

        return;
    }
}
