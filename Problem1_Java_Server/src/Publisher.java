public class Publisher {
    private String name;

    public Publisher(){
        this.name = "";
    }

    public Publisher(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * The toString method converts an object to a string. It is used to display the object in a println call.
     * @return
     */
    public String toString(){
        return "The publisher is called:"+name;
    }
}
