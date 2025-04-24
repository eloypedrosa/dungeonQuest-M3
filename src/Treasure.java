public class Treasure {
  private String name; // nombre del tesoro
  private int value; // valor en oro
  private double weight; // peso (no se usa para nada jeje...)

  private boolean isTp; // servira para id si es o no un teleproter

  public Treasure(String name, int value, double weight, boolean isTp) {
    this.name = name;
    this.value = value;
    this.weight = weight;
    this.isTp = isTp;
  }

  public String getName() {
    return name; // devuelve el nombre
  }

  public double getWeight() {
    return weight;
  }

  public boolean isTp() {
    return isTp;
  }

  @Override
  public String toString() {
    return "treasure: " + name + "\n, value: " + value + " gold coins"; // info del tesoro
  }
}