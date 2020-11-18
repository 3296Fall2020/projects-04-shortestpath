//import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.util.*;
import java.io.*;
import java.net.URL;
import java.net.HttpURLConnection;

public class Main {
    // main method for the project
    public static void main(String[] args) /*throws ParseException*/{

        //testSearch();

        Cities[] cities = new Cities[170]; //array of cities (Vertices) max = 170
        for (int i = 0; i < cities.length; i++) {
            cities[i] = new Cities();
        }

        Edges[] links = new Edges[1750];// array of links  (Edges)  max = 1750
        for (int i = 0; i < links.length; i++) {
            links[i] = new Edges();
        }

        int countOfCities; //number of cities
        int countOfLinks; //number of links

        // load cities from the cvs file
        countOfCities = loadCities(cities);

        // load links from the cvs file
        countOfLinks = loadLinks(links, cities);


        //a new scrollable map of the city and their corresponding links
        DrawTheMap(countOfCities, cities, countOfLinks, links);



        //get the users input (starting point and destination)
        InputOfTheUser(cities, countOfCities);


    } // end main
    //************************************************************************

    // method to read city data into an array from a data file
    public static int loadCities(Cities[] cities) {

        int count = 0; // count for the number of cities

        String[][] citiesData = new String[170][3]; // datas in our file
        String delimiter = ",";                     // the delimiter in cvs file
        String line;                                // this string holds each line from the file


        // our file with the list of cities
        String fileName = "citiess.csv";

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
                citiesData[count] = line.split(delimiter);

                // read data from the 2D array into an array of Cities objects
                cities[count].setName(citiesData[count][0]);
                cities[count].setX(Integer.parseInt(citiesData[count][1]));
                cities[count].setY(Integer.parseInt(citiesData[count][2]));

                count++;
            }// end while

            infile.close();

        } catch (IOException e) {
            // error message dialog box with custom title and the error icon
            JOptionPane.showMessageDialog(null, "File not found: " + fileName, "Error", JOptionPane.ERROR_MESSAGE);
        }
        return count;

    } // end loadCities()

    //    *************************************************************************

    static int loadLinks(Edges[] links, Cities[] cities) {

        // count for the number of links
        int count = 0;

        String[][] CitiesLinksArray = new String[1200][3]; // datas in our file
        String delimiter = ",";                        // the delimiter in cvs file
        String line;				                   // this string holds each line from the file


        // thats our file withe the links
        String fileName = "linkss.csv";

        try {
            // Create a Scanner to read the input from a file
            Scanner infile = new Scanner(new File(fileName));

            /* This while loop reads lines of text into an array. it uses a Scanner class
             * boolean function hasNextLine() to see if there another line in the file.
             */
            while (infile.hasNextLine()) {
                // read the line
                line = infile.nextLine();

                // split the line into separate objects and store them in a row in the array
                CitiesLinksArray[count] = line.split(delimiter);

                // read link data from the 2D array into an array of Edges objects
                // set the source for the vertex which is 1st column in the file
                links[count].setSource(findCities(cities, CitiesLinksArray[count][0]));
                // set the destination which will be the 2nd column in the file
                links[count].setDestination(findCities(cities, CitiesLinksArray[count][1]));
                //set the length between each other which is the 3rd column in the file
                links[count].setLength(Integer.parseInt(CitiesLinksArray[count][2]));

                count++;

            }// end while

        } catch (FileNotFoundException e) {
            // error message dialog box with custom title and the error icon
            JOptionPane.showMessageDialog(null, "File not found: " + fileName, "Error", JOptionPane.ERROR_MESSAGE);
        }
        return count;
    } // end loadLinks()

    //    *******************************************************************************
    static Cities findCities(Cities[] cities, String c) {
        int index = 0;  // loop counter
        // This will go through the cities array until the name of the city is found
        while (cities[index].getName().compareTo(c) != 0) {
            index++;
        }// end while()
        return cities[index];

    } // end findCities()

    //    *******************************************************************************

    public static void InputOfTheUser(Cities[] cities, int countOfCities){
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

    static void DrawTheMap(int countOfCities, Cities[] cities, int countOfLinks, Edges[] links){
        // using Jframe to create a frame for the map
        JFrame mapFrame = new JFrame();

        // set the frame's properties
        mapFrame.setTitle("Our list of U.S Cities (Not All)");
        mapFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mapFrame.setLayout(new BorderLayout());
        mapFrame.setSize(800, 600);
        mapFrame.setResizable(true);

        // create an instance of CityMap
        MapOfCities map = new MapOfCities(countOfCities, cities, countOfLinks, links);

        // put the map on a ScrollPane in the frame
        mapFrame.add(new JScrollPane(map), BorderLayout.CENTER);

        map.addMouseListener(new MouseAdapter() {// provides empty implementation of all
            // MouseListener`s methods, allowing us to
            // override only those which interests us
            @Override //I override only one method for presentation
            public void mousePressed(MouseEvent e) {
                System.out.println(e.getX() + "," + e.getY());
            }
        });

        // show the map
        mapFrame.setVisible(true);


    }// end DrawTheMap()

    /*static void testSearch() throws ParseException {
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
    }*/
}
