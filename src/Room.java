import java.util.Random;

public class Room {
    // String[] type = {"normal", "brigde", "cobweb"}; 

    Random random = new Random();

    private int randomNum = (int) (Math.random() * ((2 - 0) + 1));
    private String[] arTypes = { "normal", "bridge", "cobweb" };
    private String type;

    private Treasure treasure;
    private Entity entity;

    private char[] doors = { 'N', 'W', 'E', 'S' };
    private boolean explored;

    public Room() {
        generateRoom();

        // if bool random treasure is true we set the treasure, else we dont;

        // if bool random entity is true we set the entity, else we dont;
        // this.entity = new Entity();
        this.explored = false;

    }

    private void generateRoom() {
        double rand = random.nextDouble(10);

        if (rand > 3) {
            this.type = arTypes[0];
        } else if (rand > 1.5) {
            this.type = arTypes[1];
        } else {
            this.type = arTypes[2];
        }

        if (random.nextBoolean()) {
            // this.treasure = new Treasure();
        }
        if (random.nextBoolean()) {
            // this.entity = new Entity();
        }

    }

    public String getType() {
        return this.type;
    }

    public Treasure getTreasure() {
        return treasure;
    }

    public void explore() {
        this.explored = true;
        if (this.treasure != null) {

        }
        // loose();

    }

    @Override
    public String toString() {
        return super.toString();
    }

}