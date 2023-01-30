/**
 An interface that describes the operations of a bag of objects.
 @author Frank M. Carrano
 Modifications by Amy Liu
 */
public interface BagInterface<T>
{
  /** Gets the current number of entries in this bag.
   @return the integer number of entries currently in the bag */
  public int getCurrentSize();

  public int getArraySize();

  /** Sees whether this bag is empty.
   @return true if the bag is empty, or false if not */
  public boolean isEmpty();

  /** Adds a new entry to this bag.
   @param newEntry the object to be added as a new entry
   @return true if the addition is successful, or false if not */
  public boolean add(T newEntry);

  /** Removes one unspecified entry from this bag, if possible.
   @return either the removed entry, if the removal
   was successful, or null */
  public T remove();

  /** Removes one occurrence of a given entry from this bag,
   if possible.
   @param anEntry the entry to be removed
   @return true if the removal was successful, or false if not */
  public boolean remove(T anEntry);

  /** Removes every occurrence of a given entry from this bag,
   if possible. If specified entry is not present in the bag, do nothing.
   @param anEntry the entry to be removed */
  public void removeEvery(T anEntry);

  /** Removes all entries from this bag. */
  public void clear();

  /** Counts the number of times a given entry appears in this bag.
   @param anEntry the entry to be counted
   @return the number of times anEntry appears in the bag */
  public int getFrequencyOf(T anEntry);

  /** Tests whether this bag contains a given entry.
   @param anEntry the entry to locate
   @return true if the bag contains anEntry, or false otherwise */
  public boolean contains(T anEntry);

  /** Tests whether this bag equals the passed in object
   (that they contain the same items)
   @param o an Object to compare to, which may or may not be a bag.
   @return true if the two bags contain the same items, or false otherwise */
  public boolean equals(Object o);

  //boolean equals(ArrayBag otherBag);

  /** Creates an array of all entries that are in this bag.
   @return a newly allocated array of all the entries in the bag */
  public T[] toArray();

} // end BagInterface
