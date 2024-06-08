package ui.menu;

import ui.AppUI;
import ui.button.*;

import javax.swing.*;
import java.awt.*;

// Represents a JPanel component that displays the Memory Menu
public class MemoryMenu extends JPanel {
    private AppUI ui;
    private UpdateMemory updateMemory;
    private Save save;
    private Return rtn;
    private DeleteMemory deleteMemory;

    // MODIFIES: ui
    // EFFECT: display memory menu buttons
    public MemoryMenu(AppUI ui) {
        this.ui = ui;
        updateMemory = new UpdateMemory(this.ui, "Update a memory");
        deleteMemory = new DeleteMemory(this.ui, "Delete memory");
        save = new Save(this.ui, "Save");
        rtn = new Return(this.ui, "Return");
        add(updateMemory);
        add(deleteMemory);
        add(save);
        add(rtn);
        setMaximumSize(new Dimension(1000, 100));
    }
}
