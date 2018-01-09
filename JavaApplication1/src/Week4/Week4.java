package week4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Comparator;

public class Week4 {

    static final Integer ONE = 1;

    public static void main(String args[]) throws IOException {
        Hashtable map = new Hashtable();
        FileReader fr = new FileReader("D:\\Arnout\\documenten\\GitHub\\lag\\JavaApplication1\\src\\Week4\\dickens.txt");
        BufferedReader br = new BufferedReader(fr);
        String line;

        while ((line = br.readLine()) != null) {
            processLine(line, map);
        }

        PrintAndSortValue(map, true);
    }

    static void processLine(String line, Map map) {
        StringTokenizer st = new StringTokenizer(line);
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            token = token.replaceAll("[^a-zA-Z ]", "").toLowerCase(); //remove punctuation and capital letters
            addWord(map, token);
        }
    }

    static void addWord(Map map, String word) {
        Object obj = map.get(word);
        if (obj == null) {
            map.put(word, ONE);
        } else {
            int i = ((Integer) obj) + 1;
            map.put(word, i);
        }
    }

    public static void PrintAndSortValue(Hashtable<?, Integer> t, Boolean justprint) {
        //Transfer as List and sort it
        if (!justprint) {
            ArrayList<Map.Entry<?, Integer>> sortedArray = new ArrayList(t.entrySet());
            Collections.sort(sortedArray, new Comparator<Map.Entry<?, Integer>>() {

                public int compare(Map.Entry<?, Integer> o1, Map.Entry<?, Integer> o2) {
                    return o1.getValue().compareTo(o2.getValue());
                }
            });

            for (int i = 0; i < sortedArray.size(); i++) {
                System.out.println(sortedArray.get(i));
            }
        } else {
            Enumeration e = t.keys();
            while (e.hasMoreElements()) {
                String key = (String) e.nextElement();
                System.out.println(key + ":" + t.get(key));
            }
        }
    }
}
