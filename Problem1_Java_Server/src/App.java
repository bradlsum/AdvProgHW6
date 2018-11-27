// Sumner Bradley

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class App {
    private static ServerSocket server;
    private static int port = 9876;
    private static Socket socket = null;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Library library = new Library();

        Author a1 = new Author("John");
        Author a2 = new Author("Luke");

        Publisher p = new Publisher("Pearson");

        library.addBook("Book1","Shelf3", 1999,a1, p);
        library.addBook("Book2","Shelf3", 1999,a2, p);

        server = new ServerSocket(port);

        while (true) {
            System.out.println("Waiting for a request");

            Socket socket = server.accept();
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            String message = (String) ois.readObject();
            System.out.println("Message Received: " + message);

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            switch (message.substring(0, message.indexOf("$")).toLowerCase()) {
                case "print":
                    oos.writeObject(library.printBookTitles());
                    break;
                case "info":
                    oos.writeObject(library.printBookInfo(message.substring(message.indexOf("$")+1, message.length())));
                    break;
                case "add":
                    oos.writeObject(library.addBook(message.substring(message.indexOf("$")+1, message.length())));
                    break;
                case "exit":
                    //close resources
                    ois.close();
                    oos.close();
                    socket.close();
                    break;
            }
        }
    }
}
