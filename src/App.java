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
    // PLAYER STATE vars
    private static boolean isDefending = false;
    public static void main(String[] args) throws Exception {
        System.out.println("----- MONSTER BATTLE -----");
        Scanner input = new Scanner(System.in);
        System.out.print("How many monsters will you slay: ");
        int num = Math.max(1, input.nextInt()); 
        monsters = new Monster[num];
        // build all the monsters
        for(int i = 0; i < monsters.length; i++){
            monsters[i] = new Monster(); //TODO: add some specials
        }

        //PICK YOUR BUILD
        System.out.println("---- OPTIONS ----");
        System.out.println("1) Fighter");
        System.out.println("2) Tank");
        System.out.println("3) Healer");
        System.out.println("4) Ninja");
        System.out.print("Choice: ");
        // ACTIONS
        int choice = input.nextInt();
        if(choice == 1) {
            // fighters have low healing and little shield
            shield -= (int)(Math.random() * 46) + 5;
            heal -= (int)(Math.random() * 46) + 5;
        }else if(choice == 2){
            speed -= (int)(Math.random() * 9) + 1;
            damage -= (int)(Math.random() * 26) + 5;
        }else if(choice == 3){
            damage -= (int)(Math.random() * 26) + 5;
            shield -= (int)(Math.random() * 46) + 5;
        }else {
            heal -= (int)(Math.random() * 46) + 5;
            health -= (int)(Math.random() * 21) + 5;
        }
        // Display Monster Status
        reportMonsters();

        // Simple battle loop: attack the next alive monster until all are dead or player dies
        Monster current = getNextMonster();
        while(current != null && health > 0){
            System.out.println("\n---- PLAYER TURN ----");
            System.out.println("1) Attack");
            System.out.println("2) Defend");
            System.out.println("3) Heal");
            System.out.println("4) Pass");
            System.out.print("Choice: ");
            int action = input.nextInt();

            // ATTACK
            if(action == 1){
                int dmg = (int)(Math.random() * damage) + 1;
                if(dmg == damage) dmg = current.health(); // insta kill
                else if(dmg == 0) {
                    System.out.println("---CRITICAL FAIL!---");
                    System.out.println("Your attack missed so badly, you hit yourself for 10");
                    health -= 10;
                }
                else current.takeDamage(dmg);

                // SHIELD
            } else if(action == 2){
                isDefending = true;
                System.out.print("- SHIELD UP! -");

                // HEAL
            } else if(action == 3){
                int h = (int)(Math.random() + heal + 1);
                health += h;
                System.out.println("You healed for " + h + " points. Current health: " + health);

                // PASS
            } else {
                System.out.println("You pass your turn.");
                speed++;
                System.out.println("Your speed has increased. Current speed: " + speed);
            }

            // Monster turn (if still alive)
            if(current.health() > 0){
                int monsterDmg = (int)Math.round(current.damage());
                // shield reduces damage first
                int dmgAfterShield = Math.max(0, monsterDmg - shield);
                if(shield > 0){
                    System.out.println("Monster hits your shield for " + Math.min(shield, monsterDmg) + " damage.");
                    shield = Math.max(0, shield - monsterDmg);
                }
                if(dmgAfterShield > 0){
                    health -= dmgAfterShield;
                    System.out.println("Monster deals " + dmgAfterShield + " damage to you. Health: " + health);
                }
            }

            // MONSTER'S TURN
             int speedCheck = (int)(Math.random() * 100);
             if(speedCheck <= speed){ // BONUS TURN!
              System.out.println(" --- BONUS TURN! ---");
              continue;

             }else {
                int incomingDamage = (int)(Math.random() * current.damage() + 1);
                if(isDefending) {
                    incomingDamage -= shield;
                    if(incomingDamage < 0) incomingDamage = 0;
                    System.out.println("CLANG! Your shield absorbed " + shield + " damage.");
                }
                health -= incomingDamage;
             }

             // Is our player defeated?

             if(health <= 0 ) {
                reportMonsters();
                System.out.println("------- YOU LOSE --------");
                break;
             }
            // do i need a new monster?
            if(current.health() <= 0){
                System.out.println(" YOU HAVE SLAIN A MONSTER!!!!");
                current = getNextMonster();
                reportMonsters();
                continue; //take it from the top
            }
        
        }

        if(health <= 0){
            System.out.println("You have been defeated. Game over.");
        } else {
            System.out.println("All monsters defeated! You win!");
        }

    }

    public static int percentComplete(){
        // how many monsters are still alive
        int alive = monsterCount(0);
        return (int)(((double)(monsters.length - alive) / monsters.length) * 100);
    }
public static void reportMonsters() {
        System.out.println("\n-------------PLAYER REPORT-------------");
        System.out.println("HEALTH: " + health);
        System.out.println("ATTACK POWER: " + damage);
        System.out.println("SPEED: " + speed);
        System.out.println("SHIELD: " + shield);
        System.out.println("HEAL: " + heal);
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