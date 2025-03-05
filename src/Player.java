import java.util.Random;

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



}
