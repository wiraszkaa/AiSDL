package Lista6.src;


import java.util.Arrays;
import java.util.Collections;

public class RadixSorter implements ISorter {
    private final IChecker checker;

    public RadixSorter(IChecker checker) {
        this.checker = checker;
    }

    @Override
    public void sort(int[] values) {
        int n = getDigits(values);
        for(int i = 0; i < n; i++) {
            sortByDigit(values, i);
            checker.check(values);
        }
    }

    private int getDigits(int[] values) {
        int max = Integer.MIN_VALUE;
        for(int i: values) {
           max = Math.max(max, i);
        }
        return String.valueOf(max).split("").length;
    }

    private void sortByDigit(int[] values, int digit) {
        int[] countedDigits = new int[10];
        Arrays.fill(countedDigits, 0);
        int divider = (int) Math.pow(10, digit);
        for(int i: values) {
            countedDigits[(i / divider % 10)]++;
        }
        for(int i = 1; i < 10; i++) {
            countedDigits[i] += countedDigits[i - 1];
        }
        int[] result = new int[values.length];
        for (int i = values.length - 1; i >= 0; i--) {
            result[countedDigits[values[i] / divider % 10] - 1] = values[i];
            countedDigits[values[i] / divider % 10]--;
        }
        System.arraycopy(result, 0, values, 0, result.length);
    }
}
