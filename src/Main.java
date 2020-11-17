import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.util.*;
import java.io.*;
import java.net.URL;
import java.net.HttpURLConnection;

public class Main {

    // main method for the project
    public static void main(String[] args) throws ParseException{

        //testSearch();

        Cities[] cities = new Cities[150]; //array of cities (Vertices) max = 150
        for (int i = 0; i < cities.length; i++) {
            cities[i] = new Cities();
        }

        Edges[] links = new Edges[1750];// array of links  (Edges)  max = 1750
        for (int i = 0; i < links.length; i++) {
            links[i] = new Edges();
        }

        int countOfCities; //number of cities
        int countOfLinks; //number of links

        // load cities into an array from a datafile
        countOfCities = loadCities(cities);

        // load links into an array from a datafile
        countOfLinks = 0;


        //a new scrollable map of the city and their corresponding links
        DrawTheMap(countOfCities, cities, countOfLinks, links);


        //get the users input (starting point and destination)
        InputOfTheUser(cities, countOfCities);
    } // end main
    //************************************************************************

    // method to read city data into an array from a data file
    public static int loadCities(Cities[] cities) {

        int count = 0; // number of cities[] elements with data

        String[][] cityData = new String[200][3]; // holds data from the city file
        String delimiter = ",";                   // the delimiter in a csv file
        String line;                              // a String to hold each line from the file


        // our file with the list of cities
        String fileName = "Cities.csv";

        try {
            // Create a Scanner to read the input from a file
            Scanner infile = new Scanner(new File(fileName));

            /* This while loop reads lines of text into an array. it uses a Scanner class
             * boolean function hasNextLine() to see if there is another line in the file.
             */
            while (infile.hasNextLine()) {
                // read the line
                line = infile.nextLine();

                // split the line into separate objects and store them in a row in the array
                cityData[count] = line.split(delimiter);

                // read data from the 2D array into an array of City objects
                cities[count].setName(cityData[count][0]);
                cities[count].setX(Integer.parseInt(cityData[count][1]));
                cities[count].setY(Integer.parseInt(cityData[count][2]));

                count++;
            }// end while

            infile.close();

        } catch (IOException e) {
            // error message dialog box with custom title and the error icon
            JOptionPane.showMessageDialog(null, "File I/O error:" + fileName, "File Error", JOptionPane.ERROR_MESSAGE);
        }
        return count;

    } // end loadCities()
    //*************************************************************************

    public static void InputOfTheUser(Cities[] city, int cityCount){
        String currentPosition;   // current position of the user
        String destination;    // destination where the user wants to go

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

    static void testSearch() throws ParseException {
        // TESTING search (make get request, body will have location or put in url params, receive that locations geocode in the response)

        Search search = null;
        try {

            search = new Search(8000);

        } catch (IOException e) {
            e.printStackTrace();
        }

        // CAPITALIZE
        Map<String, String> location = new HashMap<>();

        location.put("city", "Philadelphia");
        location.put("state", "PA");

        double[] retval;

        try {
            retval = search.geocoding(location);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




