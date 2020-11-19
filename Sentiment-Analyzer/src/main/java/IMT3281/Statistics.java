package IMT3281;

import java.util.*;

public class Statistics {
    Integer[] list = {0, 0, 0}; // Positive, negative, neutral

    private static String pos = "Positive";
    private static String neg = "Negative";
    private static String neu = "Neutral";

    private int sentences = 0;

    public Statistics(){
    }

    public int getPos() {
        return list[0];
    }

    public int getNeg() {
        return list[1];
    }

    public int getNeu() {
        return list[2];
    }

    public int getMax() {
        return Collections.max(Arrays.asList(list));
    }

    public void addSentence() {
        this.sentences++;
    }

    public int getSentence() {
        return this.sentences;
    }

    public void addStat(String str) {
        if (str.equalsIgnoreCase(pos)) {
            list[0]++;
        } else if (str.equalsIgnoreCase(neg)) {
            list[1]++;
        } else if (str.equalsIgnoreCase(neu)) {
            list[2]++;
        } else {
      //      return 1;
        }
       // return 0;
    }
}
