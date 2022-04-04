package Lista5.src;

public class BubbleSorter implements ISorter {
    private final IChecker checker;

    public BubbleSorter(IChecker checker) {
        this.checker = checker;
    }

    @Override
    public void sort(int[] values) {
        boolean positionSwitch = false;
        for(int i = 0; i < values.length - 1; i++) {
            for(int j = 0; j < values.length - i - 1; j++) {
                if(values[j + 1] < values[j]) {
                    int temp = values[j + 1];
                    values[j + 1] = values[j];
                    values[j] = temp;
                    positionSwitch = true;
                }
            }
            checker.check(values);
            if(!positionSwitch) {
                break;
            }
            positionSwitch = false;
        }
    }
}
