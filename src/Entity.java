public class Entity {
    
    // Atributos

    private String name;
    private int hp;
    private int penalty;
    private boolean alive;

    // Constructores

    public Entity (String name, int hp, int penalty ){
        this.name = name;
        this.hp = hp;
        this.penalty = penalty;

    }

    // Metodos 

    // ----- GETTERS & SETTERS -----------------------------------------------------------------------------------------------
    
    public int getHp() {
        return hp;
    }
    public String getName() {
        return name;
    }
    public int getPenalty() {
        return penalty;
    }
    public boolean getAlive(){
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    // ---------------------------------------------------------------------------------------------------------------------------

    public void receiveDamage(int damage, Player player){
        this.hp -= damage;
        if (this.hp <= 0) {
            player.setExperiencie(player.getExperiencie() + (this.hp * 2));
            this.alive = false;
            
            System.out.println("Enemy fainted!");
            System.out.println("You won " + (this.hp * 2) + " points of experience!");

        } else {
            player.receiveDamage(1);

        }
        
    }

}
