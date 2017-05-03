package utils;

import java.util.Scanner;

/**
 * This class will retrieve inputs and ensure there are no errors in the inputs
 *
 * Created by Niall on 24/04/2017.
 */

public class ScannerInput {

    /**
	 * Ensures the user inputs an integer when this method is called by another method
     * Also works around the scanner input buffer bug
     *
     * @param prompt a String that will be displayed when called by a method
	 */
    public static int validNextInt(String prompt) {
        Scanner input = new Scanner(System.in);
        do {
            try {
                System.out.println(prompt);
                return input.nextInt();
            } catch (Exception e) {
                input.nextLine();//swallows the buffer contents
                System.err.println("\tEnter a number please ");
            }
        } while (true);

    }

	/**
     * Ensures the user inputs a string when this method is called by another method
     * Also works around the scanner input buffer bug
     *
     * @param prompt a String that will be displayed when called by a method
	 */
    public static String validNextString(String prompt) {
        Scanner input = new Scanner(System.in);
        do {
            System.out.println(prompt);
            return input.nextLine().trim().toLowerCase();
        } while (true);
    }
}