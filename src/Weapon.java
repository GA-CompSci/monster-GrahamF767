public class Weapon {
    // instance variables (this.stuff)
    private int damage;
    private String name;

    // constructor
    public Weapon(int damage, String name){
        // this.stuff = localStuff
        this.damage = damage;
        this.name = name;
    }

    // accessor
    public String getName(){
        return this.name;
    }
    public int getDamage() { return this.damage; }
}
