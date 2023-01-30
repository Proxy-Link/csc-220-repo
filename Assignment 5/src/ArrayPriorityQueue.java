/*
 CSC 220 Assignment 5
 Due: Monday, October 31st
 @author: Elias Magdaleno

 */
public class ArrayPriorityQueue<T extends Comparable<? super T>> implements PriorityQueueInterface<T> {

    private Comparable[] queue;
    private int numberOfEntries;
    private int frontIndex;
    private int backIndex;

    private static final int DEFAULT_CAPACITY = 50;
    private static final int MAX_CAPACITY = 10000;

    public ArrayPriorityQueue() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayPriorityQueue(int initialCapacity) {
        checkCapacity(initialCapacity);

        queue = new Comparable[initialCapacity];
        frontIndex = 0;
        backIndex = initialCapacity;
        numberOfEntries = 0;
    }

    private void doubleCapacity() {
        Comparable[] oldQueue = queue;
        int oldSize = oldQueue.length;
        int newSize = 2 * oldSize;
        checkCapacity(newSize);
        Comparable[] tempQueue = new Comparable[newSize];
        queue = tempQueue;
        for (int index = 0; index < oldSize; index++) {
            queue[index] = oldQueue[frontIndex];
            frontIndex = (frontIndex + 1) % oldSize;
        }
        frontIndex = 0;
        backIndex = oldSize - 1;
    }

    private void checkCapacity(int capacity) {
        if (capacity > MAX_CAPACITY)
            throw new IllegalStateException("Attempt to create a queue whose capacity exceeds the maximum size, " + MAX_CAPACITY);
    }

    public void add(T newEntry) {

        if(getSize() == queue.length){
            doubleCapacity();;
        }

        backIndex = (backIndex + 1) % queue.length;
        if (isEmpty()){//if it's empty add the entry at the front of the queue
            queue[frontIndex] = newEntry;
        } else{
            queue[backIndex - 1] = newEntry;
        }

        numberOfEntries++;

        for (int i = frontIndex; i < backIndex; i++) {
            for (int j = i + 1; j < backIndex; j++) {
                if (queue[i].compareTo(queue[j]) > 0) { //comparable will return a positive integer if entry at index i is greater than entry at index j
                    Comparable temp = queue[i]; //stores the entry at index i
                    queue[i] = queue[j];
                    queue[j] = temp;


                }
            }

        }

    }


    public T remove() {
        backIndex = (backIndex + 1) % queue.length;
        if (isEmpty()) {
            return null;
        }
        T result = (T) queue[backIndex-1];
        queue[backIndex-1] = null;
        numberOfEntries--;
        return result; //returns the entry at the back index since it will be the highest priority
    }

    public T peek() {
        backIndex = (backIndex + 1) % queue.length;
        while (!isEmpty()) {

            T result = (T) queue[backIndex];
            return result;

        }
        return null;
    }

    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    public int getSize() {
        return numberOfEntries;
    }

    public void clear() {
        while (!isEmpty()) {
            remove();
        }
    }

    void printQueue() {
        backIndex = (backIndex + 1) % queue.length;
        int n = numberOfEntries;
        for (int i = 0; i < n; i++) {
            System.out.print(queue[i] + " ");
        }
        System.out.println();

    }


}

