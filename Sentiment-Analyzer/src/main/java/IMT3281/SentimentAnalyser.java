package IMT3281;


import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.trees.UniversalEnglishGrammaticalRelations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SentimentAnalyser {
    private String fileName;
    private String subject;
    private String polarity;
    private String sentence;
    private int positiveOccurrence;
    private int negativeOccurrence;
    private int neutralOccurrence;
    private int occurrence;
    private ArrayList<String> processedSentences;
    private StanfordCoreNLP stanfordCoreNLP;
    private CoreDocument coreDocument;

    public SentimentAnalyser() {
        processedSentences = new ArrayList<>();


    }


    public void sentimentAnalysis() {
        stanfordCoreNLP = PipeLine.getPipeLine();
        String text = processedSentences.toString();
        coreDocument = new CoreDocument(text);
        stanfordCoreNLP.annotate(coreDocument);
        //Get the sentence from the text/or paragraph
        List<CoreSentence> coreSentences = coreDocument.sentences();
        HashMap<String, String> sentencePolarityMap = new HashMap<>();
        for (CoreSentence sentence : coreSentences) {
            String sentiment = sentence.sentiment();
            setPolarity(sentiment);
            sentencePolarityMap.put(sentence.toString(), getPolarity());
        }
        //for (Map.Entry<String, String> stringStringEntry : sentencePolarityMap.entrySet()) {

        //}

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


    public String getFileName() {
        return fileName;
    }

    public void setOccurrence(int occurrence) {
        this.occurrence = occurrence;
    }

    public int getOccurrence() {
        return occurrence;
    }

    public String getSubject() {
        return subject;
    }

    public String getPolarity() {
        return polarity;
    }

    public void setPositiveOccurrence(int positiveOccurrence) {
        this.positiveOccurrence = positiveOccurrence;
    }

    public int getPositiveOccurrence() {
        return positiveOccurrence;
    }

    public ArrayList<String> getProcessedSentences() {
        return processedSentences;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    //splits sentences
    public void sentenceRecognizer(HashMap<String, List> input) {
        String text;
        if (input != null) {
            for (Map.Entry<String, List> listEntry : input.entrySet()) {
                text = listEntry.getValue().toString();
                setFileName(listEntry.getKey());
                stanfordCoreNLP = CoreNLP.PipeLine.getPipeLine();
                CoreDocument coreDocument = new CoreDocument(text);
                stanfordCoreNLP.annotate(coreDocument);
                List<CoreSentence> sentenceList = coreDocument.sentences();
                for (CoreSentence sentence : sentenceList)
                    processedSentences.add(sentence.toString());
                setSentence(sentence);
            }

        }
    }
}
