package models;

import java.util.Date;
import java.util.HashMap;

/**
 * A concrete subclass of Member that holds no additional information
 *
 * Created by Niall on 24/04/2017.
 */
public class PremiumMember extends Member{

    /**
     * Constructor for PremiumMember class
     */
    public PremiumMember(String email, String name, String address, String gender, double height, double startingWeight,
                         String chosenPackage, HashMap<Date, Assessment> hashMap) {
        super(email, name, address, gender, height, startingWeight, chosenPackage, hashMap);
    }

    @Override
    public void chosenPackage(String packageChoice){

        setChosenPackage(packageChoice);
    }

    /**
     * Returns a human readable String interpretation of the member's details
     * @return A string version of the premium member object.
     */
    @Override
    public String toString() {
        return "PremiumMember{" +
                "email='" + getEmail() + '\'' +
                ", name='" + getName() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", gender='" + getGender() + '\'' +
                ", height=" + getHeight() +
                ", startingWeight=" + getWeight() +
                ", chosenPackage='" + getChosenPackage() + '\'' +
                ", trainerAssessment=" + latestAssessment() +
                '}';
    }
}
