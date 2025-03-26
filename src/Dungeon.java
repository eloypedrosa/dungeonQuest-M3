import java.util.Random;

public class Dungeon {
    Random random = new Random();
    private int height = random.nextInt(10) + 2;
    private int width = random.nextInt(10) + 2;
    private Room[][] grid = new Room[height][width];
    

    public Dungeon() {
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public static void main(String[] args) {
        Dungeon dungeon = new Dungeon();
        System.out.println();
    }

}