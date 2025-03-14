import java.util.Random;
import java.util.Scanner;

public class Player {
    
    // Attributes

    private String name;
    private int hp;
    private int attackValue;
    private int experiencie;
    private int agility;
    private int strength;
    private int posX;
    private int posY;
    private Treasure [] inventory; 

    // Builders

     public Player (String name, int hp, int attack, int agility, int strength){
        this.name = name;
        this.hp = hp;
        this.attackValue = attack;
        experiencie = 0;
        this.agility = agility;
        this.strength = strength;
        posX = 0;
        posY = 0;
         Treasure[] inventory = new Treasure[strength];

     }

    // Methods

     // --------------------------- GETTERS & SETTERS -----------------------------------------------------------------------

     public int getHp() {
         return hp;
     }

     public void setHp(int hp) {
         this.hp = hp;
     }

     public int getAgility() {
         return agility;
     }

     public int getPosX() {
         return posX;
     }

     public int getPosY() {
         return posY;
     }

     public void setPosX(int posX) {
         this.posX = posX;
     }

     public void setPosY(int posY) {
         this.posY = posY;
     }

     public int getExperiencie() {
         return experiencie;
     }

     public void setExperiencie(int experiencie) {
         this.experiencie = experiencie;
     }


     // -----------------------------------------------------------------------------------------------------------------------
         
    public void receiveDamage(int damage){
      this.hp -= damage;
      if (this.hp <= 0) {
         // Function die / lose (to do)
         // lose();
         
      }
  }



     public void attackEntity (Entity entity) {
      
      Random random = new Random(); 

      entity.receiveDamage(random.nextInt(this.attackValue), this);

     }

   public String toString () {
      return "Nom: " + name + "\n" +
            "HP: " + hp + "\n" +
            "Attack: " + attackValue + "\n" +
            "Experiencie: " + experiencie + "\n" +
            "Agility: " + agility + "\n" +
            "Strenght: " + strength + "\n" +
            "Position: " + posX + ", " + posY + "\n" +
            "Inventory: " + inventory + "\n";

   }


   public void explore (Treasure treasure){
         
         // If there is no treasure in the room
            // Show a message with telling the player that there
            // is no chest in the room
         
         // If there is a chest in the room
            // Show the info of the treasure
         
               // Ask the player if he wants to pick
               // the tresure ('y'/'n')
                 
                  // If he answers 'y'
                     // Add the treasure to the inventory
                 
                  // If he answers 'n'
                     // Show a message telling the player that he did not
                     // pick the treasure
                 
                  // If he answers any other thing
                     // Ask againt telling him that he has to answer 'y' or 'n'

           
   }


   public void move(char direction, char[][] dungeon) {   
    // North
    if (direction == 'N'){
            if (this.posY - 1 > 0){
                this.posY--;
                System.out.println("You moved to the north");
            } else {
                System.out.println("If you go to the North, you will leave the dungeon. ");
                leaveTheDungeon();

            }
        } else 
    // South
    if (direction == 'S'){
        if (this.posY + 1 > dungeon.length){
            this.posY++;
            System.out.println("You moved to the south");
        } else {
            System.out.println("If you go to the South, you will leave the dungeon. ");
            leaveTheDungeon();

        }
    } else 
    // West
    if (direction == 'W'){
        if (this.posX - 1 > 0){
            this.posX--;
            System.out.println("You moved to the west");
        } else {
            System.out.println("If you go to the West, you will leave the dungeon. ");
            leaveTheDungeon();

        }
    }
    // East
    if (direction == 'E'){
        if (this.posX - 1 > dungeon.length){
            this.posX--;
            System.out.println("You moved to the East");
        } else {
            System.out.println("If you go to the East, you will leave the dungeon. ");
            leaveTheDungeon();

        }
    } else {
        System.out.println("Invalid direction");


    }


   }

   // Use this to leave the dungeon, used in the function "move" to not repeat a lot of
   // code and it can e useful to have it for later

   private boolean leaveTheDungeon() {
    // Create variable answer and a Scanner used to ask the user if 
    // he is sure to leave the Dungeon.

    Scanner teclado = new Scanner(System.in);

        char answer = '*';

        // The user wont be able to keep going without answer Y or N (Yes or No)

    while (!(answer == 'Y') || !(answer == 'N')){
    System.out.println("Do you want to leave the dungeon? (Y/N)");
     answer = teclado.next().charAt(0);

   }
   // If it says yes we return the true, meaning that he will leave the dungeon
   if (answer == 'Y'){
    return true;

    // If not it will return false, meaning that he will stay in the dungeon (for now)
} else{
    return false;

}
}
}
