package controllers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import models.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.HashMap;

import static utils.ScannerInput.*;


/**
 * This class is the initial menu the user sees.
 * @author Niall Grant
 * @version 2017.05.01
 */

public class MenuController
{
    private GymApi gymApi = new GymApi();
    private String typeExit = "ex) Exit";
    private String returnToMenu = "Returning to Previous Menu...";

    public static void main(String[] args)
    {
        new MenuController();
    }

    private MenuController(){

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
        System.out.println(" " + typeExit);
        System.out.println("");
        String option = validNextString("> ");
        return option.toLowerCase();
    }

    //This is the method that controls the loop
    private void runMenu(){
        String option = mainMenu();
        while(!option.equalsIgnoreCase("ex")){
            switch(option){
                case "l":
                    //brings the user to the log in menu
                    //User is asked if they wish to login as a Member or a Trainer
                    personType("login");
                    break;

                case "r":
                    //register a person
                    //User is asked if they wish to register a Member or a Trainer
                    personType("register");
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
    private void login(String personType, String personTitle){

        //Clears screen of previous text from above
        insertLines();

        System.out.println(personTitle + " LOGIN SCREEN:");

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
            sleep();
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
        //Clears the console of any previous text
        insertLines();

        String profileChoices;
        //Each menu will request a specific string to be displayed to the user
        //The menu when it calls loginMenu() will specify what string will be stored in profileChoices
        switch(menuType) {
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
                        " 4) Search For Member By Name\n\n" +
                        " 5) List Members By Ideal Body Weight - NOT FIN -\n" +
                        " 6) List Members With A Specific BMI - NOT FIN -\n\n" +
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
                        " 2) Update comment on an assessment for a member\n\n" +
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
        System.out.println("\n" + profileChoices + "\n");
        return validNextInt("> ");
    }


    /**
     * The main menu for when a member logs with their email
     * @param personType This is used to let the loginMenu() method know what to display to screen
     * @param currMember The member who just logged in
     */
    private void memberMenu(String personType, Member currMember){
        int option = loginMenu(personType);
        while(option != 0){
            switch(option){
                case 1:
                    //View Profile
                    insertLines();
                    System.out.println(currMember.toString());
                    validNextString("Press Enter to return..");
                    System.out.println(returnToMenu);
                    sleep();
                    insertLines();
                    break;

                case 2:
                    //Update Profile;
                    updateMember(currMember);
                    //if a member updates their package they will have to be brought back to main menu to relog
                    //if they're not logged out then they can make duplicates of themselves
                    //if the last member in the array has the same email but not the same package, they're logged out
                    if(!currMember.getChosenPackage().equals(gymApi.getMembers().get(gymApi.getMembers().size() - 1).getChosenPackage()) &&
                            currMember.getEmail().equals(gymApi.getMembers().get(gymApi.getMembers().size() - 1).getEmail())){
                        return;
                    }
                    else{
                        break;
                    }

                case 3:
                    //Progress Menu that will let them see their current measurements
                    memberProgress(currMember);
                    break;

                default:
                    System.out.println("Invalid option entered: " + option);
                    break;
            }

            option = loginMenu(personType);
        }
    }

    /**
     * The main menu for when a trainer logs in with their email
     * @param personType This is used to let the loginMenu() method know what to display to screen
     * @param currTrainer The trainer who just logged in
     */
    private void trainerMenu(String personType, Trainer currTrainer){
        int option = loginMenu(personType);
        while(option != 0){
            switch(option){
                case 1:
                    //Adds a member to the members array
                    addMember("M", "MEMBER");
                    break;

                case 2:
                    //Lists out all the registered members
                    insertLines();
                    System.out.println(gymApi.listMembers());
                    validNextString("Press Enter to return..");
                    System.out.println(returnToMenu);
                    sleep();
                    insertLines();
                    break;

                case 3:
                    //Search members by email;
                    insertLines();
                    //asks user to enter an email to be used to searched for
                    System.out.println("SEARCH VIA EMAIL");
                    //user input is stored to be searched
                    String emailSearch = validNextString("Email: ");
                    //email is searched for and if a member object is found it is stored in the foundMem variable
                    Member foundMem = gymApi.searchMembersByEmail(emailSearch);
                    //if memSearch is not null the addAssessment() method will be run
                    if( foundMem != null){
                        System.out.println(foundMem.toString());
                        System.out.println(returnToMenu);
                        sleep();
                        insertLines();
                    }
                    //If no matching email is found then the user is told so
                    else{
                        System.out.println("Invalid Email: " + emailSearch);
                        sleep();
                        insertLines();
                    }
                    break;

                case 4:
                    //Search members by name;
                    insertLines();
                    //asks user to enter an email to be used to searched for
                    System.out.println("SEARCH VIA NAME");
                    //user input is stored to be searched
                    String nameSearch = validNextString("Name: ");
                    //email is searched for and if a member object is found it is stored in the foundMem variable
                    String foundName = gymApi.searchMembersByName(nameSearch);
                    //if memSearch is not null the addAssessment() method will be run
                    if(!foundName.equals("")){
                        System.out.println(foundName);
                        validNextString("Press Enter to return..");
                        System.out.println(returnToMenu);
                        sleep();
                        insertLines();
                    }
                    //If no matching email is found then the user is told so
                    else{
                        System.out.println("Invalid Name: " + nameSearch);
                        sleep();
                        insertLines();
                    }
                    break;


                case 5:
                    //List members with ideal body weight;
                    break;


                case 6:
                    // List members with a specific BMI category;
                    break;


                case 7:
                    //A menu where the trainer can add or update new assessments
                    trainerAssessSubMenu(currTrainer);
                    break;


                case 8:
                    //allows trainer to view reports on the members
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
        insertLines();
        if(currMember.getAssessments().size() > 0){
            int option = loginMenu("memberProgress");
            while(option != 0) {
                switch (option) {
                    case 1:
                        System.out.println(currMember.getName() + "'s Starting Weight: " + currMember.getWeight());
                        System.out.println(currMember.getName() + "'s Current Weight: " + currMember.latestAssessment().getWeight());
                        break;

                    case 2:
                        System.out.println(currMember.getName() + "'s First Chest Measurement: " + currMember.firstAssessment().getChest());
                        System.out.println(currMember.getName() + "'s Current Chest Measurement: " + currMember.latestAssessment().getChest());
                        break;

                    case 3:
                        System.out.println(currMember.getName() + "'s First Thigh Measurement: " + currMember.firstAssessment().getThigh());
                        System.out.println(currMember.getName() + "'s Current Thigh Measurement: " + currMember.latestAssessment().getThigh());
                        break;

                    case 4:
                        System.out.println(currMember.getName() + "'s First Arm Measurement: " + currMember.firstAssessment().getUpperArm());
                        System.out.println(currMember.getName() + "'s Current Arm Measurement: " + currMember.latestAssessment().getUpperArm());
                        break;

                    case 5:
                        System.out.println(currMember.getName() + "'s First Waist Measurement: " + currMember.firstAssessment().getWaist());
                        System.out.println(currMember.getName() + "'s Current Waist Measurement: " + currMember.latestAssessment().getWaist());
                        break;

                    case 6:
                        System.out.println(currMember.getName() + "'s First Hip Measurement: " + currMember.firstAssessment().getHips());
                        System.out.println(currMember.getName() + "'s Current Hip Measurement: " + currMember.latestAssessment().getHips());
                        break;

                    default:
                        System.out.println("Invalid option entered: " + option);
                        break;
                }

                validNextString("\nPress Enter to return..");
                sleep();
                insertLines();
                option = loginMenu("memberProgress");
            }
        }
        else{
                System.out.println("No assessments to review");
        }
        System.out.println(returnToMenu);
        sleep();
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
                    //Add assessment for member
                    //asks user to enter an email to be used to searched for
                    System.out.println("Enter the email of the member you wish to assess.");
                    //user input is stored to be searched
                    String addAssessmentSearch = validNextString("\nEmail: ");
                    //email is searched for and if a member object is found it is stored in the foundMem variable
                    Member foundMem = gymApi.searchMembersByEmail(addAssessmentSearch);
                    //if memSearch is not null the addAssessment() method will be run
                    if( foundMem != null){
                        addAssessment(currTrainer, foundMem);
                    }
                    //If no matching email is found then the user is told so
                    else{
                        System.out.println("Invalid Email: " + addAssessmentSearch);
                    }
                    break;

                case 2:
                    //Update comment on an assessment for member
                    //asks user to enter an email to be used to searched for
                    System.out.println("Enter the email of the member you wish to update");
                    //user input is stored to be searched
                    String updateAssessmentSearch = validNextString("\nEmail: ");
                    //email is searched for and if a member object is found it is stored in the memSearch variable
                    Member memSearch = gymApi.searchMembersByEmail(updateAssessmentSearch);
                    //if memSearch is not null the updateAssessment() method will be run
                    if(memSearch != null){
                        updateAssessment(currTrainer, memSearch);
                    }
                    //If no matching email is found then the user is told so
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
                    //Specific member progress (via name search). Note: brings the user to memberProgress()
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

    /**
     * A method to add a brand new and shiny assessment for a member object
     * @param currTrainer Trainer object who is adding the assessment
     * @param currMember Member object which will get a new assessment
     */
    private void addAssessment(Trainer currTrainer, Member currMember){

        //a double variable that will store the member's new weight
        double weight;

        //Clearing console of useless text
        insertLines();
        //prints member's profile and latest assessment to screen for the user to read if needed
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

        //new measurements are stored in these variables along with the trainer's comment
        //No validation was specified for these measurements
        double chest = validNextDouble("\nChest:\n> ");
        double thigh = validNextDouble("\nThigh:\n> ");
        double upperArm = validNextDouble("\nUpper Arm:\n> ");
        double waist = validNextDouble("\nWaist:\n> ");
        double hips = validNextDouble("\nHips:\n> ");
        String comment = validNextString("\nTrainer's Comment:\n> ");

        //new assessment is added to the member object with the above variables
        currMember.addAssessment(new Assessment(weight, chest, thigh, upperArm, waist, hips, comment, currTrainer));
        //This is just checking if the comment from the latest assessment matches the one the user input
        //If this is true then we know the assessment comment was updated successfully
        if(currMember.latestAssessment().getComment().equals(comment)){
            //prompts the user saying it was added successfully
            System.out.println("Assessment Successfully Added.\nReturning to menu...");
            sleep();
            insertLines();
        }
        else{
            //if the latest assessment comment doesn't match what the user entered it will return an error message
            System.out.println("add Assessment Error");
            sleep();
        }
    }

    /**
     * Is used to update the comments of the latest assessment that belongs to a member
     * @param currTrainer the trainer who entered the updated comment
     * @param currMember the member whose latest assessment is about to be updated
     */
    private void updateAssessment(Trainer currTrainer, Member currMember){
        insertLines();
        //Printing out the member profile and latest assessment for the trainer to see
        System.out.println("MEMBER:\n" + currMember.toString());
        System.out.println("\n\nUPDATE ASSESSMENT:");
        //The new comment is storeding in this string
        String updateComment = validNextString("Enter New Comment\n> ");

        if(updateComment.equalsIgnoreCase("ex")){
            System.out.println(returnToMenu);
            sleep();
            insertLines();
            return;
        }
        //Comment is updated
        currMember.latestAssessment().setComment(updateComment);
        //Incase a different trainer updates the comment this will let others know who updated it
        currMember.latestAssessment().setTrainer(currTrainer);
        //This is just checking if the comment from the latest assessment matches the one the user input
        //If this is true then we know the assessment comment was updated successfully
        if(currMember.latestAssessment().getComment().equals(updateComment)){
            //prompts the user saying it was added successfully
            System.out.println("Update Successful!" + returnToMenu);
            sleep();
            insertLines();
        }
        else{
            //if the latest assessment comment doesn't match what the user entered it will return an error message
            System.out.println("Update Assessment Error");
            sleep();
        }
    }

    private int updateMenu(Member currMember){

        //prints out the member's details for the user to see
        System.out.println(currMember.toString());

        System.out.println("Select the feature you wish to update:");
        System.out.println(" 1) NAME\n 2) EMAIL\n 3) GENDER\n 4) HEIGHT\n 5) SARTING WEIGHT");
        if(currMember.getChosenPackage().equals("STUDENT")){
            System.out.println(" 6) STUDENT ID\n 7) COLLEGE");
        }
        System.out.println(" 8) PACKAGE TYPE");
        System.out.println("\n 0) Exit");
        return validNextInt("\n> ");
    }
    /**
     * A method when called will ask the user if which member feature they wish to update
     * @param currMember The member to be updated
     */
    private void updateMember(Member currMember){
        //clears console of previous text
        insertLines();

        int updateOption = updateMenu(currMember);
        switch(updateOption){
            case 1:
                //updates member's name
                String name = validNextString("Enter new Name:\n> ");
                currMember.setName(name);
                //double checking name is updated
                if(currMember.getName().equals(name)){
                    System.out.println("Update Successful");
                }
                //if not it will prompt user with this message
                else{
                    System.out.println("Update Name Error: " + name);
                }
                break;

            case 2:
                String email = validNextString("Enter new Email:\n> ");
                currMember.setEmail(email);
                if(currMember.getEmail().equals(email)){
                    System.out.println("Update Successful");
                }
                //if not it will prompt user with this message
                else{
                    System.out.println("Update Email Error: " + email);
                }
                break;

            case 3:
                String gender = validNextString("Enter new Gender:\n> ");
                currMember.setGender(gender);
                if(currMember.getGender().equals(gender)){
                    System.out.println("Update Successful");
                }
                //if not it will prompt user with this message
                else{
                    System.out.println("Update Gender Error: " + gender);
                    sleep();
                }
                break;

            case 4:
                double height = validNextDouble("Enter new Height:\n> ");
                currMember.setHeight(height);
                if(currMember.getHeight() == height){
                    System.out.println("Update Successful");
                }
                //if not it will prompt user with this message
                else{
                    System.out.println("Update Height Error: " + height);
                    sleep();
                }
                break;

            case 5:
                double weight = validNextDouble("Enter new Starting Weight:\n> ");
                currMember.setWeight(weight);
                if(currMember.getWeight() == weight){
                    System.out.println("Update Successful");
                }
                //if not it will prompt user with this message
                else{
                    System.out.println("Update Weight Error: " + weight);
                    sleep();
                }
                break;

            case 6:
                //REFERENCE: http://stackoverflow.com/questions/898909/is-it-possible-to-call-subclasses-methods-on-a-superclass-object
                //Using the instanceof operator it allows me to call methods from the StudentMember object as long as the object is
                //a StudentMember type
                if(currMember instanceof StudentMember) {

                    int studentID = validNextInt("Enter new Student ID:\n> ");
                    //Changing student id
                    ((StudentMember)currMember).setStudentId(studentID);
                    //double checking to see if new student ID matches their new one
                    if(((StudentMember)currMember).getStudentId() == studentID){
                        System.out.println("Update Successful");
                        sleep();
                        insertLines();
                    }
                    //if not it will prompt user with this message
                    else{
                        System.out.println("Update StudentID Error: " + studentID);
                        System.out.println("Please ensure ID is between 100000 - 999999");
                        sleep();
                    }
                }
                else{
                    System.out.println("Invalid option entered: " + updateOption);
                    sleep();
                }
                break;

            case 7:
                //Using the instanceof operator it allows me to call methods from the StudentMember object
                // as long as the object is a StudentMember type
                if(currMember instanceof StudentMember) {

                    String college = validNextString("Enter new College:\n> ");
                    //Changing college name
                    ((StudentMember)currMember).setCollegeName(college);
                    //double checking to see if new college name matches their new one
                    if(((StudentMember)currMember).getCollegeName().equals(college)){
                        System.out.println("Update Successful");
                        sleep();
                        insertLines();
                    }
                    //prompted with a message if they don't match
                    else{
                        System.out.println("Update College Error: " + college);
                        sleep();
                    }
                }
                //if user is not a student then they will be prompted with this message
                else{
                    System.out.println("Invalid option entered: " + updateOption);
                    sleep();
                }
                break;

            case 8:
                String packageOption = validNextString("Do you wish to be a Premium(P) or Student(S) Member?\n> ");
                //switching to student package
                //true if they entered P and are not already a PremiumMember
                if(packageOption.equalsIgnoreCase("p") && !(currMember instanceof PremiumMember)){

                    //A new Premium Member object is added to Members array. Parameters from currentMember is used to fill
                    //the parameters for the new PremiumMember object
                    gymApi.addMember(new PremiumMember(currMember.getEmail(), currMember.getName(), currMember.getAddress(),
                            currMember.getGender(), currMember.getHeight(), currMember.getWeight(), "PREMIUM"));

                    //The hash map of assessments from currMember are added the the new PremiumMember obejct
                    gymApi.getMembers().get(gymApi.getMembers().size() - 1).addAllAssessments(currMember.getAssessments());

                    //currMember is deleted
                    gymApi.removeMember(currMember);

                    //user is prompted
                    System.out.println("Package successfully changed.\nReturning to Main Menu");
                    sleep();
                    insertLines();
                    return;
                }
                //switching to student package
                //true if they entered T and are not already a StudentMember
                else if(packageOption.equalsIgnoreCase("s") && !(currMember instanceof StudentMember)){

                    //user is asked for their college and student ID
                    int studentID = validNextInt("\nStudent ID: ");
                    String college = validNextString("\nCollege: ");
                    //A new StudentMember object is added to Members array. Parameters from currentMember is used to fill
                    //the parameters for the new StudentMember object along with the newly entered studentID and College name
                    gymApi.getMembers().add(new StudentMember(currMember.getEmail(), currMember.getName(), currMember.getAddress(),
                            currMember.getGender(), currMember.getHeight(), currMember.getWeight(), "STUDENT", studentID, college));

                    //The hash map of assessments from currMember are added the the new PremiumMember obejct
                    HashMap<Date, Assessment> oldAssessments = currMember.getAssessments();
                    gymApi.getMembers().get(gymApi.getMembers().size() - 1).addAllAssessments(oldAssessments);

                    //currMember is deleted
                    gymApi.getMembers().remove(currMember);

                    //user is prompted
                    System.out.println("Package successfully changed.\nReturning to Main Menu");
                    sleep();
                    insertLines();
                    return;
                }
                //if they enter something other than S or T
                else if (!packageOption.equalsIgnoreCase("s") && !packageOption.equalsIgnoreCase("p")){
                    System.out.println("Invalid option entered: " + packageOption);
                    sleep();
                    insertLines();
                    break;
                }
                //if user is already the same type they're trying to become
                else{
                    System.out.println("You are already of that member type.");
                    sleep();
                    insertLines();
                    break;
                }

            case 0:
                System.out.println(returnToMenu);
                sleep();
                insertLines();
                break;

            default:
                System.out.println("Update Member Error");
                sleep();
        }
    }

    private void personType(String menuType){
        insertLines();
        //A string variable that will be printed to screen depending on the person type
        String personTitle = "";

        //asking if they're a member or trainer
        String personType = validNextString("Yo bro! You a Member or a Trainer? (M or T)\n> ");
        //ensuring what the entered it upper case
        personType = personType.toUpperCase();
        //Empty lines to clean out the console display
        if(personType.equals("M")){
            personTitle = "MEMBER";
        }
        else if(personType.equals("T")){
            personTitle = "TRAINER";
        }

        //if they have no entered 'm' or 't' then the user will be brought back to the menu
        if(!personType.equals("M") && !personType.equals("T")){
            //Telling the user they messed up
            System.out.println("Invalid option entered: " + personType +
                    "\nReturning to Menu.");
            //give the user time before returning to menu
            sleep();
            //clearing the text from the console display
            insertLines();
        }
        else if(menuType.equals("login")){
            login(personType, personTitle);
        }
        else if(menuType.equals("register")){
            addMember(personType, personTitle);
        }
        else{
            System.out.println("Person Typ Error");
        }
    }

    /**
     * A method that when called will ask the user to input various details
     * which will be used to create a new Member object
     *
     */
    private void addMember(String personType, String personTitle) {
        insertLines();


        System.out.println(personTitle + " REGISTER: ");
        System.out.println("Please enter the following member details: ");
        System.out.println(typeExit);

        String memberEmail, memberName, memberAddress, gender, chosenPackage, memberCollege;
        double height, startingWeight;
        int memberStudentID;

        //the while loop ensures that the user inputs the correct information
        while (true) {
            //the user enters the desired email
            memberEmail = validNextString("Email:\n> ");
            //if user types in exit then they will be returned to the previous menu
            if (memberEmail.equalsIgnoreCase("ex")) {
                System.out.println(returnToMenu);
                sleep();
                insertLines();
                return;
            }
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
            memberName = validNextString("Name:\n> ");

            //if the user entered ex then they will be returned to the previous menu
            if (memberName.equalsIgnoreCase("ex")) {
                System.out.println(returnToMenu);
                sleep();
                insertLines();
                return;
            }
            //if onlyLetters = true then it will prompt the user with "invalid option entered"
            //and return them to the start of the while loop
            else if (onlyLetters(memberName)) {
                System.out.println("\nInvalid option entered: " + memberName);
                System.out.println("Please use only letters.\n");
            }
            //Should the entered name passes the above tests then the user can move forward onto the member's name
            else {
                break;
            }
        }

        //addresses vary and can contain a mix of letters and numbers so this one is left open and without validation
        memberAddress = validNextString("Address:\n> ");
        //incase the user decides to exit at this stage, they have the option to do so
        if(memberAddress.equalsIgnoreCase("ex")){
            return;
        }

        while (true) {

            //currently this gym only accepts two gender identities
            gender = validNextString("Gender(M/F):\n> ");
            gender = gender.toUpperCase();

            //if the user entered ex then they will be returned to the previous menu
            if (memberName.equalsIgnoreCase("ex")) {
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

        if(personType.equals("M")){
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
                    System.out.println("Please ensure height is between 1-3m.\n> ");
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

                chosenPackage = validNextString("Choose a Package: Premium(P) or Student(S):\n> ");
                chosenPackage = chosenPackage.toUpperCase();

                //if the user entered ex then they will be returned to the previous menu
                if(chosenPackage.equalsIgnoreCase("ex")){
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

            if(chosenPackage.equals("STUDENT")){

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

                    memberCollege = validNextString("College:\n> ");

                    //if the user entered ex then they will be returned to the previous menu
                    if (memberCollege.equalsIgnoreCase("ex")) {
                        return;
                    }
                    //if onlyLetters = true then it will prompt the user with "invalid option entered"
                    //and return them to the start of the while loop
                    else if (onlyLetters(memberCollege)) {
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

            }
            else if (chosenPackage.equals("PREMIUM")){
                //a premium member object is added to the members array
                gymApi.addMember(new PremiumMember(memberEmail, memberName, memberAddress, gender, height,
                        startingWeight, chosenPackage));

            }
            //this gets the name from the most recently entered member and says that it was added successfully
            //this is done so that if the wrong name appears this will help indicate an error
            if(gymApi.getMembers().get(gymApi.numberOfMembers() - 1).getName().equals(memberName)){
                System.out.println(memberName +" is successfully added");
                //give the user reading time before returning to menu
                sleep();
                //clears the console of the above text
                insertLines();

            }
            else{
                System.out.println("Add Member Error.");
                //give the user reading time before returning to menu
                sleep();
            }
        }
        else if(personType.equals("T")){

            String speciality;
            while(true){
                speciality = validNextString("Trainer Speciality:\n> ");
                //if the user entered ex then they will be returned to the previous menu
                if (speciality.equalsIgnoreCase("ex")) {
                    return;
                }
                //if onlyLetters = true then it will prompt the user with "invalid option entered"
                //and return them to the start of the while loop
                else if (onlyLetters(speciality)) {
                    System.out.println("\nInvalid option entered: " + speciality);
                    System.out.println("Please use only letters.\n");
                }
                //Should the entered college name passes the above tests then the user can move forward
                else {
                    break;
                }
            }
            gymApi.addTrainer((new Trainer(memberEmail, memberName, memberAddress, gender, speciality)));
            //this gets the name from the most recently entered member and says that it was added successfully
            //this is done so that if the wrong name appears this will help indicate an error
            if(gymApi.getMembers().get(gymApi.numberOfMembers() - 1).getName().equals(memberName)){
                System.out.println(memberName +" is successfully added");
                //give the user reading time before returning to menu
                sleep();
                //clears the console of the above text
                insertLines();

            }
            else{
                System.out.println("Add Trainer Error.");
                //give the user reading time before returning to menu
                sleep();
            }

        }

    }

    /**
     * A boolean used by the AddMember() method to ensure a string contains only letters and spaces
     * @param checkThis the string to be checked
     * @return A boolean value that will confirm or deny the existance of non alphabetical characters
     */
    private boolean onlyLetters(String checkThis){
        //a boolean that will have it's flag raised should anything other than letters is detected
        //in the user entered variable
        boolean numsDetected = false;

        //a for loop that will go through each index of the entered string and check if it's a letter or a space
        //if it detects a character is neither a letter or a space then it will set numsDetected to true and break
        //from the loop
        for (int i = 0; i < checkThis.length(); i++) {
            if (!Character.isLetter(checkThis.charAt(i)) && checkThis.charAt(i) != ' ') {
                numsDetected = true;
                break;
            }
        }
        return numsDetected;
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
     */
    private void sleep(){
        try {
            Thread.sleep(3500);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}