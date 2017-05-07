package controllers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import models.*;
import utils.Analytics;
import utils.SaveManager;
import utils.ScannerInput;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;


/**
 * Created by Niall on 24/04/2017.
 */
public class GymApi {

    public ArrayList<Member> members;
    public ArrayList<Trainer> trainers;
    public SaveManager saveManager = new SaveManager();

    public GymApi(){

        members = new ArrayList<>();
        trainers = new ArrayList<>();
        try {
            load();
        }
        catch (Exception e) {
            System.out.print(e.toString());
        }

        //members.add(new PremiumMember("test1@tmail.com", "Niall", "Waterford", "M", 1.75, 66, "PREMIUM"));
        //trainers.add(new Trainer("test2@tmail.com", "NDog", "Waterford", "M", "Skipping leg day"));

        //addAssessment(0);

    }

    public void addAssessment(int i){
        Assessment test = new Assessment(66, 55, 34, 15, 24, 10, "Keep up the good work", trainers.get(i));
        members.get(i).addAssessment(test);
        Assessment test2 = new Assessment(77, 55, 34, 15, 24, 10, "Keep up the good work", trainers.get(i));
        members.get(i).addAssessment(test2);
    }

    /**
     * Adds a member class to the members array
     * @param member Adds a member class to the members array
     */
    public void addMember(Member member){

        members.add(member);
        try {
            save();
        }
        catch (Exception e) {
            System.out.print(e.toString());
        }
    }

    /**
     * Removes a member class to the members array
     * @param member Adds a member class to the members array
     */
    public void removeMember(Member member){
        members.remove(member);
        try {
            save();
        }
        catch (Exception e) {
            System.out.print(e.toString());
        }
    }

    /**
     * Adds a trainer class to the trainers array
     * @param trainer Adds a member class to the trainers array
     */
    public void addTrainer(Trainer trainer){

        trainers.add(trainer);
        try {
            save();
        }
        catch (Exception e) {
            System.out.print(e.toString());
        }
    }

    /**
     * Returns the size of the members array list
     * @return Returns the size of the members array list
     */
    public int numberOfMembers(){

        return members.size();
    }

    /**
     * Returns the size of the trainers array list
     * @return Returns the size of the trainers array list
     */
    public int numberOfTrainers(){

        return trainers.size();
    }

    /**
     * Provides information of all the members in the members array
     * @return Information of all the members in the members array
     */
    public ArrayList<Member> getMembers(){
        return members;
    }

    /**
     * Provides information of all the trainers in the trainers array
     * @return Information of all the trainers in the trainers array
     */
    public ArrayList<Trainer> getTrainers() {
        return trainers;
    }

