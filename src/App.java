import java.util.Scanner;

public class App {
    // CLASS VARIABLE
    private static Monster[] monsters;
    // PLAYER STATS
    private static int health = 100;
    private static int speed = 10;
    private static int shield = 50;
    private static int damage = 50;
    private static int heal = 50;
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

        //PICK YOUR BUILD
        System.out.println("---- OPTIONS ----");
            System.out.println("1) Fighter");
            System.out.println("2) Tank");
            System.out.println("3) Healer");
            System.out.println("4) Ninja");
            System.out.print("Choice: ");
             // ACTIONS
            int choice;
            if(choice == 1) {
                // fighters have low healing and little shield
            
            }else if(choice == 2){

            }else if(choice == 3){

            }else {

            }
        // Display Monster Status
        reportMonsters();
    }
    
        // Display momnster status
        while(monsterCount > 0){
            // Who goes first

            //give options
            System.out.println("---- OPTIONS ----");
            System.out.println("1) Attack");
            System.out.println("2) Defend");
            System.out.println("3) Heal");
            System.out.println("4) pass");
            System.out.print("Choice: ");
            Scanner input;
            int choice = input.nextInt(); // TODO: Error handle on bad input

                // ACTIONS
            if(choice == 1) {
            
            }else if(choice == 2){

            }else if(choice == 3){

            }else {

            }

            Monster currentMonster;
            //DO I NEED A NEW MONSTER?
            if(currentMonster.health() <= 0){
                System.out.println(" YOU HAVE SLAIN A MONSTER!!!!");
                currentMonster = getNextMonster();
                reportMonsters();
            }

    }
    public static int percentComplete(){
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