import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;

public class App {
    //static ServerSocket variable
    private static ServerSocket server;
    //socket server port on which it will listen
    private static int port = 9876;

    public static void main(String[] args) throws IOException, ClassNotFoundException{
//        Author a1 = new Author("John");
//        Publisher p = new Publisher("Pearson");
//        Author a2 = new Author("Luke");
//
//        System.out.println("Creating two authors (John and Luke) and one publisher (Pearson)");
//
//        System.out.println(a1);
//        System.out.println(a2);
//        System.out.println(p);
//
//
//        System.out.printf("%n%nCreating one book%n");
//        PrintedBook b1 = new PrintedBook("Book1","Shelf3", 1999,a1, p);
//
//        System.out.println(b1); //inspect the toString method in the class Book.
//
//        System.out.printf("%n%nAdding author Ben and removing author John%n");
//        b1.addAuthor(new Author("Ben"));
//        b1.removeAuthor(a1);
//        System.out.println(b1); //inspect the toString method in the class Book.
//
//        System.out.printf("%n%nCreating another book.%n");
//
//        PrintedBook b2 = new PrintedBook("Book2","Shelf3", 1999,a2, p);
//
//        System.out.println(b2);
//
//        System.out.printf("%n%nChanging Luke (author) name to 'New Luke'%n");
//        a2.setName("New Luke");
//        System.out.println(b2);
//
//        System.out.printf("%n%nAdding Luke2 and Luke3 to book%n");
//        b2.addAuthor(new Author("Luke2"));
//        b2.addAuthor(new Author("Luke3"));
//        System.out.println(b2);

        server = new ServerSocket(port);

        while(true){
            System.out.println("Waiting for client request");
            //creating socket and waiting for client connection
            Socket socket = server.accept();
            //read from socket to ObjectInputStream object
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            //convert ObjectInputStream object to String
            String message = (String) ois.readObject();
            System.out.println("Message Received: " + message);
            //create ObjectOutputStream object
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            //write object to Socket
            oos.writeObject("Hi Client "+message);
            //close resources
            ois.close();
            oos.close();
            socket.close();
            //terminate the server if client sends exit request
            if(message.equalsIgnoreCase("exit")) break;
        }
        System.out.println("Shutting down Socket server!!");
        //close the ServerSocket object
        server.close();
    }
}
