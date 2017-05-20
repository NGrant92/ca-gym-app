package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by niall on 20/05/17.
 */
public class AssessmentTest {

    private Assessment assessment1, assessment2, assessment3;
    private Trainer slyStalone, terryCrews, rhondaRousey;


    @Before
    public void setUp() throws Exception {
        slyStalone = new Trainer("test3@tmail.com", "Sylvester Stalone", "LA", "M", "Boxercise");
        terryCrews = new Trainer("test4@tmail.com", "Terry Crews", "LA", "M", "Powerlifting");
        rhondaRousey = new Trainer("test5@tmail.com", "Rhonda Rousey", "Vegas", "F", "Cardio");

        assessment1 = new Assessment(16, 26, 36, 46, 56, 66, "Cool", slyStalone);
        assessment2 = new Assessment(17, 27, 37, 47, 57, 67, "Not Bad", terryCrews);
        assessment3 = new Assessment(18, 28, 38, 48, 58, 68, "Nice", rhondaRousey);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getWeight() throws Exception {
        assertEquals(16, assessment1.getWeight(), 0);
        assertEquals(17, assessment1.getWeight(), 0);
        assertEquals(18, assessment1.getWeight(), 0);
    }

    @Test
    public void getChest() throws Exception {
        assertEquals(16, assessment1.getWeight(), 0);
        assertEquals(17, assessment1.getWeight(), 0);
        assertEquals(18, assessment1.getWeight(), 0);
    }

    @Test
    public void getThigh() throws Exception {
    }

    @Test
    public void getUpperArm() throws Exception {
    }

    @Test
    public void getWaist() throws Exception {
    }

    @Test
    public void getHips() throws Exception {
    }

    @Test
    public void getComment() throws Exception {
    }

    @Test
    public void getTrainer() throws Exception {
    }

    @Test
    public void setWeight() throws Exception {
    }

    @Test
    public void setChest() throws Exception {
    }

    @Test
    public void setThigh() throws Exception {
    }

    @Test
    public void setUpperArm() throws Exception {
    }

    @Test
    public void setWaist() throws Exception {
    }

    @Test
    public void setHips() throws Exception {
    }

    @Test
    public void setComment() throws Exception {
    }

    @Test
    public void setTrainer() throws Exception {
    }

    @Test
    public void toString() throws Exception {
        assertEquals("DVD Title is: The Hobbit(Director)", assessment1.toString());
        assertEquals("DVD Title is: The Steve Jobs Film", assessment2.toString());
        assertEquals("DVD Title is: Avatar: Directors Cu", assessment3.toString());
    }

}