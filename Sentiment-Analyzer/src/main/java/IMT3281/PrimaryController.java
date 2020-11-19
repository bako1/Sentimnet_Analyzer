package IMT3281;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public class PrimaryController {
    @FXML
    private AnchorPane root;
    @FXML
    public static List<File> file;
    public int test = 0;



    public PrimaryController() {
    }

    @FXML
    public void FileChooser() throws IOException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Multiple file");
        Stage stage = (Stage) root.getScene().getWindow();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("text file", "*.txt"),
                new FileChooser.ExtensionFilter("doc", "*.doc"),
                new FileChooser.ExtensionFilter("csv file", "*.csv"));
        file = fileChooser.showOpenMultipleDialog(stage);

        Parent parent;
        if (file != null) {
            parent = FXMLLoader.load(getClass().getResource("FileChooser.fxml"));
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("No file chosen");
            alert.show();}
    }

@FXML
    public void onExit() {

    System.exit(0);
    }
    Runnable task = new Runnable() {
        @Override
        public void run() {
            try {
                instruction();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        }
    };

    @FXML
    public void instruction() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("IMT3281/instruction.txt").getFile());

        TextArea textArea = new TextArea();
        textArea.setWrapText(true);
        textArea.setEditable(false);
        String contents = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
        textArea.setText(contents);

        Stage newStage = new Stage();
        Scene scene = new Scene(textArea, 400, 200);
        newStage.setTitle("Instructions");
        newStage.setScene(scene);
        newStage.show();
    }
}
