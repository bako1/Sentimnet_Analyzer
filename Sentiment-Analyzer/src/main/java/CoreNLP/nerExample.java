package CoreNLP;

import edu.stanford.nlp.ling.CoreAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.List;

public class nerExample {
    public static void main(String[] args) {
        StanfordCoreNLP stanfordCoreNLP = PipeLine.getPipeLine();
        String text = "I am trying to understand ner. What about you HABIBI?, I and My wife with my children is leaving in Oslo Norway";
        CoreDocument coreDocument = new CoreDocument(text);
        stanfordCoreNLP.annotate(coreDocument);
        List<CoreLabel>coreLabels = coreDocument.tokens();
        for(CoreLabel coreLabel : coreLabels){
            String ner = coreLabel.get(CoreAnnotations.NamedEntityTagAnnotation.class);
            System.out.println(coreLabel.originalText()+ ": "+ner);
        }

    }
}