    /**
     * Returns a boolean indicating if the index passed as a parameter is a valid index for the
     * members array list.
     * @param index the index to be tested
     * @return A boolean to determine if the index is valid
     */
    public boolean isValidMemberIndex(int index){
        if(index < (members.size())){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Returns a boolean indicating if the index passed as a parameter is a valid index for the
     * trainers array list.
     * @param index the index to be tested
     * @return A boolean to determine if the index is valid
     */
    public boolean isValidTrainerIndex(int index){
        if(index < (trainers.size())){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * A method that when called will loop through the members array and try to find an email
     * that matches one the user has inputted
     *
     * @param emailEntered The email inputted by the user
     * @return A member object that has a matching email or a simple null
     */
    public Member searchMembersByEmail(String emailEntered){

        //Will be contain member details if one is found
        Member foundMember = null;

        for(Member member : members){

            //If the two strings match then the toString() method is called from
            //the member with the matching email and stored in foundMember
            if(emailEntered.equals(member.getEmail())){
                foundMember = member;
                break;
            }
        }

        return foundMember;
    }

    /**
     * A method that when called will loop through the members array list in search of members
     * whose name partially or entirely matches the entered name and return is as a String.
     *
     * @param nameEntered The email entered by the user
     * @return Returns a list of members whose name partially or entirely matches the entered name
     */
    public String searchMembersByName(String nameEntered){

        //Will be contain member details if one is found
        String foundMembers = "";
        int searchCounter = 0;

        for(Member member : members){

            //If the two strings match then the toString() method is called from
            //the member with the matching name and stored in foundMember
            //both strings being tested are set to lower case so the search is not case sensitive
            if(member.getName().toLowerCase().contains(nameEntered.toLowerCase())){
                foundMembers += "\n" + member.getName();
                searchCounter++;
            }
        }

        //If there are any members found to have a matching name then the results are returned
        if(searchCounter > 0){
            return "" + searchCounter + " Results found for: " + nameEntered + "\n" + foundMembers + "\n";
        }

        //if there are zero members in the members array list then it will return this string
        else if (members.size() == 0 && searchCounter == 0){
            return "No members in this gym bro! Drop those weights, pick up those leaflets and get out there dude!";
        }

        //If there are memebers in the members array but no matches found then it will return this string
        else {
            return "" + searchCounter + " Results found for: " + nameEntered;
        }
    }

    /**
     * A method that when called will loop through the trainers array and try to find an email
     * that matches one the user has inputted
     * @param emailEntered The email inputted by the user
     * @return A trainer class that has a matching email or a simple null
     */
    public Trainer searchTrainersByEmail(String emailEntered){

        //Will be contain member details if one is found
        Trainer foundTrainer = null;

        for(Trainer trainer : trainers){

            //If the two strings match then the toString() method is called from
            //the trainer with the matching email and stored in foundTrainer
            if(emailEntered.equals(trainer.getEmail())){
                foundTrainer = trainer;
                break;
            }
        }

        return foundTrainer;
    }

    /**
     * When called it will return a string containing all the members stored in the members array
     * @return A string containing all the members stored in the members array
     *         or a string saying there are no members currently registered
     */
    public String listMembers(){

        //string to be later returned
        String membersToString = "";

        //if there are member objects within the members array it will loop through the array and
        //adding all the information from each member object into a string that will be returned
        if(members.size() > 0){
            for(Member member : members){

                membersToString += member.toString() + "\n\n+----------------------+\n";
            }
            return membersToString;
        }
        //if there are no members in the members array this string will be returned
        else{
            return "No members in this gym bro! Drop those weights, pick up those leaflets and get out there dude!";
        }
    }

    /**
     * List all the members' weight and height both imperically and metrically.
     *
     * @return Lists all member's weight and height listed both imperically and metrically
     *
     *          The format of the output is like so:
     *              -Joe Soap:  xx kg (xxx lbs)     x.x metres  (xx inches).
     *              -Joan Soap: xx kg (xxx lbs)     x.x metres  (xx inches).
     *
     *          If there are no members in the gym, then it will return a string stating so
     */
    public String listMemberDetailsImperialAndMetric() {

        //the string to be later returned
        String memberDetails = "";
        //testing to make sure there are members in the members array
        if(members.size() > 0) {
            //for each loop to loop through the members array
            for (Member member : members) {
                //populating memberDetails with the member's Name, weight(kg & lbs) and height(meter & inches)
                memberDetails += member.getName() + ":\t"
                        + member.getWeight() + " kg ("
                        + Analytics.convertWeightKGtoPounds(member.getWeight()) + " lbs) \t"
                        + member.getHeight() + " metres ("
                        + Analytics.convertHeightMetresToInches(member.getHeight()) + " inches).\n";
            }
            return memberDetails;
        }
        else {
            return "No members in this gym bro! Drop those weights, pick up those leaflets and get out there dude!";
        }
    }

    /**
     * Returns a string containing all members who are of an ideal body weight, based on the
     * Devine Method. If there are no members a string will be returned stating so. If no members
     * are of ideal weight it will return a string stating so.
     *
     * @return A string containing all the members details in the gym whose latest assessment
     * weight is an ideal weight
     */
    public String listMembersWithIdealWeight() {
        String idealMember = "";
        Iterator var2 = this.members.iterator();

        while(var2.hasNext()) {
            Member member = (Member)var2.next();
            if(Analytics.isIdealBodyWeight(member, member.latestAssessment())) {
                idealMember = idealMember + member.toString() + "\n";
            }
        }

        if(this.members.size() > 0 && idealMember.equals("")) {
            return "There are no members in the gym with an ideal weight";
        }
        else if(this.members.size() == 0) {
            return "There are no members in the gym";
        }
        else {
            return idealMember;
        }
    }


    /**
     * List all the members of a specific BMI category
     *
     * @param category - The category you wish to search members by.
     *                  Specific Categories:
     *                  "VERY SERVERELY UNDERWEIGHT"
     *                  "SEVERELY UNDERWEIGHT"
     *                  "UNDERWEIGHT"
     *                  "NORMAL"
     *                  "OVERWEIGHT"
     *                  "MODERATELY OBESE"
     *                  "SEVERELY OBESE"
     *                  "VERY SEVERELY OBESE"
     *
     *            - This method also allows you to search by key words e.g: "OBESE" will return members in:
     *                  "MODERATELY OBESE"
     *                  "SEVERELY OBESE"
     *                  "VERY SEVERELY OBESE"
     *              Note: In in this situation, the members are not sorted by category, they are
     *              just displayed as is
     *
     * @return List of members whose BMI falls into the category passed as a parameter.
     *
     *          -If there are no members in the BMI category, the message "There are no members in the
     *          gym in this BMI category" should be returned.
     *
     *          -If there are no members in the gym, the message "There are no members in the gym"
     *          should be returned.
     */
    public String listBySpecificBMICategory(String category) {
        //A string to be populated and returned if members are found that match the entered category
        String listBMI = "";
        //ensures what the user enters is converted to upper case
        category = category.toUpperCase();
        //loops through the members array to search for people who match the entered category
        for (Member member : members) {
            if (Analytics.determineBMICategory(Analytics.calculateBMI(member, member.latestAssessment())).contains(category)) {
                listBMI += member.toString() + "\n";
            }
        }
        //this message is returned if there are no members in the members array
        if (members.size() == 0) {
            return "No members in this gym bro! Drop those weights, pick up those leaflets and get out there dude!";
        }
        //if no matches are found in a populated members array then an appropriate message is returned
        else if (members.size() > 0 && listBMI.equals("")) {

            String response;

            if(category.contains("UNDERWEIGHT")){
                response = "No toothpicks in this gym bro!";
            }
            else if (category.contains("OBESE")){
                response = "In this gym obesity is extinct! Good work dude!";
            }
            else if (category.contains("NORMAL")){
                response = "No body has normal BMI? Are you running a gym or a spa bro? Get back to work!";
            }
            else{
                response = "No one by that category!";
            }
            return response;
        }
        //if matches are found the the results are returned
        else {
            return listBMI;
        }
    }

    /**
     * Saves the current game state to xml by utilising the SaveManager class
     * @throws Exception message
     */
    private void save() throws Exception{
        saveManager.setState(trainers, members);

        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("gym.xml"));
        out.writeObject(saveManager);
        out.close();
    }

    /**
     * Loads the game from a SaveManager object
     * @throws Exception message
     */
    @SuppressWarnings ("unchecked")
    private void load() throws Exception{
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("gym.xml"));
        saveManager = (SaveManager) is.readObject();
        is.close();
        this.trainers = saveManager.getTrainers();
        this.members = saveManager.getMembers();

    }
}
