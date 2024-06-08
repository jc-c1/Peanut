package ui.button;

import ui.AppUI;

import java.awt.event.ActionEvent;

// Represents a button that adds a Memory
public class AddMemory extends Button {

    // REQUIRES: non-null AppUI and non-empty button label for msg
    // EFFECTS: create a add Memory button; ui and msg are called via super
    public AddMemory(AppUI ui, String msg) {
        super(ui, msg);
    }

    // REQUIRES: ActionEvent e to be a click
    // MODIFIES: ui
    // EFFECTS: run initializeMemoryGetter upon click
    @Override
    public void actionPerformed(ActionEvent e) {
        ui.getMemoryFormInfo();
    }

}