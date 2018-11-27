// Sumner Bradley

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class app {

    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
        String selection = "";
        Scanner sc = new Scanner(System.in);

        InetAddress host = InetAddress.getLocalHost();
        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;

        while (!selection.equals("4")){
            socket = new Socket(host.getHostName(), 9876);
            System.out.println("Welcome to the library. Please select an option:\n" +
                    "1.\tPrint titles\n" +
                    "2.\tDisplay book info\n" +
                    "3.\tAdd book\n" +
                    "4.\tExit\n");

            selection = sc.nextLine();
            switch (selection){
                case "1":
                    oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject("print$");
                    break;
                case "2":
                    oos = new ObjectOutputStream(socket.getOutputStream());
                    System.out.print("Enter book title: ");
                    oos.writeObject("info$" + sc.nextLine());
                    break;
                case "3":
                    System.out.print("Enter a title: ");
                    String book = sc.nextLine() + "$";

                    System.out.print("Enter author's name: ");
                    book += sc.nextLine() + "$";

                    System.out.print("Enter a publisher: ");
                    book += sc.nextLine() + "$";

                    System.out.print("Enter location: ");
                    book += sc.nextLine() + "$";

                    System.out.print("Enter the year published: ");
                    book += sc.nextLine();

                    oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject("add$" + book);
                    break;
                case "4":
                    oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject("exit$");
                    //close resources
                    oos.close();
                    socket.close();
                    return;
                default:
                    System.out.println("Invalid selection...");
            }


            ois = new ObjectInputStream(socket.getInputStream());
            String message = (String) ois.readObject();
            System.out.println(message);
        }
        oos.writeObject("exit");

        // Close resources
        ois.close();
        oos.close();
        socket.close();

        return;
    }
}