package ui.button;

import ui.AppUI;


import java.awt.event.ActionEvent;


// represents a button that save current file
public class Save extends Button {

    // REQUIRES: non-null AppUI and non-empty button label for msg
    // EFFECTS: create a save button; ui and msg are called via super
    public Save(AppUI ui, String msg) {
        super(ui, msg);
    }


    // REQUIRES: ActionEvent e to be a click
    // MODIFIES: ui
    // EFFECTS: run saveFile upon click
    @Override
    public void actionPerformed(ActionEvent e) {
        ui.saveFile();
    }
}
