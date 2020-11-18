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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

public class PrimaryController {
    @FXML
    private AnchorPane root;
    @FXML
    public static List<File> file;

    //private SentimentAnalyser sentenceDetection;

    //public PrimaryController(){
        ///sentenceDetection = new SentimentAnalyser();
    //}

    @FXML
    public void multipleFileChooser() throws IOException {

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
            if ((long) file.size() == 1) {
                parent = FXMLLoader.load(getClass().getResource("singleFile.fxml"));

            } else {
                parent = FXMLLoader.load(getClass().getResource("multipleFile.fxml"));
            }
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
    @FXML
    public void instruction() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("IMT3281/instruction.txt").getFile());

        FileReader fileReader=new FileReader(file);
        BufferedReader bufferedReader=new BufferedReader(fileReader);
        List<String>sentences = new ArrayList<>();
        String words;
        String str = "";
        TextArea textArea = new TextArea();
        textArea.setWrapText(true);
        textArea.setEditable(false);
        while ((words = bufferedReader.readLine()) != null) {
            sentences.add(words);


            str+= " "+words+"\n";
            textArea.setText(str);
        }

        Stage newStage = new Stage();
        Scene scene = new Scene(textArea,400,200);
        newStage.setTitle("Instructions");
        newStage.setScene(scene);
        newStage.show();



        bufferedReader.close();
        fileReader.close();
    }}
