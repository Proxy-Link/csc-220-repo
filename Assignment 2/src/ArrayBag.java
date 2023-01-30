/** Contains the definitions of the methods implemented by the bag interface to remove, add, and return the contents of the bag.
    File: ArrayBag.java
    @author Elias Magdaleno
    Date: September 19, 2022

 */
public class ArrayBag<T> implements BagInterface<T>
{
  private final T[] bag;
  private static final int DEFAULT_CAPACITY = 25;
  private int numberOfEntries;

  /** Creates an empty bag whose initial capacity is 25. */
  public ArrayBag()
  {
    this(DEFAULT_CAPACITY);
  } // end default constructor

  /** Creates an empty bag having a given initial capacity.
   @param capacity the integer capacity desired */

  public ArrayBag(int capacity)
  {
    numberOfEntries = 0;
    T[] tempBag = (T[]) new Object[capacity];
    bag = tempBag;
  } // end constructor
/*
  public ArrayBag(T[] o){



  }
*/
  /** Gets the current number of entries in this bag.
   @return the integer number of entries currently in the bag */
  public int getCurrentSize()
  {
    return numberOfEntries;
  }

  public int getArraySize() {return bag.length;}

  /** Sees whether this bag is full.
   @return true if the bag is full, or false if not */
  private boolean isArrayFull()
  {
    return numberOfEntries >= bag.length;
  }

  /** Sees whether this bag is empty.
   @return true if the bag is empty, or false if not */
  public boolean isEmpty()
  {
    return numberOfEntries == 0;
  }

  /** Adds a new entry to this bag.
   @param newEntry the object to be added as a new entry
   @return true if the addition is successful, or false if not */
  public boolean add(T newEntry)
  {
    boolean result = true;
    if (isArrayFull())
    {
      result = false;
    }
    else
    {
      // add newEntry to end of array
      bag[numberOfEntries] = newEntry;
      numberOfEntries++;
    } // end if
    return result;
  }

  /** Removes one unspecified entry from this bag, if possible.
   @return either the removed entry, if the removal
   was successful, or null */
  public T remove()
  {
    T result = null;
    if (numberOfEntries > 0) {
      result = bag[numberOfEntries - 1];
      bag[numberOfEntries - 1] = null;
      numberOfEntries--;
    } //end if
    return result;
  }

  /** Removes and returns the entry at a given index within the array bag.
   If no such entry exists, returns null.
   Preconditions: 0 <= givenIndex < numberOfEntries; */
  private T removeEntry(int givenIndex) {
    T result = null;
    if (!isEmpty() && (givenIndex >= 0)) {
      result = bag[givenIndex];
      bag[givenIndex] = bag[numberOfEntries - 1];
      bag[numberOfEntries - 1] = null;
      numberOfEntries--;
    } // end if
    return result;
  }

  /** Locates a given entry within the array bag.
   @return the index of the entry, if located, or -1 otherwise. */
  private int getIndexOf(T anEntry)
  {
    int where = -1;
    boolean found = false;
    int index = 0;
    while (!found && (index < numberOfEntries)) {
      if (anEntry.equals(bag[index])) {
        found = true;
        where = index;
      } // end if
      index++;
    } // end while
    return where;
  }

  /** Removes one occurrence of a given entry from this bag,
   if possible.
   @param anEntry the entry to be removed
   @return true if the removal was successful, or false if not */
  public boolean remove(T anEntry)
  {
    int index = getIndexOf(anEntry);
    T result = removeEntry(index);
    return anEntry.equals(result);
  }

  /** Removes every occurrence of a given entry from this bag,
   if possible. If specified entry is not present in the bag, do nothing.
   @param anEntry the entry to be removed */
  public void removeEvery(T anEntry) {
    for (int i = numberOfEntries; i >= 0; i--) {
      if (anEntry.equals(bag[i])) {

        removeEntry(i);
      } // end if
    }


  }

  /** Removes all entries from this bag. */
  public void clear()
  {
    while (!isEmpty())
    {
      remove();
    }
  }

  /** Counts the number of times a given entry appears in this bag.
   @param anEntry the entry to be counted
   @return the number of times anEntry appears in the bag */
  public int getFrequencyOf(T anEntry)
  {
    int counter = 0;
    for (int index = 0; index < numberOfEntries; index++) {
      if (anEntry.equals(bag[index])) {
        counter++;
      } // end if
    } // end for
    return counter;
  }

  /** Tests whether this bag contains a given entry.
   @param anEntry the entry to locate
   @return true if the bag contains anEntry, or false otherwise */
  public boolean contains(T anEntry)
  {
    return getFrequencyOf(anEntry) > 0;
  }

  /** Tests whether this bag equals the passed in object
   (that they contain the same items)
   @param o an Object to compare to, which may or may not be a bag.
   @return true if the two bags contain the same items, or false otherwise */
  @Override
  public boolean equals(Object o) {
   if( o instanceof ArrayBag<?>) {
     ArrayBag otherBag = (ArrayBag<T>) o;


     if (this.numberOfEntries != otherBag.numberOfEntries) {
       return false;
     } //end if
     for (int i = 0; i < otherBag.numberOfEntries; i++) {
       if (this.getFrequencyOf(bag[i]) != otherBag.getFrequencyOf(otherBag.bag[i])) {
         return false;
       } // end if
     } //end for
     return true;
   }
   else { return false; }

  }

  /** Creates an array of all entries that are in this bag.
   @return a newly allocated array of all the entries in the bag */
  public T[] toArray()
  {
    T[] result = (T[])new Object[numberOfEntries];
    for (int index = 0; index < numberOfEntries; index++)
    {
      result[index] = bag[index];
    } // end for
    return result;
  }

} // end ArrayBag
