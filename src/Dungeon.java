import java.util.Scanner;

public class Dungeon {
  public static void main(String[] args) {
    createDungeon(5, 5); // crea un dungeon de 5x5
    Scanner scanner = new Scanner(System.in); // pa leer input

    System.out.println("welcome to the DungeonQuest!"); // mensaje de bienvenida
    System.out.println("whats your name?"); // pregunta el nombre
    String name = scanner.nextLine(); // lee el nombre
    player = new Player(name); // crea al jugador
    System.out.println(dungeon[player.getRow()][player.getCol()]); // muestra la sala inicial
    boolean hasWon = false;

    while (!(player.getLife() <= 0) && !hasWon) { // mientras el jugador siga vivo
      printDungeon(); // pinta el dungeon
      System.out.println(player); // muestra info del jugador
      System.out.println("\nchoose an action:"); // menu de opciones
      System.out.println("1. explore current room");
      System.out.println("2. move to another room");
      System.out.println("3. attack entity (if present)");
      System.out.println("4. give up and die");
      System.out.print("your choice: ");

      int choice = scanner.nextInt(); // lee la opcion
      scanner.nextLine(); // limpia el espacio que se queda al final

      switch (choice) { // segun lo que has elegido
        case 1:
          player.explore(dungeon[player.getRow()][player.getCol()]); // explora la sala
          break;
        case 2:
          System.out.println("\t  | N |  ");
          System.out.println("\tW - x - E");
          System.out.println("\t  | S |  ");
          System.out.print("direction (N/S/E/W)? ");
          char dir = scanner.nextLine().toUpperCase().charAt(0);
          if (dir == 'N' || dir == 'S' || dir == 'E' || dir == 'W') {
            if (player.move(dir, dungeon)) {
              hasWon = true;
            }
          } else {
            System.out.println("invalid direction! use N, S, E, or W.");
          }
          break;
        case 3:
          Room current = dungeon[player.getRow()][player.getCol()]; // sala actual
          if (current.getEntity() != null && current.getEntity().getLife() > 0) {
            player.attack(current.getEntity()); // ataca al enemigo
          } else {
            System.out.println("theres no entity to attack here!"); // no hay nadie para pelear
          }
          break;
        case 4:
          player.setLife(0); // Pierde todos los puntos de vida
          break;
        default:
          System.out.println("invalid choice! please enter 1, 2, 3, or 4."); // opcion invalida
          break;
      }

      if (hasWon) {
        System.out.println("\nmoltes feliçitats! u won");
      } else if (player.getLife() <= 0) {
        System.out.println("has perdio tolai");
      }
    }
  }

  // lista de tesoros disponibles
  static Treasure[] treasures = {
      new Treasure("crown", 100, 2.5, false),
      new Treasure("ring", 50, 0.3, false),
      new Treasure("necklace", 75, 1.0, false),
      new Treasure("some rare orb", 0, 1.5, true),
      new Treasure("other green and rare orb", 0, 1.5, true)
  };

  // lista de enemigos disponibles
  static Entity[] entities = {
      new Entity("dragon", 10, 3),
      new Entity("goblin", 2, 1),
      new Entity("troll", 8, 2)
  };

  static Room[][] dungeon; // el dungeon en si
  static Player player; // el jugador

  // metodo para crear el dungeon
  public static void createDungeon(int rows, int cols) {
    dungeon = new Room[rows][cols]; // inicializa el array
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        String type = generateRoomType(); // tipo de sala aleatorio
        Treasure treasure;
        if (Math.random() < 0.3) { // 30% de posibilidades de tener tesoro
          treasure = treasures[(int) (Math.random() * treasures.length)];
        } else {
          treasure = null; // no hay tesoro
        }
        Entity entity;
        if (Math.random() < 0.3) { // 30% de posibilidades de tener enemigo
          entity = entities[(int) (Math.random() * entities.length)];
        } else {
          entity = null; // no hay enemigo
        }
        dungeon[i][j] = new Room(type, treasure, entity); // crea la sala

        if (i == rows - 1 && j == cols - 1) {
          dungeon[i][j].getDoors()[1] = true; // puerta sur abierta siempre
        }
      }
    }

    // conecta las puertas entre salas
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        boolean[] doors = dungeon[i][j].getDoors();

        if (i > 0 && doors[0]) {
          dungeon[i - 1][j].getDoors()[1] = true; // conecta norte con sur
        }

        if (i < rows - 1 && doors[1]) {
          dungeon[i + 1][j].getDoors()[0] = true; // conecta sur con norte
        }

        if (j < cols - 1 && doors[2]) {
          dungeon[i][j + 1].getDoors()[3] = true; // conecta este con oeste
        }

        if (j > 0 && doors[3]) {
          dungeon[i][j - 1].getDoors()[2] = true; // conecta oeste con este
        }
      }
    }
  }

  // genera el tipo de sala aleatorio
  public static String generateRoomType() {
    double r = Math.random();
    if (r < 0.6) {
      return "normal";
    } else if (r < 0.75) {
      return "bridge"; // 15% puente
    } else if (r < 0.9) {
      return "web"; // 15% telaraña
    } else {
      return "chapel"; // 10% de capilla
    }
  }

  // pinta el dungeon en consola
  public static void printDungeon() {
    for (int i = 0; i < dungeon.length; i++) {
      for (int j = 0; j < dungeon[i].length; j++) {
        if (player.getRow() == i && player.getCol() == j) {
          System.out.print("& "); // posicion del jugador
        } else if (dungeon[i][j].isExplored()) {
          System.out.print("* "); // sala explorada
        } else {
          System.out.print("- "); // sala no explorada
        }
      }
      System.out.println();
    }
  }
}