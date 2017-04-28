package models;

/**
 * Created by Niall on 24/04/2017.
 */
public abstract class Person {

    private String email, name, address, gender;

    public Person(String email, String name, String address, String gender){
        this.email = email;

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
    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getGender() {
        return gender;
    }

    //-------
    //SETTERS
    //-------
    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {

        if(name.length() > 30) {
            this.name = name.substring(0,30);
        }
        else{
            this.name = name;
        }
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setGender(String gender) {

        gender = gender.toUpperCase();
        if (gender.equals("MALE") || gender.equals("FEMALE")){
            this.gender = gender.substring(0,1);
        }
        else if (gender.length() == 1 && (gender.charAt(0)== 'M' || gender.charAt(0)== 'F')){
            this.gender = gender;
        }
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
