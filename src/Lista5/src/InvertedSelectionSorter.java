package Lista5.src;

public class InvertedSelectionSorter implements ISorter {
    private final IChecker checker;

    public InvertedSelectionSorter(IChecker checker) {
        this.checker = checker;
    }

    @Override
    public void sort(int[] values) {
        for(int i = values.length - 1; i >= 0; i--) {
            int max = i;
            for(int j = i; j >= 0; j--) {
                if(values[j] > values[max]) {
                    max = j;
                }
            }
            int temp = values[i];
            values[i] = values[max];
            values[max] = temp;
            checker.check(values);
        }
    }
}
