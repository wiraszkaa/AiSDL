package Lista5.src;

public class InsertionSorter implements ISorter {
    private final IChecker checker;

    public InsertionSorter(IChecker checker) {
        this.checker = checker;
    }

    @Override
    public void sort(int[] values) {
        for(int i = 1; i < values.length; i++) {
            int j = i - 1;
            int temp = values[i];
            while(j >= 0 && temp < values[j]) {
                values[j + 1] = values[j];
                j--;
            }
            values[j + 1] = temp;
            checker.check(values);
        }
    }
}
