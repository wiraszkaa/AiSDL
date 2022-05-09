package Lista8.src;


import java.util.List;

public class BinarySearchTree<T extends Comparable<T>> {
    Node<T> root;

    public void add(T value) throws DuplicateElementException {
        if (root == null) {
            root = new Node<>(null, null, null, value);
        } else {
            Node<T> node = recFindNode(value, root);
            if (node.key.compareTo(value) == 0) {
                throw new DuplicateElementException();
            }
            Node<T> newNode = new Node<>(node, null, null, value);
            if (node.key.compareTo(value) > 0) {
                node.left = newNode;
            } else {
                node.right = newNode;
            }
        }
    }

    private Node<T> recFindNode(T value, Node<T> currNode) {
        if(currNode != null) {
            if (currNode.key.compareTo(value) > 0) {
                if (currNode.left != null) {
                    return recFindNode(value, currNode.left);
                }
            }
            if (currNode.key.compareTo(value) < 0) {
                if (currNode.right != null) {
                    return recFindNode(value, currNode.right);
                }
            }
        }
        return currNode;
    }

    public boolean contains(T value) {
        Node<T> node = recFindNode(value, root);
        if(node != null) {
            return node.key.compareTo(value) == 0;
        }
        return false;
    }

    public void delete(T value) {
        Node<T> startingNode = recFindNode(value, root);
        if(startingNode == null) {
            return;
        }

        Node<T> node = startingNode;
        if (node.left != null && node.right != null) {
            node = recFindMin(node.right);
        }

        Node<T> childNode = null;
        if (node.left != null) {
            childNode = node.left;
        } else if (node.right != null) {
            childNode = node.right;
        }

        if(childNode != null) {
            childNode.parent = node.parent;
        }

        if(node.parent == null) {
            root = childNode;
        } else if (node.parent.left == node) {
            node.parent.left = childNode;
        } else {
            node.parent.right = childNode;
        }

        if(startingNode != node) {
            T temp = startingNode.key;
            startingNode.key = node.key;
            node.key = temp;
        }
    }

    private Node<T> recFindMin(Node<T> currNode) {
        if (currNode.left != null) {
            return recFindMin(currNode.left);
        }
        return currNode;
    }

    public String toStringPreOrder() {
        StringBuilder sb = new StringBuilder();
        preOrderWalk(root, sb);
        if(sb.length() > 2) {
            sb.setLength(sb.length() - 2);
            return sb.toString();
        }
        return "";
    }

    private void preOrderWalk(Node<T> currNode, StringBuilder sb) {
        if (currNode != null) {
            sb.append(currNode.key).append(", ");
            preOrderWalk(currNode.left, sb);
            preOrderWalk(currNode.right, sb);
        }
    }

    public String toStringInOrder() {
        StringBuilder sb = new StringBuilder();
        inOrderWalk(root, sb);
        if(sb.length() > 2) {
            sb.setLength(sb.length() - 2);
            return sb.toString();
        }
        return "";
    }

    private void inOrderWalk(Node<T> currNode, StringBuilder sb) {
        if (currNode != null) {
            inOrderWalk(currNode.left, sb);
            sb.append(currNode.key).append(", ");
            inOrderWalk(currNode.right, sb);
        }
    }

    public void getInOrder(Node<T> currNode, List<T> list) {
        if(currNode != null) {
            getInOrder(currNode.left, list);
            list.add(currNode.key);
            getInOrder(currNode.right, list);
        }
    }

    public String toStringPostOrder() {
        StringBuilder sb = new StringBuilder();
        postOrderWalk(root, sb);
        if(sb.length() > 2) {
            sb.setLength(sb.length() - 2);
            return sb.toString();
        }
        return "";
    }

    private void postOrderWalk(Node<T> currNode, StringBuilder sb) {
        if (currNode != null) {
            postOrderWalk(currNode.left, sb);
            postOrderWalk(currNode.right, sb);
            sb.append(currNode.key).append(", ");
        }
    }

    private class Node<T> {
        Node<T> parent;
        Node<T> left;
        Node<T> right;
        T key;

        public Node(Node<T> parent, Node<T> left, Node<T> right, T key) {
            this.parent = parent;
            this.left = left;
            this.right = right;
            this.key = key;
        }
    }
}
