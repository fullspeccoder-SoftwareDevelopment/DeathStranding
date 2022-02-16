package app.deathstranding;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class PrintFacilityController {

    Scene scene;
    Stage stage;
    private String[] keys;
    private int currIndex = 0;

    @FXML
    private Label locationLabel;
    @FXML
    private Label chiralLabel;
    @FXML
    private Label resinLabel;
    @FXML
    private Label metalLabel;
    @FXML
    private Label ceramicLabel;
    @FXML
    private Label chemicalLabel;
    @FXML
    private Label alloyLabel;

    public void prevLocation() {

        // Modifies current index to get to previous location in array
        --currIndex;
        if (currIndex == -1) {
            currIndex = keys.length - 1;
        }

        // Changes Display to show previous facility
        alterLocationDisplay(currIndex);

    }

    public void nextLocation() {

        // Modifies current index to get to next location in array
        ++currIndex;
        if (currIndex == keys.length) {
            currIndex = 0;
        }

        // Changes Display to show next facility
        alterLocationDisplay(currIndex);

    }

    public void setLocation() {

        // Gets each location
        keys = DataManagement.getKeys();

        // Changes Display to show first facility
        alterLocationDisplay(0);

    }

    private void alterLocationDisplay(int index) {

        // Created variables for clarity; Retrieves respective resource from Storage Object
        String currLocation = keys[index];
        int amountOfChiralCrystals = DataManagement.getFacilities().get(currLocation).getChiralCrystals();
        int amountOfResins = DataManagement.getFacilities().get(currLocation).getResins();
        int amountOfMetals = DataManagement.getFacilities().get(currLocation).getMetals();
        int amountOfCeramics = DataManagement.getFacilities().get(currLocation).getCeramics();
        int amountOfChemicals = DataManagement.getFacilities().get(currLocation).getChemicals();
        int amountOfAlloys = DataManagement.getFacilities().get(currLocation).getSpecialAlloys();
        int[] amountOfResources = {amountOfChiralCrystals, amountOfResins, amountOfMetals, amountOfCeramics, amountOfChemicals, amountOfAlloys};

        // Sets the text of each label to the next location amounts and location name
        changeText(currLocation, amountOfResources);

    }

    private void changeText(String location, int[] arr) {

        // Collection of all the labels to iterate in the same sequence for both arrays
        Label[] labels = {chiralLabel, resinLabel, metalLabel, ceramicLabel, chemicalLabel, alloyLabel};

        // Sets text for location
        locationLabel.setText(location);

        // Sets text for the respective label
        for(int i = 0; i < arr.length; ++i) {
            labels[i].setText(String.valueOf(arr[i]));
        }

    }

    public void backToMainMenu(MouseEvent event) throws IOException {

        // Loads FXML into a Parent Object
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainMenu.fxml")));

        // Gets the stage from the node
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        // Loads FXML into new Scene
        scene = new Scene(root);

        // Triggers new Scene to be displayed
        stage.setScene(scene);
        stage.show();

    }

}
