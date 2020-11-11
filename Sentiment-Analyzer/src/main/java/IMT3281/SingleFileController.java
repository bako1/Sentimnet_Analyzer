package IMT3281;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SingleFileController  implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<Table> tableView;
    @FXML
    private TableColumn<Table,String> fileName;
    @FXML
    private TableColumn<Table,String>subject;
    @FXML
    private TableColumn<Table,String>polarity;
    @FXML
    private TableColumn<Table,Integer>occurrence;

    @FXML
    private void goBackToHomePage() throws IOException {
        Stage appStage;
        Parent parent;

        appStage=(Stage)root.getScene().getWindow();
        parent= FXMLLoader.load(getClass().getResource("primary.fxml"));
        Scene scene=new Scene(parent);
        appStage.setScene(scene);
        appStage.show();
    }
    @FXML
    private void onExit(){
        PrimaryController primaryController = new PrimaryController();
        primaryController.onExit();
    }
    @FXML
    private void singleFileChooser() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File Dialog");
        Stage stage = (Stage) root.getScene().getWindow();
        //get the file object
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("text file", "*.txt"),
                new FileChooser.ExtensionFilter("document", "*.doc"),
                new FileChooser.ExtensionFilter("csv file", "*.csv"));
        File file = fileChooser.showOpenDialog(stage);

        //Check whether file is selected
        if (file != null) {
            ReadFiles readFiles = new ReadFiles();
          //  List<String> strings = readFiles.readTextFile(file);
            //for (String s : strings)
             //   System.out.println(s);
        }
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
        SentimentAnalyser sentenceDetection = new SentimentAnalyser();
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
