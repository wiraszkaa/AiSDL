package Lista11.src;

import java.util.*;

public class Graph<T> {
    private final HashMap<T, Integer> dictionary;
    private List<Node<T>>[] neighboursArray;
    private int nodesAmount;

    private int[] distances;

    public Graph(List<Edge<T>> edges) {
        dictionary = new HashMap<>();
        nodesAmount = 0;
        neighboursArray = new List[10];
        for (Edge<T> edge : edges) {
            T node1 = edge.getNode1();
            T node2 = edge.getNode2();
            addNode(node1);
            addNode(node2);
            handleFullArray();
            handleNeighboursArray(node1, node2, edge.getDistance());
            handleNeighboursArray(node2, node1, edge.getDistance());
        }
    }

    private void handleNeighboursArray(T node1, T node2, int distance) {
        int index = getIndex(node1);
        Node<T> node = new Node<>(node2, distance);
        if (neighboursArray[index] != null) {
            neighboursArray[index].add(node);
        } else {
            List<Node<T>> neighbours = new LinkedList<>();
            neighbours.add(node);
            neighboursArray[index] = neighbours;
        }
    }

    private void addNode(T node) {
        if (!dictionary.containsKey(node)) {
            dictionary.put(node, nodesAmount);
            nodesAmount++;
        }
    }

    private void handleFullArray() {
        if (nodesAmount >= neighboursArray.length) {
            int length = neighboursArray.length * 2;
            List<Node<T>>[] newArray = new List[length];
            System.arraycopy(neighboursArray, 0, newArray, 0, neighboursArray.length);
            neighboursArray = newArray;
        }
    }

    private int getIndex(T node) {
        if (dictionary.containsKey(node)) {
            return dictionary.get(node);
        }
        return -1;
    }

    public Map<T, Integer> calculateShortestPaths(T startNode) throws NoSuchElementException {
        return calculateShortestPathsHandler(startNode, null);
    }

    public Map<T, Integer> calculateShortestPathsHandler(T startNode, T endNode) {
        if (getIndex(startNode) == -1) {
            throw new NoSuchElementException();
        }
        if(endNode != null) {
            if (getIndex(endNode) == -1) {
                throw new NoSuchElementException();
            }
        }

        Map<T, Integer> result = new HashMap<>();
        result.put(startNode, 0);

        distances = new int[nodesAmount];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[getIndex(startNode)] = 0;

        TreeSet<T> nodesToCheck = new TreeSet<>(new DistanceComparator());
        T currentNode = startNode;
        while (result.size() < nodesAmount) {
            int currentNodeIndex = getIndex(currentNode);
            List<Node<T>> neighbours = neighboursArray[currentNodeIndex];
            if (neighbours != null) {
                neighbours.forEach((node) -> {
                    if (!result.containsKey(node.node)) {
                        int neighbourIndex = getIndex(node.node);
                        int distance = node.distance + distances[currentNodeIndex];
                        if (distance < distances[neighbourIndex]) {
                            if (distances[neighbourIndex] != Integer.MAX_VALUE) {
                                nodesToCheck.remove(node.node);
                            }
                            distances[neighbourIndex] = distance;
                        }
                    }
                });
                for (Node<T> node : neighbours) {
                    if (!result.containsKey(node.node)) {
                        nodesToCheck.add(node.node);
                    }
                }
            }
            currentNode = nodesToCheck.first();
            result.put(currentNode, distances[getIndex(currentNode)]);
            nodesToCheck.remove(currentNode);
            if (currentNode == endNode) {
                break;
            }
        }
        result.remove(startNode);

        return result;
    }

    public Integer calculateShortestPath(T startNode, T endNode) throws NoSuchElementException {
        return calculateShortestPathsHandler(startNode, endNode).get(endNode);
    }

    private static class Node<T> {
        T node;
        int distance;

        public Node(T node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    private class DistanceComparator implements Comparator<T> {
        @Override
        public int compare(T o1, T o2) {
            int comparedValue = distances[getIndex(o1)] - distances[getIndex(o2)];
            if (comparedValue == 0) {
                return o1.hashCode() - o2.hashCode();
            } else {
                return comparedValue;
            }
        }
    }
}
