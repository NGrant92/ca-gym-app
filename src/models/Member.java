package models;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.SortedSet;

/**
 * An abstract class that is a child of the Person class
 * while also the parent class of the PermiumMember and StudentMember classes
 *
 * Created by Niall on 24/04/2017.
 */
public abstract class Member extends Person{

    private double height, startingWeight;
    private String chosenPackage;
    private HashMap<Date, Assessment> trainerAssessment = new HashMap<>();


    /**
     * Constructor for Member class
     * 
     * @param height - The member's height is measured in Meters. A minimum height of one metre(inclusive)
     * is allowed and a maximum height of three metres(inclusive).
     *
     * @param startingWeight - The member's weight upon joining the gym(in KGs). A minimum weight of
     * 35kg(inclusive)and a max of 250kg(inclusive) is permitted in the gym.
     *
     * @param chosenPackage - Currently only 2 payment packages exist, Premium and Student. If not specified
     *                      default to "Unspqecified"
     */
    public Member(String email, String name, String address, String gender, double height, double startingWeight, String chosenPackage){

        super(email, name, address, gender);


        if(height >= 1.0 && height <= 3.0) {
            this.height = height;
        }
        else {
            this.height = 0;
        }

        if(startingWeight >= 35 && startingWeight <= 250) {
            this.startingWeight = startingWeight;
        }
        else {
            this.startingWeight = 0;
        }

        if(chosenPackage.toUpperCase().equals("PREMIUM") || chosenPackage.toUpperCase().equals("STUDENT")){
            this.chosenPackage = chosenPackage;
        }
        else {
            this.chosenPackage = "Unspecified";
        }
    }

    //-------
    //GETTERS
    //-------

    /**
     * Returns the hash map of assessments
     * @return the hash map of assessments
     */
    public HashMap<Date, Assessment> getAssessments(){

        return trainerAssessment;
    }

    /**
     * Returns the member's height in meters
     * @return The member's height in meters
     */
    public double getHeight() {
        return height;
    }

    /**
     * Returns the member's weight in kg
     * @return The member's weight in kg
     */
    public double getWeight() {
        return startingWeight;
    }

    /**
     * Returns the member's Chosen Package
     * @return The member's Chosen Package
     */
    public String getChosenPackage() {
        return chosenPackage;
    }

    //-------
    //SETTERS
    //-------

    /**
     * Updates the member's height
     * @param height Updates the member's height
     */
    public void setHeight(double height) {
        if(height >= 1.0 && height <= 3.0) {
            this.height = height;
        }
    }

    /**
     * Updates the member's starting weight
     * @param startingWeight Updates the member's starting weight
     */
    public void setWeight(double startingWeight) {
        if(startingWeight >= 35 && startingWeight <= 250) {
            this.startingWeight = startingWeight;
        }
    }

    /**
     * Updates the member's starting Chosen Package
     * @param chosenPackage Updates the member's Chosen Package
     */
    public void setChosenPackage(String chosenPackage) {
        if(chosenPackage.toUpperCase().equals("PREMIUM") || chosenPackage.toUpperCase().equals("STUDENT")){
            this.chosenPackage = chosenPackage;
        }
    }

    //--------------
    //HELPER METHODS
    //--------------



    /**
     * Adds a new assessment to the the member's Assessment HashMap
     * @param newAssessment The assessment object to be added to the member's assessment HashMap
     */
    public void addAssessment(Assessment newAssessment){

        trainerAssessment.put(new Date(), newAssessment);
    }

    /**
     * Returns the latest assessment based on last entry (by calendar date).
     * @return Returns the latest assessment based on last entry (by calendar date).
     */
    public Assessment latestAssessment(){

        return trainerAssessment.get(sortedAssessmentDates().last());

    }

    /**
     * Returns the first assessment based on the first entry (by calendar date).
     * @return Returns the first assessment based on the first entry (by calendar date).
     */
    public Assessment firstAssessment(){

        return trainerAssessment.get(sortedAssessmentDates().first());

    }

    /**
     * Returns the assessments dates sorted in date order.
     * @return Returns the assessments dates sorted in date order.
     */
    public SortedSet<Date> sortedAssessmentDates(){

        return new TreeSet<>(trainerAssessment.keySet());
    }

    /**
     * Adds a new hashmap of assessments to the the member's Assessment HashMap
     */
    public void addAllAssessments(HashMap<Date, Assessment> allAssessments){

        trainerAssessment.putAll(allAssessments);
    }

    public abstract void chosenPackage(String packageChoice);

    /**
     * Returns a human readable String interpretation of the member's details
     * @return A string version of the member object.

    */
    @Override
    public String toString() {
        return  "NAME..........." + getName() + "\n" +
                "EMAIL.........." + getEmail() + "\n" +
                "GENDER........." + getGender() + "\n" +
                "HEIGHT........." + getHeight() + "\n" +
                "START WEIGHT..." + getWeight() + "\n\n";
    }
}
