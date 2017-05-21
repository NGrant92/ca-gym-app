package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by niall on 20/05/17.
 */
public class PremiumMemberTest {
    private PremiumMember premMember1, premMember2, premMember3;
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
        premMember1 = new PremiumMember("test1@tmail.com", "Deadpool 'Merc with the Mouth'", "New York", "m", 3, 250, "PREMIUM");
        //31 characters long, maleZ should = unspecified, height and weight = 0, "NOTPREMUIM" = ""
        premMember2 = new PremiumMember("test2@tmail.com", "Percival Fredrickstein de Rolo1", "Castle Whitestone", "maleZ", 3.1, 251, "NOTPREMIUM");
        //29 characters long, email should be become lowercase, "female" should become "F", height and weight = 0
        premMember3 = new PremiumMember("TEST3@TMAIL.COM", "Rhonda UndefeatedChamp Rousey", "Vegas", "female", 0.9, 34.9, "premium");

        slyStalone = new Trainer("test4@tmail.com", "Sylvester Stalone", "LA", "M", "Boxercise");
        terryCrews = new Trainer("test5@tmail.com", "Terry Crews", "LA", "M", "Powerlifting");
        rhondaRousey = new Trainer("test6@tmail.com", "Rhonda Rousey", "Vegas", "F", "Cardio");

        assessment1 = new Assessment(11, 21, 31, 41, 51, 61, "Assess1", slyStalone);
        assessment2 = new Assessment(12, 22, 32, 42, 52, 62, "Assess2", slyStalone);

        assessment3 = new Assessment(13, 23, 33, 43, 53, 63, "Assess3", terryCrews);
        assessment4 = new Assessment(14, 24, 34, 44, 54, 64, "Assess4", terryCrews);

        assessment5 = new Assessment(15, 25, 35, 45, 55, 65, "Assess5", rhondaRousey);
        assessment6 = new Assessment(16, 26, 36, 46, 56, 66, "Assess6", rhondaRousey);

        premMember1.getAssessments().put(dateFormat.parse(date1), assessment1);
        premMember1.getAssessments().put(dateFormat.parse(date2), assessment2);

        premMember2.getAssessments().put(dateFormat.parse(date3), assessment3);
        premMember2.getAssessments().put(dateFormat.parse(date4), assessment4);

        premMember3.getAssessments().put(dateFormat.parse(date5), assessment5);
        premMember3.getAssessments().put(dateFormat.parse(date6), assessment6);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testConstructor(){
        assertEquals("test1@tmail.com", premMember1.getEmail());
        assertEquals("Deadpool 'Merc with the Mouth'", premMember1.getName());
        assertEquals("New York", premMember1.getAddress());
        assertEquals("M", premMember1.getGender());
        assertEquals(3, premMember1.getHeight(), 0);
        assertEquals(250, premMember1.getWeight(), 0);
        assertEquals("PREMIUM", premMember1.getChosenPackage());

        assertEquals("test2@tmail.com", premMember2.getEmail());
        assertEquals("Percival Fredrickstein de Rolo", premMember2.getName());
        assertEquals("Castle Whitestone", premMember2.getAddress());
        assertEquals("Unspecified", premMember2.getGender());
        assertEquals(0, premMember2.getHeight(), 0);
        assertEquals(0, premMember2.getWeight(), 0);
        assertEquals("Unspecified", premMember2.getChosenPackage());

        assertEquals("test3@tmail.com", premMember3.getEmail());
        assertEquals("Rhonda UndefeatedChamp Rousey", premMember3.getName());
        assertEquals("Vegas", premMember3.getAddress());
        assertEquals("F", premMember3.getGender());
        assertEquals(0, premMember3.getHeight(), 0);
        assertEquals(0, premMember3.getWeight(), 0);
        assertEquals("PREMIUM", premMember3.getChosenPackage());
    }

    @Test
    public void chosenPackage() throws Exception {
        premMember1.chosenPackage("Package 1");
        assertEquals("Package 1", premMember1.getChosenPackage());

        premMember2.chosenPackage("Package 123456789");
        assertEquals("Package 123456789", premMember2.getChosenPackage());

        premMember3.chosenPackage("Nothing");
        assertEquals("Nothing", premMember3.getChosenPackage());
    }

    @Test
    public void getAssessments() throws Exception {
        assertEquals("Package 1", premMember1.getChosenPackage());
    }

