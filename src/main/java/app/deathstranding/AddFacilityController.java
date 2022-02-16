package app.deathstranding;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
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

    public void addFacility(MouseEvent event) throws SQLException {

        int chiralAmount = Integer.parseInt(chiralInput.getText());
        int resinAmount = Integer.parseInt(resinInput.getText());
        int metalAmount = Integer.parseInt(metalInput.getText());
        int ceramicAmount = Integer.parseInt(ceramicInput.getText());
        int chemicalAmount = Integer.parseInt(chemicalInput.getText());
        int alloyAmount = Integer.parseInt(alloyInput.getText());

        Storage tempStorage = new Storage(chiralAmount, resinAmount, metalAmount, ceramicAmount, chemicalAmount, alloyAmount);

        DataManagement.addToTable(locationInput.getText(), tempStorage);

        try{
            backToMainMenu(event);
            System.out.println("Going back to main menu...");
        }
        catch(IOException fileException) {
            System.out.println(Arrays.toString(fileException.getStackTrace()));
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
