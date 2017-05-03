package controllers;

import models.*;

import java.util.HashMap;
import java.util.Date;

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
        int option = validNextInt("->");
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


        //Empty lines to clean out the console display
        insertLines();
        //asking if they're a member or trainer
        String personType = validNextString("Yo bro! You a Member or a Trainer? (M or T)");
        //ensuring what the entered it upper case
        personType = personType.toUpperCase();

        //if they have no entered 'm' or 't' then the user will be brought back to the menu
        //matches.matches("[0-9]+")
        if(!personType.equals("M") && !personType.equals("T")){
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

        System.out.println("LOGIN SCREEN:");

        //asking for users email
        String personEmail = validNextString("Enter Your Email:");

        //If user typed M just above AND it finds their email address then it will launch the member menu
        if (personType.contains("M") && gymApi.searchMembersByEmail(personEmail) != null){
            memberMenu(personType, gymApi.searchMembersByEmail(personEmail));
        }
        //If user typed T just above AND it finds their email address then it will launch the trainer menu
        else if(personType.contains("T") && gymApi.searchTrainersByEmail(personEmail) != null){
            trainerMenu(personType, gymApi.searchTrainersByEmail(personEmail));
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

    /**
     * The text portion of the
     * @param personType
     * @return
     */
    private int loginMenu(String personType) {

        String profileChoices = "";

        switch(personType) {
            case "M":
                profileChoices = " 1) View Profile\n" +
                        " 2) Update Profile\n" +
                        " 3) Progress Sub-Menu\n\n" +
                        " 0) Return to Main Menu";
                break;

            case "T":
                profileChoices = " 1) Add New Member\n" +
                        " 2) List All Members\n" +
                        " 3) Search For Member By Email\n" +
                        " 4) Search For Member By Name\n" +
                        " 5) List Members By Ideal Body Weight\n" +
                        " 6) List Members With A Specific BMI\n" +
                        " 7) Assessment Sub-Menu\n" +
                        " 8) Reports Sub-Menu\n\n" +
                        " 0) Return to Main Menu";
                break;

            case "memberProgress":
                profileChoices = " 1) View progress by weight\n" +
                        " 2) View progress by chest measurement\n" +
                        " 3) View progress by thigh measurement\n" +
                        " 4) View progress by upper arm measurement\n" +
                        " 5) View progress by waist measurement\n" +
                        " 6) View progress by hips measurement";
                break;

            case "trainerAssessmentMenu":
                profileChoices = " 1) Add an assessment for a member\n" +
                        " 2) Update comment on an assessment for a member\n";
                break;

            case "trainerReportsMenu":
                profileChoices = " 1) Specific member progress (via email search)\n" +
                        " 2) Specific member progress (via name search)\n" +
                        " 3) Overall members’ report\n";
                break;

            default:
                profileChoices = "loginMenu Switch Error";
        }


        System.out.println("Enter the number for the action you wish to take:");
        System.out.println("");
        System.out.println(profileChoices);
        System.out.println("");
        System.out.println(" 0) Previous Menu");
        int option = validNextInt("");
        return option;
    }


    private void memberMenu(String personType, Member currMember){
        int option = loginMenu(personType);
        while(option != 0){
            switch(option){
                case 1:
                    System.out.println(currMember.toString());
                    break;

                case 2:
                    //Update Profile;
                    break;

                case 3:
                    memberProgress(currMember);
                    break;

                default:
                    System.out.println("Invalid option entered: " + option);
                    break;
            }

            option = loginMenu(personType);
        }
        //the user chose option 0, so exit the program
        //System.out.println("Exiting... bye");
        //System.exit(0);
    }

    private void trainerMenu(String personType, Trainer currTrainer){
        int option = loginMenu(personType);
        while(option != 0){
            switch(option){
                case 1:
                    addMember();
                    break;

                case 2:
                    //List all Members;
                    break;

                case 3:
                    //Search members by email;
                    break;

                case 4:
                    //Search members by name;
                    break;


                case 5:
                    //List members with ideal body weight;
                    break;


                case 6:
                    // List members with a specific BMI category;
                    break;


                case 7:
                    trainerAssessSubMenu(currTrainer);
                    break;


                case 8:
                    trainerReportsSubMenu();
                    break;

                default:
                    System.out.println("Invalid option entered: " + option);
                    break;
            }

            option = loginMenu(personType);
        }
        //the user chose option 0, so exit the program
        //System.out.println("Exiting... bye");
        //System.exit(0);
    }

    private void memberProgress(Member currMember){
        int option = loginMenu("memberProgress");
        while(option != 0){
            switch(option){
                case 1:
                    System.out.println(currMember.latestAssessment().getWeight());
                    break;

                case 2:
                    System.out.println(currMember.latestAssessment().getChest());
                    break;

                case 3:
                    System.out.println(currMember.latestAssessment().getThigh());
                    break;

                case 4:
                    System.out.println(currMember.latestAssessment().getUpperArm());
                    break;

                case 5:
                    System.out.println(currMember.latestAssessment().getWaist());
                    break;

                case 6:
                    System.out.println(currMember.latestAssessment().getHips());
                    break;

                default:
                    System.out.println("Invalid option entered: " + option);
                    break;
            }

            option = loginMenu("MS");
        }
        //the user chose option 0, so exit the program
        //System.out.println("Exiting... bye");
        //System.exit(0);

    }

    private void trainerAssessSubMenu(Trainer currTrainer){

        int option = loginMenu("trainerAssessmentMenu");
        while(option != 0){
            switch(option){
                case 1:
                    //Add assessment for member;
                    break;

                case 2:
                    //Update comment and assessment for member;
                    break;

                default:
                    System.out.println("Invalid option entered: " + option);
                    break;
            }

            option = loginMenu("trainerAssessmentMenu");
        }
    }

    private void trainerReportsSubMenu(){
        int option = loginMenu("trainerReportsMenu");
        while(option != 0){
            switch(option){
                case 1:
                    //Specific member progress (via email search). Note: brings the user to memberProgress()
                    break;

                case 2:
                    //. Specific member progress (via name search). Note: brings the user to memberProgress()
                    break;

                case 3:
                    //Overall members’ report;
                    break;

                default:
                    System.out.println("Invalid option entered: " + option);
                    break;
            }

            option = loginMenu("trainerReportsMenu");
        }
    }

    private void addMember(){
        System.out.println("Please enter the following member details: ");

        String memberEmail = validNextString("Email:\n");
        String memberName = validNextString("Name:\n");
        String memberAddress  = validNextString("Address:\n");
        String gender = validNextString("Address:\n");
        double height = validNextDouble("Height(between 1 and 3 metres):\n");
        double startingWeight = validNextDouble("Starting Weight(between 35kg and 250kg):\n");
        String chosenPackage = "PREMIUM";
        HashMap<Date, Assessment> hashMap = null;


        gymApi.addMember(new PremiumMember(memberEmail, memberName, memberAddress, gender, height, startingWeight, chosenPackage, hashMap));

    }
    /**
     * Prints out blank lines to stop a player reading the previous turn
     */
    private void insertLines() {
        for (int clear = 0; clear < 25; clear++) {
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


