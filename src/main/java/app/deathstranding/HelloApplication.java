package app.deathstranding;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("FXML/mainMenu.fxml"));

        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("Main Menu");

        stage.setScene(scene);

        stage.show();

    }

    public static void main(String[] args) throws Exception {

        DataManagement.connection();

        DataManagement.initializeFacilities();

        launch();

        DataManagement.updateDatabase();

    }
}