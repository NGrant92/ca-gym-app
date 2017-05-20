package models;

/**
 * A concrete subclass of the Person class that holds the trainers speciality
 *
 * Created by Niall on 24/04/2017.
 */
public class Trainer extends Person{

    String speciality;

    /**
     * Constructor for Trainer class
     *
     * @param speciality a string defining the trainer's speciality
     */
    public Trainer(String email, String name, String address, String gender, String speciality) {

        super(email, name, address, gender);
        this.speciality = speciality;
    }

    /**
     * Returns the trainer's speciality
     * @return The trainer's speciality
     */
    public String getSpeciality() {
        return speciality;
    }

    /**
     * Updates the trainer's speciality
     * @param speciality Updates the trainer's speciality
     */
    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    /**
     * Returns a human readable String interpretation of the trainer's details
     * @return A string version of the trianer object.
     */
    @Override
    public String toString() {
        return "NAME..........." + getName() + "\n" +
                "EMAIL.........." + getEmail() + "\n" +
                "GENDER........." + getGender() + "\n" +
                "SPECIALITY....." + getSpeciality();
    }
}
