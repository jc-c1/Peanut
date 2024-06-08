package ui.display;

import model.user.Parent;
import ui.AppUI;
import ui.list.ParentList;

import javax.swing.*;
import java.awt.*;

// represent the Home screen with the option to start a new Peanut App or load previous ones
public class HomeDisplay extends JPanel {
    private AppUI ui;

    // EFFECTS: Constructs Home Screen displaying Parents
    public HomeDisplay(AppUI ui) {
        this.ui = ui;
        JLabel homePage = new JLabel("HOME PAGE");
        homePage.setFont(new Font("Arial", Font.PLAIN, 24));

        add(homePage);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        for (Parent pr : this.ui.getParents()) {
            add(new ParentList(this.ui, pr.getFirstName().concat(" ")
                    .concat(pr.getLastName()), pr.getFirstName()));

        }
    }
}
