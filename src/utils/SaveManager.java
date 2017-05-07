package utils;

import models.Member;
import models.Trainer;

import java.util.ArrayList;

/**
 * Created by niall on 07/05/17.
 */
public class SaveManager {
    private ArrayList<Trainer> trainers;
    private ArrayList<Member> members;
    private int currentTurn;

    /**
     * Sets the trainers array and players array into the SaveManager object
     *
     * @param trainers the array list of trainers from GymApi
     * @param members  the array list of members from GymApi
     */
    public void setState(ArrayList<Trainer> trainers, ArrayList<Member> members) {
        this.trainers = new ArrayList<>();
        this.members = new ArrayList<>();
        this.trainers.addAll(trainers);
        this.members.addAll(members);
    }

    /**
     * Returns the list of trainers that were saved to the xml
     * @return The trainers ArrayList saved to the xml
     */
    public ArrayList<Trainer> getTrainers() {
        return trainers;
    }

    /**
     * Returns the list of trainers that were saved to the xml
     * @return The trainers ArrayList saved to the xml
     */
    public ArrayList<Member> getMembers() {
        return members;
    }

}
