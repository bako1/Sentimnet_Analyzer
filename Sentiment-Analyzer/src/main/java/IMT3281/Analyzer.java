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

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;


import java.io.File;
import java.io.IOException;
import java.net.URL;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;

public class Analyzer extends PrimaryController implements Initializable {

    @FXML
    public TableView<Table> tableView;
    @FXML
    public TableColumn<Table,String> polarity;
    @FXML
    public TableColumn <Table,String> subject;
    @FXML
    public TableColumn<Table, String> target;
    @FXML
    public static MenuItem exportToXML;
    public MenuItem exportToCSV;
    public TextArea stat;
    public TextArea subjects;

    @FXML
    private  AnchorPane root;
    @FXML
    public static File whereToSave;

    public Analyzer(){

    }

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
            subject.setCellValueFactory(new PropertyValueFactory<>("subject"));
            polarity.setCellValueFactory(new PropertyValueFactory<>("polarity"));
            target.setCellValueFactory(new PropertyValueFactory<>("target"));
            tableView.setItems(getInfo());
            tableView.setVisible(true);
    }
    private ObservableList<Table> getInfo() {
//        int pos, neg ,neu;
//            pos=neg=neu=0;


//        String overAllPol = "";
        ReadFiles readFiles = new ReadFiles();
        StanfordCoreNLP stanfordCoreNLP = PipeLine.getPipeLine();
        ObservableList<Table>tableObservableList = FXCollections.observableArrayList();
        Statistics stats = new Statistics();
        Statistics fileStats = new Statistics();
        String sentiment;
        String text;
        String subject;

        Annotation doc;
        Table table = null;
        FileExporter fileExporter;

        HashMap<String, String>listHashMap = readFiles.readFiles(file); // Read all files the PrimaryController has in memory

        for(Map.Entry<String, String>read : listHashMap.entrySet()) { // For every file
            text = read.getValue();
            doc = new Annotation(text);
            stanfordCoreNLP.annotate(doc);
            for (CoreMap sentence : doc.get(CoreAnnotations.SentencesAnnotation.class)) { // For every sentence

                sentiment = sentence.get(SentimentCoreAnnotations.SentimentClass.class);
                subject = "No subject or unknown";

                Collection<RelationTriple> triples = sentence.get(NaturalLogicAnnotations.RelationTriplesAnnotation.class);
                Iterator <RelationTriple> it = triples.iterator();

                if (it.hasNext()) {
                    subject = it.next().subjectGloss();
                    stats.addSubject(read.getKey(), subject);
                }

                stats.addStat(sentiment);
                fileStats.addStat(sentiment);
                stats.addSentence();

                //System.out.println("Sentiments: " + read.getKey() + " " + sentence.toString() + " " + sentiment + " " + subject); // Console version of output, for debugging

                if(file.size()==1){
                    table = new Table(sentence.toString(), subject, sentiment);
                    //fileExporter = new FileExporter(sentence.toString(), subject, sentiment,);
                    tableObservableList.add(table);
                }
            }

            if(file.size() > 1) {
                table = new Table(read.getKey(), " må jobbes", fileStats.getMax());
                tableObservableList.add(table);
                fileStats.reset();
            }
        }

        showStatistics(stats);
        return tableObservableList;
    }
@FXML
    public void browseFiles() throws IOException { FileChooser();}

    @FXML
    private void usage() throws IOException {
        instruction();
    }

    private void showStatistics(Statistics stats) {
        String statContent = "\t\t\t Statistics\n\n"+
                "\t\tFiles Selected:\t"+file.size()+
                "\n\t\tPositive:\t\t" + stats.getPos() + "\n\t\tNegative:\t\t"
                + stats.getNeg() + "\n\t\tNeutral:\t\t" + stats.getNeu()
                + "\n\t\tSentences:\t" + stats.getSentence();
        String subjContent = "\t\t\t Subjects\n\n" + stats.getSubjects();

        stat.setEditable(false);
        stat.setWrapText(true);
        stat.setText(statContent);

        subjects.setEditable(false);
        subjects.setWrapText(true);
        subjects.setText(subjContent);

    }

    private String overAllPolarity(int pos, int neg, int neu){
        String overAllPolarity="";
        if(pos>neg && pos>neu)
            overAllPolarity = "positive";
        else if(neg>pos&&neg>neu)
            overAllPolarity = "negative";
        else
            overAllPolarity = "neutral";
        return overAllPolarity;
    }


    public void whereToSave() {

    }
}
