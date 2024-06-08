package model.usertest;

import model.user.*;
import org.junit.jupiter.api.*;


import static org.junit.jupiter.api.Assertions.*;

// represents tests for ParentList
public class ParentListTest {
    private Parent parent;
    private Parent partner;
    private ParentList parentList;

    @BeforeEach
    public void runBefore() {
        parentList = new ParentList();
        parent = new Parent("Wallace", "Nut");
        partner = new Parent("Hazel", "Nut");
    }


    @Test
    public void testGetParents() {
        assertTrue(parentList.getParents().isEmpty());
    }

    @Test
    public void testAddParent() {
        assertTrue(parentList.getParents().isEmpty());
        parentList.addParent(parent);
        assertTrue(parentList.getParents().size()==1);
        assertTrue(parentList.getParents().get(0).equals(parent));
    }

    @Test
    public void testGetParentByIndex() {
        assertEquals(null, parentList.getParentByIndex(-1));
        assertEquals(null, parentList.getParentByIndex(0));
        parentList.addParent(parent);
        assertEquals(parent, parentList.getParentByIndex(1));
        assertEquals(null, parentList.getParentByIndex(7));
        parentList.addParent(partner);
        assertEquals(partner, parentList.getParentByIndex(2));
    }

}
