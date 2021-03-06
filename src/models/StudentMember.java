package models;

/**
 * A concrete subclass of the Member class that stores studentID and and collegeName
 *
 * Created by Niall on 24/04/2017.
 */
public class StudentMember extends Member {

    private int studentId;
    private String collegeName;

    /**
     * @param studentId - Student's ID number given to them by their college
     * @param collegeName - Name of the college they attend
     */
    public StudentMember(String email, String name, String address, String gender, double height, double startingWeight,
                         String chosenPackage, int studentId, String collegeName) {
        super(email, name, address, gender, height, startingWeight, chosenPackage);

        if(studentId >= 100000 && studentId <= 999999 ){
            this.studentId = studentId;
        }
        else{
            this.studentId = 0;
        }

        this.collegeName = collegeName;

    }

    @Override
    public void chosenPackage(String packageChoice){

        if(!collegeName.equalsIgnoreCase("WIT")){
            setChosenPackage("Package 3");
        }
        else{
            setChosenPackage("WIT");
        }
    }

    /**
     * Returns the Student's ID
     * @return The Student's ID
     */
    public int getStudentId() {
        return studentId;
    }

    /**
     * Returns the Student's college name
     * @return The Student's college name
     */
    public String getCollegeName() {
        return collegeName;
    }

    /**
     * Updates the Student's ID
     * @param studentId Updates the Student's ID
     */
    public void setStudentId(int studentId) {
        if(studentId >= 100000 && studentId <= 999999 ){
            this.studentId = studentId;
        }

    }

    /**
     * Updates the Student's college name
     * @param collegeName Updates the college name
     */
    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    /**
     * Returns a human readable String interpretation of the student's details
     * @return A string version of the student object.
     */
    @Override
    public String toString() {
        return  "NAME..........." + getName() + "\n" +
                "EMAIL.........." + getEmail() + "\n" +
                "STUDENT ID....." + getStudentId() + "\n" +
                "COLLEGE........" + getCollegeName() + "\n" +
                "GENDER........." + getGender() + "\n" +
                "HEIGHT........." + getHeight() + "\n" +
                "START WEIGHT..." + getWeight();
    }
}
