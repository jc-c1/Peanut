package model.journaltest;

import model.journal.*;
//import model.tag.Tag;
import model.user.*;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

// represents tests for Milestone
public class MilestoneTest {
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
    public void testGetTitle() {
        assertEquals("a", milestone.getTitle());
    }

    @Test
    public void testAddCommentGetComment() {
        assertEquals(null, milestone.getContent());
        milestone.addContent("test comment");
        assertEquals("test comment", milestone.getContent());
    }

    @Test
    public void testAddImageGetImage() {
        assertEquals(null, milestone.getImage());
        milestone.addImage("test image");
        assertEquals("test image", milestone.getImage());
    }

    @Test
    public void testAddMilestoneDateGetMilestoneDate() {
        LocalDateTime m = LocalDateTime.now();
        assertEquals(null, milestone.getMilestoneDate());
        milestone.addMilestoneDate(m);
        assertEquals(m, milestone.getMilestoneDate());
    }

    @Test
    public void testAddHeightGetHeight() {
        assertEquals(null, milestone.getHeight());
        milestone.addHeight(30);
        assertEquals(30, milestone.getHeight());
        milestone.addHeight(38);
        assertEquals(38, milestone.getHeight());
    }

    @Test
    public void testAddWeightGetWeight() {
        assertEquals(null, milestone.getWeight());
        milestone.addWeight(30);
        assertEquals(30, milestone.getWeight());
        milestone.addWeight(38);
        assertEquals(38, milestone.getWeight());
    }
    

    @Test
    public void testDeleteComment() {
        assertEquals(null, milestone.getContent());
        milestone.addContent("test comment");
        assertEquals("test comment", milestone.getContent());
        milestone.deleteComment();
        assertEquals(null, milestone.getContent());
        milestone.addContent("test comment");
        assertEquals("test comment", milestone.getContent());
    }

    @Test
    public void testDeleteImage() {
        assertEquals(null, milestone.getImage());
        milestone.addImage("test image");
        assertEquals("test image", milestone.getImage());
        milestone.deleteImage();
        assertEquals(null, milestone.getImage());
        milestone.addImage("test image");
        assertEquals("test image", milestone.getImage());
    }



    
    @Test
    public void testGetID() {
        assertEquals(6, milestone.getID());
    }

    @Test
    public void testGetPeanut() {
        assertEquals(peanut, milestone.getPeanut());
    }

    @Test
    public void testAddAuthorGetAuthors() {
        assertEquals(parent, milestone.getAuthors().get(0));
        assertEquals(1, milestone.getAuthors().size());
        milestone.addAuthor(partner);
        assertEquals(2, milestone.getAuthors().size());
        assertEquals(partner, milestone.getAuthors().get(1));

    }


    @Test
    public void testDeleteAuthor() {
        assertEquals(parent, milestone.getAuthors().get(0));
        assertEquals(1, milestone.getAuthors().size());
        milestone.addAuthor(partner);
        assertEquals(2, milestone.getAuthors().size());
        assertEquals(partner, milestone.getAuthors().get(1));
        milestone.deleteAuthor(partner);
        assertEquals(parent, milestone.getAuthors().get(0));
        assertEquals(1, milestone.getAuthors().size());
    }

    @Test
    public void testDeleteMilestoneDate() {
        LocalDateTime m = LocalDateTime.now();
        assertEquals(null, milestone.getMilestoneDate());
        milestone.addMilestoneDate(m);
        assertEquals(m, milestone.getMilestoneDate());
        milestone.deleteMilestoneDate();
        assertEquals(null, milestone.getMilestoneDate());
        milestone.addMilestoneDate(m);
        assertEquals(m, milestone.getMilestoneDate());
    }

    @Test
    public void testDeleteHeight() {
        assertEquals(null, milestone.getHeight());
        milestone.addHeight(30);
        assertEquals(30, milestone.getHeight());
        milestone.addHeight(38);
        assertEquals(38, milestone.getHeight());
        milestone.deleteHeight();
        assertEquals(null, milestone.getHeight());
    }

    @Test
    public void testDeleteWeight() {
        assertEquals(null, milestone.getWeight());
        milestone.addWeight(30);
        assertEquals(30, milestone.getWeight());
        milestone.addWeight(38);
        assertEquals(38, milestone.getWeight());
        milestone.deleteWeight();
        assertEquals(null, milestone.getWeight());
    }

    // TODO: need to omit at some point
    @Test
    public void testAddTitle() {
        assertEquals("a", milestone.getTitle());
        milestone.addTitle("bbb");
        assertEquals("a", milestone.getTitle());
    }

}