    @Test
    public void getHeight() throws Exception {
        assertEquals(3, premMember1.getHeight(), 0);
        assertEquals(0, premMember2.getHeight(), 0);
        assertEquals(0, premMember3.getHeight(), 0);
    }

    @Test
    public void getWeight() throws Exception {
        assertEquals(250, premMember1.getWeight(), 0);
        assertEquals(0, premMember2.getWeight(), 0);
        assertEquals(0, premMember3.getWeight(), 0);
    }

    @Test
    public void getChosenPackage() throws Exception {
        assertEquals("PREMIUM", premMember1.getChosenPackage());
        assertEquals("Unspecified", premMember2.getChosenPackage());
        assertEquals("PREMIUM", premMember3.getChosenPackage());
    }

    @Test
    public void setHeight() throws Exception {
        premMember1.setHeight(2.9);
        assertEquals(2.9, premMember1.getHeight(), 0);
        premMember2.setHeight(4);
        assertEquals(0, premMember2.getHeight(), 0);
        premMember3.setHeight(0.8);
        assertEquals(0, premMember3.getHeight(), 0);
    }

    @Test
    public void setWeight() throws Exception {
        premMember1.setWeight(249.9);
        assertEquals(249.9, premMember1.getWeight(), 0);
        premMember2.setWeight(250.1);
        assertEquals(0, premMember2.getWeight(), 0);
        premMember3.setWeight(34.9);
        assertEquals(0, premMember3.getWeight(), 0);
    }

    @Test
    public void latestAssessment() throws Exception {
        assertEquals(assessment2, premMember1.latestAssessment());
        assertEquals(assessment4, premMember2.latestAssessment());
        assertEquals(assessment6, premMember3.latestAssessment());
    }

    @Test
    public void progressAssessment() throws Exception {
        assertEquals("\n" + "Starting Weight:\n250.0kg" +
                "\n" + dateFormat.parse(date1) + "\n11.0kg" +
                "\n" + dateFormat.parse(date2) + "\n12.0kg", premMember1.progressAssessment("weight"));

        assertEquals("\n" + dateFormat.parse(date1) + "\n21.0cm" +
                "\n" + dateFormat.parse(date2) + "\n22.0cm", premMember1.progressAssessment("chest"));

        assertEquals("\n" + dateFormat.parse(date1) + "\n31.0cm" +
                "\n" + dateFormat.parse(date2) + "\n32.0cm", premMember1.progressAssessment("thigh"));

        assertEquals("\n" + dateFormat.parse(date1) + "\n41.0cm" +
                "\n" + dateFormat.parse(date2) + "\n42.0cm", premMember1.progressAssessment("upperArm"));

        assertEquals("\n" + dateFormat.parse(date1) + "\n51.0cm" +
                "\n" + dateFormat.parse(date2) + "\n52.0cm", premMember1.progressAssessment("waist"));

        assertEquals("\n" + dateFormat.parse(date1) + "\n61.0cm" +
                "\n" + dateFormat.parse(date2) + "\n62.0cm", premMember1.progressAssessment("hips"));

        //Only difference between premMember1 and the other 2 is premMember1's starting wait isn't 0
        assertEquals("\n" + "Starting Weight:\n0.0kg" +
                "\n" + dateFormat.parse(date3) + "\n13.0kg" +
                "\n" + dateFormat.parse(date4) + "\n14.0kg", premMember2.progressAssessment("weight"));

        assertEquals("\n" + "Starting Weight:\n0.0kg" +
                "\n" + dateFormat.parse(date5) + "\n15.0kg" +
                "\n" + dateFormat.parse(date6) + "\n16.0kg", premMember3.progressAssessment("weight"));
    }

    @Test
    public void sortedAssessmentDates() throws Exception {
        assertEquals(dateFormat.parse(date1), premMember1.sortedAssessmentDates().first());
        assertEquals(dateFormat.parse(date2), premMember1.sortedAssessmentDates().last());


        assertEquals(dateFormat.parse(date3), premMember2.sortedAssessmentDates().first());
        assertEquals(dateFormat.parse(date4), premMember2.sortedAssessmentDates().last());


        assertEquals(dateFormat.parse(date5), premMember3.sortedAssessmentDates().first());
        assertEquals(dateFormat.parse(date6), premMember3.sortedAssessmentDates().last());
    }

