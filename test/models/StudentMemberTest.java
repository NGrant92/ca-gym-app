package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;


import static org.junit.Assert.*;

/**
 * I was unsure about testing the Person and Member methods in trainer, student and premium classes but I came across this answer and felt
 * it justified doing so: http://stackoverflow.com/questions/7569444/how-to-test-abstract-class-in-java-with-junit
 * * Created by niall on 21/05/17.
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
        HashMap<Date, Assessment> hashMap1 = new HashMap<>();
        hashMap1.put(dateFormat.parse(date1) , assessment1);
        hashMap1.put(dateFormat.parse(date2) , assessment2);
        assertEquals(hashMap1, studentMember1.getAssessments());

        HashMap<Date, Assessment> hashMap2 = new HashMap<>();
        hashMap2.put(dateFormat.parse(date3) , assessment3);
        hashMap2.put(dateFormat.parse(date4) , assessment4);
        assertEquals(hashMap2, studentMember2.getAssessments());

        HashMap<Date, Assessment> hashMap3 = new HashMap<>();
        hashMap3.put(dateFormat.parse(date5) , assessment5);
        hashMap3.put(dateFormat.parse(date6) , assessment6);
        assertEquals(hashMap3, studentMember3.getAssessments());
    }

    @Test
    public void getHeight() throws Exception {
        assertEquals(3, studentMember1.getHeight(), 0);
        assertEquals(0, studentMember2.getHeight(), 0);
        assertEquals(0, studentMember3.getHeight(), 0);
    }

    @Test
    public void getWeight() throws Exception {
        assertEquals(250, studentMember1.getWeight(), 0);
        assertEquals(0, studentMember2.getWeight(), 0);
        assertEquals(0, studentMember3.getWeight(), 0);
    }

    @Test
    public void getChosenPackage() throws Exception {
        assertEquals("STUDENT", studentMember1.getChosenPackage());
        assertEquals("Unspecified", studentMember2.getChosenPackage());
        assertEquals("STUDENT", studentMember3.getChosenPackage());
    }

    @Test
    public void setHeight() throws Exception {
        studentMember1.setHeight(2.9);
        assertEquals(2.9, studentMember1.getHeight(), 0);
        studentMember2.setHeight(4);
        assertEquals(0, studentMember2.getHeight(), 0);
        studentMember3.setHeight(0.8);
        assertEquals(0, studentMember3.getHeight(), 0);
    }

    @Test
    public void setWeight() throws Exception {
        studentMember1.setWeight(249.9);
        assertEquals(249.9, studentMember1.getWeight(), 0);
        studentMember2.setWeight(250.1);
        assertEquals(0, studentMember2.getWeight(), 0);
        studentMember3.setWeight(34.9);
        assertEquals(0, studentMember3.getWeight(), 0);
    }

    @Test
    public void setChosenPackage() throws Exception {
        studentMember1.setChosenPackage("Random");
        assertEquals("Random", studentMember1.getChosenPackage());
        studentMember2.setChosenPackage("Words");
        assertEquals("Words", studentMember2.getChosenPackage());
        studentMember3.setChosenPackage("Here");
        assertEquals("Here", studentMember3.getChosenPackage());
    }

    @Test
    public void latestAssessment() throws Exception {
        assertEquals(assessment2, studentMember1.latestAssessment());
        assertEquals(assessment4, studentMember2.latestAssessment());
        assertEquals(assessment6, studentMember3.latestAssessment());
    }

    @Test
    public void progressAssessment() throws Exception {
        assertEquals("\n" + "Starting Weight:\n250.0kg" +
                "\n" + dateFormat.parse(date1) + "\n11.0kg" +
                "\n" + dateFormat.parse(date2) + "\n12.0kg", studentMember1.progressAssessment("weight"));

        assertEquals("\n" + dateFormat.parse(date1) + "\n21.0cm" +
                "\n" + dateFormat.parse(date2) + "\n22.0cm", studentMember1.progressAssessment("chest"));

        assertEquals("\n" + dateFormat.parse(date1) + "\n31.0cm" +
                "\n" + dateFormat.parse(date2) + "\n32.0cm", studentMember1.progressAssessment("thigh"));

        assertEquals("\n" + dateFormat.parse(date1) + "\n41.0cm" +
                "\n" + dateFormat.parse(date2) + "\n42.0cm", studentMember1.progressAssessment("upperArm"));

        assertEquals("\n" + dateFormat.parse(date1) + "\n51.0cm" +
                "\n" + dateFormat.parse(date2) + "\n52.0cm", studentMember1.progressAssessment("waist"));

        assertEquals("\n" + dateFormat.parse(date1) + "\n61.0cm" +
                "\n" + dateFormat.parse(date2) + "\n62.0cm", studentMember1.progressAssessment("hips"));

        //Only difference between studentMember1 and the other 2 is studentMember1's starting wait isn't 0
        assertEquals("\n" + "Starting Weight:\n0.0kg" +
                "\n" + dateFormat.parse(date3) + "\n13.0kg" +
                "\n" + dateFormat.parse(date4) + "\n14.0kg", studentMember2.progressAssessment("weight"));

        assertEquals("\n" + "Starting Weight:\n0.0kg" +
                "\n" + dateFormat.parse(date5) + "\n15.0kg" +
                "\n" + dateFormat.parse(date6) + "\n16.0kg", studentMember3.progressAssessment("weight"));

    }

    @Test
    public void sortedAssessmentDates() throws Exception {
        assertEquals(dateFormat.parse(date1), studentMember1.sortedAssessmentDates().first());
        assertEquals(dateFormat.parse(date2), studentMember1.sortedAssessmentDates().last());


        assertEquals(dateFormat.parse(date3), studentMember2.sortedAssessmentDates().first());
        assertEquals(dateFormat.parse(date4), studentMember2.sortedAssessmentDates().last());


        assertEquals(dateFormat.parse(date5), studentMember3.sortedAssessmentDates().first());
        assertEquals(dateFormat.parse(date6), studentMember3.sortedAssessmentDates().last());
    }

    @Test
    public void addAllAssessments() throws Exception {
        HashMap<Date, Assessment> hashMap1 = new HashMap<>();
        hashMap1.put(dateFormat.parse(date6), assessment6);
        studentMember1.addAllAssessments(hashMap1);
        assertEquals(dateFormat.parse(date6), studentMember1.sortedAssessmentDates().last());

        HashMap<Date, Assessment> hashMap2 = new HashMap<>();
        hashMap2.put(dateFormat.parse(date6), assessment6);
        studentMember2.addAllAssessments(hashMap2);
        assertEquals(dateFormat.parse(date6), studentMember2.sortedAssessmentDates().last());

        HashMap<Date, Assessment> hashMap3 = new HashMap<>();
        hashMap3.put(dateFormat.parse(date1), assessment1);
        studentMember3.addAllAssessments(hashMap3);
        assertEquals(dateFormat.parse(date1), studentMember3.sortedAssessmentDates().first());
    }

    @Test
    public void getEmail() throws Exception {
        assertEquals("test1@tmail.com", studentMember1.getEmail());
        assertEquals("test2@tmail.com", studentMember2.getEmail());
        assertEquals("test3@tmail.com", studentMember3.getEmail());
    }

    @Test
    public void getName() throws Exception {
        assertEquals("Deadpool 'Merc with the Mouth'", studentMember1.getName());
        assertEquals("Percival Fredrickstein de Rolo", studentMember2.getName());
        assertEquals("Rhonda UndefeatedChamp Rousey", studentMember3.getName());
    }

    @Test
    public void getAddress() throws Exception {
        assertEquals("New York", studentMember1.getAddress());
        assertEquals("Castle Whitestone", studentMember2.getAddress());
        assertEquals("Vegas", studentMember3.getAddress());
    }

    @Test
    public void getGender() throws Exception {
        assertEquals("M", studentMember1.getGender());
        assertEquals("Unspecified", studentMember2.getGender());
        assertEquals("F", studentMember3.getGender());
    }

    @Test
    public void setEmail() throws Exception {
        studentMember1.setEmail("4@tmail.com");
        assertEquals("4@tmail.com", studentMember1.getEmail());
        studentMember2.setEmail("noSymbol");
        assertEquals("test2@tmail.com", studentMember2.getEmail());
        studentMember3.setEmail("UPPER@CASE.COM");
        assertEquals("upper@case.com", studentMember3.getEmail());
    }

    @Test
    public void setName() throws Exception {
        studentMember1.setName("Deadpool 'Merc with the Mouth'");
        assertEquals("Deadpool 'Merc with the Mouth'", studentMember1.getName());
        studentMember2.setName("Percival Fredrickstein de Rolo1");
        assertEquals("Percival Fredrickstein de Rolo", studentMember2.getName());
        studentMember3.setName("Rhonda UndefeatedChamp Rousey");
        assertEquals("Rhonda UndefeatedChamp Rousey", studentMember3.getName());
    }

    @Test
    public void setAddress() throws Exception {
        studentMember1.setAddress("Anything");
        assertEquals("Anything", studentMember1.getAddress());
        studentMember2.setAddress("goes");
        assertEquals("goes", studentMember2.getAddress());
        studentMember3.setAddress("here");
        assertEquals("here", studentMember3.getAddress());
    }

    @Test
    public void setGender() throws Exception {
        studentMember1.setGender("female");
        assertEquals("F", studentMember1.getGender());
        studentMember2.setGender("MaLe");
        assertEquals("M", studentMember2.getGender());
        studentMember3.setGender("mail");
        assertEquals("F", studentMember3.getGender());
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("NAME...........Deadpool 'Merc with the Mouth'\n" +
                "EMAIL..........test1@tmail.com\n" +
                "STUDENT ID.....999999\n" +
                "COLLEGE........WIT\n" +
                "GENDER.........M\n" +
                "HEIGHT.........3.0\n" +
                "START WEIGHT...250.0", studentMember1.toString());

        assertEquals("NAME...........Percival Fredrickstein de Rolo\n" +
                "EMAIL..........test2@tmail.com\n" +
                "STUDENT ID.....0\n" +
                "COLLEGE........Any College\n" +
                "GENDER.........Unspecified\n" +
                "HEIGHT.........0.0\n" +
                "START WEIGHT...0.0", studentMember2.toString());

        assertEquals("NAME...........Rhonda UndefeatedChamp Rousey\n" +
                "EMAIL..........test3@tmail.com\n" +
                "STUDENT ID.....0\n" +
                "COLLEGE........UCC\n" +
                "GENDER.........F\n" +
                "HEIGHT.........0.0\n" +
                "START WEIGHT...0.0", studentMember3.toString());
    }

}