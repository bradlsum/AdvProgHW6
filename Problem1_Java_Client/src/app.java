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

        //get the localhost IP address, if server is running on some other IP, you need to use that
        InetAddress host = InetAddress.getLocalHost();
        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;

        socket = new Socket(host.getHostName(), 9876);


        while (selection.toLowerCase() != "3"){
            System.out.println("Welcome to the library. Please select an option:\n" +
                    "1.\tPrint titles\n" +
                    "2.\tDisplay book info\n" +
                    "3.\tAdd book\n" +
                    "4.\tExit\n");

            oos = new ObjectOutputStream(socket.getOutputStream());

            selection = sc.nextLine();
            switch (selection){
                case "1":
                    oos.writeObject("print");
                    break;
                case "2":
                    System.out.print("Enter book title: ");
                    oos.writeObject("info," + sc.nextLine());
                    break;
                case "3":
                    oos.writeObject("add");
                    break;
                default:
                    System.out.println("Invalid selection...");
            }


            ois = new ObjectInputStream(socket.getInputStream());
            String message = (String) ois.readObject();

            System.out.println("Message: " + message);
        }
        oos.writeObject("exit");
        //close resources
        ois.close();
        oos.close();
        Thread.sleep(100);

    }
}