package persistencetest;

import model.journal.Entry;
import model.journal.Memory;
import model.journal.Milestone;
import model.journal.MilestoneQuestion;
import model.user.*;
import persistence.JsonReader;
import persistence.JsonWriter;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// represents test for the JSON writer
class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            ParentList pl = new ParentList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyParent() {
        try {
            ParentList pl = new ParentList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyParentList.json");
            writer.open();
            writer.write(pl);
            writer.close();
            JsonReader reader = new JsonReader("./data/testWriterEmptyParentList.json");
            pl = reader.read();
            assertEquals(0, pl.getParents().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralParent() {
        try {
            ParentList pl;
            pl = testGeneralInit();
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralParentList.json");
            writer.open();
            writer.write(pl);
            writer.close();
            JsonReader reader = new JsonReader("./data/testWriterGeneralParentList.json");
            pl = reader.read();
            List<Parent> parents = pl.getParents();
            assertEquals(3, parents.size());
            Parent pr1 = pl.getParents().get(0);
            checkName("fn1", "ln1", pr1);
            checkName("fn2", "ln2", parents.get(1));
            List<Peanut> peanuts = pr1.getPeanutsOfParent();
            assertEquals(3, peanuts.size());
            Peanut pn1 = peanuts.get(0);
            checkName("fn11", "ln11", pn1);
            checkName("fn12", "ln12", peanuts.get(1));
            checkName("fn13", "ln13", peanuts.get(2));

            Entry mem1 = pn1.getJournal().getIndex().get(0);
            checkEntry("title1", "content1", "", mem1);
            Entry mil1 = pn1.getJournal().getIndex().get(1);
            System.out.println(mil1);
            checkEntry("title2", "content2", "", mil1);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    // EFFECTS: returns a dummy ParentList to test for non-empty general parent lists
    private ParentList testGeneralInit() {
        ParentList pl = new ParentList();
        Parent pr1 = new Parent("fn1", "ln1");
        Parent pr2 = new Parent("fn2", "ln2");
        Parent pr3 = new Parent("fn3", "ln3");
        Peanut pn11 = new Peanut("fn11", "ln11", pr1);
        Peanut pn12 = new Peanut("fn12", "ln12", pr1);
        Peanut pn13 = new Peanut("fn13", "ln13", pr1);

        Entry mem1 = new Memory(pn11, pr1) ;
        mem1.addTitle("title1");
        mem1.addContent("content1");
        mem1.addImage("");
        pn11.getJournal().addEntry(mem1);

        Entry mil1 = new Milestone(1, "title2", pr1, pn11, new ArrayList<MilestoneQuestion>());
        mil1.addContent("content2");
        mil1.addImage("");
        pn11.getJournal().addEntry(mil1);

        pl.addParent(pr1);
        pl.addParent(pr2);
        pl.addParent(pr3);

        return pl;
    }
}