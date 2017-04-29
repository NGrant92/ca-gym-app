package models;

/**
 * An abstract super class that is the parent class of Member and Trainer
 * This will hold the accessors and mutators for the variables: email, name, address & gender
 *
 * Created by Niall on 24/04/2017.
 */
public abstract class Person {

    /**
     * @param email - emails are all lower case.
     * @param name - The member's name should be no more than 30 characters. If the entered name exceed
     *             30 characters, the characters will be truncated and only the first 30 characters will be retained.
     * @param address - There is no validation on the member's address.
     * @param gender - The member's gender i.e. can be either "M" or "F". If not specified, default
     * to "Unspecified".
     *
     */
    private String email, name, address, gender;

    public Person(String email, String name, String address, String gender){

        this.email = email.toLowerCase();

        if(name.length() > 30) {
            this.name = name.substring(0,30);
        }
        else{
            this.name = name;
        }

        this.address = address;

        gender = gender.toUpperCase();
        if (gender.equals("MALE") || gender.equals("FEMALE")){
            this.gender = gender.substring(0,1);
        }
        else if (gender.length() == 1 && (gender.charAt(0)== 'M' || gender.charAt(0)== 'F')){
            this.gender = gender;
        }
        else{
            this.gender = "Unspecified";
        }
    }

    //-------
    //GETTERS
    //-------

    /**
     * Returns the person's email
     * @return The person's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the person's name
     * @return The person's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the person's address
     * @return The person's address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Returns the person's gender
     * @return The person's gender
     */
    public String getGender() {
        return gender;
    }

    //-------
    //SETTERS
    //-------

    /**
     * Updates the person's email
     * @param email Updates the person's email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Updates the person's email. If name is more than 30 characters long
     * it truncates it to the first 30 characters
     * @param name Updates the person's name
     */
    public void setName(String name) {

        if(name.length() > 30) {
            this.name = name.substring(0,30);
        }
        else{
            this.name = name;
        }
    }

    /**
     * Updates the person's address
     * @param address Updates the person's address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Updates the person's gender.
     * @param gender Updates the person's gender
     */
    public void setGender(String gender) {

        gender = gender.toUpperCase();
        if (gender.equals("MALE") || gender.equals("FEMALE")){
            this.gender = gender.substring(0,1);
        }
        else if (gender.length() == 1 && (gender.charAt(0) == 'M' || gender.charAt(0) == 'F')){
            this.gender = gender;
        }
    }

    //--------------
    //HELPER METHODS
    //--------------

    /**
     * Returns a human readable String interpretation of the person's details
     * @return A string version of the person object.
     */
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
