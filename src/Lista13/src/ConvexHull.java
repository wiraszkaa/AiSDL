package Lista13.src;

import java.awt.*;
import java.util.*;
import java.util.List;

public class ConvexHull {
    public static List<Point> solve(List<Point> points) throws IllegalArgumentException {
        if (points.size() < 3) {
            throw new IllegalArgumentException();
        }

        Point start = getBottommostPoint(points);
        List<Point> sortedPoints = new LinkedList<>(points);
        sortedPoints.sort(new PolarPointComparator(start));
        points = removeCollinear(sortedPoints, start);

        if (points.size() < 3) {
            throw new IllegalArgumentException();
        }

        MyStack<Point> pointStack = new MyStack<>();
        for (int i = 0; i < 3; i++) {
            pointStack.push(points.get(i));
        }

        for (int i = 3; i < points.size(); i++) {
            while(orientation(pointStack.nextToTop(), pointStack.top(), points.get(i)) >= 0) {
                pointStack.pop();
            }
            pointStack.push(points.get(i));
        }

        List<Point> result = new LinkedList<>(pointStack);
        result.add(start);
        return result;
    }

    private static int orientation(Point p, Point q, Point r) {
        return (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y); // clock or counterclock wise
    }

    private static int orientationMatrix(Point p, Point q, Point r) {
        int addValue = p.x * q.y + p.y * r.x + q.x * r.y;
        int subValue = p.y * q.x + p.x * r.y + q.y * r.x;
        int result = addValue - subValue;

        if (result == 0) {
            return 0; // collinear
        }

        return (result > 0) ? -1 : 1; // counterclock or clock wise
    }

    private static Point getBottommostPoint(List<Point> points) {
        Point bottomPoint = points.get(0);
        for (Point point: points) {
            if (point.y < bottomPoint.y) {
                bottomPoint = point;
            } else if (point.y == bottomPoint.y) {
                if (point.x < bottomPoint.x) {
                    bottomPoint = point;
                }
            }
        }
        return bottomPoint;
    }

    private static List<Point> removeCollinear(List<Point> points, Point start) {
        List<Point> result = new LinkedList<>();
        result.add(start);
        for (int i = 1; i < points.size(); i++) {
            while(i < points.size() - 1 && orientation(start, points.get(i), points.get(i + 1)) == 0) {
                i += 1;
            }
            result.add(points.get(i));
        }
        return result;
    }

    private record PolarPointComparator(Point startingPoint) implements Comparator<Point> {
        @Override
        public int compare(Point o1, Point o2) {
            int orientation = orientation(startingPoint, o1, o2);
            if (orientation == 0) {
                double distance = o1.distance(startingPoint) - o2.distance(startingPoint);
                if (distance == 0) {
                    return o1.hashCode() - o2.hashCode();
                } else if (distance > 0) {
                    return 1;
                } else {
                    return -1;
                }
            } else {
                return orientation;
            }
        }
    }

    private static class MyStack<T> extends Stack<T> {
        public T top() {
            T value = super.pop();
            super.push(value);

            return value;
        }

        public T nextToTop() {
            T temp = super.pop();
            T value = top();
            super.push(temp);

            return value;
        }
    }
}
