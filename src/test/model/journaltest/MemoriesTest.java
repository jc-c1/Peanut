package model.journaltest;
import model.journal.*;
//import model.tag.Tag;
import model.user.*;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

// represents tests for Memory
class MemoriesTest {
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
    public void testAddTitleGetTitle() {
        assertTrue(memory.getTitle().isEmpty());
        memory.addTitle("test title");
        assertEquals("test title", memory.getTitle());
    }

    @Test
    public void testAddContentGetContent() {
        assertTrue(memory.getContent().isEmpty());
        memory.addContent("test content");
        assertEquals("test content", memory.getContent());
    }

    @Test
    public void testAddImageGetImage() {
        assertEquals(null, memory.getImage());
        memory.addImage("test image");
        assertEquals("test image", memory.getImage());
    }

    @Test
    public void testAddMemoryDateGetMemoryDate() {
        LocalDateTime m = LocalDateTime.now();
        assertEquals(null, memory.getMemoryDate());
        memory.addMemoryDate(m);
        assertEquals(m, memory.getMemoryDate());
    }

    @Test
    public void testAddHeightGetHeight() {
        assertEquals(null, memory.getHeight());
        memory.addHeight(30);
        assertEquals(30, memory.getHeight());
        memory.addHeight(38);
        assertEquals(38, memory.getHeight());
    }

    @Test
    public void testAddWeightGetWeight() {
        assertEquals(null, memory.getWeight());
        memory.addWeight(30);
        assertEquals(30, memory.getWeight());
        memory.addWeight(38);
        assertEquals(38, memory.getWeight());
    }


    @Test
    public void testDeleteTitle() {
        assertTrue(memory.getTitle().isEmpty());
        assertEquals("",memory.getTitle());
        memory.addTitle("test title");
        assertEquals("test title", memory.getTitle());
        memory.deleteTitle();
        assertEquals(null, memory.getTitle());
        memory.addTitle("test title");
        assertEquals("test title", memory.getTitle());
    }

    @Test
    public void testDeleteContent() {
        assertTrue(memory.getContent().isEmpty());
        assertEquals("",memory.getContent());
        memory.addContent("test content");
        assertEquals("test content", memory.getContent());
        memory.deleteContent();
        assertEquals(null, memory.getContent());
        memory.addContent("test content");
        assertEquals("test content", memory.getContent());
    }

    @Test
    public void testDeleteImage() {
        assertEquals(null, memory.getImage());
        memory.addImage("test image");
        assertEquals("test image", memory.getImage());
        memory.deleteImage();
        assertEquals(null, memory.getImage());
        memory.addImage("test image");
        assertEquals("test image", memory.getImage());
    }

    @Test
    public void testDeleteMemoryDate() {
        LocalDateTime m = LocalDateTime.now();
        assertEquals(null, memory.getMemoryDate());
        memory.addMemoryDate(m);
        assertEquals(m, memory.getMemoryDate());
        memory.deleteMemoryDate();
        assertEquals(null, memory.getMemoryDate());
        memory.addMemoryDate(m);
        assertEquals(m, memory.getMemoryDate());
    }


//    @Test
//    public void testAddTagGetTags() {
//        assertTrue(memory.getTags().isEmpty());
//        memory.addTag(tag);
//        assertEquals(tag, memory.getTags().get(0));
//    }



    @Test
    public void testGetEntryDate() {
        LocalDateTime m = LocalDateTime.now();
        // checking if the entry date of memory (which is "now" of the creation of object), is < 1 second
        // the "now" of new LocalDateTime object m created;
        assertTrue((memory.getEntryDate().isAfter(m.minusSeconds(1))
                && (memory.getEntryDate().isBefore(m.plusSeconds(1)))));
        assertFalse((memory.getEntryDate().isAfter(m.plusSeconds(10))));

    }

    @Test
    public void testGetPeanut() {
        assertEquals(peanut, memory.getPeanut());
    }

    @Test
    public void testAddAuthorGetAuthors() {
        assertEquals(parent, memory.getAuthors().get(0));
        assertEquals(1, memory.getAuthors().size());
        memory.addAuthor(partner);
        assertEquals(2, memory.getAuthors().size());
        assertEquals(partner, memory.getAuthors().get(1));

    }


    @Test
    public void testDeleteAuthor() {
        assertEquals(parent, memory.getAuthors().get(0));
        assertEquals(1, memory.getAuthors().size());
        memory.addAuthor(partner);
        assertEquals(2, memory.getAuthors().size());
        assertEquals(partner, memory.getAuthors().get(1));
        memory.deleteAuthor(partner);
        assertEquals(parent, memory.getAuthors().get(0));
        assertEquals(1, memory.getAuthors().size());
    }
    
    @Test
    public void testDeleteHeight() {
        assertEquals(null, memory.getHeight());
        memory.addHeight(30);
        assertEquals(30, memory.getHeight());
        memory.addHeight(38);
        assertEquals(38, memory.getHeight());
        memory.deleteHeight();
        assertEquals(null, memory.getHeight());
    }

    @Test
    public void testDeleteWeight() {
        assertEquals(null, memory.getWeight());
        memory.addWeight(30);
        assertEquals(30, memory.getWeight());
        memory.addWeight(38);
        assertEquals(38, memory.getWeight());
        memory.deleteWeight();
        assertEquals(null, memory.getWeight());
    }


}