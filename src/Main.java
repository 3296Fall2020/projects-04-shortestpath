import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.io.*;

public class Main {

    // main method for the project
    public static void main(String[] args) {
        Cities[] cities = new Cities[150];  //array for max cities 150
        int countOfCities = 0;                  // the number of cities
        Edges[] links = new Edges[1700];  // array for max of edges 1700
        int countOfLinks = 0;                  // the number of links

        //a new scrollable map of the city and their corresponding links
        DrawTheMap(countOfCities, cities, countOfLinks, links);


        //get the users input (starting point and destination)
        InputOfTheUser(cities, countOfCities);

    }
    public static void InputOfTheUser(Cities[] city, int cityCount){
        String currentPosition;   // current position of the user
        String destination;    // destination where the user wants to go
        System.out.println("group Members : Faris, Andreas, Francesko, Tonmoy, Xhuljano");

        System.out.println("Hello, World - Andreas G");
        System.out.println("Hello, World - Tonmoy C");
        System.out.println("Hello, World - Xhuljano R");

        Scanner user = new Scanner(System.in);

        System.out.println("Please enter name of the city followed by the state you want to start from: ");
        currentPosition = user.nextLine();
        System.out.println("Please enter name of the city followed by the state you want to go to: ");
        destination = user.nextLine();

        System.out.println("The shortest path from " + currentPosition+ " to " + destination +":");
        System.out.println("Coming Soon :)!");

    }

    static void DrawTheMap(int cityCount, Cities[] city, int linksCount, Edges[] link){
        // using Jframe to create a frame for the map
        JFrame mapFrame = new JFrame();

        // set the frame's properties
        mapFrame.setTitle("Our list of U.S Cities (Not All)");
        mapFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mapFrame.setLayout(new BorderLayout());
        mapFrame.setSize(800, 600);
        mapFrame.setResizable(true);

        // create an instance of CityMap
        MapOfCities map = new MapOfCities(cityCount, city, linksCount, link);

        // put the map on a ScrollPane in the frame
        mapFrame.add(new JScrollPane(map), BorderLayout.CENTER);
        // show the map
        mapFrame.setVisible(true);
    }// end DrawTheMap()
}
