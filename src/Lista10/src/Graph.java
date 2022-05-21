package Lista10.src;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

public class Graph<T> {
    private final HashMap<T, Integer> dictionary;
    private int nodeAmount;
    private int[][] neighboursMatrix;

    public Graph(List<Edge<T>> edges) {
        // TODO: Przekształcenie krawędzi na macierz sąsiedztwa, odwzorowanie wierzchołka na indeks, itp.
        dictionary = new HashMap<>();
        nodeAmount = 0;
        neighboursMatrix = new int[10][10];
        for(Edge<T> edge: edges) {
            T source = edge.getSource();
            T destination = edge.getDestination();
            addNode(source);
            addNode(destination);
            handleFullMatrix();
            neighboursMatrix[getIndex(source)][getIndex(destination)] = edge.getWeight();
        }
    }

    private void addNode(T node) {
        if(!dictionary.containsKey(node)) {
            dictionary.put(node, nodeAmount);
            nodeAmount++;
        }
    }

    private int getIndex(T node) {
        return dictionary.get(node);
    }

    private void handleFullMatrix() {
        if(nodeAmount >= neighboursMatrix.length) {
            int length = neighboursMatrix.length * 2;
            int[][] newMatrix = new int[length][length];
            for(int i = 0; i < neighboursMatrix.length; i++) {
                System.arraycopy(neighboursMatrix[i], 0, newMatrix[i], 0, neighboursMatrix.length);
            }
            neighboursMatrix = newMatrix;
        }
    }

    public String depthFirst(T startNode) throws NoSuchElementException {
        // TODO: Przejście przez graf metodą najpierw-wgłąb od podanego wierzchołka
        return "";
    }

    public String breadthFirst(T startNode) throws NoSuchElementException {
        // TODO: Przejście przez graf metodą najpierw-wszerz od podanego wierzchołka
        return "";
    }

    public int connectedComponents() {
        // TODO: Liczba składowych spójnych grafu
        return -1;
    }
}
