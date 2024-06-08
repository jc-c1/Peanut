package ui.button;

import ui.AppUI;

import java.awt.event.ActionEvent;

// represents a button that adds a Peanut
public class AddPeanut extends Button {

    // REQUIRES: non-null AppUI and non-empty button label for msg
    // EFFECTS: create a add Peanut button; ui and msg are called via super
    public AddPeanut(AppUI ui, String msg) {
        super(ui, msg);
    }

    // REQUIRES: ActionEvent e to be a click
    // MODIFIES: ui
    // EFFECTS: run initializePeanutGetter upon click
    @Override
    public void actionPerformed(ActionEvent e) {
        ui.getPeanutFormInfo();
    }

}