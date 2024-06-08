package ui.button;

import ui.AppUI;

import java.awt.event.ActionEvent;

// represents a button that deletes a Memory
public class DeleteMemory extends Button {
    public DeleteMemory(AppUI ui, String msg) {
        super(ui, msg);
    }


    // REQUIRES: ActionEvent e to be a click
    // MODIFIES: ui
    // EFFECTS: run initializeMemoryDeletion upon click
    @Override
    public void actionPerformed(ActionEvent e) {
        ui.memoryDelete();

    }
}
