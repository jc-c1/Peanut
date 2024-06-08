package ui.menu;

import ui.AppUI;
import ui.button.*;

import javax.swing.*;
import java.awt.*;

// Represents a JPanel component that displays the Parent Menu
public class ParentMenu extends JPanel {
    private AppUI ui;
    private AddPeanut addPeanut;
    private Save save;
    private Return rtn;


    // MODIFIES: ui
    // EFFECT: display parent menu buttons
    public ParentMenu(AppUI ui) {
        this.ui = ui;
        addPeanut = new AddPeanut(this.ui, "Add a peanut");
        save = new Save(this.ui, "Save");
        rtn = new Return(this.ui, "Return");
        add(addPeanut);
        add(save);
        add(rtn);
        setMaximumSize(new Dimension(1000, 100));
    }
}
