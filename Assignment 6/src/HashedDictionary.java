/*
    CSC 220 Assignment 6
    Due: Monday, November 28th at 6:00 PM
    @author Elias Magdaleno
 */
public class HashedDictionary<K, V> implements DictionaryInterface<K, V> {

    private int numOfEntries;
    private int chainCount;
    private static final int DEFAULT_CAPACITY = 5;
    private static final int MAX_CAPACITY = 10000;

    private TableEntry<K, V>[] hashTable;
    private int tableSize;
    private static final int MAX_SIZE = 2 * MAX_CAPACITY;
    private boolean initialized = false;
    private static final double MAX_LOAD_FACTOR = 1.0;


    public HashedDictionary() {
        this(DEFAULT_CAPACITY);
    }

    public HashedDictionary(int initialCapacity) {

        checkCapacity(initialCapacity);
        numOfEntries = 0;
        chainCount = 0;
        int tableSize = getNextPrime(initialCapacity);
        @SuppressWarnings("unchecked")
        TableEntry<K, V>[] temp = (TableEntry<K, V>[]) new TableEntry[tableSize];
        hashTable = temp;
        initialized = true;

    }

    public V add(K key, V value) {

        if (key == null || value == null)
            throw new IllegalArgumentException("Arguments cannot be empty");
        else {
            int index = getHashIndex(key);
            V oldValue = null;
            assert (index >= 0 && index < hashTable.length);
            if (hashTable[index] == null) { //its null add a new entry at that index
                hashTable[index] = new TableEntry<>(key, value);
                numOfEntries++;
            } else {
                TableEntry<K, V> temp = hashTable[index];//store the entry at that index
                if (hashTable[index].getKey() != key) {
                    TableEntry<K, V> prev = temp;
                    temp = temp.getNext();
                    while (temp != null && temp.getKey() != key) { //move to next node
                        prev = temp;
                        temp = temp.getNext();
                    }
                    if (temp == null) { //if that node is empty
                        prev.setNext(new TableEntry<>(key, value));
                        numOfEntries++;
                        chainCount++;
                    } else {//store and replace the value
                        oldValue = hashTable[index].getValue();
                        temp.setValue(value);

                    }
                } else { //save old value to return it
                    oldValue = hashTable[index].getValue();
                    hashTable[index].setValue(value); //replace the old value
                }
            }
            if ((chainCount > 0) && ((numOfEntries / chainCount) > MAX_LOAD_FACTOR)) {//increase the size of the array;
                enlargeHashTable();
            }
            return oldValue;

        }


    }

    public V remove(K key) {
        V removedValue = null;
        int index = getHashIndex(key);
        TableEntry<K, V> prev = null;


        if (hashTable[index] != null) {
            if (hashTable[index].getKey() == key) {
                removedValue = hashTable[index].getValue(); //save the value that will be removed
                hashTable[index] = hashTable[index].getNext();

            } else {
                boolean removed = false;
                TableEntry<K, V> temp = hashTable[index].getNext();
                prev = hashTable[index];

                while (temp != null) {
                    if (temp.getKey() == key) {
                        removedValue = temp.getValue();
                        //removed = true;//flag entry as removed
                        chainCount--;
                        prev.setNext(temp.getNext()); //
                        break;
                    }
                    prev = temp; //iterate through the rest of the nodes
                    temp = temp.getNext();

                }

            }
            numOfEntries--;

        }


        return removedValue;
    }


    public V getValue(K key) {
        V result = null;
        int index = getHashIndex(key);
        TableEntry<K, V> temp = hashTable[index];
        while (temp != null) {
            if (temp.getKey() == key) {
                result = temp.getValue();
                break;
            }
            temp = temp.getNext(); //if the keys are not equal move to the next one
        }

        return result;

    }

    public boolean contains(K key) {
        return getValue(key) != null;
    }

    public boolean isEmpty() {
        return numOfEntries == 0;
    }

    public int getSize() {
        return numOfEntries;
    }

    public void clear() {
        for (int i = 0; i < hashTable.length; i++) {
            hashTable[i] = null;
            numOfEntries = 0;
        }

    }

    private void checkCapacity(int capacity) {
        if (capacity > MAX_CAPACITY)
            throw new IllegalStateException("The maximum capacity has been reached");
    }

    private int getHashIndex(K key) {
        int hashIndex = key.hashCode() % hashTable.length;
        if (hashIndex < 0)
            hashIndex = hashIndex + hashTable.length;
        return hashIndex;
    }

    private boolean isPrime(int num) {
        boolean prime = false;
        int i = 2;
        while (!prime && (i <= num / 2)) {
            prime = num % i == 0;
            i++;
        }
        return !prime;
    }

    private int getNextPrime(int num) {
        if (num <= 0)
            throw new RuntimeException("Number must be greater than 0");
        if (num % 2 == 0)
            num++;
        while (!isPrime(num))
            num += 2;
        return num;
    }


    //Adjust how large the new table size is
    private void enlargeHashTable() {
        TableEntry<K, V>[] oldTable = hashTable;
        int oldSize = hashTable.length;
        //System.out.println("Old size " + oldSize);
        int newSize = getNextPrime(oldSize * 2);
        //System.out.println("checking new size " + newSize);
        checkCapacity(newSize);
        @SuppressWarnings("unchecked")
        TableEntry<K, V>[] temp = (TableEntry<K, V>[]) new TableEntry[newSize];
        hashTable = temp;
        numOfEntries = 0;
        chainCount = 0;

        for (int i = 0; i < oldSize; i++) {
            if (oldTable[i] != null)
                add(oldTable[i].getKey(), oldTable[i].getValue());
        }
    }

    private static class TableEntry<S, T> {
        private S key;
        private T value;
        private TableEntry<S, T> next;

        private TableEntry(S searchKey, T dataValue) {
            this.key = searchKey;
            this.value = dataValue;
            this.next = null;
        }

        private TableEntry(S searchKey, T dataValue, TableEntry<S, T> nextEntry) {
            this.key = searchKey;
            this.value = dataValue;
            this.next = nextEntry;
        }

        public S getKey() {
            return key;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T entryValue) {
            this.value = entryValue;
        }

        public TableEntry<S, T> getNext() {
            return next;
        }

        public void setNext(TableEntry<S, T> nextEntry) {
            this.next = nextEntry;
        }
    }

    public static void main(String args[]) {
        HashedDictionary<String, Integer> dict = new HashedDictionary<>(10);
        dict.add("Elias", 2000);
        dict.add("Damian", 2002);
        dict.add("Caren", 2006);
        dict.add("Alyssa", 2000);

        //System.out.println(dict.isEmpty());
        dict.add("Elias", 2001);
        System.out.println(dict.getSize());
        System.out.println(dict.contains("Elias"));
        dict.remove("Alyssa");
        System.out.println(dict.getSize());

    }
}




