package Lista12.src;

import java.util.*;

public class AutomatonMatcher implements IStringMatcher {
    private final HashMap<Integer, HashMap<Character, Integer>> transitionFunction;

    public AutomatonMatcher() {
        transitionFunction = new HashMap<>();
    }

    @Override
    public List<Integer> validShifts(String textToSearch, String patternToFind) {
        List<Integer> patternIndexes = new LinkedList<>();
        char[] textArray = textToSearch.toCharArray();
        int length = patternToFind.length();
        createTransitionFunction(patternToFind, getUniqueCharacters(textToSearch + patternToFind), length);
        int q = 0;
        for (int i = 0; i < textArray.length; i++) {
             q = transitionFunction.get(q).get(textArray[i]);
            if (q == length) {
                patternIndexes.add(i - length + 1);
            }
        }
        return patternIndexes;
    }

    private void createTransitionFunction(String patternToFind, Set<Character> alphabet, int length) {
        buildTransitionMap(length);

        for (int q = 0; q <= length; q++) {
            for (char character: alphabet) {
                int k = Math.min(length + 1, q + 2);
                do {
                    k--;
                } while(isNotSuffix(patternToFind, character, q, k));
                transitionFunction.get(q).put(character, k);
            }
        }
    }

    private void buildTransitionMap(int states) {
        for (int i = 0; i <= states; i++) {
            transitionFunction.put(i, new HashMap<>());
        }
    }

    private boolean isNotSuffix(String patternArray, char character, int q, int k) {
        String string = patternArray.substring(0, q) + character;
        return !string.endsWith(patternArray.substring(0, k));
    }

    private Set<Character> getUniqueCharacters(String text) {
        Set<Character> characters = new TreeSet<>();
        for (char i: text.toCharArray()) {
            characters.add(i);
        }
        return characters;
    }
}
