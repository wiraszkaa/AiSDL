package Lista6.src;

public class IterativeMergeSorter implements ISorter {
    private final IChecker checker;

    public IterativeMergeSorter(IChecker checker) {
        this.checker = checker;
    }

    @Override
    public void sort(int[] values) {
        for(int size = 1; size <= values.length - 1; size *= 2) {
            for (int i = 0; i < values.length - 1; i += 2 * size) {
                merge(values, i, Math.min(i + size - 1, values.length - 1), Math.min(i + 2 * size - 1, values.length - 1));
            }
            checker.check(values);
        }
    }

    private void merge(int[] values, int leftIndex, int middleIndex, int rightIndex) {
        int[] left = new int[middleIndex - leftIndex + 1];
        int[] right = new int[rightIndex - middleIndex];
        System.arraycopy(values, leftIndex, left, 0, left.length);
        System.arraycopy(values, middleIndex + 1, right, 0, right.length);
        int i = leftIndex;
        int i1 = 0;
        int i2 = 0;
        while(i1 < left.length && i2 < right.length) {
            if(left[i1] <= right[i2]) {
                values[i] = left[i1];
                i1++;
            } else {
                values[i] = right[i2];
                i2++;
            }
            i++;
        }
        while(i1 < left.length) {
            values[i] = left[i1];
            i1++;
            i++;
        }
        while(i2 < right.length) {
            values[i] = right[i2];
            i2++;
            i++;
        }
    }
}
