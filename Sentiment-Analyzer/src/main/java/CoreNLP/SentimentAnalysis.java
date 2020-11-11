package CoreNLP;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.List;

public class SentimentAnalysis {
    public static void main(String[] args) {
        StanfordCoreNLP stanfordCoreNLP = PipeLine.getPipeLine();

        String text = "Hello this is John. I really don't like this place.";
        CoreDocument coreDocument = new CoreDocument(text);
        stanfordCoreNLP.annotate(coreDocument);
        //Get the sentence from the text/or paragraph
        List<CoreSentence>coreSentences =coreDocument.sentences();
        for(CoreSentence sentence : coreSentences){
           String sentiment =   sentence.sentiment();
            System.out.println(sentiment+'\t'+sentence);
        }

    }
}
