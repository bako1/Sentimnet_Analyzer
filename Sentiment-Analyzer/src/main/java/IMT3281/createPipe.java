package IMT3281;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.io.IOException;
import java.util.Properties;

public class createPipe extends Thread {

    public createPipe() {

    }

    public void run() {
        PipeLine.getPipeLine();

    }

}
