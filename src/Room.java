public class Room {
    private int randomNum = (int) (Math.random() * 12);
    private String[] arTypes = { "normal", "normal", "normal", 
            "normal", "normal", 
            "normal", "normal", 
            "bridge", "bridge",
            "cobred", "cobred" };

    private String type;

    public void Rooom() {
        this.type = this.arTypes[this.randomNum];
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }

}