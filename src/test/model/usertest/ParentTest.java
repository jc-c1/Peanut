package model.usertest;

import model.journal.*;
//import model.tag.Tag;
import model.user.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// represents tests for Parent; tests for User abstract interface indirectly
public class ParentTest {
    private Journal journal;
    private Peanut peanut;
    private Parent parent;
    private Peanut weenut;
    private Parent partner;
    private Milestone milestone;
    private Memory memory;
    private List<MilestoneQuestion> sampleQuestions1;
//    private Tag tag;

    @BeforeEach
    public void runBefore() {
        parent = new Parent("Wallace", "Nut");
        partner = new Parent("Hazel", "Nut");
        peanut = new Peanut("Pea", "Nut", parent);
        weenut = new Peanut("Wee", "Nut", parent);
        journal = new Journal(peanut, parent);
        sampleQuestions1 = new ArrayList<MilestoneQuestion>();
        milestone = new Milestone(6, "a", parent, peanut, sampleQuestions1);
        milestone.completedStatus();
        memory = new Memory(peanut, parent);
//        tag = new Tag(parent);
    }


    @Test
    public void testAddPeanutsOfParentGetPeanutsOfParent() {
        assertFalse(parent.getPeanutsOfParent().isEmpty());
        parent.addPeanutsOfParent(peanut);
        assertEquals(peanut, parent.getPeanutsOfParent().get(0));
        assertEquals(3, parent.getPeanutsOfParent().size());
    }


    @Test
    public void testRemovePeanutsOfParent() {
        assertFalse(parent.getPeanutsOfParent().isEmpty());
        assertEquals(peanut, parent.getPeanutsOfParent().get(0));
        assertEquals(weenut, parent.getPeanutsOfParent().get(1));
        assertEquals(2, parent.getPeanutsOfParent().size());
        parent.removePeanutsOfParent(weenut);
        parent.removePeanutsOfParent(peanut);
        assertTrue(parent.getPeanutsOfParent().isEmpty());
    }

    @Test
    public void testAddPartnersOfParentGetPartnersOfParent() {
        assertTrue(parent.getPartnersOfParent().isEmpty());
        parent.addPartnersOfParent(partner);
        assertEquals(partner, parent.getPartnersOfParent().get(0));
        assertEquals(1, parent.getPartnersOfParent().size());
    }

    @Test
    public void testRemovePartnersOfParent() {
        assertTrue(parent.getPartnersOfParent().isEmpty());
        parent.addPartnersOfParent(partner);
        assertEquals(partner, parent.getPartnersOfParent().get(0));
        assertEquals(1, parent.getPartnersOfParent().size());
        parent.removePartnersOfParent(partner);
        assertTrue(parent.getPartnersOfParent().isEmpty());
    }

    @Test
    public void testGetPeanutByIndex() {
        assertEquals(null, parent.getPeanutByIndex(-1));
        assertEquals(null, parent.getPeanutByIndex(0));
        assertEquals(peanut, parent.getPeanutByIndex(1));
        assertEquals(null, parent.getPeanutByIndex(7));
        parent.addPeanutsOfParent(weenut);
        assertEquals(weenut, parent.getPeanutByIndex(2));
    }

}
