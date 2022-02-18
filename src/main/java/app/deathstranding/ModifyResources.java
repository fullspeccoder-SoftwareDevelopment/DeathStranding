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
import java.util.Objects;

public class ModifyResources {

    Stage stage;
    Scene scene;

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
    private TextField locationInput;
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
    private Label textLabel;
    @FXML
    private Label confirmLabel;
    @FXML
    private Label cancelLabel;


    public void addResources() {

        // Gets each input the user entered in text fields
        int chiralAmount = Integer.parseInt(chiralInput.getText());
        int resinAmount = Integer.parseInt(resinInput.getText());
        int metalAmount = Integer.parseInt(metalInput.getText());
        int ceramicAmount = Integer.parseInt(ceramicInput.getText());
        int chemicalAmount = Integer.parseInt(chemicalInput.getText());
        int alloyAmount = Integer.parseInt(alloyInput.getText());

        // Creates temporary storage for use in modifying location needing to be modified
        Storage tempStorage = new Storage(chiralAmount, resinAmount, metalAmount, ceramicAmount, chemicalAmount, alloyAmount);

        // Data management class handles modification of resources
        if(DataManagement.modifyLocationResources(locationInput.getText(), tempStorage)) {

            rearrangeConfirmationElements("Resources Successfully Added!");

            // When clicking mouse on confirmation label, it will go back to main menu
            confirmLabel.setOnMouseClicked(mouseEvent -> {
                try {
                    backToMainMenu(mouseEvent);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // When clicking mouse on back to main menu label, it will set call to addResources
                confirmLabel.setOnMouseClicked(mouseEvent1 -> {
                    addResources();
                });

            });
        }
        else{

            // Sets labels for error screen
            textLabel.setText("Facility does not exist.\nWhat would you like to do?");
            confirmLabel.setText("Back to Main Menu");

            // When clicking mouse on confirmation label, it will go back to main menu
            confirmLabel.setOnMouseClicked(mouseEvent -> {

                try {
                    backToMainMenu(mouseEvent);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Resets back to original settings for confirmation label
                confirmLabel.setText("Add");
                confirmLabel.setOnMouseClicked(mouseEvent1 -> {
                    addResources();
                });

            });

            // Changes text and allows the user to select between heading back to main or modifying other resources
            cancelLabel.setText("Modify Resources");
            cancelLabel.setOnMouseClicked(mouseEvent -> {
                textLabel.setText("Are you sure you want to add the materials to this facility?");
                confirmLabel.setText("Add");
                cancelLabel.setText("Cancel");
                hideConfirmationPrompt();
                confirmLabel.setOnMouseClicked(mouseEvent1 -> {
                    addResources();
                });

            });
        }

    }

    public void removeResources() {

        // Gets each input the user entered in text fields
        int chiralAmount = -Integer.parseInt(chiralInput.getText());
        int resinAmount = -Integer.parseInt(resinInput.getText());
        int metalAmount = -Integer.parseInt(metalInput.getText());
        int ceramicAmount = -Integer.parseInt(ceramicInput.getText());
        int chemicalAmount = -Integer.parseInt(chemicalInput.getText());
        int alloyAmount = -Integer.parseInt(alloyInput.getText());

        // Creates temporary storage for use in modifying location needing to be modified
        Storage tempStorage = new Storage(chiralAmount, resinAmount, metalAmount, ceramicAmount, chemicalAmount, alloyAmount);

        // Data management class handles modification of resources
        if(DataManagement.modifyLocationResources(locationInput.getText(), tempStorage)) {

            rearrangeConfirmationElements("Resources Successfully Removed!");

            // When clicking mouse on confirmation label, it will go back to main menu
            confirmLabel.setOnMouseClicked(mouseEvent -> {
                try {
                    backToMainMenu(mouseEvent);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // When clicking mouse on back to main menu label, it will set call to removeResources
                confirmLabel.setOnMouseClicked(mouseEvent1 -> {
                    removeResources();
                });

            });
        }
        else{

            // Sets labels for error screen
            textLabel.setText("Facility does not exist.\nWhat would you like to do?");
            confirmLabel.setText("Back to Main Menu");

            // When clicking mouse on confirmation label, it will go back to main menu
            confirmLabel.setOnMouseClicked(mouseEvent -> {

                try {
                    backToMainMenu(mouseEvent);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Resets back to original settings for confirmation label
                confirmLabel.setText("Remove");
                confirmLabel.setOnMouseClicked(mouseEvent1 -> {
                    removeResources();
                });

            });

            // Changes text and allows the user to select between heading back to main or modifying other resources
            cancelLabel.setText("Modify Resources");
            cancelLabel.setOnMouseClicked(mouseEvent -> {
                textLabel.setText("Are you sure you want to remove the materials from this facility?");
                confirmLabel.setText("Remove");
                cancelLabel.setText("Cancel");
                hideConfirmationPrompt();
                confirmLabel.setOnMouseClicked(mouseEvent1 -> {
                    removeResources();
                });

            });
        }

    }

    public void showConfirmationPromptFromAdd() {
        textLabel.setText("Are you sure you want to add the materials to the facility?");
        confirmLabel.setText("Add");
        confirmLabel.setOnMouseClicked(mouseEvent -> {
            addResources();
        });
        groupLabel.toFront();
    }

    public void hideConfirmationPrompt() {
        groupLabel.toBack();
    }

    public void showConfirmationPromptFromRemove() {
        textLabel.setText("Are you sure you want to remove the materials from the facility?");
        confirmLabel.setText("Remove");
        confirmLabel.setOnMouseClicked(mouseEvent -> {
            removeResources();
        });
        groupLabel.toFront();
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

    private void rearrangeConfirmationElements(String text) {

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
        textLabel.setText(text);

    }

}
