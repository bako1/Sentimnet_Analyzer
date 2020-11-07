package IMT3281;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class PrimaryController implements Initializable {





    @FXML
    private VBox root;
    @FXML
    private TableView<Table>tableView;
    @FXML
    private TableColumn<Table,String>fileName;
    @FXML
    private TableColumn<Table,String>subject;
    @FXML
    private TableColumn<Table,String>polarity;
    @FXML
    private TableColumn<Table,Integer>occurrence;


    @FXML
    private void singleFileChooser() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File Dialog");
        Stage stage = (Stage) root.getScene().getWindow();
        //get the file object
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("text file", "*.txt"),
                new FileChooser.ExtensionFilter("document","*.doc"),
                new FileChooser.ExtensionFilter("csv file","*.csv"));
        File file =  fileChooser.showOpenDialog(stage);

        //Check whether file is selected
        if(file!=null){
            ReadFiles readFiles = new ReadFiles();
           List<String>strings  = readFiles.readTextFile(file);
           for (String s:strings)
               System.out.println(s);
        }

    }
    @FXML
    private void multipleFileChooser(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Multiple file");
        Stage stage = (Stage) root.getScene().getWindow();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("text file", "*.txt"),
                new FileChooser.ExtensionFilter("document","*.doc"),
                new FileChooser.ExtensionFilter("csv file","*.csv"));
        fileChooser.showOpenMultipleDialog(stage);
    }
    @FXML
    private void instruction() throws IOException {
        FileReader fileReader=new FileReader("Sentiment-Anal yzer/src/main/resources/IMT3281/instruction");
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
        for (String s:sentences){
            System.out.println(s);;}
        Stage newStage = new Stage();
        Scene scene = new Scene(textArea,300,300);
        newStage.setTitle("Instructions");
        newStage.setScene(scene);
        newStage.show();



        bufferedReader.close();
        fileReader.close();
    }

        @FXML
    private void onExit(){
        System.exit(0);

    }
//Initializes the table
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fileName.setCellValueFactory(new PropertyValueFactory<>("fileName"));
        subject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        polarity.setCellValueFactory(new PropertyValueFactory<>("polarity"));
        occurrence.setCellValueFactory(new PropertyValueFactory<>("occurrence"));
        tableView.setItems(getInfo()); //Updates the table with newly added values if any
    }
    private ObservableList<Table> getInfo(){
        ReadFiles readFiles = new ReadFiles();
        SentenceDetection sentenceDetection = new SentenceDetection();
        ObservableList<Table> observableList  = FXCollections.observableArrayList();
        sentenceDetection.setFileName("test.txt");
        sentenceDetection.setSubject("Coffee");
        sentenceDetection.setPolarity("positive");
        sentenceDetection.setOccurrence(100);

        observableList.add(new Table(sentenceDetection.getFileName(),sentenceDetection.getSubject(),
                                     sentenceDetection.getPolarity(),sentenceDetection.getOccurrence()));
        //observableList.add("Coffee");
        //observableList.add("positive");
        //observableList.add("100");
        return observableList;
    }


}
