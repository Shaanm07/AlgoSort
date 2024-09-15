/**
* U3A2_ShaanMehta
* 
* This program is a sorting routine that is capable
* of sorting a list of randomly generated numbers.
* The user is given an option as to how many numbers
* are to be sorted. The random numbers are all in the 
* range of -1000 to 1000. These numbers are then sorted 
* in ascending or descending order, depending on user
* selection. Error checking is implemented to ensure
* valid input from the user. The user is given a list 
* of sorting options like; selection sort, bubble sort, 
* insertion sort, and quick sort. 
* There are also some other features:
*
* Extra features are implemented in this program. 
* The user is given an option to sort their desired
* list of numbers using all four sorting algorithms.
* Additionally, regardless of which algorithm(s) are
* used, the program will output the efficiency of the
* algorithm. This includes the time taken, loop count, 
* comparison count, and shift count. These variables
* are displayed neatly in a concise format. Lastly, 
* there is an option to exit the program at any time!
* 
* Bugs: none known
* 
* @author  Shaan Mehta
* @version 1.0
* @date    2024-07-16
*/


import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

// Main class
public class SortingProgram {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Display a menu of options to the user
        // Do this until the user chooses to exit
        while (true) {
            System.out.println(" ");
            System.out.println("Sorting Program Menu:");
            System.out.println("1. Selection Sort");
            System.out.println("2. Bubble Sort");
            System.out.println("3. Insertion Sort");
            System.out.println("4. Quick Sort");
            System.out.println("5. Sort Using All");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            if (choice == 6) {
                System.out.println("Goodbye!");
                break;
            }

            // Get the number of elements to sort from the user
            System.out.print("Enter how many numbers are to be sorted: ");
            int n = scanner.nextInt();

            // Ask the user to decide the sorting order of the numbers
            System.out.print("Enter sorting order (ascending/descending): ");
            String order = scanner.next().toLowerCase();

            // Validate user input
            if (!order.equals("ascending") && !order.equals("descending")) {
                System.out.println("Invalid input. Please enter 'ascending' or 'descending'.");
                continue;
            }

            // Generate random numbers within a range
            int[] numbers = new int[n];
            for (int i = 0; i < n; i++) {
                numbers[i] = random.nextInt(2001) - 1000;
            }

            // For each case, sort the numbers and display the results
            switch (choice) {
                case 1:
                    sortAndDisplay(numbers, "Selection Sort", order, SortingProgram::selectionSort);
                    break;
                case 2:
                    sortAndDisplay(numbers, "Bubble Sort", order, SortingProgram::bubbleSort);
                    break;
                case 3:
                    sortAndDisplay(numbers, "Insertion Sort", order, SortingProgram::insertionSort);
                    break;
                case 4:
                    sortAndDisplay(numbers, "Quick Sort", order, SortingProgram::quickSort);
                    break;
                case 5:
                    sortAndDisplay(numbers, "Selection Sort", order, SortingProgram::selectionSort);
                    sortAndDisplay(numbers, "Bubble Sort", order, SortingProgram::bubbleSort);
                    sortAndDisplay(numbers, "Insertion Sort", order, SortingProgram::insertionSort);
                    sortAndDisplay(numbers, "Quick Sort", order, SortingProgram::quickSort);
                    break;

                // Display an error message for an invalid integer option
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }

        // Close the scanner
        scanner.close();
    }

    // Create a flexible and reusable design for sorting algorithms
    @FunctionalInterface
    interface SortAlgorithm {
        void sort(int[] arr, boolean ascending);
    }

    // Sort the array using the specified algorithm
    private static void sortAndDisplay(int[] numbers, String algorithmName, String order, SortAlgorithm algorithm) {
        int[] numbersCopy = Arrays.copyOf(numbers, numbers.length);
        boolean ascending = order.equals("ascending");

        // Print the selected options to the user
        // Start the timer
        System.out.println("Sorting using " + algorithmName + " in " + order + " order.");
        long startTime = System.currentTimeMillis();
        algorithm.sort(numbersCopy, ascending);
        long endTime = System.currentTimeMillis();

        // Display the sorted numbers and efficiency of the algorithm
        System.out.println("Sorted List: " + Arrays.toString(numbersCopy));
        System.out.println("Time taken: " + (endTime - startTime) + " ms");
        System.out.println("Loop Count: " + loopCounter);
        System.out.println("Comparison Count: " + comparisonCounter);
        System.out.println("Shift Count: " + shiftCounter);
        resetCounters();
    }

    // Initialize variables
    private static int loopCounter = 0;
    private static int comparisonCounter = 0;
    private static int shiftCounter = 0;

    // Function to reset variables
    private static void resetCounters() {
        loopCounter = 0;
        comparisonCounter = 0;
        shiftCounter = 0;
    }

    // Sort the list using selection sort
    private static void selectionSort(int[] arr, boolean ascending) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            loopCounter++;
            int minOrMaxIndex = i;
            for (int j = i + 1; j < n; j++) {
                loopCounter++;
                comparisonCounter++;
                if ((ascending && arr[j] < arr[minOrMaxIndex]) || (!ascending && arr[j] > arr[minOrMaxIndex])) {
                    minOrMaxIndex = j;
                }
            }
            int temp = arr[minOrMaxIndex];
            arr[minOrMaxIndex] = arr[i];
            arr[i] = temp;
            shiftCounter++;
        }
    }

    // Sort the list using bubble sort
    private static void bubbleSort(int[] arr, boolean ascending) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            loopCounter++;
            for (int j = 0; j < n - i - 1; j++) {
                loopCounter++;
                comparisonCounter++;
                if ((ascending && arr[j] > arr[j + 1]) || (!ascending && arr[j] < arr[j + 1])) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    shiftCounter++;
                }
            }
        }
    }

    // Sort the list using insertion sort
    private static void insertionSort(int[] arr, boolean ascending) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            loopCounter++;
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && ((ascending && arr[j] > key) || (!ascending && arr[j] < key))) {
                loopCounter++;
                comparisonCounter++;
                arr[j + 1] = arr[j];
                j = j - 1;
                shiftCounter++;
            }
            arr[j + 1] = key;
            shiftCounter++;
        }
    }

    // Sort the list using quick sort
    private static void quickSort(int[] arr, boolean ascending) {
        quickSortRecursive(arr, 0, arr.length - 1, ascending);
    }

    // Recursive function to perform quick sort
    private static void quickSortRecursive(int[] arr, int low, int high, boolean ascending) {
        if (low < high) {
            loopCounter++;
            int pi = partition(arr, low, high, ascending);
            quickSortRecursive(arr, low, pi - 1, ascending);
            quickSortRecursive(arr, pi + 1, high, ascending);
        }
    }

    // Function to partition the array for quick sort
    private static int partition(int[] arr, int low, int high, boolean ascending) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            loopCounter++;
            comparisonCounter++;

            // Case of current element being smaller than or equal to the pivot
            if ((ascending && arr[j] < pivot) || (!ascending && arr[j] > pivot)) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                shiftCounter++;
            }
        }

        // Swap the pivot element with the element at the correct position
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        shiftCounter++;
        return i + 1;
    }
}
