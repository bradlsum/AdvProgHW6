// Sumner Bradley

import java.util.ArrayList;

/**
 * Represent books in our library
 */
abstract public class Book {

    private String title;
    private String locationCode;
    private int yearPub;

    private ArrayList<Author> authors;
    private Publisher publisher;

    /**
     * Initializes the book
     * @param title book's title
     * @param location book's location in the library. it's usually a code like G70.212.R54
     * @param yearPub year of publication
     * @param author author object.
     * @param publisher publisher object.
     */

    public Book(){
        this.title = "";
        this.locationCode = "";
        this.yearPub = 0;

        this.publisher = new Publisher();
    }

    public Book ( String title, String location, int yearPub,
                  Author author, Publisher publisher){
        this.authors = new ArrayList<Author>();
        this.title =title;
        this.locationCode = location;
        this.yearPub = yearPub;

        this.addAuthor(author);
        this.publisher = publisher;

    }

    // This is not recommended as every book will have a unique author.
    // It is better to have the author object created elsewhere and shared (pass through parameter) with the book
    //    public Book ( String author){
    //        this.author = new Author(author);
    //
    //    }

    public ArrayList<Author> getAuthors() {
        return this.authors;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public int getYearPub() {
        return yearPub;
    }

    public void setYearPub(int yearPub) {
        this.yearPub = yearPub;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * The toString method converts an object to a string. It is used to display the object in a println call.
     * @return
     */
    public String toString(){
        return ("A book called '" + title +
                "' written by '" + this.authorList() +
                "' and published in " + yearPub+ " by '"+ publisher.getName()+"'");
    }

    public String authorList(){
        String temp = "";
        if (this.authors.size() == 1) return this.authors.get(0).getName();
        for (int i = 0; i < this.authors.size() - 1; ++i){
            temp += this.authors.get(i).getName() + ", ";
        }
        temp += this.authors.get(this.authors.size() - 1).getName();
        return temp;
    }

    public void addAuthor(Author a){
        this.authors.add(a);
    }

    public void removeAuthor(Author a){
        this.authors.remove(a);
    }

    public void removeAuthor(String a){
        for (int i = 0; i < this.authors.size(); ++i){
            if (this.authors.get(i).getName() == a) this.authors.remove(this.authors.get(i));
        }
    }
}
