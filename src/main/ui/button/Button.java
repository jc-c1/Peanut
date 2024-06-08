package ui.button;

import ui.AppUI;
import javax.swing.*;
import java.awt.event.ActionListener;

// represents abstract button class
public abstract class Button extends JButton implements ActionListener {
    protected AppUI ui;

    // EFFECTS: creates a button outline with click function
    public Button(AppUI ui, String msg) {
        super(msg);
        this.ui = ui;
        addActionListener(this);
    }

}