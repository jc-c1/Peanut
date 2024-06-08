package ui.list;

import ui.AppUI;
import ui.button.Button;

import java.awt.event.ActionEvent;


// Represents a button that selects a Memory from Peanut's journal to view
public class MemoryList extends Button {
    private String currentMemory;


    // EFFECTS: creates a list of memories in the form of buttons
    public MemoryList(AppUI ui, String msg) {
        super(ui, msg);
        currentMemory = msg;
    }

    // MODIFIES: ui
    // EFFECTS: load displayMemory of current memory from ui upon click
    @Override
    public void actionPerformed(ActionEvent e) {
        ui.displayMemory(currentMemory);
    }

}