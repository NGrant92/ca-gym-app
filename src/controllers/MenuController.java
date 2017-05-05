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
    private String mainMenu() {

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
        System.out.println(" l) Login");
        System.out.println(" r) Register");
        System.out.println(" a) Assessment");
        System.out.println(" exit) Exit");
        System.out.println("");
        String option = validNextString("> ");
        return option.toLowerCase();
    }

    //This is the method that controls the loop
    private void runMenu(){
        String option = mainMenu();
        while(option != "exit"){
            switch(option){
                case "l":
                    login();
                    break;

                case "r":
                    addMember();
                    break;

                case "a":
                    addAssessment(gymApi.getTrainers().get(0), gymApi.getMembers().get(0));
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
        String personLogin = "";


        //Empty lines to clean out the console display
        insertLines();
        //asking if they're a member or trainer
        String personType = validNextString("Yo bro! You a Member or a Trainer? (M or T)\n> ");
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
        else if(personType.equals("M")){
            personLogin = "MEMBER";
        }
        else if(personType.equals("T")){
            personLogin = "TRAINER";
        }
        //Clears screen of previous text from above
        insertLines();

        System.out.println(personLogin + " LOGIN SCREEN:");

        //asking for users email
        String personEmail = validNextString("Enter Your Email:\n> ");

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
     * The text portion of the sub menues that will be printed.
     * The submenues will call the correct text to print
     * @param menuType This will tell the method what list to display
     * @return returns the option entered by the user
     */
    private int loginMenu(String menuType) {
        insertLines();

        String profileChoices;

        switch(menuType) {
            case "M":
                profileChoices = " 1) View Profile\n" +
                        " 2) Update Profile - NOT FIN -\n" +
                        " 3) Progress Sub-Menu\n\n" +
                        " 0) Return to Main Menu";
                break;

            case "T":
                profileChoices = " 1) Add New Member\n" +
                        " 2) List All Members\n" +
                        " 3) Search For Member By Email - NOT FIN -\n" +
                        " 4) Search For Member By Name - NOT FIN -\n" +
                        " 5) List Members By Ideal Body Weight - NOT FIN -\n" +
                        " 6) List Members With A Specific BMI - NOT FIN -\n" +
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
                        " 6) View progress by hips measurement\n\n" +
                        " 0) Return to Previous Menu";
                break;

            case "trainerAssessmentMenu":
                profileChoices = " 1) Add an assessment for a member\n" +
                        " 2) Update comment on an assessment for a member - NOT FIN -\n\n" +
                        " 0) Return to Previous Menu";
                break;

            case "trainerReportsMenu":
                profileChoices = " 1) Specific member progress (via email search)\n" +
                        " 2) Specific member progress (via name search) - NOT FIN -\n" +
                        " 3) Overall members’ report - NOT FIN -\n\n" +
                        " 0) Return to Previous Menu";
                break;

            default:
                profileChoices = "loginMenu Switch Error";
        }

        System.out.println("Enter the number for the action you wish to take:");
        System.out.println("");
        System.out.println(profileChoices);
        System.out.println("");
        int option = validNextInt("> ");
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
    }

    private void trainerMenu(String personType, Trainer currTrainer){
        int option = loginMenu(personType);
        while(option != 0){
            switch(option){
                case 1:
                    addMember();
                    break;

                case 2:
                    System.out.println(gymApi.listMembers());
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
    }

    /**
     * A method that will give the player/trainer the ability to call details
     * about their latest assessment
     * @param currMember The member to be reviewed
     */
    private void memberProgress(Member currMember){
        int option = loginMenu("memberProgress");
        while(option != 0){
            switch(option){
                case 1:
                    System.out.println(currMember.getName() + "'s Current Weight:");
                    System.out.println(currMember.latestAssessment().getWeight());
                    break;

                case 2:
                    System.out.println(currMember.getName() + "'s Current Chest Measurement:");
                    System.out.println(currMember.latestAssessment().getChest());
                    break;

                case 3:
                    System.out.println(currMember.getName() + "'s Current Thigh Measurement:");
                    System.out.println(currMember.latestAssessment().getThigh());
                    break;

                case 4:
                    System.out.println(currMember.getName() + "'s Current Arm Measurement:");
                    System.out.println(currMember.latestAssessment().getUpperArm());
                    break;

                case 5:
                    System.out.println(currMember.getName() + "'s Current Waist Measurement:");
                    System.out.println(currMember.latestAssessment().getWaist());
                    break;

                case 6:
                    System.out.println(currMember.getName() + "'s Current Hip Measurement:");
                    System.out.println(currMember.latestAssessment().getHips());
                    break;

                default:
                    System.out.println("Invalid option entered: " + option);
                    break;
            }

            option = loginMenu("memberProgress");
        }
        //the user chose option 0, so exit the program
        //System.out.println("Exiting... bye");
        //System.exit(0);
    }

    private Member searchMember(){
        System.out.println("Enter the email of the member you wish to assess.");
        String addAssessmentSearch = validNextString("\nEmail: ");
        return gymApi.searchMembersByEmail(addAssessmentSearch);
    }

    /**
     * A submenu that is for the trainer only. Allows them to
     * add assessments or allows the trainer to update assessments
     * @param currTrainer trainer currently logged in
     */
    private void trainerAssessSubMenu(Trainer currTrainer){

        int option = loginMenu("trainerAssessmentMenu");
        while(option != 0){
            switch(option){
                case 1:
                    //Add assessment for member;
                    Member foundMem = searchMember();
                    if( foundMem != null){
                        addAssessment(currTrainer, foundMem);
                    }
                    else{
                        System.out.println("Invalid Email: " + foundMem);
                    }
                    break;

                case 2:
                    //Update comment and assessment for member;
                    System.out.println("Enter the email of the member you wish to update");
                    String updateAssessmentSearch = validNextString("\nEmail: ");
                    Member memSearch = gymApi.searchMembersByEmail(updateAssessmentSearch);
                    if(memSearch != null){
                        String updateComment = validNextString("Enter new comment:\n> ");
                        memSearch.latestAssessment().setComment(updateComment);
                        if(memSearch.latestAssessment().getComment().equals(updateComment)){
                            System.out.println("Update Successful!");
                            sleep(2000);
                            insertLines();
                        }
                        else{
                            System.out.println("Update Comment Error");
                            sleep(3500);
                        }
                    }
                    else{
                        System.out.println("Invalid Email: " + updateAssessmentSearch);
                    }
                    break;

                default:
                    System.out.println("Invalid option entered: " + option);
                    break;
            }

            option = loginMenu("trainerAssessmentMenu");
        }
    }

    /**
     * A sub menu that is exclusive to the trainer. It allows the trainer
     * to check up on a member's progress. They can access the information
     * by searching the member's name or email or a summary of every member's performance.
     *
     */
    private void trainerReportsSubMenu(){
        int option = loginMenu("trainerReportsMenu");
        while(option != 0){
            switch(option){
                case 1:
                    //Specific member progress (via email search). Note: brings the user to memberProgress()
                    String memEmail = validNextString("Member's Email: ");
                    //Storing the searchMembersByEmail() results into a member variable
                    Member memSearch = gymApi.searchMembersByEmail(memEmail);
                    //testing to see if searchMembersByEmail returned a valid member
                    //if so then it will call the memberProgress() method
                    if(memSearch != null){
                        memberProgress(memSearch);
                    }
                    else{
                        System.out.println("Invalid Email: " + memEmail);
                    }
                    break;

                case 2:
                    /**
                    //. Specific member progress (via name search). Note: brings the user to memberProgress()
                    //Specific member progress (via email search). Note: brings the user to memberProgress()
                    String memName = validNextString("Member's Name:\n> ");
                    //Storing the searchMembersByEmail() results into a member variable
                    Member memSearch = gymApi.searchMembersByName(memName);
                    //testing to see if searchMembersByEmail returned a valid member
                    //if so then it will call the memberProgress() method
                    if(memSearch != null){
                        memberProgress(memSearch);
                    }
                    else{
                        System.out.println("Invalid Name: " + memEmail);
                    }
                    break;
                     */

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

    private void addAssessment(Trainer currTrainer, Member currMember){

        double weight;

        insertLines();
        System.out.println("MEMBER:\n" + currMember.toString());

        System.out.println("\n\nNEW ASSESSMENT:");
        //ensures what is entered for the weight category is between 35 and 250
        //should non-numerical numbers be entered it will be caught by the validNextDouble() method
        while(true) {
            weight = validNextDouble("Weight(between 35kg and 250kg):\n> ");

            //checking to see if the entered height is between 35kg and 65kg
            //if so then it will continue onto the next stage
            if(weight == 0){
                return;
            }
            else if(weight >= 35 && weight <= 250) {
                break;
            }
            //if what is entered is not between 1.0-3.0 then it will return this prompt before
            //returning to the start of the loop
            else {
                System.out.println("\nInvalid option entered: " + weight);
                System.out.println("Please ensure weight is between 35-250kg.\n");
            }
        }

        double chest = validNextDouble("Chest:\n> ");
        double thigh = validNextDouble("Thigh:\n> ");
        double upperArm = validNextDouble("Upper Arm:\n> ");
        double waist = validNextDouble("Waist:\n> ");
        double hips = validNextDouble("Hips:\n> ");
        String comment = validNextString("Trainer's Comment:\n> ");


        currMember.addAssessment(new Assessment(weight, chest, thigh, upperArm, waist, hips, comment, currTrainer));
        if(currMember.latestAssessment().equals(comment)){
            System.out.println("Assessment Successfully Added.\nReturning to menu...");
            sleep(3500);
            insertLines();
        }
        else{
            System.out.println("add Assessment Error");
            sleep(3500);
        }
    }

    private void updateAssessment(Trainer currTrainer, Member currMember){

        insertLines();
        System.out.println("MEMBER:\n" + currMember.toString());
        System.out.println("\n\nUPDATE ASSESSMENT:");


    }

    /**
     * A method that when called will ask the user to input various details
     * which will be used to create a new Member object
     *
     */
    private void addMember() {
        insertLines();
        System.out.println("Please enter the following member details: ");
        System.out.println("(Type 'EXIT' to return to previous menu)");

        String memberEmail, memberName, memberAddress, gender, chosenPackage, memberCollege;
        double height, startingWeight;
        int memberStudentID;

        //the while loop ensures that the user inputs the correct information
        while (true) {
            //the user enters the desired email
            memberEmail = validNextString("Email:\n> ");
            //if user types in exit then they will be returned to the previous menu
            if (memberEmail.equalsIgnoreCase("exit")) {
                return;
            }
            //TODO http://stackoverflow.com/questions/8204680/java-regex-email/13013056#13013056
            //emails always contain the "@" symbol so this checks to make sure it's in the email
            else if (!memberEmail.contains("@")) {
                System.out.println("Invalid option entered: " + memberEmail);
            }
            //If the entered email is found in either the members array or the trainers array then
            // it will output a message saying so before looping back and asking for an email again
            else if (gymApi.searchMembersByEmail(memberEmail) != null || gymApi.searchTrainersByEmail(memberEmail) != null) {
                System.out.println("Email already exists: " + memberEmail);
            }
            //Should the entered email pass the above tests then the user can move forward onto the member's name
            else {
                break;
            }
        }

        //a while loop to ensure the user enters a valid name
        while (true) {
            //The name that will be tested and added to a new member object if it proves valid
            System.out.println("Type 'EXIT' to return to previous menu");
            memberName = validNextString("Name:\n> ");

            //a boolean that will have it's flag raised should anything other than letters is detected
            //in the user entered variable
            boolean numsDetected = false;

            //a for loop that will go through each index of the entered string and check if it's a letter or a space
            //if it detects a character is neither a letter or a space then it will set numsDetected to true and break
            //from the loop
            for (int i = 0; i < memberName.length(); i++) {
                if (!Character.isLetter(memberName.charAt(i)) && memberName.charAt(i) != ' ') {
                    numsDetected = true;
                    break;
                }
            }

            //if the user entered "exit" then they will be returned to the previous menu
            if (memberName.equalsIgnoreCase("exit")) {
                return;
            }
            //if numsDetected = true then it will prompt the user with "invalid option entered"
            //and return them to the start of the while loop
            else if (numsDetected) {
                System.out.println("\nInvalid option entered: " + memberName);
                System.out.println("Please use only letters.\n");
            }
            //Should the entered name passes the above tests then the user can move forward onto the member's name
            else {
                break;
            }
        }

        //addresses vary and can contain a mix of letters and numbers so this one is left open and without validation
        memberAddress = validNextString("Address:\n");
        //incase the user decides to exit at this stage, they have the option to do so
        if(memberAddress.equalsIgnoreCase("exit")){
            return;
        }

        while (true) {

            //currently this gym only accepts two gender identities
            gender = validNextString("Gender(M/F):\n> ");
            gender = gender.toUpperCase();

            //if the user entered "exit" then they will be returned to the previous menu
            if (memberName.equalsIgnoreCase("exit")) {
                return;
            }
            //if the user enters "male" or "female" then will accept those appropriate answers
            //and will store only the first letter of the words
            else if ((gender.length() > 1) && (gender.equals("MALE") || gender.equals("FEMALE"))) {
                gender = gender.substring(0, 1);
                break;
            }
            //if they enter in a single character that isn't M or F then it will prompt the user pointing out the error
            //and will loop back and ask them to enter the member's gender
            else if (!gender.equals("M") && !gender.equals("F")) {
                System.out.println("\nInvalid option entered: " + gender);
                System.out.println("Please enter either M/F.\n");
            }
            //Should the entered email pass the above tests then the user can move forward onto the member's name
            else {
                break;
            }
        }

        //ensures what is entered for the height category is between 1.0 and 3.0
        //should non-numerical numbers be entered it will be caught by the validNextDouble() method
        while(true) {
            height = validNextDouble("Height(between 1 and 3 metres):\n> ");

            //checking to see if the entered height is between 1m and 3m
            //if so then it will continue onto the next stage
            if(height >= 1.0 && height <= 3.0) {
                break;
            }
            //if what is entered is not between 1.0-3.0 then it will return this prompt before
            //returning to the start of the loop
            else {
                System.out.println("\nInvalid option entered: " + height);
                System.out.println("Please ensure height is between 1-3m.\n");
            }
        }

        //ensures what is entered for the weight category is between 35 and 250
        //should non-numerical numbers be entered it will be caught by the validNextDouble() method
        while(true) {
            startingWeight = validNextDouble("Starting Weight(between 35kg and 250kg):\n");

            //checking to see if the entered height is between 1m and 3m
            //if so then it will continue onto the next stage
            if(startingWeight >= 35 && startingWeight <= 250) {
                break;
            }
            //if what is entered is not between 1.0-3.0 then it will return this prompt before
            //returning to the start of the loop
            else {
                System.out.println("\nInvalid option entered: " + startingWeight);
                System.out.println("Please ensure weight is between 35-250kg.\n> ");
            }
        }

        //ensures what is entered is either a premium or student package
        while(true){

            System.out.println("Available Packages: (Premium) or (Student)");
            chosenPackage = validNextString("Chose a package(P/S):\n> ");
            chosenPackage = chosenPackage.toUpperCase();

            //if the user entered "exit" then they will be returned to the previous menu
            if(chosenPackage.equalsIgnoreCase("exit")){
                return;
            }
            //if user enters P it will save chosenPackage as PREMIUM before breaking the loop
            else if(chosenPackage.equals("P")){
                chosenPackage = "PREMIUM";
                break;
            }
            //if user enters S it will save chosenPackage as STUDENT before breaking the loop
            else if(chosenPackage.equals("S")){
                chosenPackage = "STUDENT";
                break;
            }
            //if the user input equals PREMIUM or STUDENT then it will break the loop
            else if(chosenPackage.equals("PREMIUM") || chosenPackage.equals("STUDENT")){
                break;
            }
            //if what is entered is not PREMIUM, STUDENT, P or S then it will return this prompt before
            //returning to the start of the loop
            else {
                System.out.println("\nInvalid option entered: " + chosenPackage);
                System.out.println("Please chose between (Premium) or (Student).\n");
            }
        }

        if(chosenPackage.contains("STUDENT")){

            //while loop to ensure a valid student id is entered
            while(true){

                //stored the entered integer
                memberStudentID = validNextInt("Student ID (100000 - 999999):\n> ");

                //ensure the entered integer is between 100000 - 999999
                if(memberStudentID >= 100000 && memberStudentID <= 999999 ){
                    break;
                }
                //if it's not then it will display this prompt to screen before returning to the start of the loop
                else {
                    System.out.println("\nInvalid option entered: " + memberStudentID);
                    System.out.println("Please chose between 100000 - 999999.\n");
                }
            }

            //a while loop to ensure memberCollege contains only letters and spaces
            while(true){

                System.out.println("Type 'EXIT' to return to previous menu");
                memberCollege = validNextString("College:\n> ");

                //a boolean that will have it's flag raised should anything other than letters is detected
                //in the user entered variable
                boolean numsDetected = false;

                //a for loop that will go through each index of the entered string and check if it's a letter or a space
                //if it detects a character is neither a letter or a space then it will set numsDetected to true and break
                //from the loop
                for (int i = 0; i < memberCollege.length(); i++) {
                    if (!Character.isLetter(memberCollege.charAt(i)) && memberCollege.charAt(i) != ' ') {
                        numsDetected = true;
                        break;
                    }
                }

                //if the user entered "exit" then they will be returned to the previous menu
                if (memberCollege.equalsIgnoreCase("exit")) {
                    return;
                }
                //if numsDetected = true then it will prompt the user with "invalid option entered"
                //and return them to the start of the while loop
                else if (numsDetected) {
                    System.out.println("\nInvalid option entered: " + memberCollege);
                    System.out.println("Please use only letters.\n");
                }
                //Should the entered college name passes the above tests then the user can move forward
                else {
                    break;
                }
            }


            //a student member object is now created and entered into the members array
            gymApi.addMember(new StudentMember(memberEmail, memberName, memberAddress, gender, height,
                    startingWeight, chosenPackage, memberStudentID, memberCollege));

            //this gets the name from the most recently entered member and says that it was added successfully
            //this is done so that if the wrong name appears this will help indicate an error
            System.out.println("" + gymApi.getMembers().get(gymApi.numberOfMembers() - 1).getName() +" Student Member is successfully added");

            //give the user reading time before returning to menu
            sleep(3500);
            //clears the console of the above text
            insertLines();
        }
        else if (chosenPackage.contains("PREMIUM")){
            //a premium member object is added to the members array
            gymApi.addMember(new PremiumMember(memberEmail, memberName, memberAddress, gender, height,
                    startingWeight, chosenPackage));

            //this gets the name from the most recently entered member and says that it was added successfully
            //this is done so that if the wrong name appears this will help indicate an error
            System.out.println("" + gymApi.getMembers().get(gymApi.numberOfMembers() - 1).getName() +" Premium Member is successfully added");

            //give the user reading time before returning to menu
            sleep(3500);
            //clears the console of the above text
            insertLines();
        }
        else{
            System.out.println("Add Member Error.");

            //give the user reading time before returning to menu
            sleep(3500);
            //clears the console of the above text
            insertLines();
        }
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


