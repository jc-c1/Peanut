package model.journaltest;
import model.journal.*;
//import model.tag.Tag;
import model.user.*;
import org.junit.jupiter.api.*;

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;


// represents tests for Journal
class JournalTest {
    private Journal journal;
    private Peanut peanut;
    private Parent parent;
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
        journal = new Journal(peanut, parent);
        sampleQuestions1 = new ArrayList<MilestoneQuestion>();
        milestone = new Milestone(6, "a", parent, peanut, sampleQuestions1);
        milestone.completedStatus();
        memory = new Memory(peanut, parent);
//        tag = new Tag(parent);

    }


    @Test
    public void testGetPeanut() {
        assertEquals(peanut, journal.getPeanut());
    }

    @Test
    public void testGetMainParent() {
        assertEquals(parent, journal.getMainParent());
    }

    @Test
    public void testUpdateMainParent() {
        assertEquals(parent, journal.getMainParent());
        journal.updateMainParent(partner);
        assertEquals(partner, journal.getMainParent());
    }

    @Test
    public void testGetParents() {
        assertEquals(parent, journal.getParents().get(0));
    }

    @Test
    public void testAddParent() {
        assertFalse(journal.getParents().contains(partner));
        journal.addParent(partner);
        assertTrue(journal.getParents().contains(partner));
    }

    @Test
    public void testRemoveParent() {
        assertTrue(journal.getParents().contains(parent));
        assertFalse(journal.getParents().contains(partner));
        journal.addParent(partner);
        assertTrue(journal.getParents().contains(parent));
        assertTrue(journal.getParents().contains(partner));
        journal.removeParent(partner);
        assertTrue(journal.getParents().contains(parent));
        assertFalse(journal.getParents().contains(partner));

    }

    @Test
    public void testGetIndex() {
        assertTrue(journal.getIndex().isEmpty());
        journal.addEntry(milestone);
        assertEquals(milestone, journal.getIndex().get(0));
        assertFalse(journal.getIndex().contains(memory));
        journal.addEntry(memory);
        assertTrue(journal.getIndex().contains(memory));
    }

    @Test
    public void testGetEntryByIndex() {
        assertEquals(null, journal.getEntryByIndex(-1));
        assertEquals(null, journal.getEntryByIndex(0));
        assertEquals(null, journal.getEntryByIndex(1));
        journal.addEntry(milestone);
        assertEquals(milestone, journal.getEntryByIndex(1));
        assertEquals(null, journal.getEntryByIndex(7));
        journal.addEntry(memory);
        assertEquals(memory, journal.getEntryByIndex(2));
    }

    @Test
    public void testAddEntry() {
        assertFalse(journal.getIndex().contains(milestone));

        journal.addEntry(milestone);
        assertTrue(journal.getIndex().contains(milestone));

        assertFalse(journal.getIndex().contains(memory));
        journal.addEntry(memory);
        assertTrue(journal.getIndex().contains(memory));
    }

    @Test
    public void testDeleteEntry() {
        journal.addEntry(milestone);
        journal.addEntry(memory);
        assertTrue(milestone.getStatus());
        assertTrue(journal.getIndex().contains(milestone));
        assertTrue(journal.getIndex().contains(memory));
        journal.removeEntry(milestone);
        assertFalse(journal.getIndex().contains(milestone));
        assertFalse(milestone.getStatus());
        journal.removeEntry(memory);
        assertFalse(journal.getIndex().contains(memory));
    }

    @Test
    public void testGetAllMilestones() {
        assertEquals(5, journal.getAllMilestones().size());
        assertEquals(journal.getAllMilestones().get(0), journal.getMilestoneByIndex(1));
        assertEquals(journal.getAllMilestones().get(1), journal.getMilestoneByIndex(2));
        assertEquals(journal.getAllMilestones().get(2), journal.getMilestoneByIndex(3));
        assertEquals(journal.getAllMilestones().get(3), journal.getMilestoneByIndex(4));
        assertEquals(journal.getAllMilestones().get(4), journal.getMilestoneByIndex(5));
        assertEquals(null, journal.getMilestoneByIndex(0));
        assertEquals(null, journal.getMilestoneByIndex(6));
    }

    @Test
    public void testGetIncompleteMilestones() {
        assertEquals(5, journal.getIncompleteMilestones().size());
        journal.getMilestoneByIndex(1).completedStatus();
        assertEquals(4, journal.getIncompleteMilestones().size());
        journal.getMilestoneByIndex(1).incompleteStatus();
        assertEquals(5, journal.getIncompleteMilestones().size());
    }
}