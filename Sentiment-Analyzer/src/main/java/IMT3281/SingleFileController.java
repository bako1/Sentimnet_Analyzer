package IMT3281;

import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
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

import java.util.*;

public class SingleFileController extends PrimaryController implements Initializable {

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
    private  AnchorPane root;
    public SingleFileController(){}

    @FXML
    public void onexit( ) {
        onExit();}
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
        StanfordCoreNLP stanfordCoreNLP = PipeLine.getPipeLine();
        ObservableList<Table>tableObservableList = FXCollections.observableArrayList();

        CoreDocument coreDocument;
        Table table;

        HashMap<String, List>listHashMap = readFiles.readFiles(file);

        for(Map.Entry<String,List>read : listHashMap.entrySet()) { // For every file
            String text = read.getValue().toString();
            coreDocument = new CoreDocument(text);
            stanfordCoreNLP.annotate(coreDocument);
            List<CoreSentence> sentenceList = coreDocument.sentences();
            for (CoreSentence sentence : sentenceList) { // For every sentence
                String sentiment = sentence.sentiment();
                table = new Table(read.getKey(), sentence.toString(), "***må jobbes", sentiment, 0);
                tableObservableList.add(table);
            }
        }
        return tableObservableList;
    }
@FXML
    public void browseFiles() throws IOException { multipleFileChooser();}

    @FXML
    private void usage() throws IOException {
        instruction();
    }
}
