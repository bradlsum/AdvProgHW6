/**
 * This is the class Author. It models Book Authors.
 */
public class Author {

    private String name;

    /**
     * Author Constructor.
     * @param name Name of the author.
     */
    public Author(){
        this.name = "";
    }

    public Author(String name){
        this.name = name;
    }

    /**
     *
     * @return author's name
     */
    public String getName(){
        return name;
    }


    /**
     * Sets the author's name
     * @param name
     */
    public void setName (String name){
        this.name = name;
    }

    /**
     * The toString method converts an object to a string. It is used to display the object in a println call.
     * @return
     */
    public String toString(){
        return "The author is called:"+name;
    }
}
