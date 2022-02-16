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

public class mainMenuController {

    Parent root;
    Scene scene;
    Stage stage;

    @FXML
    Label printFacilityTab;
    @FXML
    Label addFacilityTab;
    @FXML
    Label modifyResourcesTab;

    public void switchToPrintScene(MouseEvent event) throws IOException {

        // Creates an FXMLLoader to load FXML content
        FXMLLoader loader  = new FXMLLoader(getClass().getResource("FXML/printFacility.fxml"));

        // Puts the FXML into the Parent Object
        root = loader.load();

        // Creates a PrintFacilityController Object which allows you to use the other class available
        PrintFacilityController printFacilityController = loader.getController();

        // Sets the stage with a node from the current Scene
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        scene = new Scene(root);

        // Sets scene to have the first location already set up
        printFacilityController.setLocation();

        // Adds Key Pressing functionality
        scene.setOnKeyPressed(event1 -> {

            // Handles which key is pressed (Only two keys do anything - <- ->)
            switch (event1.getCode()) {
                case LEFT -> printFacilityController.prevLocation();
                case RIGHT -> printFacilityController.nextLocation();
            }
        });

        stage.setScene(scene);
        stage.show();

    }

    public void switchToAddFacilityScene(MouseEvent event) throws IOException {

        // Creates an FXMLLoader to load FXML content
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/addFacility.fxml"));

        // Puts the FXML into the Parent Object
        root = loader.load();

        // Sets the stage with a node from the current Scene
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        scene = new Scene(root);

        stage.setScene(scene);

        stage.show();

    }

    public void switchToModifyResourcesScene(MouseEvent event) throws IOException {

        // Creates an FXMLLoader to load FXML content
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/modifyResources.fxml"));

        // Puts the FXML into the Parent Object
        root = loader.load();

        // Sets the stage with a node from the current Scene
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        scene = new Scene(root);

        stage.setScene(scene);

        stage.show();

    }
}