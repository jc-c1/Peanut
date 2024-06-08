package persistencetest;

import model.journal.Entry;
import model.user.*;
import persistence.JsonReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// represents test for the JSON reader
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ParentList pl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyParentList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyParentList.json");
        try {
            ParentList pl = reader.read();
            assertEquals(0, pl.getParents().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralParentList() {
        try {
            ParentList pl;
            JsonReader reader = new JsonReader("./data/testReaderGeneralParentList.json");
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
            checkEntry("title2", "content2", "", mil1);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


}