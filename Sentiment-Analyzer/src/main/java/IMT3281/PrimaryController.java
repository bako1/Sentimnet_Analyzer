package IMT3281;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class PrimaryController {





    @FXML
    private AnchorPane root;
    public PrimaryController(){}

    @FXML
    private void singleFileChooser() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File Dialog");
        Stage stage = (Stage) root.getScene().getWindow();
        //get the file object
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("text file", "*.txt"),
                new FileChooser.ExtensionFilter("doc","*.doc"),
                new FileChooser.ExtensionFilter("csv file","*.csv"));
        File file =  fileChooser.showOpenDialog(stage);

        //Checks whether file is selected
        if(file!=null){
            ReadFiles readFiles = new ReadFiles();
           HashMap<String, List> strings  = readFiles.readTextFile(file);
           for(Map.Entry<String, List> maps : strings.entrySet()){
             //  System.out.println(maps.getKey()+ ": "+maps.getValue() );

           }
           SentimentAnalyser sentenceDetection = new SentimentAnalyser();
           sentenceDetection.sentenceSplitter(strings);
        }





            Stage appStage;
            Parent parent;

                appStage=(Stage)root.getScene().getWindow();
                parent= FXMLLoader.load(getClass().getResource("singleFile.fxml"));
                Scene scene=new Scene(parent);
                appStage.setScene(scene);
                appStage.show();

          //  if(event.getSource()==btnExit)
           // {
          //      Platform.exit();
        //    }



    }
    @FXML
    private void multipleFileChooser() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Multiple file");
        Stage stage = (Stage) root.getScene().getWindow();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("text file", "*.txt"),
                new FileChooser.ExtensionFilter("doc","*.doc"),
                new FileChooser.ExtensionFilter("csv file","*.csv"));
        fileChooser.showOpenMultipleDialog(stage);


        Stage appStage;
        Parent parent;

        appStage=(Stage)root.getScene().getWindow();
        parent= FXMLLoader.load(getClass().getResource("multipleFile.fxml"));
        Scene scene=new Scene(parent);
        appStage.setScene(scene);
        appStage.show();
    }
    @FXML
    public void instruction() throws IOException {
        FileReader fileReader=new FileReader("Sentiment-Analyzer/src/main/resources/IMT3281/instruction");
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
        Scene scene = new Scene(textArea,400,200);
        newStage.setTitle("Instructions");
        newStage.setScene(scene);
        newStage.show();



        bufferedReader.close();
        fileReader.close();
    }

        @FXML
    public void onExit(){
        System.exit(0);

    }


}
