import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Library {
    ArrayList<Book> books = new ArrayList<>();
    Map<String, Author> authors = new HashMap<>();
    Map<String, Publisher> publishers = new HashMap<>();

    public void addBook(String title, String locationCode, int yearPub, Author author, Publisher publisher){
        books.add(new PrintedBook(title,locationCode,yearPub,author,publisher));
    }

    public String printBookTitles(){
        String temp = "";

        for (Book book:this.books) {
            temp += book.getTitle() +", ";
        }

        return temp;
    }

}
