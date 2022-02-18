package app.deathstranding;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class AddFacilityController {

    Stage stage;
    Scene scene;

    @FXML
    private TextField locationInput;
    @FXML
    private TextField chiralInput;
    @FXML
    private TextField resinInput;
    @FXML
    private TextField metalInput;
    @FXML
    private TextField ceramicInput;
    @FXML
    private TextField chemicalInput;
    @FXML
    private TextField alloyInput;
    @FXML
    private Group groupLabel;
    @FXML
    private Rectangle rec1;
    @FXML
    private Rectangle rec2;
    @FXML
    private Rectangle rec3;
    @FXML
    private Rectangle rec4;
    @FXML
    private Rectangle rec5;
    @FXML
    private Rectangle rec6;
    @FXML
    private Rectangle rec7;
    @FXML
    private Rectangle rec8;
    @FXML
    private Label confirmLabel;
    @FXML
    private Label cancelLabel;
    @FXML
    private Label textLabel;

    public void addFacility() throws SQLException, IOException {

        // Parses all the integers in the amounts provided
        int chiralAmount = Integer.parseInt(chiralInput.getText());
        int resinAmount = Integer.parseInt(resinInput.getText());
        int metalAmount = Integer.parseInt(metalInput.getText());
        int ceramicAmount = Integer.parseInt(ceramicInput.getText());
        int chemicalAmount = Integer.parseInt(chemicalInput.getText());
        int alloyAmount = Integer.parseInt(alloyInput.getText());

        // Creates temporary storage for later use
        Storage tempStorage = new Storage(chiralAmount, resinAmount, metalAmount, ceramicAmount, chemicalAmount, alloyAmount);

        if(DataManagement.addedToTable(locationInput.getText(), tempStorage)) {

            rearrangeConfirmationElements();

            // Sets up a click event for the confirmation button to go back to the main menu
            confirmLabel.setOnMouseClicked(mouseEvent -> {

                try {
                    backToMainMenu(mouseEvent);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                resetConfirmLabel();

            });
        }
        else {

            // Sets labels for error screen
            textLabel.setText("Facility already exists.\nWhat would you like to do?");
            confirmLabel.setText("Back to Main Menu");

            // When clicking mouse on confirmation label, it will go back to main menu
            confirmLabel.setOnMouseClicked(mouseEvent -> {

                try {
                    backToMainMenu(mouseEvent);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Resets back to original settings for confirmation label
                confirmLabel.setText("OK");
                resetConfirmLabel();

            });

            cancelLabel.setText("Add New Facility");
            cancelLabel.setOnMouseClicked(mouseEvent -> {
                textLabel.setText("Are you sure you want to add this facility?");
                confirmLabel.setText("OK");
                cancelLabel.setText("Cancel");
                hideConfirmationPrompt();
                resetConfirmLabel();

            });
        }
    }

    public void backToMainMenu(MouseEvent event) throws IOException {

        // Loads FXML into a Parent Object
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("FXML/mainMenu.fxml")));

        // Gets the stage from the node
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        // Loads FXML into new Scene
        scene = new Scene(root);

        // Triggers new Scene to be displayed
        stage.setScene(scene);
        stage.show();

    }

    public void hideConfirmationPrompt() {
        groupLabel.toBack();
    }

    public void showConfirmationPrompt() {
        groupLabel.toFront();
    }

    private void resetConfirmLabel() {

        // Sets mouse click on confirmation label back to original function call
        confirmLabel.setOnMouseClicked(mouseEvent1 -> {
            try {
                addFacility();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        });

    }

    private void rearrangeConfirmationElements() {

        // Hides all the small rectangles for the cancel Button
        rec1.setVisible(false);
        rec2.setVisible(false);
        rec3.setVisible(false);
        rec4.setVisible(false);
        cancelLabel.setVisible(false);

        // Moves Main Menu button over to the middle
        confirmLabel.setLayoutX(590);
        rec8.setLayoutX(579);
        rec7.setLayoutX(1091);
        rec6.setLayoutX(579);
        rec5.setLayoutX(1091);
        confirmLabel.setText("Back to Main Menu");
        textLabel.setText("Facility Successfully Added!");

    }
}
