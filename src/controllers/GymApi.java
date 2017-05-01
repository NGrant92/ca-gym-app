package controllers;

import models.*;
import static utils.ScannerInput.*;
import static utils.Analytics.*;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * Created by Niall on 24/04/2017.
 */
public class GymApi {

    private ArrayList<Member> members = new ArrayList();
    private ArrayList<Trainer> trainers  = new ArrayList();

    public GymApi(){


    }

    /**
     * Adds a member class to the members array
     * @param member Adds a member class to the members array
     */
    public void addMember(Member member){

        members.add(member);
    }

    /**
     * Adds a trainer class to the trainers array
     * @param trainer Adds a member class to the trainers array
     */
    public void addTrainer(Trainer trainer){

        trainers.add(trainer);
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
        if(index > (members.size() - 1)){
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
        if(index > (trainers.size() - 1)){
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
            return "" + searchCounter + " Results found for: " + nameEntered + "\n" + foundMembers;
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

                membersToString += member.toString() + "\n";
            }
            return membersToString;
        }
        //if there are no members in the members array this string will be returned
        else{
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
            if(member.isIdealBodyWeight()) {
                idealMember = idealMember + member.toString() + "\n";
            }
        }

        if(this.members.size() > 0 && idealMember.equals("")) {
            return "There are no members in the gym with an ideal weight";
        } else if(this.members.size() == 0) {
            return "There are no members in the gym";
        } else {
            return idealMember;
        }
    }

    /**
    public String listBySpecificBMICategory(String category) {
        String listBMI = "";
        category = category.toUpperCase();
        for(Member member : members){
            if(member.determineBMICategory().contains(category)) {
                listBMI = listBMI + member.toString() + "\n";
            }
        }
        if(members.size() == 0) {
            return "There are no members in the gym";
        }
        else if(members.size() > 0 && listBMI.equals("")) {
            return "There are no members in the gym in this BMI category";
        }
        else {
            return listBMI;
        }
    }
     */

}
