package IMT3281;

import java.util.ArrayList;

public class Table {
    private String target;
    private String subject;
    private String polarity;
    private String sentence;
    private ArrayList<String> processedSentence;

    public ArrayList<String> getProcessedSentence() {
        return processedSentence;
    }

    public void setProcessedSentence(ArrayList<String> processedSentence) {
        this.processedSentence = processedSentence;
    }
    public Table(){}
    public Table(String target, String polarity){
        this.target = target;
        this.polarity = polarity;
    }
    public Table(String target,String subject,String polarity){
        this.target = target;
        this.subject = subject;
        this.polarity = polarity;
    }

    public String getTarget() {
        return target;
    }

    public String getSubject() {
        return subject;
    }

    public String getPolarity() {
        return polarity;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public void setTarget(String fileName) {
        this.target = fileName;
    }
}
