package models;

/**
 * Created by Niall on 24/04/2017.
 */
public class StudentMember extends Member {

    int studentId;
    String collegeName;

    public StudentMember(int studentId, String collegeName) {
        this.studentId = studentId;
        this.collegeName = collegeName;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    @Override
    public String toString() {
        return "StudentMember{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", height=" + height +
                ", studentId=" + studentId +
                ", weight=" + weight +
                ", gender=" + gender +
                ", collegeName='" + collegeName + '\'' +
                ", chosenPackage='" + chosenPackage + '\'' +
                '}';
    }
}
