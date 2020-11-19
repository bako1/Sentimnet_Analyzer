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

    public String getMax() {
        int max = Collections.max(Arrays.asList(list));
        System.out.println("max from stats: " + max);
        if (list[0] == max) {
            System.out.println("pos from stats: " + pos);
            return pos;
        }
        else if(list[1] == max) {
            System.out.println("neg from stats: " + neg);
            return neg;
        }
        else {
            System.out.println("neu from stats: "  + neu);
            return neu;
        }
    }

    public void reset() {
        this.sentences = 0;
        for (int i = 0; i < 3; i++) {
            list[i] = 0;
        }
    }

    public void addSentence() {
        this.sentences++;
    }

    public int getSentence() {
        return this.sentences;
    }

    public int addStat(String str) {
        if (str.equalsIgnoreCase(pos)) {
            list[0]++;
        } else if (str.equalsIgnoreCase(neg)) {
            list[1]++;
        } else if (str.equalsIgnoreCase(neu)) {
            list[2]++;
        } else {
            return 1;
        }
        return 0;
    }
}
