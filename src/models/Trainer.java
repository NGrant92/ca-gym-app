package models;

/**
 * Created by Niall on 24/04/2017.
 */
public class Trainer extends Person{

    String speciality;

    public Trainer(String speciality) {
        this.speciality = speciality;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "speciality='" + speciality + '\'' +
                '}';
    }
}
