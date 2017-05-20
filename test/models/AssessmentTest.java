package models;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

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
        assertEquals(17, assessment2.getWeight(), 0);
        assertEquals(18, assessment3.getWeight(), 0);
    }

    @Test
    public void getChest() throws Exception {
        assertEquals(26, assessment1.getChest(), 0);
        assertEquals(27, assessment2.getChest(), 0);
        assertEquals(28, assessment3.getChest(), 0);
    }

    @Test
    public void getThigh() throws Exception {
        assertEquals(36, assessment1.getThigh(), 0);
        assertEquals(37, assessment2.getThigh(), 0);
        assertEquals(38, assessment3.getThigh(), 0);
    }

    @Test
    public void getUpperArm() throws Exception {
        assertEquals(46, assessment1.getUpperArm(), 0);
        assertEquals(47, assessment2.getUpperArm(), 0);
        assertEquals(48, assessment3.getUpperArm(), 0);
    }

    @Test
    public void getWaist() throws Exception {
        assertEquals(56, assessment1.getWaist(), 0);
        assertEquals(57, assessment2.getWaist(), 0);
        assertEquals(58, assessment3.getWaist(), 0);
    }

    @Test
    public void getHips() throws Exception {
        assertEquals(66, assessment1.getHips(), 0);
        assertEquals(67, assessment2.getHips(), 0);
        assertEquals(68, assessment3.getHips(), 0);
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
    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(Assessment.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

}
