package models;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Niall on 24/04/2017.
 */
public abstract class Member extends Person{

    private double height, startingWeight;
    private String chosenPackage;
    private HashMap<Date, Assessment> trainerAssessment;

    public Member(String email, String name, String address, String gender, double height, double startingWeight, String chosenPackage){

        super(email, name, address, gender);

        trainerAssessment = new HashMap<>();

        if(height >= 1.0 && height <= 3.0) {
            this.height = height;
        }
        else {
            this.height = 0;
        }

        if(startingWeight >= 35 && startingWeight <= 250)
        {
            this.startingWeight = startingWeight;
        }
        else
        {
            this.startingWeight = 0;
        }

        this.chosenPackage = chosenPackage;
    }



    //-------
    //GETTERS
    //-------
    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return startingWeight;
    }

    public String getChosenPackage() {
        return chosenPackage;
    }

    //-------
    //SETTERS
    //-------
    public void setHeight(double height) {
        this.height = height;
    }

    public void setWeight(double startingWeight) {
        this.startingWeight = startingWeight;
    }

    public void setChosenPackage(String chosenPackage) {
        this.chosenPackage = chosenPackage;
    }

    //--------------
    //HELPER METHODS
    //--------------
    @Override
    public String toString() {
        return "Member{" +
                "height=" + height +
                ", weight=" + startingWeight +
                ", chosenPackage='" + chosenPackage + '\'' +
                '}';
    }
}
