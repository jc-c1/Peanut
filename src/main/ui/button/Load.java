package ui.button;

import ui.AppUI;

import java.awt.event.ActionEvent;

// represents a button that loads previously saved files
public class Load extends Button {

    // REQUIRES: non-null AppUI and non-empty button label for msg
    // EFFECTS: create a load button; ui and msg are called via super
    public Load(AppUI ui, String msg) {
        super(ui, msg);
    }


    // REQUIRES: ActionEvent e to be a click
    // MODIFIES: ui
    // EFFECTS: run loadFile upon click
    @Override
    public void actionPerformed(ActionEvent e) {
        ui.loadFile();
    }
}
