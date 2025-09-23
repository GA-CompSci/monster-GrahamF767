import java.util.Scanner;

public class App {
    // CLASS VARIABLE
    private static Monster[] monsters;
    public static void main(String[] args) throws Exception {
        System.out.println("----- MONSTER BATTLE -----");
        Scanner input = new Scanner(System.in);
        System.out.print("How many monsters will you slay: ");
        int num = input.nextInt(); // TODO: handle errors if it's not a number
        monsters = new Monster[num];
        // build all the monsters
        for(int i = 0; i < monsters.length; i++){
            monsters[i] = new Monster(); // TODO: add some specials
        }
        // HOW MANY MONSTERS HAVE MORE THAN 50 HEALTH?
        System.out.println(monsterCount(50) + " monsters have more than 50 health");

        // WHAT IS THE % OF DEFEATED MONSTERS?
        System.out.println("You have defeated " + percentComplete() + "% of the monsters");

        // Display Monster Status
        reportMonsters();
    }
    
    public static double percentComplete(){
            return 100 - monsterCount(0) / monsters.length * 100;
    }

    public static void reportMonsters() {
        System.out.println("\n-------------MONSTER REPORT------------");
        for(int i = 0; i < monsters.length; i++){
            System.out.print("[" + i + "]");
            System.out.print(" - Health: " + monsters[i].health());
            System.out.print(" - Dmg: " + monsters[i].damage());
            System.out.print(" - Speed: " + monsters[i].speed());
            if(!monsters[i].special().equals(""))
            System.out.print(" - Special: " + monsters[i].special());
            // new line at the end
            System.out.println();
        }
    }
    /**
     * How many monsters have over the given health
     * @param health number to check
     * @return number of monsters above that health
     */
    public static int monsterCount(int health){
        int count = 0;
        // traverse the monsters array and find out how many have < 50 health
        for(Monster m : monsters){
            if(m.health() > health) count++;
        }
        return count;
    }

    public static Monster getNextMonster() {
        for(int i = 0; i < monsters.length; i++){
            if(monsters[i].health() > 0) return monsters[i];
        }
        return null;
    }




    
}