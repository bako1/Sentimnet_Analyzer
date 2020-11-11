package CoreNLP;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.List;

public class TokenizeEsx {
    public static void main(String[] args) {
        StanfordCoreNLP stanfordCoreNLP = PipeLine.getPipeLine();

    String text = "This is Abdi Bako! But what is tokenization?";
        CoreDocument coreDocument = new CoreDocument(text);
        //annotating the text/string
        stanfordCoreNLP.annotate(coreDocument);
        List<CoreLabel>coreLabels = coreDocument.tokens();
        for(CoreLabel coreLabel:coreLabels){
            System.out.println(coreLabel.originalText());
        }
    }
}
