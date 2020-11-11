
package IMT3281;
import opennlp.tools.sentdetect.SentenceDetector;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class openNLP {
    public static void main(String[] args) {
    openNLP openNLP = new openNLP();
    try {
        openNLP.useOpenNlp("My name is Abdi Bako and I am Africa. What about you my brother?","Sentiment-Analyzer/src/main/resources/IMT3281/en-sent.bin","sentence");
    }catch (IOException e){
        System.out.println("e.getMessage()");
    }
    }
    public void useOpenNlp(String sourceText,String modelPath, String choice) throws IOException{
        InputStream modelIn;
        modelIn = new FileInputStream(modelPath);
        if(choice.equalsIgnoreCase("sentence")){
            SentenceModel model = new SentenceModel(modelIn);
            modelIn.close();
            SentenceDetectorME sentenceDetectorME = new SentenceDetectorME(model);
        String sentence[] = sentenceDetectorME.sentDetect(sourceText);
            System.out.println("Sentence: ");
            for (String sent : sentence)
                System.out.println(sent);
        }
        else if (choice.equalsIgnoreCase("word")){
            TokenizerModel model = new TokenizerModel(modelIn);
            modelIn.close();
            Tokenizer tokenizer = new TokenizerME(model);
            String tokens[] = tokenizer.tokenize(sourceText);
            System.out.println("Words: ");
            for (String token: tokens)
                System.out.println(token);
        }else {
            System.out.println("Error in choice ");
            modelIn.close();
            return;

        }
    }
}

