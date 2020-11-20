package IMT3281;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.Properties;

public class PipeLine {
    private static Properties properties;
    private static StanfordCoreNLP stanfordCoreNLP;
    private static String propertiesName = "tokenize, ssplit , pos, lemma, depparse, natlog, parse, sentiment, openie";

    private PipeLine(){

    }
    //Load all annotators into the property
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


