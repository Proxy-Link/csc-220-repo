/*
 CSC 220 Assignment 5
 Due: Monday, October 31st
 @author: Elias Magdaleno

 */
public class LinkedPriorityQueue<T extends Comparable<? super T>> implements PriorityQueueInterface<T>{

    private Node firstNode;
    private Node lastNode;
    private int numberOfEntries;

    public LinkedPriorityQueue(){firstNode = null; lastNode = null;};
    public void add(T newEntry) {
        Node newNode = new Node(newEntry, null);

        if (isEmpty()){
            firstNode = newNode;
        } else {
            if (newEntry.compareTo(firstNode.getData()) > 0){
               Node tempNode = firstNode;
               lastNode.setNextNode(newNode);
               firstNode.setData((T) newNode);

            }

        }



    }

    public T remove() {
        T front = peek();
        firstNode.setData(null);
        firstNode = firstNode.getNextNode();
        if (firstNode == null){
            lastNode = null;
        }
        return front;
    }

    public T peek() {
        if(!isEmpty()){
            return firstNode.getData();
        }
        return null;
    }

    public boolean isEmpty() {
        return (firstNode == null) && (lastNode == null);
    }

    public int getSize() {
        return 0;
    }

    public void clear() {
        firstNode = null;
        lastNode = null;

    }
    private class Node {
        private T data;
        private Node next;

        private Node(T dataPortion) {
            data = dataPortion;
            int priority;
            next = null;
        } // end constructor

        private Node(T dataPortion, Node nextNode) {
            data = dataPortion;
            next = nextNode;
        } // end constructor

        private T getData() {
            return data;
        } // end getData

        private void setData(T newData) {
            data = newData;
        } // end setData

        private Node getNextNode() {
            return next;
        } // end getNextNode

        private void setNextNode(Node nextNode) {
            next = nextNode;
        } // end setNextNode
    } // end Node
}
