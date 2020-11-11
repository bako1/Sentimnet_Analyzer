package CoreNLP;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.List;

public class Lemmetization {
    //Identifies the root of the word

    public static void main(String[] args) {
        StanfordCoreNLP stanfordCoreNLP = PipeLine.getPipeLine();
        String text  = "I am learning new technologies. What about you bro";
        //creating the document
        CoreDocument coreDocument = new CoreDocument(text);
        stanfordCoreNLP.annotate(coreDocument);
        //gets tokenized
       List<CoreLabel> coreLabelList = coreDocument.tokens();
       for(CoreLabel coreLabel : coreLabelList){
//perfdorm lemma
           String lemma = coreLabel.lemma();
           System.out.println(coreLabel.originalText()+ ": "+lemma);
       }

    }
}
