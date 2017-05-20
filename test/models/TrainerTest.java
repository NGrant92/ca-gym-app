package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by niall on 20/05/17.
 */
public class TrainerTest {
    private Trainer premMember1, premMember2, premMember3;

    @Before
    public void setUp() throws Exception {
        premMember1 = new Trainer("test1@tmail.com", "Sylvester Stalone", "New York", "M", "Boxercise");
        premMember2 = new Trainer("test2@tmail.com", "Terry Crews", "Hollywood", "M", "Powerlifting");
        premMember3 = new Trainer("test3@tmail.com", "Rhonda Rousey", "Vegas", "F", "Cardio");

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getSpeciality() throws Exception {
        assertEquals("Boxercise", premMember1.getSpeciality());
        assertEquals("Powerlifting", premMember2.getSpeciality());
        assertEquals("Cardio", premMember3.getSpeciality());
    }

    @Test
    public void getEmail() throws Exception {
        assertEquals("test1@tmail.com", premMember1.getEmail());
        assertEquals("test2@tmail.com", premMember2.getEmail());
        assertEquals("test3@tmail.com", premMember3.getEmail());
    }

    @Test
    public void getName() throws Exception {
        assertEquals("Sylvester Stalone", premMember1.getName());
        assertEquals("Terry Crews", premMember2.getName());
        assertEquals("Rhonda Rousey", premMember3.getName());
    }

    @Test
    public void getAddress() throws Exception {
        assertEquals("New York", premMember1.getAddress());
        assertEquals("Hollywood", premMember2.getAddress());
        assertEquals("Vegas", premMember3.getAddress());
    }

    @Test
    public void getGender() throws Exception {
        assertEquals("M", premMember1.getGender());
        assertEquals("M", premMember2.getGender());
        assertEquals("F", premMember3.getGender());
    }

    @Test
    public void setEmail() throws Exception {

        premMember1.setEmail("rocky@tmail.com");
        assertEquals("rocky@tmail.com", premMember1.getEmail());

        premMember2.setEmail("oldspice@tmail.com");
        assertEquals("oldspice@tmail.com", premMember2.getEmail());

        premMember3.setEmail("rowdytmail.com");
        assertEquals("test3@tmail.com", premMember3.getEmail());
    }

    @Test
    public void setName() throws Exception {

        //30 characters long
        premMember1.setName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        assertEquals("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", premMember1.getName());
        //31 characters long
        premMember2.setName("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbZ");
        assertEquals("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbb", premMember2.getName());
        //29 characters long
        premMember3.setName("ccccccccccccccccccccccccccccc");
        assertEquals("ccccccccccccccccccccccccccccc", premMember3.getName());
    }

    @Test
    public void setAddress() throws Exception {

        premMember1.setAddress("LA1");
        assertEquals("LA1", premMember1.getAddress());

        premMember2.setAddress("LA2");
        assertEquals("LA2", premMember2.getAddress());

        premMember3.setAddress("LA3");
        assertEquals("LA3", premMember3.getAddress());
    }

    @Test
    public void setGender() throws Exception {

        //Changing M to F
        premMember1.setGender("F");
        assertEquals("F", premMember1.getGender());

        //Changing M to Z
        //getGender() should return M
        premMember2.setGender("Z");
        assertEquals("M", premMember2.getGender());


        //Changing F to male
        //should return M
        premMember3.setGender("male");
        assertEquals("M", premMember3.getGender());
    }

    @Test
    public void testToString() throws Exception {

        assertEquals("NAME...........Sylvester Stalone" + "\n" +
                "EMAIL..........test1@tmail.com" + "\n" +
                "GENDER.........M" + "\n" +
                "SPECIALITY.....Boxercise", premMember1.toString());

        assertEquals("NAME...........Terry Crews" + "\n" +
                "EMAIL..........test2@tmail.com" + "\n" +
                "GENDER.........M" + "\n" +
                "SPECIALITY.....Powerlifting", premMember2.toString());

        assertEquals("NAME...........Rhonda Rousey" + "\n" +
                "EMAIL..........test3@tmail.com" + "\n" +
                "GENDER.........F" + "\n" +
                "SPECIALITY.....Cardio", premMember3.toString());
    }

}