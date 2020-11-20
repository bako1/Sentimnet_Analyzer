package IMT3281;

import edu.stanford.nlp.ie.util.RelationTriple;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.naturalli.NaturalLogicAnnotations;

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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.beans.XMLEncoder;
import java.io.*;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Iterator;


public class Analyzer extends PrimaryController implements Initializable {

    @FXML
    public TableView<Table> tableView;
    @FXML
    public TableColumn<Table, String> polarity;
    @FXML
    public TableColumn<Table, String> subject;
    @FXML
    public TableColumn<Table, String> target;
    @FXML
    public static MenuItem exportToXML;
    public MenuItem exportToCSV;
    public TextArea stat;
    public TextArea subjects;

    @FXML
    private AnchorPane root;
    private File whereToSave = null;

    public Analyzer() {

    }

    @FXML
    public void onexit() {
        onExit();
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
        subject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        polarity.setCellValueFactory(new PropertyValueFactory<>("polarity"));
        target.setCellValueFactory(new PropertyValueFactory<>("target"));
        tableView.setItems(getInfo());
        tableView.setVisible(true);
    }

    private ObservableList<Table> getInfo() {

        if(file.size() > 1) {
            return getMultipleInfo();
        }
        else {
            ReadFiles readFiles = new ReadFiles();
            StanfordCoreNLP stanfordCoreNLP = PipeLine.getPipeLine();
            ObservableList<Table> tableObservableList = FXCollections.observableArrayList();
            Statistics stats = new Statistics();

            String sentiment;
            String text;
            String subject;

            Annotation doc;
            Table table;

            HashMap<String, String> listHashMap = readFiles.readFiles(file); // Read all files the PrimaryController has in memory

            String[] key = listHashMap.keySet().toArray(new String[1]);
            doc = new Annotation(listHashMap.get(key[0]));
            stanfordCoreNLP.annotate(doc);
            for (CoreMap sentence : doc.get(CoreAnnotations.SentencesAnnotation.class)) { // For every sentence

                sentiment = sentence.get(SentimentCoreAnnotations.SentimentClass.class);
                subject = "No subject or unknown";

                Collection<RelationTriple> triples = sentence.get(NaturalLogicAnnotations.RelationTriplesAnnotation.class);
                Iterator<RelationTriple> it = triples.iterator();

                if (it.hasNext()) {
                    subject = it.next().subjectGloss();
                    stats.addSubject(key[0], subject);
                }

                stats.addStat(sentiment);
                stats.addSentence();

                table = new Table(sentence.toString(), subject, sentiment);
                tableObservableList.add(table);
            }

            showStatistics(stats);
            return tableObservableList;
        }
    }

    private ObservableList<Table> getMultipleInfo() {

        ReadFiles readFiles = new ReadFiles();
        StanfordCoreNLP stanfordCoreNLP = PipeLine.getPipeLine();
        ObservableList<Table> tableObservableList = FXCollections.observableArrayList();
        Statistics stats = new Statistics();

        String sentiment;
        String text;
        String subject;

        Annotation doc;
        Table table;

        HashMap<String, String> listHashMap = readFiles.readFiles(file); // Read all files the PrimaryController has in memory

        for (Map.Entry<String, String> read : listHashMap.entrySet()) { // For every file
            text = read.getValue();
            doc = new Annotation(text);
            stanfordCoreNLP.annotate(doc);
            for (CoreMap sentence : doc.get(CoreAnnotations.SentencesAnnotation.class)) { // For every sentence

                sentiment = sentence.get(SentimentCoreAnnotations.SentimentClass.class);
                subject = "No subject or unknown";

                Collection<RelationTriple> triples = sentence.get(NaturalLogicAnnotations.RelationTriplesAnnotation.class);
                Iterator<RelationTriple> it = triples.iterator();

                if (it.hasNext()) {
                    subject = it.next().subjectGloss();
                    stats.addSubject(read.getKey(), subject);
                }

                stats.addStat(sentiment);
                stats.addSentence();

            }

                table = new Table(read.getKey(), stats.getMax());
                tableObservableList.add(table);
            }

            tableView.getColumns().remove(1);

            showStatistics(stats);
            return tableObservableList;
    }

    @FXML
    public void browseFiles() throws IOException {
        FileChooser();
    }

    @FXML
    private void usage() throws IOException {
        instruction();
    }

    private void showStatistics(Statistics stats) {
        String statContent = "";
        if (file.size() > 1) {
            statContent += "\t\tStatistics\n\n" +
                    "Files Selected:\t " + file.size() + "\n\tPer-sentence" + "\tPer-file" +
                    "\nPositive:\t\t" + stats.getPos() + "\t   " + stats.getFilePos() +
                    "\nNegative:\t\t" + stats.getNeg() + "\t   " + stats.getFileNeg() +
                    "\nNeutral:\t\t" + stats.getNeu() + "\t   " + stats.getFileNeu() +
                    "\n\nTotal sentences:\t" + stats.getSentence();
        } else {
            statContent += "\t\t Statistics\n\n" +
                    "\tFiles Selected:\t " + file.size() +
                    "\n\tPositive:\t\t" + stats.getPos() + "\n\tNegative:\t\t"
                    + stats.getNeg() + "\n\tNeutral:\t\t" + stats.getNeu() +
                    "\n\tSentences:\t" + stats.getSentence();
        }

        String subjContent = "\t\t\t Subjects\n\n" + stats.getSubjects();

        stat.setEditable(false);
        stat.setWrapText(true);
        stat.setText(statContent);

        subjects.setEditable(false);
        subjects.setWrapText(true);
        subjects.setText(subjContent);

    }

    @FXML

    private void whereToSave() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File Dialog");
        fileChooser.setInitialFileName("sentiment");
        Stage stage = (Stage) root.getScene().getWindow();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("xml", "*.xml"),
                new FileChooser.ExtensionFilter("csv file", "*.csv"));
        whereToSave = fileChooser.showSaveDialog(stage);
        if (whereToSave != null) {
            if (whereToSave.getAbsolutePath().endsWith(".xml")) {
                writeXMLFile(whereToSave);
                alertInfo("XML");
            } else if (whereToSave.getAbsolutePath().endsWith(".csv")) {
                writeToCSVFile(whereToSave);
                alertInfo("CSV");

            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("OBS! The File could not be saved");
            alert.show();
        }
    }

    private void writeXMLFile(File file) {

        FileExporter fileExporter;
        Table table;
        for (int i = 0; i < tableView.getItems().size(); i++) {
            table = tableView.getItems().get(i);
            fileExporter = new FileExporter(table.getTarget(), table.getSubject(), table.getPolarity());
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file, true);
                if (file != null) {
                    XMLEncoder xmlEncoder = new XMLEncoder(fileOutputStream);
                    xmlEncoder.writeObject(fileExporter);
                    xmlEncoder.close();
                    fileOutputStream.close();
                }
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    private void writeToCSVFile(File file) throws IOException {
        Table table;
        FileExporter fileExporter;

        try {
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);
            for (int i = 0; i < tableView.getItems().size(); i++) {
                table = tableView.getItems().get(i);
                fileExporter = new FileExporter(table.getTarget(), table.getSubject(), table.getPolarity());
                printWriter.println(fileExporter.getTarget()+","+fileExporter.getSubject()+","+fileExporter.getPolarity());
            }
            printWriter.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    private Alert alertInfo(String fileExtn){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Your "+ fileExtn+" file is successfully saved");
        alert.show();
        return alert;
    }

}

