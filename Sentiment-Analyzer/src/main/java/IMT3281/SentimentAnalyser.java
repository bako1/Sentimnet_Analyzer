package IMT3281;


import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SentimentAnalyser {
private String fileName;
private String subject;
private String polarity;
private int occurrence;
private StanfordCoreNLP stanfordCoreNLP;

    public SentimentAnalyser(){
        this.occurrence = 100;
        ReadFiles readFiles = new ReadFiles();
        stanfordCoreNLP = PipeLine.getPipeLine();



    }
    public HashMap<String,String> sentenceSplitter(HashMap<String, List>sentenceInput){
        String[] split;
        String simple = "[.?!]";
        for (Map.Entry<String,List> stringHashMapEntry : sentenceInput.entrySet()){

            split =  stringHashMapEntry.getValue().toString().split(simple);
            System.out.println("File: "+stringHashMapEntry.getKey());
            setFileName(stringHashMapEntry.getKey());
          for(String s:split)
            System.out.println( "\n"+s);
        }
    return null;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setPolarity(String polarity) {
        this.polarity = polarity;
    }

    public void setOccurrence(int occurrence) {
        this.occurrence = occurrence;
    }

    public String getFileName() {
        return fileName;
    }

    public String getSubject() {
        return subject;
    }

    public String getPolarity() {
        return polarity;
    }

    public int getOccurrence() {
        return occurrence;
    }
}