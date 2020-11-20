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

    public PrimaryController() {
    }

    @FXML
    public void FileChooser() throws IOException {

        // File open dialog
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Multiple file");
        Stage stage = (Stage) root.getScene().getWindow();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Supported types", "*.txt", "*.doc", "*.csv"),
                new FileChooser.ExtensionFilter("Text file", "*.txt"),
                new FileChooser.ExtensionFilter("Word Document", "*.doc"),
                new FileChooser.ExtensionFilter("Comma-separated", "*.csv"));
        file = fileChooser.showOpenMultipleDialog(stage);

        Parent parent;
        if (file != null) { // File is selected
            parent = FXMLLoader.load(getClass().getResource("FileChooser.fxml"));
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("No file chosen");
            alert.show();}
    }

@FXML
    public void onExit() { // Exiting
    System.exit(0);
    }

    @FXML
    public void instruction() throws IOException { // Load instructions.txt as new Scene
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

    private Alert information(String info){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(info);
        alert.show();
        return alert;
    }

    //Some basic info buttons

@FXML
    public void authorInfo() {
        String info = "Abdi Bako \n" +
                "Amr Hamcho\n" +
                "Eirik Tobiassen ";
        information(info);
    }
    @FXML
    public void contactInfo() {
        String info = "abdimb@stud.ntnu.no\namrhh@stud.ntnu.no\neiriktob@stud.ntnu.no";
        information(info);
    }
    @FXML
    public void versionInfo() {
        String info = "Version: 1.0.0";
        information(info);
    }
}
