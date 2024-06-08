package ui.list;

import ui.AppUI;
import ui.button.Button;

import java.awt.event.ActionEvent;

// Represents a button that selects a Peanut from Parent's list of Peanuts to view
public class PeanutList extends Button {
    private String currentPeanut;


    // EFFECTS: creates a list of peanuts in the form of buttons
    public PeanutList(AppUI ui, String msg, String name) {
        super(ui, msg);
        currentPeanut = name;
    }

    // MODIFIES: ui
    // EFFECTS: load displayPeanut of current peanut from ui upon click
    @Override
    public void actionPerformed(ActionEvent e) {
        ui.displayPeanut(currentPeanut);
    }

}