package ui.list;

import ui.AppUI;
import ui.button.Button;

import java.awt.event.ActionEvent;

// Represents a button that selects a Parent from Home page to view
public class ParentList extends Button {
    private String currentParent;

    // EFFECTS: creates a list of parents in the form of buttons
    public ParentList(AppUI ui, String msg, String name) {
        super(ui, msg);
        currentParent = name;
    }

    // MODIFIES: ui
    // EFFECTS: load displayParent of current parent from ui upon click
    @Override
    public void actionPerformed(ActionEvent e) {
        ui.displayParent(currentParent);
    }

}