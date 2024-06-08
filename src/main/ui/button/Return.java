package ui.button;

import ui.AppUI;

import java.awt.event.ActionEvent;

// represents a button that returns above level page
public class Return extends Button {

    // REQUIRES: non-null AppUI and non-empty button label for msg
    // EFFECTS: create a return button; ui and msg are called via super
    public Return(AppUI ui, String msg) {
        super(ui, msg);
    }


    // REQUIRES: ActionEvent e to be a click
    // MODIFIES: ui
    // EFFECTS: run returnToLast upon click
    @Override
    public void actionPerformed(ActionEvent e) {
        ui.returnToLast();
    }
}