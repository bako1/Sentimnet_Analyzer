package CoreNLP;


import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

import java.util.Properties;

public class CoreNLPTest {
    public static void main(String[] args) {


    StanfordCoreNLP pipeLien;
    Properties props = new Properties();
    props.put("annotators","tokenize,ssplit,pos,lemma");
    pipeLien = new StanfordCoreNLP(props,false);
    String text = "Prepared by experienced English teachers, the texts, articles and conversations are brief and appropriate to your level of proficiency. Take the multiple-choice quiz following each text, and you'll get the results immediately. You will feel both challenged and accomplished! You can even download (as PDF) and print the texts and exercises. It's enjoyable, fun and free. Good luck!\n" +
            "\n";
        Annotation document = pipeLien.process(text);
        for(CoreMap sentence : document.get(CoreAnnotations.SentencesAnnotation.class)){
          for(CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)){
            String word = token.get(CoreAnnotations.TextAnnotation.class);
          String lemma  = token.get(CoreAnnotations.LemmaAnnotation.class);
            System.out.println("lemmetized ver: "+lemma);
        }
    }

}}
