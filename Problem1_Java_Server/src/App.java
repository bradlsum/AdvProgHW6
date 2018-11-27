import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class App {
    //static ServerSocket variable
    private static ServerSocket server;
    //socket server port on which it will listen
    private static int port = 9876;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Library library = new Library();

        Author a1 = new Author("John");
        Author a2 = new Author("Luke");

        Publisher p = new Publisher("Pearson");

        library.addBook("Book1","Shelf3", 1999,a1, p);
        library.addBook("Book2","Shelf3", 1999,a2, p);

        server = new ServerSocket(port);

        while (true) {
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

            switch (message.toLowerCase()) {
                case "print":
                    oos.writeObject(library.printBookTitles());
                    break;
                case "add":
                    //library.addBook();
                    break;
                case "exit":
                    //close resources
                    ois.close();
                    oos.close();
                    socket.close();
                    break;
            }
        }
//        System.out.println("Shutting down Socket server!!");
//        //close the ServerSocket object
//        server.close();
    }
}
