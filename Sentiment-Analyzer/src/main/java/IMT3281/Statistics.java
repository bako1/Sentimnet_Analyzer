package IMT3281;

import java.util.*;

public class Statistics {
    Integer[] list = {0, 0, 0}; // Positive, negative, neutral

    private static String pos = "Positive";
    private static String neg = "Negative";
    private static String neu = "Neutral";

    private HashMap<String, ArrayList<String>> subjects = new HashMap<String, ArrayList<String>>();

    private int sentences = 0;

    private int filePos = 0;
    private int fileNeg = 0;
    private int fileNeu = 0;

    public int getFilePos() {
        return filePos;
    }

    public int getFileNeg() {
        return fileNeg;
    }

    public int getFileNeu() {
        return fileNeu;
    }

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
        if (list[0] == max) {
            filePos++;
            return pos;
        }
        else if(list[1] == max) {
            fileNeg++;
            return neg;
        }
        else {
            fileNeu++;
            return neu;
        }
    }

    public void reset() {
        this.sentences = 0;
        for (int i = 0; i < 3; i++) {
            list[i] = 0;
        }
    }

    public void addSubject(String file, String subject) {
        if (subjects.containsKey(file)) {
            subjects.get(file).add(subject);
        }
        else {
            ArrayList<String> arr = new ArrayList<>();
            arr.add(subject);
            subjects.put(file, arr);
        }
    }

    public String getSubjects() {
        String subs = "";
        for (Map.Entry<String, ArrayList<String>> entry : subjects.entrySet()) {
            subs += entry.getKey();
            subs += ": ";
            Iterator it = entry.getValue().iterator();
            while (it.hasNext()) {
                subs += it.next();
                if (it.hasNext()) {
                    subs += ", ";
                }
            }
            subs += "\n";
        }
        return subs;
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