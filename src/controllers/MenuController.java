package controllers;

import static utils.ScannerInput.validNextInt;


/**
 * This class is the initial menu the user sees.
 * @author Niall Grant
 * @version 2017.05.01
 */

public class MenuController
{

    public static void main(String[] args)
    {
        new MenuController();
    }

    public MenuController(){
        runMenu();
    }

    /**
     * mainMenu() - This method displays the menu for the application,
     * reads the menu option that the user entered and returns it.
     *
     * @return     the users menu choice
     */
    private int mainMenu() {


        System.out.println("\t+ = = = = = = = = = = = = = = = = = = = +");
        System.out.println("\t||                                     ||");
        System.out.println("\t             WELCOME TO THE             ");
        System.out.println("\t||             BRO SCIENCE             ||");
        System.out.println("\t                 GYM APP                 ");
        System.out.println("\t||                                     ||");
        System.out.println("\t       ❚█═█❚    ᕙ(▀_▀‶)ᕗ    ❚█═█❚        ");
        System.out.println("\t||                                     ||");
        System.out.println("\t+ = = = = = = = = = = = = = = = = = = = +");
        System.out.println("Enter the number for the action you wish to take:");
        System.out.println("");
        System.out.println(" 1) Login/Register");
        System.out.println("");
        System.out.println(" 0) Exit");
        System.out.println("");
        int option = validNextInt("==>>");
        return option;
    }

    //This is the method that controls the loop
    private void runMenu(){
        int option = mainMenu();
        while(option != 0){
            switch(option){
                case 1:
                    new GymApi();
                    break;

                default:
                    System.out.println("Invalid option entered: " + option);
                    break;
            }

            option = mainMenu();
        }
        //the user chose option 0, so exit the program
        System.out.println("Exiting... bye");
        System.exit(0);

    }


}


