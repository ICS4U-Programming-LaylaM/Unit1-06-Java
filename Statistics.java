import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
* This program reads from files containing integers and
* calculates and displays their mean, median and mode.
*
* @author Layla Michel
* @version 1.0
* @since 2022-03-08
*/

class Statistics {
    /**
    * Declaring variables.
    */
    static int counter;
    static double listMean;
    static double sum;
    static double average;
    static double listMedian;
    static int middle;
    static double median;
    static int mode;
    static int maxCount;
    static int value;
    static int count;

    /**
    * Creating private constructor due to
    * public/default constructor error.
    *
    * @throws IllegalStateException if there is an issue
    */
    private Statistics() {
        throw new IllegalStateException("Utility class");
    }

    /**
    * Creating function to calculate the average of the array.
    *
    * @param arrayNums as array
    *
    * @return average as double
    */
    public static double calcMean(int[] arrayNums) {
        sum = 0;

        // Get the sum of all elements of the array
        for (counter = 0; counter < arrayNums.length; counter++) {
            sum += arrayNums[counter];
        }

        // Calculate the average
        average = sum / arrayNums.length;
        return average;
    }

    /**
    * Creating function to calculate the median of the array.
    *
    * @param arrayNums as array
    *
    * @return median as double
    */
    public static double calcMedian(int[] arrayNums) {
        // Sorts array from smallest to largest number
        Arrays.sort(arrayNums);

        // Number at the middle of the array
        middle = arrayNums.length / 2;
        median = 0;

        // Find the median
        if (arrayNums.length % 2 == 0) {
            median = (arrayNums[middle] + arrayNums[middle - 1]) / 2;
        } else {
            median = arrayNums[middle];
        }
        return median;
    }

    /**
    * Creating function to calculate the mode of the array.
    *
    * @param arrayNums as array
    *
    * @return modeList as list of integers
    */
    public static List<Integer> calcMode(int[] arrayNums) {
        // Mode is initialized to the first element of the array
        List<Integer> modeList = new ArrayList<Integer>();
        maxCount = 0;
        count = 0;
        value = 0;
        for (counter = 0; counter < arrayNums.length; counter++) {
            // Check if the array at index counter is the same as a past value
            if (arrayNums[counter] == value) {
                // Add to the count
                count++;
            } else {
                // Reset the count to 0
                count = 0;
            }
            // Check if a value appeared more times than others
            if (count > maxCount) {
                // If so, replaces the past mode by this new value
                maxCount = count;
                modeList.removeAll(modeList);
                modeList.add(value);
            // Check if value appeared the same amount of times as
            // the one that appeared the most
            } else if (count == maxCount) {
                // If so, adds the value to the mode list
                modeList.add(arrayNums[counter]);
            }
            value = arrayNums[counter];
        }
        return modeList;
    }

    /**
    * Creating main function.
    *
    * @param args nothing passed in
    * @throws IOException if no file is passed in
    */
    public static void main(String[] args)
            throws IOException {
        // List that holds strings of a file
        final List<String> listOfStrings =
            new ArrayList<String>();

        // Load data from file
        BufferedReader bf = null;
        // Try to read the file
        try {
            // Check if there are some arguments
            if (null != args[0]
                // Lenght > 4 because a.txt will be shortest filename
                && args[0].length() > 4
                // check if arguments have the correct file extension
                && args[0].endsWith(".txt")) {
                bf = new BufferedReader(new FileReader(args[0]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Read entire line as string
        String line = bf.readLine();

        // Add element of file to the list
        // Check for the end of the file
        while (line != null) {
            listOfStrings.add(line);
            line = bf.readLine();
        }

        // Create array of ints of the size of the lise
        final int[] intArray = new int[listOfStrings.size()];

        // Convert each element of the list from string to int
        for (counter = 0; counter < listOfStrings.size(); counter++) {
            intArray[counter] = Integer.parseInt(listOfStrings.get(counter));
        }

        System.out.println(listOfStrings);
        System.out.println("\n");

        listMean = calcMean(intArray);
        System.out.println("The average is " + listMean);

        listMedian = calcMedian(intArray);
        System.out.println("The median is " + listMedian);

        List<Integer> listMode = new ArrayList<Integer>();
        listMode = calcMode(intArray);
        System.out.println("The mode is " + listMode);

        // closing bufferreader object
        bf.close();
    }
}
