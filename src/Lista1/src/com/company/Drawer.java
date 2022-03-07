package Lista1.src.com.company;

public class Drawer {
    public static void drawTriangle(int size) {
        if (size > 0) {
            for (int i = 1; i <= size; i++) {
                for (int j = 0; j < i; j++) {
                    System.out.print("#");
                }
                System.out.print("\n");
            }
        } else {
            System.out.print("fail\n");
        }
    }

    public static void drawSquare(int size) {
        if (size > 0) {
            handleRectangle(size, size);
        } else {
            System.out.print("fail\n");
        }
    }

    public static void drawRectangle(int width, int height) {
        if (width > 0 && height > 0) {
            handleRectangle(width, height);
        } else {
            System.out.print("fail\n");
        }
    }

    private static void handleRectangle(int width, int height) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 || j == 0 || i == height - 1 || j == width - 1) {
                    System.out.print("#");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.print("\n");
        }
    }

    public static void drawPyramid(int size) {
        if (size > 0) {
            pyramidHandler(size, size);
        } else {
            System.out.print("fail\n");
        }
    }

    public static void drawChristmasTree(int size) {
        if (size > 0) {
            for (int k = 1; k <= size; k++) {
                pyramidHandler(size, k);
            }
        } else {
            System.out.print("fail\n");
        }
    }

    private static void pyramidHandler(int size, int segmentSize) {
        // segmentSize is used in christmastree and defines size of each segment, size is needed to center all segments
        for (int i = 1; i <= segmentSize; i++) {
            for (int j = 0; j < size - i; j++) { // after every iteration prints 1 less space
                System.out.print(" ");
            }
            for (int j = 0; j < i * 2 - 1; j++) { // after every iterations prints 2 more #
                System.out.print("#");
            }
            System.out.print("\n");
        }
    }
}
