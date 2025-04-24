public class Room {
  private String type; // tipo de sala
  private Treasure treasure; // tesoro (puede ser null)
  private Entity entity; // enemigo (puede ser null)
  private boolean[] doors = new boolean[4]; // puertas (N,S,E,W)
  private boolean explored; // si ya fue explorada

  public Room(String type, Treasure treasure, Entity entity) {
    this.type = type;
    this.treasure = treasure;
    this.entity = entity;
    this.explored = false;

    // genera puertas aleatorias
    for (int i = 0; i < doors.length; i++) {
      doors[i] = Math.random() < 0.5; // 50% de posiibildad por puerta
    }
  }

  // getters y setters
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public boolean[] getDoors() {
    return doors;
  }

  public Entity getEntity() {
    return entity;
  }

  public boolean isExplored() {
    return explored;
  }

  public void setExplored(boolean explored) {
    this.explored = explored;
  }

  public Treasure getTreasure() {
    return treasure;
  }

  public void setTreasure(Treasure treasure) {
    this.treasure = treasure;
  }

  // mostrar informacion
  @Override
  public String toString() {
    String result = "\n=== room: " + type.toUpperCase() + " ===\n";

    if (type.equals("chapel") && !explored) {
      result += "no huele un poco raro aqui?...\n";
    }

    if (entity != null && entity.getLife() > 0) {
      result += "!!! cuidao !!! theres a " + this.entity.getName() + " here!\n";
      result += entity.toString() + "\n";
    } else {
      result += "no entities in this room.\n";
    }

    if (treasure != null && !explored) { // si la sala no ha sido explorada y hay tesoro
      result += "there might be treasure here!\n";
    }

    result += "available exits: ";

    // lista puertas disponibles
    if (doors[0]) {
      result += "N ";
    }
    if (doors[1]) {
      result += "S ";
    }
    if (doors[2]) {
      result += "E ";
    }
    if (doors[3]) {
      result += "W ";
    }

    result += "\n";
    return result;
  }
}