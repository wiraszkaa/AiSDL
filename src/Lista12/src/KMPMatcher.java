package Lista12.src;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class KMPMatcher implements IStringMatcher {
    private final HashMap<Integer, Integer> prefixFunction;

    public KMPMatcher() {
        prefixFunction = new HashMap<>();
    }

    @Override
    public List<Integer> validShifts(String textToSearch, String patternToFind) {
        List<Integer> patternIndexes = new LinkedList<>();

        char[] textArray = textToSearch.toCharArray();
        char[] patternArray = patternToFind.toCharArray();
        computePrefixFunction(patternArray);

        int q = 0;
        for (int i = 0; i < textArray.length; i++) {
            while (q > 0 && patternArray[q + 1] != textArray[i]) {
                q = prefixFunction.get(q);
            }
            if (patternArray[q + 1] == textArray[i]) {
                q++;
            }
            if (q == patternArray.length - 1) {
                patternIndexes.add(i - patternArray.length + 1);
                q = prefixFunction.get(q);
            }
        }
        return patternIndexes;
    }

    private void computePrefixFunction(char[] patternArray) {
        prefixFunction.put(0, 0);
        int k = 0;
        for (int q = 1; q < patternArray.length; q++) {
            while (k > 0 && patternArray[k + 1] != patternArray[q]) {
                k = prefixFunction.get(k);
            }
            if (patternArray[k] == patternArray[q]) {
                k++;
            }
            prefixFunction.put(q, k);
        }
    }
}
