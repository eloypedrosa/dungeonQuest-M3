public class Entity {
  private String name; // nombre del enemigo
  private int life; // vida del enemigo
  private int escapePenalty; // daño al escapar

  public Entity(String name, int life, int escapePenalty) {
    this.name = name;
    this.life = life;
    this.escapePenalty = escapePenalty;
  }

  // getters y setters basicos
  public int getLife() {
    return life;
  }

  public String getName() {
    return name;
  }

  public int getEscapePenalty() {
    return escapePenalty;
  }

  public void setLife(int life) {
    this.life = life;
  }

  @Override
  public String toString() {
    return "monster: " + name + ", life: " + life; // info del enemigo
  }
}