package Lista10.src;

import Lista9.src.DisjointSetForest;
import Lista9.src.ItemOutOfRangeException;

import java.util.*;

public class Graph<T> {
    private final HashMap<T, Integer> dictionary;
    private HashMap<T, Boolean> nodesVisited;
    private int nodesAmount;
    private Edge<T>[][] neighboursMatrix;

    private final DisjointSetForest connectedElements;

    public Graph(List<Edge<T>> edges) {
        dictionary = new HashMap<>();
        nodesAmount = 0;
        neighboursMatrix = (Edge<T>[][]) new Edge[10][10];
        for (Edge<T> edge : edges) {
            T source = edge.getSource();
            T destination = edge.getDestination();
            addNode(source);
            addNode(destination);
            handleFullMatrix();
            neighboursMatrix[getIndex(source)][getIndex(destination)] = edge;
        }
        connectedElements = new DisjointSetForest(nodesAmount);
    }

    private void addNode(T node) {
        if (!dictionary.containsKey(node)) {
            dictionary.put(node, nodesAmount);
            nodesAmount++;
        }
    }

    private int getIndex(T node) {
        if (dictionary.containsKey(node)) {
            return dictionary.get(node);
        }
        return -1;
    }

    private void handleFullMatrix() {
        if (nodesAmount >= neighboursMatrix.length) {
            int length = neighboursMatrix.length * 2;
            Edge<T>[][] newMatrix = new Edge[length][length];
            for (int i = 0; i < neighboursMatrix.length; i++) {
                System.arraycopy(neighboursMatrix[i], 0, newMatrix[i], 0, neighboursMatrix.length);
            }
            neighboursMatrix = newMatrix;
        }
    }

    public String depthFirst(T startNode) throws NoSuchElementException {
        return listToString(getDFS(startNode));
    }

    private List<T> getDFS(T startNode) {
        checkIndex(startNode);
        nodesVisited = new HashMap<>();
        List<T> nodes = new LinkedList<>();

        DFS(startNode, nodes);

        return nodes;
    }

    private void DFS(T node, List<T> nodes) {
        if (isVisited(node)) {
            return;
        }
        nodes.add(node);
        visitNode(node);
        for (T i : getSortedNeighbours(node)) {
            DFS(i, nodes);
        }
    }

    private boolean isVisited(T node) {
        if (!nodesVisited.containsKey(node)) {
            nodesVisited.put(node, false);
        }

        return nodesVisited.get(node);
    }

    private void visitNode(T node) {
        nodesVisited.put(node, true);
    }

    private void checkIndex(T startNode) {
        if (getIndex(startNode) == -1) {
            throw new NoSuchElementException();
        }
    }

    public String breadthFirst(T startNode) throws NoSuchElementException {
        return listToString(getBFS(startNode));
    }

    private List<T> getBFS(T startNode) {
        checkIndex(startNode);
        nodesVisited = new HashMap<>();
        List<T> nodes = new LinkedList<>();
        List<T> nodesToVisit = new LinkedList<>();
        nodesToVisit.add(startNode);

        while (!nodesToVisit.isEmpty()) {
            T currentNode = nodesToVisit.get(0);
            nodesToVisit.addAll(getSortedNeighbours(currentNode));
            if (!isVisited(currentNode)) {
                nodes.add(currentNode);
                visitNode(currentNode);
            }
            nodesToVisit.remove(currentNode);
        }

        return nodes;
    }

    private List<T> getSortedNeighbours(T node) {
        TreeSet<Edge<T>> neighbours = new TreeSet<>(new EdgesComparator());
        List<T> result = new LinkedList<>();

        for (int i = 0; i < nodesAmount; i++) {
            Edge<T> edge = neighboursMatrix[getIndex(node)][i];
            if (edge != null) {
                if (!isVisited(edge.getDestination())) {
                    neighbours.add(edge);
                    result.add(edge.getDestination());
                }
            }
        }
//        for (Edge<T> edge : neighbours) {
//            result.add(edge.getDestination());
//        }

        return result;
    }

    public int connectedComponents() {
        for(int i = 0; i < nodesAmount; i++) {
            for(int j = 0; j < nodesAmount; j++) {
                Edge<T> edge = neighboursMatrix[i][j];
                if(edge != null) {
                    handleConnectedElements(edge.getSource(), edge.getDestination());
                }
            }
        }
        return connectedElements.getAmount();
    }

    private void handleConnectedElements(T source, T destination) {
        int sourceIndex = getIndex(source);
        int destinationIndex = getIndex(destination);
        try {
            if (connectedElements.findSet(sourceIndex) != connectedElements.findSet(destinationIndex)) {
                connectedElements.union(sourceIndex, destinationIndex);
            }
        } catch (ItemOutOfRangeException ignored) {

        }
    }

    private String listToString(List<T> nodes) {
        StringBuilder sb = new StringBuilder();

        for (T node : nodes) {
            sb.append(node.toString()).append(", ");
        }

        return sb.substring(0, sb.length() - 2);
    }

    private class EdgesComparator implements Comparator<Edge<T>> {
        @Override
        public int compare(Edge<T> o1, Edge<T> o2) {
            if (o1.getWeight() < o2.getWeight()) {
                return -1;
            } else if (o1.getWeight() > o2.getWeight()) {
                return 1;
            } else {
                return o1.hashCode() - o2.hashCode();
            }
        }
    }
}
