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
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

public class MultipleFileController extends PrimaryController implements Initializable {

    @FXML
    public TableView<Table> tableView;
    @FXML
    public TableColumn<Table,String> fileName;
    @FXML
    public TableColumn<Table,String> polarity;
    @FXML
    public TableColumn<Table,Integer> occurrence;
    @FXML
    public TableColumn <Table,String> subject;
    @FXML
    public TableColumn<Table, String> sentence;
    @FXML
    private AnchorPane root;


    public MultipleFileController(){
        super();
    }
    @FXML
    public void onexit( ) {
        System.exit(0);
    }
    @FXML
    public void goBackToHomePage() throws IOException {

        Stage appStage;
        Parent parent;
        appStage = (Stage) root.getScene().getWindow();
        parent = FXMLLoader.load(getClass().getResource("primary.fxml"));
        Scene scene = new Scene(parent);
        appStage.setScene(scene);
        appStage.show();
    }

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fileName.setCellValueFactory(new PropertyValueFactory<>("fileName"));
        subject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        occurrence.setCellValueFactory(new PropertyValueFactory<>("occurrence"));
        polarity.setCellValueFactory(new PropertyValueFactory<>("polarity"));
        sentence.setCellValueFactory(new PropertyValueFactory<>("sentence"));

        tableView.setItems(getInfo());

    }

    private ObservableList<Table> getInfo() {
        ReadFiles readFiles = new ReadFiles();

        ObservableList<Table>tableObservableList = FXCollections.observableArrayList();
        //SentimentAnalyser sentimentAnalyser = new SentimentAnalyser();
      //  sentimentAnalyser.sentenceRecognizer(readFiles.readFiles(file));
       // Table table = new Table(sentimentAnalyser.getFileName(),sentimentAnalyser.getProcessedSentences(),sentimentAnalyser.getSubject(),sentimentAnalyser.getPolarity(),sentimentAnalyser.getOccurrence());
        //for(String s : table.getProcessedSentence())
        // table.setSentence(s);
      //  tableObservableList.add(table);

        //DoubleBinding usedWidth = fileName.widthProperty().add(subject.widthProperty()).add(occurrence.widthProperty());

//        sentence.prefWidthProperty().bind(tableView.widthProperty().subtract(usedWidth));
        return tableObservableList;
    }
    @FXML
    public void browseFiles() throws IOException {
        multipleFileChooser();

    }
    @FXML
    private void usage() throws IOException {
        instruction();
    }
}
