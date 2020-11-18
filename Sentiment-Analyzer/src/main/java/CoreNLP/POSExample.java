package CoreNLP;

import edu.stanford.nlp.ling.CoreAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.List;

public class POSExample {
    public static void main(String[] args) {
        StanfordCoreNLP stanfordCoreNLP = PipeLine.getPipeLine();
        String text = "Hey! Abdi ABdi Bako. I am student, husband and father of two beautiful children";
        CoreDocument coreDocument = new CoreDocument(text);
        stanfordCoreNLP.annotate(coreDocument);
        List<CoreLabel>coreLabelList = coreDocument.tokens();
        for (CoreLabel coreLabel : coreLabelList){
         String pos =   coreLabel.get(CoreAnnotations.PartOfSpeechAnnotation.class);
            System.out.println(coreLabel.originalText() + " :"+pos);


        }

    }
}
