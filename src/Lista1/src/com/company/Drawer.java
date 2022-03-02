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
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (i == 0 || j == 0 || i == size - 1 || j == size - 1) {
                        System.out.print("#");
                    } else {
                        System.out.print(" ");
                    }
                }
                System.out.print("\n");
            }
        } else {
            System.out.print("fail\n");
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

    private static void pyramidHandler(int size, int k) {
        for (int i = 1; i <= k; i++) {
            for (int j = 0; j < size - i; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < i * 2 - 1; j++) {
                System.out.print("#");
            }
            System.out.print("\n");
        }
    }
}
