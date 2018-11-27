// Sumner Bradley

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Library {
    ArrayList<Book> books = new ArrayList<>();
    Map<String, Author> authors = new HashMap<>();
    Map<String, Publisher> publishers = new HashMap<>();

    public String addBook(String message){
        int yearPub = 0;
        String title = "", locationCode = "", author ="", publisher = "";

        title = message.substring(0, message.indexOf("$"));
        System.out.println(title);
        message = message.substring(message.indexOf("$")+1);

        author = message.substring(0, message.indexOf("$"));
        System.out.println(author);
        message = message.substring(message.indexOf("$")+1);

        publisher = message.substring(0, message.indexOf("$"));
        System.out.println(publisher);
        message = message.substring(message.indexOf("$")+1);

        locationCode = message.substring(0, message.indexOf("$"));
        System.out.println(locationCode);
        message = message.substring(message.indexOf("$")+1);

        yearPub = Integer.parseInt(message);
        System.out.println(yearPub);

        if (this.authors.containsKey(author) && this.publishers.containsKey(publisher))
            this.books.add(new PrintedBook(title,locationCode,yearPub,this.authors.get(author),this.publishers.get(publisher)));
        else if (!this.authors.containsKey(author) && this.publishers.containsKey(publisher))
            this.books.add(new PrintedBook(title,locationCode,yearPub,new Author(author),this.publishers.get(publisher)));
        else if (this.authors.containsKey(author) && !this.publishers.containsKey(publisher))
            this.books.add(new PrintedBook(title,locationCode,yearPub,this.authors.get(author),new Publisher(publisher)));
        else if (!this.authors.containsKey(author) && !this.publishers.containsKey(publisher))
            this.books.add(new PrintedBook(title,locationCode,yearPub,new Author(author),new Publisher(publisher)));

        return title + " added to the library.";
    }

    public void addBook(String title,String locationCode, int yearPub, Author author, Publisher publisher){
        this.books.add(new PrintedBook(title,locationCode,yearPub,author,publisher));
    }

    public String printBookTitles(){
        String temp = "";

        for (Book book:this.books) {
            temp += book.getTitle() +", ";
        }

        return temp;
    }

    public String printBookInfo(String title){
        System.out.println("Title: " + title);
        System.out.println("Number of books: " + this.books.size());
        for (Book book:
             this.books) {
            if (book.getTitle().toLowerCase().equals(title.toLowerCase())){
                System.out.println(book.getTitle());
                return book.getTitle() + "\n" + book.authorList() + "\n" + book.getPublisher().getName() + "\n" + book.getLocationCode() + "\n" + book.getYearPub() + "\n";
            }
            else System.out.println(title + "!=" + book.getTitle());
        }

        System.out.println("Not found...");
        return "Not found";
    }
}
