package models;

/**
 * Created by Niall on 24/04/2017.
 */
public class StudentMember extends Member {

    private int studentId;
    private String collegeName;

    public StudentMember(String email, String name, String address, String gender, double height, double weight, String chosenPackage, int studentId, String collegeName) {
        super(email, name, address, gender, height, weight, chosenPackage);
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
                "studentId=" + studentId +
                ", collegeName='" + collegeName + '\'' +
                '}';
    }
}
