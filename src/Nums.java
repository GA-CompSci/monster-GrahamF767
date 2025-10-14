import java.util.ArrayList;
import java.util.Scanner;

public class Nums {
    public static void main(String[] args){
        // ArrayLists are upgraded, felxable arrays with extra tools
        ArrayList<String> names = new ArrayList<>();

        Scanner s = new Scanner(System.in);
        System.out.print("Enter name: ");
        String input = s.nextLine();
        // INPUT LOOP
        while(!input.equals("")){
            names.add(input);
            System.out.print("Enter next name:  ");
            input = s.nextLine();
        }

        while(!input.equalsIgnoreCase("quit") || names.size() == 0){
            int r = (int)(Math.random() * names.size());
            System.out.println("RANDOM SELECTION: " + names.get(r));
            names.remove(r);
            System.out.println("Enter for another same, QUIT to exit");
            input = s.nextLine();
        }
    }
}
