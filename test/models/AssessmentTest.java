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

        assessment1 = new Assessment(17, 27, 37, 47, 57, 67, "Cool", slyStalone);
        assessment2 = new Assessment(18, 28, 38, 48, 58, 68, "Not Bad", terryCrews);
        assessment3 = new Assessment(19, 29, 39, 49, 59, 69, "Nice", rhondaRousey);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getWeight() throws Exception {
        assertEquals(17, assessment1.getWeight(), 0);
        assertEquals(18, assessment2.getWeight(), 0);
        assertEquals(19, assessment3.getWeight(), 0);
    }

    @Test
    public void getChest() throws Exception {
        assertEquals(27, assessment1.getChest(), 0);
        assertEquals(28, assessment2.getChest(), 0);
        assertEquals(29, assessment3.getChest(), 0);
    }

    @Test
    public void getThigh() throws Exception {
        assertEquals(37, assessment1.getThigh(), 0);
        assertEquals(38, assessment2.getThigh(), 0);
        assertEquals(39, assessment3.getThigh(), 0);
    }

    @Test
    public void getUpperArm() throws Exception {
        assertEquals(47, assessment1.getUpperArm(), 0);
        assertEquals(48, assessment2.getUpperArm(), 0);
        assertEquals(49, assessment3.getUpperArm(), 0);
    }

    @Test
    public void getWaist() throws Exception {
        assertEquals(57, assessment1.getWaist(), 0);
        assertEquals(58, assessment2.getWaist(), 0);
        assertEquals(59, assessment3.getWaist(), 0);
    }

    @Test
    public void getHips() throws Exception {
        assertEquals(67, assessment1.getHips(), 0);
        assertEquals(68, assessment2.getHips(), 0);
        assertEquals(69, assessment3.getHips(), 0);
    }

    @Test
    public void getComment() throws Exception {
        assertEquals("Cool", assessment1.getComment());
        assertEquals("Not Bad", assessment2.getComment());
        assertEquals("Nice", assessment3.getComment());
    }

    @Test
    public void getTrainer() throws Exception {
        assertEquals(slyStalone, assessment1.getTrainer());
        assertEquals(terryCrews, assessment2.getTrainer());
        assertEquals(rhondaRousey, assessment3.getTrainer());
    }

    @Test
    public void setWeight() throws Exception {
        assessment1.setWeight(11);
        assertEquals(11, assessment1.getWeight(), 0);
        assessment2.setWeight(12);
        assertEquals(12, assessment2.getWeight(), 0);
        assessment3.setWeight(13);
        assertEquals(13, assessment3.getWeight(), 0);
    }

    @Test
    public void setChest() throws Exception {
        assessment1.setChest(21);
        assertEquals(21, assessment1.getChest(), 0);
        assessment2.setChest(22);
        assertEquals(22, assessment2.getChest(), 0);
        assessment3.setChest(23);
        assertEquals(23, assessment3.getChest(), 0);
    }

    @Test
    public void setThigh() throws Exception {
        assessment1.setThigh(31);
        assertEquals(31, assessment1.getThigh(), 0);
        assessment2.setThigh(32);
        assertEquals(32, assessment2.getThigh(), 0);
        assessment3.setThigh(33);
        assertEquals(33, assessment3.getThigh(), 0);
    }

    @Test
    public void setUpperArm() throws Exception {
        assessment1.setUpperArm(41);
        assertEquals(41, assessment1.getUpperArm(), 0);
        assessment2.setUpperArm(42);
        assertEquals(42, assessment2.getUpperArm(), 0);
        assessment3.setUpperArm(43);
        assertEquals(43, assessment3.getUpperArm(), 0);
    }

    @Test
    public void setWaist() throws Exception {
        assessment1.setWaist(51);
        assertEquals(51, assessment1.getWaist(), 0);
        assessment2.setWaist(52);
        assertEquals(52, assessment2.getWaist(), 0);
        assessment3.setWaist(53);
        assertEquals(53, assessment3.getWaist(), 0);
    }

    @Test
    public void setHips() throws Exception {
        assessment1.setHips(61);
        assertEquals(61, assessment1.getHips(), 0);
        assessment2.setHips(62);
        assertEquals(62, assessment2.getHips(), 0);
        assessment3.setHips(63);
        assertEquals(63, assessment3.getHips(), 0);
    }

    @Test
    public void setComment() throws Exception {
        assessment1.setComment("test1");
        assertEquals("test1", assessment1.getComment());
        assessment2.setComment("test2");
        assertEquals("test2", assessment2.getComment());
        assessment3.setComment("test3");
        assertEquals("test3", assessment3.getComment());
    }

    @Test
    public void setTrainer() throws Exception {
        assessment1.setTrainer(rhondaRousey);
        assertEquals(rhondaRousey, assessment1.getTrainer());
        assessment2.setTrainer(slyStalone);
        assertEquals(slyStalone, assessment2.getTrainer());
        assessment3.setTrainer(terryCrews);
        assertEquals(terryCrews, assessment3.getTrainer());
    }

    @Test
    public void testToString() throws Exception {

        assertEquals("WEIGHT.........17.0" + "\n" +
                "CHEST..........27.0" + "\n" +
                "THIGH..........37.0" + "\n" +
                "UPPER ARM......47.0" + "\n" +
                "WAIST..........57.0" + "\n" +
                "HIPS...........67.0" + "\n" +
                "COMMENT........Cool" + "\n" +
                "TRAINER........Sylvester Stalone\n", assessment1.toString());

        assertEquals("WEIGHT.........18.0" + "\n" +
                "CHEST..........28.0" + "\n" +
                "THIGH..........38.0" + "\n" +
                "UPPER ARM......48.0" + "\n" +
                "WAIST..........58.0" + "\n" +
                "HIPS...........68.0" + "\n" +
                "COMMENT........Not Bad" + "\n" +
                "TRAINER........Terry Crews\n", assessment2.toString());

        assertEquals("WEIGHT.........19.0" + "\n" +
                "CHEST..........29.0" + "\n" +
                "THIGH..........39.0" + "\n" +
                "UPPER ARM......49.0" + "\n" +
                "WAIST..........59.0" + "\n" +
                "HIPS...........69.0" + "\n" +
                "COMMENT........Nice" + "\n" +
                "TRAINER........Rhonda Rousey\n", assessment3.toString());
    }

}