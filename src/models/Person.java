package models;

/**
 * Created by Niall on 24/04/2017.
 */
public abstract class Person {

    String email, name, address;
    char gender;

    //-------
    //GETTERS
    //-------
    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public char getGender() {
        return gender;
    }

    //-------
    //SETTERS
    //-------
    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    //--------------
    //HELPER METHODS
    //--------------
    @Override
    public String toString() {
        return "Person{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", gender=" + gender +
                '}';
    }
}
