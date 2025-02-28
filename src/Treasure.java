public class Treasure {

    
    // Attributes

    private String name;
    private int value;
    private double weight;

    // Builders

    public Treasure (String name, int value){
        this.name = name;
        this.value = value;
        this.weight = value * 0.2;

    } 

    // Methods

    public String toString(){
        return "Name: " + this.name + "\n" + "Value: " + this.value + " gold"

    }




}
