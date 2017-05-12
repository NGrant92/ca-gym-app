package models;
/**
 *The Assessment class is a concrete class that stores weight, chest, thigh, upperArm, waist, hips,
 * comment and a Trainer that entered the member’s assessment
 *
 * Created by niall on 28/04/17.
 */
public class Assessment {

    private double weight, chest, thigh, upperArm, waist, hips;
    String comment;
    private Trainer trainer;

    /**
     * Constructor method for the Assessment class
     *
     * @param weight - Member's weight as a double
     * @param thigh - Member's thigh length as a double
     * @param upperArm - Member's upper arm length as a double
     * @param waist - Member's waist size as a double
     * @param hips - Member's hip measurements as a double
     * @param comment - A comment from the trainer on the member's progress
     * @param trainer - The trainer who made the comment
     */
    public Assessment(double weight, double chest, double thigh, double upperArm, double waist, double hips,
                      String comment, Trainer trainer){

        this.weight = weight;
        this.chest = chest;
        this.thigh = thigh;
        this.upperArm = upperArm;
        this.waist = waist;
        this.hips = hips;
        this.comment = comment;
        this.trainer = trainer;
    }

    //-------
    //GETTERS
    //-------

    /**
     * Returns the member's weight in kg
     * @return The member's weight in kg
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Returns the member's chest in cm
     * @return The member's chest in cm
     */
    public double getChest() {
        return chest;
    }

    /**
     * Returns the member's thigh legnth in cm
     * @return The member's thigh legnth in cm
     */
    public double getThigh() {
        return thigh;
    }

    /**
     * Returns the member's upper arm legnth in cm
     * @return The member's upper arm legnth in cm
     */
    public double getUpperArm() {
        return upperArm;
    }

    /**
     * Returns the member's waist measurements in cm
     * @return The member's waist measurements in cm
     */
    public double getWaist() {
        return waist;
    }

    /**
     * Returns the member's hip measurements in cm
     * @return The member's hip measurements in cm
     */
    public double getHips() {
        return hips;
    }

    /**
     * Returns Trainer's comment
     * @return The trainer's comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Returns the trainer
     * @return The trainer
     */
    public Trainer getTrainer() {
        return trainer;
    }


    //-------
    //SETTERS
    //-------

    /**
     * Updates the member's weight
     * @param weight Updates the the member's weight
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Updates the member's chest
     * @param chest Updates the the member's chest
     */
    public void setChest(double chest) {
        this.chest = chest;
    }

    /**
     * Updates the member's thigh measurements
     * @param thigh Updates the the member's thigh measurements
     */
    public void setThigh(double thigh) {
        this.thigh = thigh;
    }

    /**
     * Updates the member's upperArm measurements
     * @param upperArm Updates the the member's upperArm measurements
     */
    public void setUpperArm(double upperArm) {
        this.upperArm = upperArm;
    }

    /**
     * Updates the member's waist measurements
     * @param waist Updates the the member's waist measurements
     */
    public void setWaist(double waist) {
        this.waist = waist;
    }

    /**
     * Updates themember's hips measurements
     * @param hips Updates the the member's hips measurements
     */
    public void setHips(double hips) {
        this.hips = hips;
    }

    /**
     * Updates the trainer's comment
     * @param comment Updates the trainer's comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Updates the trainer
     * @param trainer Updates the trainer
     */
    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }


    //--------------
    //HELPER METHODS
    //--------------

    /**
     * Returns a human readable String interpretation of the assessment object
     * @return A string version of the Assessment object.
     */
    @Override
    public String toString() {
        return
                "WEIGHT........." + weight + "\n" +
                "CHEST.........." + chest + "\n" +
                "THIGH.........." + thigh + "\n" +
                "UPPER ARM......" + upperArm + "\n" +
                "WAIST.........." + waist + "\n" +
                "HIPS..........." + hips + "\n" +
                "COMMENT........" + comment + "\n" +
                "TRAINER........" + trainer.getName() + "\n";
    }
}
