package IMT3281;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.Properties;

public class PipeLine {
        private static Properties properties;
        private static StanfordCoreNLP stanfordCoreNLP;
        //ssplit for sentence recognition
    //pos,ner,lemma,
        private static String propertiesName = "tokenize, ssplit ,parse,sentiment";

        private PipeLine(){

        }
        //Load alla the annotator into the propery
        static {
            properties = new Properties();
            properties.setProperty("annotators",propertiesName);
        }
        //if stanfordCoreNLP is null we create new object and return it
        //else return the existing object so that we get only one object
        //is typical singleton design pattern
        public static StanfordCoreNLP getPipeLine(){
            if(stanfordCoreNLP == null) stanfordCoreNLP = new StanfordCoreNLP(properties);
            return stanfordCoreNLP;
        }
    }


