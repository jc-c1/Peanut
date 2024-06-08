package ui.button;

import ui.AppUI;

import java.awt.event.ActionEvent;

// represents a button that update Memory
public class UpdateMemory extends Button {

    // REQUIRES: non-null AppUI and non-empty button label for msg
    // EFFECTS: create a update memory button; ui and msg are called via super
    public UpdateMemory(AppUI ui, String msg) {
        super(ui, msg);
    }

    // REQUIRES: ActionEvent e to be a click
    // MODIFIES: ui
    // EFFECTS: run initializeMemoryUpdater upon click
    @Override
    public void actionPerformed(ActionEvent e) {
        ui.getMemoryUpdateFormInfo();
    }

}