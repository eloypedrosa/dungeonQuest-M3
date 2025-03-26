
public class Treasure {

    
    // Attributes

    private String name;
    private int value;
    private double weight;
    private int specialEffect;
 

    // Builders

    public Treasure (String name, int value, int specialEffect){
        this.name = name;
        this.value = value;
        this.weight = value * 0.2;
        this.specialEffect = specialEffect;
    } 

    // Methods
    public double getWeight() {
        return weight;
    }

    
@Override
    public String toString(){
        return "Name: " + this.name + "\n" + "Value: " + this.value + " gold";

    }

    // The is that when you pick up these treasure it will have a special
    // effect like leveling up any of the stats of the character
   public void specialEffect(Player player){
        if (this.specialEffect == 1){
            // In special effect 1 the player will recieve a buff to his attack value
            player.setAttackValue(player.getAttackValue() + 5);
            System.out.println("The tresure was blessed and you recieved 5 points of attack");

        } else if (this.specialEffect == 2) {
            // In special effect 2 the player will recieve 5 points of damage
            player.setHp(player.getHp() - 5);
            System.out.println("The tresure was cursed and you recieved 5 of damage!");
            
        }
    }
}
