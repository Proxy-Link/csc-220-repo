import java.util.Arrays;


public class ArrayStack<T> implements StackInterface<T>
{
  private T[] stack; // array of stack entries
  private int topIndex; // index of top entry
  private static final int DEFAULT_INITIAL_CAPACITY = 50;

  public ArrayStack()
  {
    this(DEFAULT_INITIAL_CAPACITY);
  } // end default constructor

  public ArrayStack(int initialCapacity)
  {
    T[] tempStack = (T[])new Object[initialCapacity];
    stack = tempStack;
    topIndex = -1;
  } // end constructor

  public void push(T newEntry)
  {
    topIndex++;
    stack[topIndex] = newEntry;
  } // end push

  public T peek()
  {
    if(!isEmpty()){
      return stack[topIndex];
    }
    else {
      return null;
    }

  } // end peek

  public T pop()
  {
   T top = null;
   if(!isEmpty()){
     top = stack[topIndex];
     stack[topIndex] = null;
     topIndex--;
   }
   return top;
  } // end pop

  public boolean isEmpty()
  {
   return topIndex < 0;
  } // end isEmpty

  public void clear()
  {
    for(int i = 0; i < topIndex; i++){
      stack[i] = null;
    }
    topIndex = -1;
  } // end clear

  private void doubleArray()
  {
    int newLength = 2 * stack.length;
    stack = Arrays.copyOf(stack, newLength);
  }
} // end ArrayStack
