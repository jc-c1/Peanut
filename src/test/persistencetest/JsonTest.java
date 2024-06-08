package persistencetest;


import model.journal.Entry;
import model.user.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// represents class extendable for Json Reader and Writer for commonly checked tests
public class JsonTest {
    protected void checkName(String fn, String ln, User p) {
        assertEquals(fn, p.getFirstName());
        assertEquals(ln, p.getLastName());
    }
    protected void checkEntry(String title, String content, String img, Entry e) {
        assertEquals(title, e.getTitle());
        assertEquals(content, e.getContent());
        assertEquals(img, e.getImage());
    }


}
