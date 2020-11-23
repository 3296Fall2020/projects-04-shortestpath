import javax.swing.*;
import java.awt.*;

public class SearchField {
    // Source and City will use this class for obj. to contain: CityLabel, CityInput, StateLabel, StateInput, AddBtn, Result/ErrorField

    JTextField cityInput;
    JTextField stateInput;
    int cols;
    JButton btn;
    JLabel resultsLabel;
    Font font;

    SearchField(){
        this.font = new Font("Georgia", Font.PLAIN, 12);
        this.cityInput = new JTextField(6);
        this.stateInput = new JTextField(1);
        btn = new JButton("ADD");
    }

    SearchField(int cityCols, int stateCols, int cityMaxChars, int stateMaxChars, Font font, String btnTxt, String btnColor){
        this.cols = cols;
        this.font = font;

        this.cityInput = new JTextField(cityCols);
        this.cityInput.setFont(font);
        this.cityInput.setDocument(new JTextFieldLimit(cityMaxChars));

        this.stateInput = new JTextField(stateCols);
        this.stateInput.setFont(font);
        this.stateInput.setDocument(new JTextFieldLimit(stateMaxChars));

        this.btn = new JButton(btnTxt);
        this.btn.setBackground(Color.decode(btnColor));

        resultsLabel = new JLabel("");
    }

    public String getCityInput(){
        return cityInput.getText().trim();
    }

    public String getStateInput(){
        return stateInput.getText().trim();
    }


    public void setResults(String msg){
        resultsLabel.setText(msg);
    }


    public String checkValidInput(String city, String state){
        // Letters only (a-Z, A-Z), City can contain '.', State cannot
        // state input ALREADY restricted to 2 characters, city input ALREADY restricted to 85 characters.

        city = city.replaceAll("\\s", ""); // remove spaces

        if(!city.matches("[a-zA-Z]*")){
            System.out.println("1");
            if(!city.contains(".")) return "Invalid, letters only!";
        }

        if(!state.matches("[a-zA-Z]*")) return "Invalid, letters only for state!";

        return "";
    }

}
