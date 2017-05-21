package controllers;

import models.Assessment;
import models.Member;
import models.PremiumMember;
import models.StudentMember;
import models.Trainer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by niall on 21/05/17.
 */
public class GymApiTest {

    private StudentMember studentMember1, studentMember2, studentMember3;
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

    private GymApi gymApi;
    @Before
    public void setUp() throws Exception {
        gymApi = new GymApi();

        //30 characters long, "m" should become M
        studentMember1 = new StudentMember("junittest1@tmail.com", "Wolverine with Mutant Friendss", "New York", "m", 3, 250, "STUDENT", 999999, "WIT");
        //31 characters long, maleZ should = unspecified, height and weight = 0, "NOTSTUDENT" = ""
        studentMember2 = new StudentMember("junittest2@tmail.com", "Grog", "Castle Whitestone", "male", 3, 250, "STUDENT", 999999, "Any College");
         //30 characters long, "m" should become M
        premMember1 = new PremiumMember("junittest4@tmail.com", "Deadpool 'Merc with the Mouth'", "New York", "m", 3, 250, "PREMIUM");
        //31 characters long, maleZ should = unspecified, height and weight = 0, "NOTPREMUIM" = ""
        premMember2 = new PremiumMember("junittest5@tmail.com", "Percy", "Castle Whitestone", "male", 3, 250, "PREMIUM");

        slyStalone = new Trainer("junittest7@tmail.com", "Sylvester Stalone", "LA", "M", "Boxercise");
        terryCrews = new Trainer("junittest8@tmail.com", "Terry Crews", "LA", "male", "Powerlifting");

        assessment1 = new Assessment(11, 21, 31, 41, 51, 61, "Assess1", slyStalone);
        assessment2 = new Assessment(12, 22, 32, 42, 52, 62, "Assess2", slyStalone);

        assessment3 = new Assessment(13, 23, 33, 43, 53, 63, "Assess3", terryCrews);
        assessment4 = new Assessment(183, 24, 34, 44, 54, 64, "Assess4", terryCrews);

        premMember1.getAssessments().put(dateFormat.parse(date1), assessment1);
        premMember1.getAssessments().put(dateFormat.parse(date2), assessment2);
        premMember2.getAssessments().put(dateFormat.parse(date1), assessment1);
        premMember2.getAssessments().put(dateFormat.parse(date2), assessment2);

        studentMember1.getAssessments().put(dateFormat.parse(date3), assessment3);
        studentMember1.getAssessments().put(dateFormat.parse(date4), assessment4);
        studentMember2.getAssessments().put(dateFormat.parse(date3), assessment3);
        studentMember2.getAssessments().put(dateFormat.parse(date4), assessment4);

        gymApi.addMember(studentMember1);
        gymApi.addMember(studentMember2);
        gymApi.addMember(premMember1);
        gymApi.addMember(premMember2);

        gymApi.addTrainer(slyStalone);
        gymApi.addTrainer(terryCrews);
        gymApi.addTrainer(rhondaRousey);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testConstructor(){
        assertEquals(false, gymApi.members.isEmpty());
        assertEquals(false, gymApi.trainers.isEmpty());
    }

    @Test
    public void addMember() throws Exception {
        int beforeMemberAdd = gymApi.numberOfMembers();
        int afterMemberAdd;

        PremiumMember memberAdd = new PremiumMember("add@tmail.com", "ADD ME PLEASE", "New York", "m", 3, 250, "PREMIUM");
        gymApi.addMember(memberAdd);
        afterMemberAdd = gymApi.numberOfMembers();

        assertEquals(afterMemberAdd, beforeMemberAdd + 1, 0);
    }

    @Test
    public void removeMember() throws Exception {
        int beforeMemberRem = gymApi.numberOfMembers();
        int afterMemberRem;

        gymApi.removeMember(premMember2);
        afterMemberRem = gymApi.numberOfMembers();

        assertEquals(afterMemberRem, beforeMemberRem - 1, 0);
    }

    @Test
    public void addTrainer() throws Exception {
        int beforeTrainerAdd = gymApi.numberOfTrainers();
        int afterTrainerAdd;

        Trainer trainerAdd = new Trainer("add@tmail.com", "ADD ME PLEASE", "New York", "m", "Testing");
        gymApi.addTrainer(trainerAdd);
        afterTrainerAdd = gymApi.numberOfTrainers();

        assertEquals(afterTrainerAdd, beforeTrainerAdd + 1, 0);
    }

    @Test
    public void numberOfMembers() throws Exception {
        assertEquals(gymApi.numberOfMembers(), gymApi.getMembers().size(), 0);

        PremiumMember memberAdd = new PremiumMember("add@tmail.com", "ADD ME PLEASE", "New York", "m", 3, 250, "PREMIUM");
        gymApi.addMember(memberAdd);
        assertEquals(gymApi.numberOfMembers(), gymApi.getMembers().size(), 0);

        gymApi.removeMember(memberAdd);
        assertEquals(gymApi.numberOfMembers(), gymApi.getMembers().size(), 0);
    }

    @Test
    public void numberOfTrainers() throws Exception {

        assertEquals(gymApi.numberOfTrainers(), gymApi.getTrainers().size(), 0);

        Trainer trainerAdd = new Trainer("add@tmail.com", "ADD ME PLEASE", "New York", "m", "Testing");
        gymApi.addTrainer(trainerAdd);
        assertEquals(gymApi.numberOfTrainers(), gymApi.getTrainers().size(), 0);
    }

    @Test
    public void getMembers() throws Exception {
        ArrayList<Member> membersTest = new ArrayList<>();
        membersTest.addAll(gymApi.members);

        assertEquals(membersTest.size(), gymApi.numberOfMembers());
    }

    @Test
    public void getTrainers() throws Exception {
        ArrayList<Trainer> trainersTest = new ArrayList<>();
        trainersTest.addAll(gymApi.trainers);

        assertEquals(trainersTest.size(), gymApi.numberOfTrainers());
    }

    @Test
    public void isValidMemberIndex() throws Exception {

        for(int i = 0 ; i < gymApi.members.size() ; i++){
            assertEquals(true, gymApi.isValidMemberIndex(i));
        }

        assertEquals(false, gymApi.isValidTrainerIndex(gymApi.members.size() + 1));
        assertEquals(false, gymApi.isValidTrainerIndex(gymApi.members.size() - gymApi.members.size() - 1));
    }

    @Test
    public void isValidTrainerIndex() throws Exception {

        for(int i = 0 ; i < gymApi.trainers.size() ; i++){
            assertEquals(true, gymApi.isValidTrainerIndex(i));
        }

        assertEquals(false, gymApi.isValidTrainerIndex(gymApi.trainers.size() + 1));
        assertEquals(false, gymApi.isValidTrainerIndex(gymApi.trainers.size() - gymApi.trainers.size() - 1));
    }

    @Test
    public void searchMembersByEmail() throws Exception {

        assertEquals(studentMember1, gymApi.searchMembersByEmail("junittest1@tmail.com"));
        assertEquals(premMember1, gymApi.searchMembersByEmail("junittest4@tmail.com"));

        assertEquals(null, gymApi.searchMembersByEmail("junittest4@tmail.com1234"));
    }

    @Test
    public void searchMembersByName() throws Exception {

        gymApi.members.clear();
        assertEquals("0 members in this gym bro!", gymApi.searchMembersByName("Wolverine with Mutant Friendss"));

        gymApi.members.add(studentMember1);
        gymApi.members.add(premMember1);

        assertEquals("1 Results found for: Wolverine with Mutant Friendss\n" +
                "\n" + "0 Wolverine with Mutant Friendss\n", gymApi.searchMembersByName("Wolverine with Mutant Friendss"));

        assertEquals("1 Results found for: Deadpool 'Merc with the Mouth'\n" +
                "\n" + "1 Deadpool 'Merc with the Mouth'\n", gymApi.searchMembersByName("Deadpool 'Merc with the Mouth'"));

        assertEquals("0 Results found for: Nothing at all", gymApi.searchMembersByName("Nothing at all"));
    }

    @Test
    public void searchTrainersByEmail() throws Exception {

        assertEquals(slyStalone, gymApi.searchTrainersByEmail("junittest7@tmail.com"));

        assertEquals(null, gymApi.searchMembersByEmail("junittest4@tmail.com1234"));
    }

    @Test
    public void listMembers() throws Exception {

        gymApi.members.clear();
        assertEquals("No members in this gym bro!", gymApi.listMembers());
        gymApi.members.add(studentMember1);
        gymApi.members.add(premMember1);


        assertEquals(studentMember1.toString() + "\n+----------------------+\n" +
                premMember1.toString() + "\n+----------------------+\n", gymApi.listMembers());
    }

    @Test
    public void listMemberDetailsImperialAndMetric() throws Exception {

        gymApi.members.clear();
        assertEquals("No members in this gym bro!", gymApi.listMemberDetailsImperialAndMetric());
        gymApi.members.add(studentMember2);
        gymApi.members.add(premMember2);

        assertEquals("Grog:\t250.0 kg (550.0 lbs) \t3.0 metres (118.1 inches).\n" +
                "Percy:\t250.0 kg (550.0 lbs) \t3.0 metres (118.1 inches).\n", gymApi.listMemberDetailsImperialAndMetric());
    }

    @Test
    public void listMembersWithIdealWeight() throws Exception {

        gymApi.members.clear();
        assertEquals("No members in this gym bro!", gymApi.listMembersWithIdealWeight());

        gymApi.members.add(premMember2);
        assertEquals("There are no members in the gym with an ideal weight", gymApi.listMembersWithIdealWeight());

        gymApi.members.add(studentMember2);
        assertEquals(studentMember2.toString() + "\n", gymApi.listMembersWithIdealWeight());
    }

    @Test
    public void listBySpecificBMICategory() throws Exception {

        gymApi.members.clear();
        assertEquals("No members in this gym bro!", gymApi.listBySpecificBMICategory("NORMAL"));
        assertEquals("No members in this gym bro!", gymApi.listBySpecificBMICategory("normal"));

        gymApi.members.add(premMember2);
        assertEquals("No one by that category!: NORMAL", gymApi.listBySpecificBMICategory("NORMAL"));
        assertEquals("No one by that category!: NORMAL", gymApi.listBySpecificBMICategory("normal"));

        assertEquals(premMember2.toString() + "\n", gymApi.listBySpecificBMICategory("UNDERWEIGHT"));
        assertEquals(premMember2.toString() + "\n", gymApi.listBySpecificBMICategory("underweight"));
    }
}