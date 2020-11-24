import java.awt.*;
import javax.swing.*;

public class MapOfCities extends JPanel {
    Cities[] cities = new Cities[170];  //array for max cities 170
    int countOfCities;                  // the number of cities
    Edges[] links = new Edges[1750];  // array for max of edges 1750
    int countOfLinks;                  // the number of links

    private int sourceX = 0;
    private int sourceY = 0;
    private int destX = 0;
    private int destY = 0;

    MapOfCities() {
    }

    MapOfCities(int cityCount, Cities[] city, int linkCount, Edges[] link) {
        setPreferredSize(new Dimension(1800, 900));
        this.cities = city;
        this.countOfCities = cityCount;
        this.links = link;
        this.countOfLinks = linkCount;

    } // end Cities Canvas


    public void paint(Graphics g) {


        // color for the background
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 1950, 1350);

        // place our image under map
        Image UsaImage = new ImageIcon("usa.jpg").getImage();
        g.drawImage(UsaImage, 280, 0, null);

        // draw cities
        for (int i = 0; i < countOfCities; i++) {
            //This method will draw a dot for each city
            g.setColor(Color.ORANGE);
            g.fillOval(cities[i].getX() - 3, cities[i].getY() - 3, 8, 8);

        } // end for

        // draw labels
        for (int i = 0; i < countOfCities; i++) {
            // draw a label for each city
            g.setColor(Color.RED);
            g.setFont(new Font("Albertus Extra Bold", Font.BOLD, 12));
            g.drawString(cities[i].getName(), cities[i].getX() + 6, cities[i].getY() + 9);
        } // end for

        // draw links
        for (int i = 0; i < countOfLinks; i++) {

            // get the coordinations for the source and destination
            int xS = links[i].getSource().getX();       // x coordination for the source
            int yS = links[i].getSource().getY();       // y coordination for the source
            int xD = links[i].getDestination().getX();	// x coordination for the destination
            int yD = links[i].getDestination().getY();  // y coordination for the destination

            g.setColor(new Color(38, 200, 14));
            g.drawLine(xS, yS, xD, yD);
        } // end for

        // if repaint, highlight selected point/s
        if(sourceX != 0 && sourceY != 0){
            g.setColor(Color.decode("#00468b"));
            g.fillOval(sourceX, sourceY, 15, 15);
        }

        if(destX != 0 && destY != 0){
            g.setColor(Color.decode("#00468b"));
            g.fillOval(destX, destY, 15, 15);
        }


        //add a text
        Graphics g2 = (Graphics) g;
        g2.setColor(Color.RED);
        g2.setFont(new Font ( "Times New Roman", Font.BOLD, 18));
        g2.drawString("Our List of USA Cities and their Links.", 150, 780);
        g2.drawString("Not all the cities are listed. Some of their locations have been edited,", 150, 800);
        g2.drawString("only because to make the map clearer and more understandable.", 150, 820);
        g2.drawString("Please select your starting point and then your destination to find how to get", 150, 840);
        g2.drawString("at your destination the shortest way possible.", 150, 860);
        g2.drawString("Enjoy!!!", 150, 880);
        g2.drawString("Team: 1- Francesko Racaku", 150, 900);
        g2.drawString("2- Xhuljano Racaku", 204, 920);
        g2.drawString("3- Andreas", 204, 940);
        g2.drawString("4- Faris", 204, 960);
        g2.drawString("5- Tonmoy", 204, 980);

        Image note = new ImageIcon("info.jpg").getImage();
        g.drawImage(note, 110, 600, null);
    }// end paint


    public void setSource(int sourceX, int sourceY){
        this.sourceX = sourceX;
        this.sourceY = sourceY;
    }

    public void setDest(int destX, int destY){
        this.destX = destX;
        this.destY = destY;
    }




}// end MapOfCities()