    @Test
    public void addAllAssessments() throws Exception {
        HashMap<Date, Assessment> hashMap1 = new HashMap<>();
        hashMap1.put(dateFormat.parse(date6), assessment6);
        premMember1.addAllAssessments(hashMap1);
        assertEquals(dateFormat.parse(date6), premMember1.sortedAssessmentDates().last());

        HashMap<Date, Assessment> hashMap2 = new HashMap<>();
        hashMap2.put(dateFormat.parse(date6), assessment6);
        premMember2.addAllAssessments(hashMap2);
        assertEquals(dateFormat.parse(date6), premMember2.sortedAssessmentDates().last());

        HashMap<Date, Assessment> hashMap3 = new HashMap<>();
        hashMap3.put(dateFormat.parse(date1), assessment1);
        premMember3.addAllAssessments(hashMap3);
        assertEquals(dateFormat.parse(date1), premMember3.sortedAssessmentDates().first());
    }

    @Test
    public void getEmail() throws Exception {
        assertEquals("test1@tmail.com", premMember1.getEmail());
        assertEquals("test2@tmail.com", premMember2.getEmail());
        assertEquals("test3@tmail.com", premMember3.getEmail());
    }

    @Test
    public void getName() throws Exception {
        assertEquals("Deadpool 'Merc with the Mouth'", premMember1.getName());
        assertEquals("Percival Fredrickstein de Rolo", premMember2.getName());
        assertEquals("Rhonda UndefeatedChamp Rousey", premMember3.getName());
    }

    @Test
    public void getAddress() throws Exception {
        assertEquals("New York", premMember1.getAddress());
        assertEquals("Castle Whitestone", premMember2.getAddress());
        assertEquals("Vegas", premMember3.getAddress());
    }

    @Test
    public void getGender() throws Exception {
        assertEquals("M", premMember1.getGender());
        assertEquals("Unspecified", premMember2.getGender());
        assertEquals("F", premMember3.getGender());
    }

    @Test
    public void setEmail() throws Exception {
        premMember1.setEmail("4@tmail.com");
        assertEquals("4@tmail.com", premMember1.getEmail());
        premMember2.setEmail("noSymbol");
        assertEquals("test2@tmail.com", premMember2.getEmail());
        premMember3.setEmail("UPPER@CASE.COM");
        assertEquals("upper@case.com", premMember3.getEmail());
    }

    @Test
    public void setName() throws Exception {
        premMember1.setName("Deadpool 'Merc with the Mouth'");
        assertEquals("Deadpool 'Merc with the Mouth'", premMember1.getName());
        premMember2.setName("Percival Fredrickstein de Rolo1");
        assertEquals("Percival Fredrickstein de Rolo", premMember2.getName());
        premMember3.setName("Rhonda UndefeatedChamp Rousey");
        assertEquals("Rhonda UndefeatedChamp Rousey", premMember3.getName());
    }

    @Test
    public void setAddress() throws Exception {
        premMember1.setAddress("Anything");
        assertEquals("Anything", premMember1.getAddress());
        premMember2.setAddress("goes");
        assertEquals("goes", premMember2.getAddress());
        premMember3.setAddress("here");
        assertEquals("here", premMember3.getAddress());
    }

    @Test
    public void setGender() throws Exception {
        premMember1.setGender("female");
        assertEquals("F", premMember1.getGender());
        premMember2.setGender("MaLe");
        assertEquals("M", premMember2.getGender());
        premMember3.setGender("mail");
        assertEquals("F", premMember3.getGender());
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("NAME...........Deadpool 'Merc with the Mouth'\n" +
                "EMAIL..........test1@tmail.com\n" +
                "GENDER.........M\n" +
                "HEIGHT.........3.0\n" +
                "START WEIGHT...250.0", premMember1.toString());

        assertEquals("NAME...........Percival Fredrickstein de Rolo\n" +
                "EMAIL..........test2@tmail.com\n" +
                "GENDER.........Unspecified\n" +
                "HEIGHT.........0.0\n" +
                "START WEIGHT...0.0", premMember2.toString());

        assertEquals("NAME...........Rhonda UndefeatedChamp Rousey\n" +
                "EMAIL..........test3@tmail.com\n" +
                "GENDER.........F\n" +
                "HEIGHT.........0.0\n" +
                "START WEIGHT...0.0", premMember3.toString());
    }

}