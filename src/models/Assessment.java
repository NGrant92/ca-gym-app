package models;

/**
 * Created by niall on 28/04/17.
 */
public class Assessment {

    private double weight, height, thigh, upperArm, waist, hips;
    String comment;
    private Trainer trainer;

    public Assessment(double weight, double height, double thigh, double upperArm, double waist, double hips, String comment, Trainer trainer){

        this.weight = weight;
        this.height = height;
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
    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public double getThigh() {
        return thigh;
    }

    public double getUpperArm() {
        return upperArm;
    }

    public double getWaist() {
        return waist;
    }

    public double getHips() {
        return hips;
    }

    public String getComment() {
        return comment;
    }

    public Trainer getTrainer() {
        return trainer;
    }


    //-------
    //SETTERS
    //-------
    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setThigh(double thigh) {
        this.thigh = thigh;
    }

    public void setUpperArm(double upperArm) {
        this.upperArm = upperArm;
    }

    public void setWaist(double waist) {
        this.waist = waist;
    }

    public void setHips(double hips) {
        this.hips = hips;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    //--------------
    //HELPER METHODS
    //--------------


    @Override
    public String toString() {
        return "Assessment{" +
                "weight=" + weight +
                ", height=" + height +
                ", thigh=" + thigh +
                ", upperArm=" + upperArm +
                ", waist=" + waist +
                ", hips=" + hips +
                ", comment='" + comment + '\'' +
                ", trainer=" + trainer +
                '}';
    }
}
