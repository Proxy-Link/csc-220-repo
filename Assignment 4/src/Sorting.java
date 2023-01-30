/*
    CSC 220 Assignment 4
    Due: Monday, October 17th
    @author Elias Magdaleno

 */
public class Sorting {


    /**
     * Sorts an array of integers following the selection sort algorithm
     *
     * @param arr array of integers to sort
     */
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        int minIndex, tempValue;
        //looks for the smallest element
        for (int i = 0; i < n - 1; i++) {
            minIndex = i; //the index of the smallest element

            //index of the smallest element is equal to j if it's smaller than i
            for (int j = i + 1; j < n; j++)
                if (arr[j] < arr[minIndex])
                    minIndex = j;
            //swaps the index of the min with the first element
            tempValue = arr[i]; //holds onto the value of arr[i]
            arr[i] = arr[minIndex]; //
            arr[minIndex] = tempValue;

        }

    }


    /**
     * Sorts an array of integers following the insertion sort algorithm
     *
     * @param arr array of integers to sort
     */
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int temp = arr[i]; //holds onto the element
            int j = i - 1; //sets j to start at index 0

            while (j >= 0 && arr[j] > temp) {
                arr[j + 1] = arr[j]; //if arr[j] is greater than temp or <= 0, the index of arr[j]
                j--;
            }
            arr[j + 1] = temp;
        }
    }


    /**
     * Debugging method to print out the current state of an array
     *
     * @param arr array of integers to print
     */
    private static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        //int[] arr = {2, 8, 10, 15, 5};
        int[] arr = {6,12,2,9,11,13,11,1};
        printArray(arr);
        //selectionSort(arr);
        insertionSort(arr);
        printArray(arr);

    }
}
