import java.util.*;
import java.io.*;

public class Main {

    // main method for the project
    public static void main(String[] args) {
        String currentPosition;   // current position of the user
        String destination;    // destination where the user wants to go
        System.out.println("group Members : Faris, Andreas, Francesko, Tonmoy, Xhuljano");

        System.out.println("Hello, World - Andreas G");
        System.out.println("Hello, World - Tonmoy C");
        System.out.prinln("Hello, World - Xhuljano R");

        Scanner user = new Scanner(System.in);

        System.out.println("Please enter name of the city followed by the state you want to start from: ");
        currentPosition = user.nextLine();
        System.out.println("Please enter name of the city followed by the state you want to go to: ");
        destination = user.nextLine();

        System.out.println("The shortest path from " + currentPosition+ " to " + destination +":");
        System.out.println("Coming Soon :)!");
    }
}
