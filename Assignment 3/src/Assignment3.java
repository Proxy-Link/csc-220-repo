/**
 * CSC 220 Assignment 3
 * Due: Monday, October 3rd at 6:00 PM
 *
 * @author Elias Magdaleno
 */

public class Assignment3 {


    /**
     * Uses a stack to determine whether the input string is a palindrome.
     *
     * @param myString the string to examine
     * @return true if myString is a palindrome, or false if not
     */
    public static boolean isPalindromeStack(String myString) {
        StackInterface<Character> palindromeStack = new LinkedStack<>();

        int middleChar = myString.length() / 2;
        String front = myString.substring(0, middleChar);
        String back = myString.substring(middleChar);
        String frontReverse = "";

        if (myString.length() == 0 || myString.length() == 1)//returns true if the string is empty or only contains 1 character
            return true;

        if (myString.length() % 2 != 0) { //if there is an odd amount of characters in the string then it will ignore the middle character
            back = myString.substring(middleChar + 1);
        }

        for (int i = 0; i < front.length(); i++) { //pushes the front half the string to the stack
            palindromeStack.push(front.charAt(i));
        }
        while (!palindromeStack.isEmpty()) { //while the stack isn't empty, the popped characters are added to frontReverse
            frontReverse += palindromeStack.pop();
        }
        return frontReverse.equals(back); //returns true if the frontReverse is equal to the back 


    }

    /**
     * Uses recursion to determine whether the input string is a palindrome.
     *
     * @param myString the string to examine
     * @return true if myString is a palindrome, or false if not
     */
    public static boolean isPalindromeRecursion(String myString) {
        int lastCharIndex = myString.length() - 1;

        if (myString.length() == 0 || myString.length() == 1)  //returns true if the string is empty or only has 1 character
            return true;
        if (myString.charAt(0) != myString.charAt(lastCharIndex)) { //returns false if the first & last characters in the string aren't equal
            return false;
        }
        return isPalindromeRecursion(myString.substring(1, lastCharIndex)); //creates a substring by removing the first and last character of the string


    }

    /**
     * Finds the smallest integer of an array of integers between the specified
     * start and last index (inclusive), using recursion.
     *
     * @param arr   the non-empty array of integers to search.
     * @param start the starting index of the range we want to search in the array
     * @param last  the ending index of the range we want to search in the array
     * @return the smallest integer within the specified start/last indices
     */
    public static int findSmallest(int[] arr, int start, int last) {
        int smallest, firstHalf, secondHalf;

        if (start == last) { //if the start and last are equal then there must be only one element in the array
            return arr[start];
        } else {
            int middle = (start + last) / 2;
            firstHalf = findSmallest(arr, start, middle); //Looks for the min in the first half of the array
            secondHalf = findSmallest(arr, middle + 1, last); //looks for the min in the second half of the array
        }
        if (firstHalf < secondHalf)
            smallest = firstHalf;
        else
            smallest = secondHalf;
        return smallest;

    }

    public static void main(String[] args) {
        // Below are some basic test cases for these problems.
        // Please feel free to un-comment these as you go to test your code,
        // and to add more thorough test coverage (not required but highly recommended).

        System.out.println("isPalindromeStack(\"racecar\"): " + isPalindromeStack("racecar")); // should print "true"
        System.out.println("isPalindromeStack(\"racecars\"): " + isPalindromeStack("racecars")); // should print "false"
        System.out.println("isPalindromeStack(\"r\"): " + isPalindromeStack("r")); // should print "true"
        System.out.println("isPalindromeStack(\"\"): " + isPalindromeStack("")); // should print "true"

        System.out.println("isPalindromeRecursion(\"racecar\"): " + isPalindromeRecursion("racecar")); // should print "true"
        System.out.println("isPalindromeRecursion(\"racecars\"): " + isPalindromeRecursion("racecars")); // should print "false"
        System.out.println("isPalindromeRecursion(\"r\"): " + isPalindromeRecursion("r")); // should print "true"
        System.out.println("isPalindromeRecursion(\"\"): " + isPalindromeRecursion("")); // should print "true"

        int[] arr1 = {1, 2, -3};
        System.out.println("findSmallest for arr1: " + findSmallest(arr1, 0, arr1.length - 1)); // should print -3
        int[] arr2 = {3, 1, 4, 1, 5};
        System.out.println("findSmallest for arr2: " + findSmallest(arr2, 0, arr2.length - 1)); // should print 1
        int[] arr3 = {2};
        System.out.println("findSmallest for arr3: " + findSmallest(arr3, 0, arr3.length - 1)); // should print 2
        int[] arr4 = {10, 2, 4, 3, 2, 5, 4, 6};
        //Arrays.sort(arr4); //tested to see if sorting the array affects the output
        //System.out.println("findSmallest for arr4: " + findSmallest(arr4, 0, arr4.length - 1));

    }
}
