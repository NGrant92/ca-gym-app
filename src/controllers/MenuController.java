package controllers;

import models.*;
import static utils.ScannerInput.*;


/**
 * This class is the initial menu the user sees.
 * @author Niall Grant
 * @version 2017.05.01
 */

public class MenuController
{
    private GymApi gymApi = new GymApi();

    public static void main(String[] args)
    {
        new MenuController();
    }

    public MenuController(){
        runMenu();
    }

    /**
     * This method displays the menu for the application,
     * reads the menu option that the user entered and returns it.
     *
     * @return The users menu choice
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
        System.out.println(" 1) Login");
        System.out.println(" 2) Register");
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
                    login();
                    break;

                case 2:
                    ;
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

    /**
     * If the user has selected to login they will be asked if they're a member or trainer. This
     * will be to know which array(members or trainers) to search when asked for an email to log in.
     * It will also let the method know which menu to display.
     */
    private void login(){

        //A string variable that will be printed to screen depending on the person type
        String personLogin;

        boolean emailSearch = false;

        //Empty lines to clean out the console display
        insertLines();
        //asking if they're a member or trainer
        String personType = validNextString("Yo bro! You a Member or a Trainer? (M or T)");
        //ensuring what the entered it upper case
        personType = personType.toUpperCase();
        /**
        //if they have no entered 'm' or 't' then the user will be brought back to the menu
        if(!personType.equals("M") || !personType.equals("T")){
            //Telling the user they messed up
            System.out.println("Invalid option entered: " + personType +
                                "\nReturning to Menu.");
            //give the user time before returning to menu
            sleep(3500);
            //clearing the text from the console display
            insertLines();
            //returns to main menu
            return;
        }
        //Clears screen of previous text from above
        insertLines();
         */

        System.out.println("LOGIN SCREEN:");

        //asking for users email
        String personEmail = validNextString("Enter Your Email:");

        //If user typed M just above AND it finds their email address then it will launch the member menu
        if (personType.contains("M") && gymApi.searchMembersByEmail(personEmail) != null){
            trainerMenu(personType);
        }
        //If user typed T just above AND it finds their email address then it will launch the trainer menu
        else if(personType.contains("T") && gymApi.searchTrainersByEmail(personEmail) != null){
            memberMenu(personType);
        }
        //if neither turn out to be true the user will be returned to menu
        else {
            System.out.println("Invalid Email entered: " + personEmail +
                    "\nReturning to Menu.");
            //Program is paused for 3.5 seconds to allow reading time
            sleep(3500);
            //Clears screen of previous text from above
            insertLines();
        }
    }

    private int loginMenu(String personType) {

        String profileChoices = "";

        if(personType.equals("M")){
            profileChoices = " 1) View Profile\n" +
                    " 2) Update Profile\n" +
                    " 3) Progress Sub-Menu";
        }
        else if(personType.equals("T")){
            profileChoices = " 1) Add New Member\n" +
                    " 2) List All Members\n" +
                    " 3) Search For Member By Email\n" +
                    " 4) Search For Member By Name\n" +
                    " 5) List Members By Ideal Body Weight\n" +
                    " 6) List Members With A Specific BMI\n" +
                    " 7) Assessment Sub-Menu\n" +
                    " 8) Reports Sub-Menu";
        }

        System.out.println("Enter the number for the action you wish to take:");
        System.out.println("");
        System.out.println(profileChoices);
        int option = validNextInt("");
        return option;
    }

    private void memberMenu(String personType){
        int option = loginMenu(personType);
        while(option != 0){
            switch(option){
                case 1:
                    //View Profile;
                    break;

                case 2:
                    //Update Profile;
                    break;

                case 3:
                    //Progress Submenu;
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

    private void trainerMenu(String personType){
        int option = loginMenu(personType);
        while(option != 0){
            switch(option){
                case 1:
                    //View Profile;
                    break;

                case 2:
                    //Update Profile;
                    break;

                case 3:
                    //Progress Submenu;
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

    /**
     * Prints out blank lines to stop a player reading the previous turn
     */
    private void insertLines() {
        for (int clear = 0; clear < 15; clear++) {
            System.out.println("\n");
        }
    }

    /**
     * A method that will tell the gym app to wait before executing more code
     * @param i The amount of time the gym app must wait(in milliseconds)
     */
    private void sleep(int i){
        try {
            Thread.sleep(i);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

}


