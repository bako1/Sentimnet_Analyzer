package IMT3281;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {



    public static void main(String[] args) {
        launch();

    }

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("primary.fxml"));
        stage.setTitle("Sentiment Analyzer");
        stage.setScene(new Scene(root, 500, 500));
        stage.show();
        ReadFiles readFiles = new ReadFiles();
        String simple = "[.?!]";
        String[] splitString;


    }
}