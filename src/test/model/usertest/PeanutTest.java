package model.usertest;

import model.journal.*;
//import model.tag.Tag;
import model.user.*;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// represents tests for Peanut; tests for User abstract interface indirectly
class PeanutTest {
    private Journal journal;
    private Peanut peanut;
    private Peanut weenut;
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
        weenut = new Peanut("Wee", "Nut", parent);
        journal = new Journal(peanut, parent);
        sampleQuestions1 = new ArrayList<MilestoneQuestion>();
        milestone = new Milestone(6, "a", parent, peanut, sampleQuestions1);
        milestone.completedStatus();
        memory = new Memory(peanut, parent);
//        tag = new Tag(parent);

    }


    @Test
    public void testAddSiblingsOfPeanutGetSiblingsOfPeanut() {
        assertTrue(peanut.getSiblingsOfPeanut().isEmpty());
        peanut.addSiblingOfPeanut(weenut);
        assertEquals(weenut, peanut.getSiblingsOfPeanut().get(0));
        assertEquals(1, peanut.getSiblingsOfPeanut().size());
    }


    @Test
    public void testRemoveSiblingsOfPeanut() {
        assertTrue(peanut.getSiblingsOfPeanut().isEmpty());
        peanut.addSiblingOfPeanut(weenut);
        assertEquals(weenut, peanut.getSiblingsOfPeanut().get(0));
        assertEquals(1, peanut.getSiblingsOfPeanut().size());
        peanut.removeSiblingOfPeanut(weenut);
        assertTrue(peanut.getSiblingsOfPeanut().isEmpty());
    }


    @Test
    public void testAddParentsOfPeanutGetParentsOfPeanut() {
        assertFalse(peanut.getParentsOfPeanut().isEmpty());
        assertEquals(1, peanut.getParentsOfPeanut().size());
        assertEquals(parent, peanut.getParentsOfPeanut().get(0));
        peanut.addParentOfPeanut(partner);
        assertEquals(partner, peanut.getParentsOfPeanut().get(1));
        assertEquals(2, peanut.getParentsOfPeanut().size());
    }

    @Test
    public void testRemoveParentsOfPeanut() {
        assertFalse(peanut.getParentsOfPeanut().isEmpty());
        assertEquals(1, peanut.getParentsOfPeanut().size());
        assertEquals(parent, peanut.getParentsOfPeanut().get(0));
        peanut.addParentOfPeanut(partner);
        assertEquals(partner, peanut.getParentsOfPeanut().get(1));
        assertEquals(2, peanut.getParentsOfPeanut().size());
        peanut.removeParentOfPeanut(partner);
        assertFalse(peanut.getParentsOfPeanut().isEmpty());
        assertEquals(1, peanut.getParentsOfPeanut().size());
        assertEquals(parent, peanut.getParentsOfPeanut().get(0));
    }

    @Test
    public void testUpdateFirstNameGetFirstName() {
        assertEquals("Pea", peanut.getFirstName());
        assertEquals("Wee", weenut.getFirstName());
        peanut.updateFirstName("Pina");
        weenut.updateFirstName("Mimo");
        assertEquals("Pina", peanut.getFirstName());
        assertEquals("Mimo", weenut.getFirstName());
    }

    @Test
    public void testUpdateLastNameGetLastName() {
        assertEquals("Nut", peanut.getLastName());
        assertEquals("Nut", weenut.getLastName());
        peanut.updateLastName("Colada");
        weenut.updateLastName("Sa");
        assertEquals("Colada", peanut.getLastName());
        assertEquals("Sa", weenut.getLastName());
    }

    @Test
    public void testAddDateOfBirthGetDateOfBirth() {
        LocalDateTime peanutDOB = LocalDateTime.of(2022, Month.JANUARY, 1, 0, 0, 0);
        LocalDateTime weenutDOB = LocalDateTime.of(2023, Month.JUNE, 30, 0, 0, 0);
        assertEquals(null, peanut.getDateOfBirth());
        peanut.addDateOfBirth(peanutDOB);
        assertEquals(peanutDOB, peanut.getDateOfBirth());
        assertEquals(null, weenut.getDateOfBirth());
        weenut.addDateOfBirth(weenutDOB);
        assertEquals(weenutDOB, weenut.getDateOfBirth());
    }

    @Test
    public void testGetJournal() {
        assertEquals(parent, peanut.getJournal().getParents().get(0));
        assertEquals(peanut, peanut.getJournal().getPeanut());
        assertTrue(peanut.getJournal().getIndex().isEmpty());
    }

}