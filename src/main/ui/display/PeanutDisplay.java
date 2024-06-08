package ui.display;

import model.journal.Entry;
import model.user.Peanut;
import ui.AppUI;
import ui.list.MemoryList;

import javax.swing.*;
import java.awt.*;

// represent the Peanut screen, lists all the memories in Peanut's journal
public class PeanutDisplay extends JPanel {
    private AppUI ui;
    private Peanut pn;

    // EFFECTS: Constructs Peanut Screen displaying Memories
    public PeanutDisplay(AppUI ui, Peanut p) {
        this.ui = ui;
        this.pn = p;
        JLabel peanutName = new JLabel(pn.getFirstName() + " " + pn.getLastName());
        peanutName.setFont(new Font("Arial", Font.PLAIN, 24));

        add(peanutName);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        for (Entry e: pn.getJournal().getIndex()) {
            add(new MemoryList(this.ui, e.getTitle()));
        }
    }
}
