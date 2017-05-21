package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;


import static org.junit.Assert.*;

/**
 * Created by niall on 21/05/17.
 */
public class StudentMemberTest {


    private StudentMember studentMember1, studentMember2, studentMember3;
    private Trainer slyStalone, terryCrews, rhondaRousey;
    private Assessment assessment1, assessment2, assessment3, assessment4, assessment5, assessment6;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private String date1 = "20/05/2017";
    private String date2 = "21/05/2017";
    private String date3 = "22/05/2017";
    private String date4 = "23/05/2017";
    private String date5 = "24/05/2017";
    private String date6 = "25/05/2017";
    @Before
    public void setUp() throws Exception {
        //30 characters long, "m" should become M
        studentMember1 = new StudentMember("test1@tmail.com", "Deadpool 'Merc with the Mouth'", "New York", "m", 3, 250, "STUDENT", 999999, "WIT");
        //31 characters long, maleZ should = unspecified, height and weight = 0, "NOTSTUDENT" = ""
        studentMember2 = new StudentMember("test2@tmail.com", "Percival Fredrickstein de Rolo1", "Castle Whitestone", "maleZ", 3.1, 251, "NOTSTUDENT", 9999990, "Any College");
        //29 characters long, email should be become lowercase, "female" should become "F", height and weight = 0
        studentMember3 = new StudentMember("TEST3@TMAIL.COM", "Rhonda UndefeatedChamp Rousey", "Vegas", "female", 0.9, 34.9, "student", 99999, "UCC");

        slyStalone = new Trainer("test4@tmail.com", "Sylvester Stalone", "LA", "M", "Boxercise");
        terryCrews = new Trainer("test5@tmail.com", "Terry Crews", "LA", "M", "Powerlifting");
        rhondaRousey = new Trainer("test6@tmail.com", "Rhonda Rousey", "Vegas", "F", "Cardio");

        assessment1 = new Assessment(11, 21, 31, 41, 51, 61, "Assess1", slyStalone);
        assessment2 = new Assessment(12, 22, 32, 42, 52, 62, "Assess2", slyStalone);

        assessment3 = new Assessment(13, 23, 33, 43, 53, 63, "Assess3", terryCrews);
        assessment4 = new Assessment(14, 24, 34, 44, 54, 64, "Assess4", terryCrews);

        assessment5 = new Assessment(15, 25, 35, 45, 55, 65, "Assess5", rhondaRousey);
        assessment6 = new Assessment(16, 26, 36, 46, 56, 66, "Assess6", rhondaRousey);

        studentMember1.getAssessments().put(dateFormat.parse(date1), assessment1);
        studentMember1.getAssessments().put(dateFormat.parse(date2), assessment2);

        studentMember2.getAssessments().put(dateFormat.parse(date3), assessment3);
        studentMember2.getAssessments().put(dateFormat.parse(date4), assessment4);

        studentMember3.getAssessments().put(dateFormat.parse(date5), assessment5);
        studentMember3.getAssessments().put(dateFormat.parse(date6), assessment6);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testConstructor(){
        assertEquals("test1@tmail.com", studentMember1.getEmail());
        assertEquals("Deadpool 'Merc with the Mouth'", studentMember1.getName());
        assertEquals("New York", studentMember1.getAddress());
        assertEquals("M", studentMember1.getGender());
        assertEquals(3, studentMember1.getHeight(), 0);
        assertEquals(250, studentMember1.getWeight(), 0);
        assertEquals("STUDENT", studentMember1.getChosenPackage());
        assertEquals(999999, studentMember1.getStudentId(), 0);
        assertEquals("WIT", studentMember1.getCollegeName());


        assertEquals("test2@tmail.com", studentMember2.getEmail());
        assertEquals("Percival Fredrickstein de Rolo", studentMember2.getName());
        assertEquals("Castle Whitestone", studentMember2.getAddress());
        assertEquals("Unspecified", studentMember2.getGender());
        assertEquals(0, studentMember2.getHeight(), 0);
        assertEquals(0, studentMember2.getWeight(), 0);
        assertEquals("Unspecified", studentMember2.getChosenPackage());
        assertEquals(0, studentMember2.getStudentId(), 0);
        assertEquals("Any College", studentMember2.getCollegeName());

        assertEquals("test3@tmail.com", studentMember3.getEmail());
        assertEquals("Rhonda UndefeatedChamp Rousey", studentMember3.getName());
        assertEquals("Vegas", studentMember3.getAddress());
        assertEquals("F", studentMember3.getGender());
        assertEquals(0, studentMember3.getHeight(), 0);
        assertEquals(0, studentMember3.getWeight(), 0);
        assertEquals("STUDENT", studentMember3.getChosenPackage());
        assertEquals(0, studentMember3.getStudentId(), 0);
        assertEquals("UCC", studentMember3.getCollegeName());
    }

    @Test
    public void chosenPackage() throws Exception {
        studentMember1.chosenPackage("STUDENT");
        assertEquals("WIT", studentMember1.getChosenPackage());

        studentMember2.chosenPackage("Package 123456789");
        assertEquals("Package 3", studentMember2.getChosenPackage());

        studentMember3.chosenPackage("Nothing");
        assertEquals("Package 3", studentMember3.getChosenPackage());
    }

    @Test
    public void getStudentId() throws Exception {
        assertEquals(999999, studentMember1.getStudentId(), 0);
        assertEquals(0, studentMember2.getStudentId(), 0);
        assertEquals(0, studentMember3.getStudentId(), 0);
    }

    @Test
    public void getCollegeName() throws Exception {
        assertEquals("WIT", studentMember1.getCollegeName());
        assertEquals("Any College", studentMember2.getCollegeName());
        assertEquals("UCC", studentMember3.getCollegeName());
    }

    @Test
    public void setStudentId() throws Exception {
        studentMember1.setStudentId(100000);
        assertEquals(100000, studentMember1.getStudentId(), 0);
        studentMember2.setStudentId(9999991);
        assertEquals(0, studentMember2.getStudentId(), 0);
        studentMember3.setStudentId(99998);
        assertEquals(0, studentMember3.getStudentId(), 0);
    }

    @Test
    public void setCollegeName() throws Exception {
        studentMember1.setCollegeName("DIT");
        assertEquals("DIT", studentMember1.getCollegeName());
        studentMember2.setCollegeName("Some other college");
        assertEquals("Some other college", studentMember2.getCollegeName());
        studentMember3.setCollegeName("CIT");
        assertEquals("CIT", studentMember3.getCollegeName());
    }

    @Test
    public void getAssessments() throws Exception {
    }

    @Test
    public void getHeight() throws Exception {
    }

    @Test
    public void getWeight() throws Exception {
    }

    @Test
    public void getChosenPackage() throws Exception {
    }

    @Test
    public void setHeight() throws Exception {
    }

    @Test
    public void setWeight() throws Exception {
    }

    @Test
    public void setChosenPackage() throws Exception {
    }

    @Test
    public void latestAssessment() throws Exception {
    }

    @Test
    public void progressAssessment() throws Exception {
    }

    @Test
    public void sortedAssessmentDates() throws Exception {
    }

    @Test
    public void addAllAssessments() throws Exception {
    }

    @Test
    public void getEmail() throws Exception {
    }

    @Test
    public void getName() throws Exception {
    }

    @Test
    public void getAddress() throws Exception {
    }

    @Test
    public void getGender() throws Exception {
    }

    @Test
    public void setEmail() throws Exception {
    }

    @Test
    public void setName() throws Exception {
    }

    @Test
    public void setAddress() throws Exception {
    }

    @Test
    public void setGender() throws Exception {
    }

    @Test
    public void testToString() throws Exception {
    }

}