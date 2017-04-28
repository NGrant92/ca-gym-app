package models;

/**
 * Created by Niall on 24/04/2017.
 */
public abstract class Member extends Person{

    private double height, weight;
    private String chosenPackage;

    public Member(String email, String name, String address, String gender, double height, double weight, String chosenPackage){
        super(email, name, address, gender);
        this.height = height;
        this.weight = weight;
        this.chosenPackage = chosenPackage;
    }



    //-------
    //GETTERS
    //-------
    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
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

    public void setWeight(double weight) {
        this.weight = weight;
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
                ", weight=" + weight +
                ", chosenPackage='" + chosenPackage + '\'' +
                '}';
    }
}
