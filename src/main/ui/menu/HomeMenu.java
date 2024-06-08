package ui.menu;

import ui.AppUI;
import ui.button.AddParent;
import ui.button.Load;
import ui.button.Save;

import javax.swing.*;
import java.awt.*;

// Represents a JPanel component that displays the Home Menu
public class HomeMenu extends JPanel {
    private AppUI ui;
    private AddParent addParent;
    private Save save;
    private Load load;

    // MODIFIES: ui
    // EFFECT: display home menu buttons
    public HomeMenu(AppUI ui) {
        this.ui = ui;
        addParent = new AddParent(this.ui, "Add a parent");
        save = new Save(this.ui, "Save");
        load = new Load(this.ui, "Load last saved data");
        add(addParent);
        add(save);
        add(load);
        setMaximumSize(new Dimension(1000, 100));
    }
}
