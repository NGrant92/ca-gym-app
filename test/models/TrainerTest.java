package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * I was unsure about testing the Person and Member methods in trainer, student and premium classes but I came across this answer and felt
 * it justified doing so: http://stackoverflow.com/questions/7569444/how-to-test-abstract-class-in-java-with-junit
 * Created by niall on 20/05/17.
 */
public class TrainerTest {
    private Trainer trainer1, trainer2, trainer3;


    @Before
    public void setUp() throws Exception {
        trainer1 = new Trainer("test1@tmail.com", "Sylvester Stalone", "New York", "M", "Boxercise");
        trainer2 = new Trainer("test2@tmail.com", "Terry Crews", "Hollywood", "M", "Powerlifting");
        trainer3 = new Trainer("test3@tmail.com", "Rhonda Rousey", "Vegas", "F", "Cardio");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getSpeciality() throws Exception {
        assertEquals("Boxercise", trainer1.getSpeciality());
        assertEquals("Powerlifting", trainer2.getSpeciality());
        assertEquals("Cardio", trainer3.getSpeciality());
    }

    @Test
    public void getEmail() throws Exception {
        assertEquals("test1@tmail.com", trainer1.getEmail());
        assertEquals("test2@tmail.com", trainer2.getEmail());
        assertEquals("test3@tmail.com", trainer3.getEmail());
    }

    @Test
    public void getName() throws Exception {
        assertEquals("Sylvester Stalone", trainer1.getName());
        assertEquals("Terry Crews", trainer2.getName());
        assertEquals("Rhonda Rousey", trainer3.getName());
    }

    @Test
    public void getAddress() throws Exception {
        assertEquals("New York", trainer1.getAddress());
        assertEquals("Hollywood", trainer2.getAddress());
        assertEquals("Vegas", trainer3.getAddress());
    }

    @Test
    public void getGender() throws Exception {
        assertEquals("M", trainer1.getGender());
        assertEquals("M", trainer2.getGender());
        assertEquals("F", trainer3.getGender());
    }

    @Test
    public void setEmail() throws Exception {

        trainer1.setEmail("rocky@tmail.com");
        assertEquals("rocky@tmail.com", trainer1.getEmail());

        trainer2.setEmail("oldspice@tmail.com");
        assertEquals("oldspice@tmail.com", trainer2.getEmail());

        trainer3.setEmail("rowdytmail.com");
        assertEquals("test3@tmail.com", trainer3.getEmail());
    }

    @Test
    public void setName() throws Exception {

        //30 characters long
        trainer1.setName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        assertEquals("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", trainer1.getName());
        //31 characters long
        trainer2.setName("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbZ");
        assertEquals("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbb", trainer2.getName());
        //29 characters long
        trainer3.setName("ccccccccccccccccccccccccccccc");
        assertEquals("ccccccccccccccccccccccccccccc", trainer3.getName());
    }

    @Test
    public void setAddress() throws Exception {

        trainer1.setAddress("LA1");
        assertEquals("LA1", trainer1.getAddress());

        trainer2.setAddress("LA2");
        assertEquals("LA2", trainer2.getAddress());

        trainer3.setAddress("LA3");
        assertEquals("LA3", trainer3.getAddress());
    }

    @Test
    public void setGender() throws Exception {

        //Changing M to F
        trainer1.setGender("F");
        assertEquals("F", trainer1.getGender());

        //Changing M to Z
        //getGender() should return M
        trainer2.setGender("Z");
        assertEquals("M", trainer2.getGender());


        //Changing F to male
        //should return M
        trainer3.setGender("male");
        assertEquals("M", trainer3.getGender());
    }

    @Test
    public void testToString() throws Exception {

        assertEquals("NAME...........Sylvester Stalone" + "\n" +
                "EMAIL..........test1@tmail.com" + "\n" +
                "GENDER.........M" + "\n" +
                "SPECIALITY.....Boxercise", trainer1.toString());

        assertEquals("NAME...........Terry Crews" + "\n" +
                "EMAIL..........test2@tmail.com" + "\n" +
                "GENDER.........M" + "\n" +
                "SPECIALITY.....Powerlifting", trainer2.toString());

        assertEquals("NAME...........Rhonda Rousey" + "\n" +
                "EMAIL..........test3@tmail.com" + "\n" +
                "GENDER.........F" + "\n" +
                "SPECIALITY.....Cardio", trainer3.toString());
    }

}