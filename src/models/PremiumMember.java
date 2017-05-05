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
                         String chosenPackage) {
        super(email, name, address, gender, height, startingWeight, chosenPackage);
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
        return  "NAME..........." + getName() + "\n" +
                "EMAIL.........." + getEmail() + "\n" +
                "GENDER........." + getGender() + "\n" +
                "HEIGHT........." + getHeight() + "\n" +
                "START WEIGHT..." + getWeight() + "\n\n" +
                "LATEST ASSESSMENT:" + "\n" + latestAssessment();
    }
}
