package ui.display;

import model.user.Parent;
import model.user.Peanut;
import ui.AppUI;
import ui.list.PeanutList;

import javax.swing.*;
import java.awt.*;

// represent the Parent screen, lists all the Peanuts in Parent's journal
public class ParentDisplay extends JPanel {
    private AppUI ui;
    private Parent pr;

    // EFFECTS: Constructs Parent Screen displaying Peanuts
    public ParentDisplay(AppUI ui, Parent pr) {
        this.ui = ui;
        this.pr = pr;

        JLabel parentName = new JLabel(this.pr.getFirstName() + " " + this.pr.getLastName());
        parentName.setFont(new Font("Arial", Font.PLAIN, 24));

        add(parentName);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        for (Peanut pn: this.pr.getPeanutsOfParent()) {

            add(new PeanutList(this.ui, pn.getFirstName().concat(" ")
                    .concat(pn.getLastName()), pn.getFirstName()));
        }
    }


}
