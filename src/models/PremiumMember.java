package models;

import java.util.HashMap;

/**
 * Created by Niall on 24/04/2017.
 */
public class PremiumMember extends Member{

    public PremiumMember(String email, String name, String address, String gender, double height, double startingWeight,
                         String chosenPackage, int studentId, String collegeName) {
        super(email, name, address, gender, height, startingWeight, chosenPackage);
    }
}
