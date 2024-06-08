package ui.menu;

import ui.AppUI;
import ui.button.*;

import javax.swing.*;
import java.awt.*;

// Represents a JPanel component that displays the Peanut Menu
public class PeanutMenu extends JPanel {
    private AppUI ui;
    private AddMemory addMemory;
    private Save save;
    private Return rtn;



    // MODIFIES: ui
    // EFFECT: display peanut menu buttons
    public PeanutMenu(AppUI ui) {
        this.ui = ui;
        addMemory = new AddMemory(ui, "Add a Memory");
        save = new Save(this.ui, "Save");
        rtn = new Return(this.ui, "Return");
        add(addMemory);
        add(save);
        add(rtn);

        setMaximumSize(new Dimension(1000, 100));
    }
}
