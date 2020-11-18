package IMT3281;

import edu.stanford.nlp.ie.util.RelationTriple;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.naturalli.NaturalLogicAnnotations;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;
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

public class Analyzer extends PrimaryController implements Initializable {

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
    public Analyzer(){}

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

        Annotation doc;
        Table table;

        HashMap<String, String>listHashMap = readFiles.readFiles(file); // Read all files the PrimaryController has in memory

        for(Map.Entry<String, String>read : listHashMap.entrySet()) { // For every file
            String text = read.getValue();
            doc = new Annotation(text);
            stanfordCoreNLP.annotate(doc);
            for (CoreMap sentence : doc.get(CoreAnnotations.SentencesAnnotation.class)) { // For every sentence
                String sentiment = sentence.get(SentimentCoreAnnotations.SentimentClass.class);
                String subject = "No subject or unknown";
                Collection<RelationTriple> triples =
                        sentence.get(NaturalLogicAnnotations.RelationTriplesAnnotation.class);
                Iterator <RelationTriple> it = triples.iterator();

                if (it.hasNext()) {
                    subject = it.next().subjectGloss();
                }

                //System.out.println("Sentiments: " + read.getKey() + " " + sentence.toString() + " " + sentiment + " " + subject); // Console version of output, for debugging
                table = new Table(read.getKey(), sentence.toString(), subject, sentiment, 0);
                tableObservableList.add(table);
            }
        }
        return tableObservableList;
    }
@FXML
    public void browseFiles() throws IOException { FileChooser();}

    @FXML
    private void usage() throws IOException {
        instruction();
    }
}
