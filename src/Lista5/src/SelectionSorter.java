package Lista5.src;

public class SelectionSorter implements ISorter {
    private final IChecker checker;

    public SelectionSorter(IChecker checker) {
        this.checker = checker;
    }

    @Override
    public void sort(int[] values) {
        for(int i = 0; i < values.length; i++) {
            int min = i;
            for(int j = i; j < values.length; j++) {
                if(values[j] < values[min]) {
                    min = j;
                }
            }
            int temp = values[i];
            values[i] = values[min];
            values[min] = temp;
            checker.check(values);
        }
    }
}
