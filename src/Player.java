public class Player {
  private String name; // nombre del jugador
  private int life; // vida del jugador
  private int attack; // fuerza de ataque
  private int experience; // experiencia (no se usa mucho)
  private int agility; // agilidad pra esquivar
  private int strength; // fuerza para cargar tesoros
  private int row, col; // posicion en el dungeon
  private Treasure[] equipment; // tesoros que lleva
  private int equipmentCount; // cuantos tesoros lleva

  public Player(String name) {
    this.name = name;
    this.life = 5 + (int) (Math.random() * 16); // vida aleatoria
    this.attack = 1 + (int) (Math.random() * 4); // ataque aleatorio
    this.experience = 0; // empieza con 0 xp
    this.agility = 4 + (int) (Math.random() * 8); // agilidad aleatoria
    this.strength = 4 + (int) (Math.random() * 8); // fuerza aleatoria
    this.row = 0; // empieza en la esquina
    this.col = 0; // arriba a la izq
    this.equipment = new Treasure[strength]; // espacio segun su fuerza
    this.equipmentCount = 0; // empieza sin nada
  }

  // getters basicos
  public int getLife() {
    return life;
  }

  public int getRow() {
    return row;
  }

  public int getCol() {
    return col;
  }

  // metodo pa explorar la sala
  public void explore(Room room) {
    if (!room.isExplored()) { // si no esta explorada
      room.setExplored(true); // la marca como explorada
      if (room.getTreasure() != null && equipmentCount < equipment.length) {
        if (room.getTreasure().isTp()) { // es el tesoro un tp?
          int newRow = (int) (Math.random() * Dungeon.dungeon.length);
          int newCol = (int) (Math.random() * Dungeon.dungeon[0].length);

          this.row = newRow;
          this.col = newCol;

          System.out.println("woooosh! el magic orb te ha teleportao a otra sala!");
          System.out.println(Dungeon.dungeon[row][col]);
        } else {
          equipment[equipmentCount++] = room.getTreasure();
          System.out.println(name + " found a treasure!");
          System.out.println(room.getTreasure());
        }
      } else {
        System.out.println("no treasure found or equipment full"); // no hay espacio
      }
    } else {
      System.out.println("room already explored"); // ya estaba explorada
    }
  }

  // metodo pa moverse
  public boolean move(char direction, Room[][] dungeon) {
    Room currentRoom = dungeon[row][col]; // sala actual
    boolean canMove = false; // flag pa saber si se puede mover
    direction = Character.toUpperCase(direction); // convierte a mayuscula

    // chequea si se cae del dungeon
    if ((direction == 'N' && row == 0) ||
        (direction == 'S' && row == dungeon.length - 1) ||
        (direction == 'E' && col == dungeon[0].length - 1) ||
        (direction == 'W' && col == 0)) {

      // esquina inferior derecha es la salida
      if (row == dungeon.length - 1 && col == dungeon[0].length - 1 &&
          (direction == 'S' || direction == 'E')) {
        System.out.println("you win...");
        return true; // se a ganado!!!
      } else {
        System.out.println("u fall from the dungeon"); // se cayo
      }
      life = 0; // se muere
      return false;
    }

    // chequea direcciones
    if (direction == 'N' && currentRoom.getDoors()[0] && row > 0) {
      canMove = true; // puede ir al norte
    }
    if (direction == 'S' && currentRoom.getDoors()[1] && row < dungeon.length - 1) {
      canMove = true; // puede ir al sur
    }
    if (direction == 'E' && currentRoom.getDoors()[2] && col < dungeon[0].length - 1) {
      canMove = true; // puede ir al este
    }
    if (direction == 'W' && currentRoom.getDoors()[3] && col > 0) {
      canMove = true; // puede ir al oeste
    }

    if (canMove) {
      // efectos especiales de salas
      if (currentRoom.getType().equals("web")) {
        System.out.println("uoure trying to move through a sticky web.."); // telaraña
        if (!roll(strength)) { // intenta liberarse
          System.out.println("failed to escape the web! You're stuck for now");
          return false;
        }
        System.out.println("you are free from the web!");
      } else if (currentRoom.getType().equals("bridge")) {
        System.out.println("carefully crossing the shaky bridge.."); // puente

        if (!roll(agility)) { // intenta cruzar
          System.out.println("oh no.. fell from the bridge! lost 1 life");
          life--;
          if (life <= 0) {
            return false;
          }

        } else {
          System.out.println("you made it safely across!");
        }
      }

      // actualiza posicion
      if (direction == 'N') {
        row--;
      } else if (direction == 'S') {
        row++;
      } else if (direction == 'E') {
        col++;
      } else if (direction == 'W') {
        col--;
      }

      Room newRoom = dungeon[row][col]; // nueva sala
      System.out.println("\nyou moved to a new room:");
      if (newRoom.getType().equals("chapel") && !newRoom.isExplored()) {
        int healAmount = 5 + (int) (Math.random() * 6); // cura entre 5-10
        life += healAmount;
        System.out.println("\nYou entered a healing sanctuary! +" + healAmount + " life restored.");
        newRoom.setExplored(true); // marcamos como explorada
      }

      System.out.println(newRoom);

      // ataque sorpresa de enemigos
      if (newRoom.getEntity() != null && newRoom.getEntity().getLife() > 0) {
        System.out.println("the " + newRoom.getEntity().getName() + " attacks as you enter!");
        life -= newRoom.getEntity().getEscapePenalty();
        System.out.println("you took " + newRoom.getEntity().getEscapePenalty() + " damage!");
      }
    } else {
      System.out.println("you cant move in that direction! check available exits"); // no se puede mover
      System.out.println(currentRoom); // muestra la sala actual
    }

    return false;
  }

  // metodo pa atacar
  public void attack(Entity entity) {
    if (entity != null && entity.getLife() > 0) {
      int damage = 1 + (int) (Math.random() * attack); // daño aleatorio
      entity.setLife(entity.getLife() - damage); // aplica daño

      System.out.println(name + " attacks and deals " + damage + " damage!");

      if (entity.getLife() <= 0) { // si mata al enemigo
        int xpGain = damage * 2;
        experience += xpGain;
        System.out.println(name + " defeated the entity and gained " + xpGain + " XP!");
      } else {
        System.out.println(entity.getName() + " counterattacks!"); // contraataque
        life--; // pierde vida
      }
    } else {
      System.out.println("no entity to attack!"); // no hay nadie pa pelear
    }
  }

  // metodo interno pa tirar dados
  private boolean roll(int stat) {
    int roll = 1 + (int) (Math.random() * 12); // tira un dado de 12
    return roll <= stat; // exito si roll es menor que stat
  }

  @Override
  public String toString() {
    String result = name + " - Life: " + life + ", Agility: " + agility + ", Strength: " + strength + ", Pos: (" + row
        + "," + col + ")\n";
    result += "Equipment: ";
    for (int i = 0; i < equipmentCount; i++) {
      if (equipment[i] != null) {
        result += equipment[i].getName() + " "; // lista equipamiento
      }
    }
    result += "\n";
    return result;
  }
}