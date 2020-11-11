package CoreNLP;

import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.List;

public class SentyenceRecognizer {
    public static void main(String[] args) {
     StanfordCoreNLP stanfordCoreNLP = PipeLine.getPipeLine();
     String text = "I am Abdi Bako. I am student and father of two children";
        CoreDocument coreDocument = new CoreDocument(text);
        stanfordCoreNLP.annotate(coreDocument);
        List<CoreSentence> sentenceList = coreDocument.sentences();
        for(CoreSentence coreSentence : sentenceList){
            System.out.println(coreSentence.toString());
        }

    }
}
