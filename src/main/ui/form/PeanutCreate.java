package ui.form;

import model.user.Parent;
import model.user.Peanut;
import ui.AppUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DateTimeException;


// Represents a JPanel component that contains the form to get user input to create a new Peanut
public class PeanutCreate extends JPanel {

    private JButton submit;
    private AppUI ui;
    private JLabel getFName;
    private JLabel getLName;
    private JTextField peanutFName;
    private JTextField peanutLName;
    private Parent currentParent;

    // EFFECTS: Constructs a new peanut create form
    public PeanutCreate(AppUI ui) {
        this.ui = ui;
        initializeForm();
        setVisible(true);
        currentParent = ui.getCurrentParent();
    }

    // EFFECTS: display form label and field for input
    private void initializeForm() {
        getFName = new JLabel("First Name:");
        getLName = new JLabel("Last Name:");
        peanutFName = new JTextField(15);
        peanutLName = new JTextField(15);
        submit = new JButton("Submit");
        submit.addActionListener(new SubmitInfo());
        add(getFName);
        add(peanutFName);
        add(getLName);
        add(peanutLName);
        add(submit);
    }

    // Represents action listener for button submission
    private class SubmitInfo implements ActionListener {

        // MODIFIES: ui
        // EFFECTS: receives peanut info from form and add it to ui upon submission
        @Override
        public void actionPerformed(ActionEvent e) {

            Peanut newPeanut = new Peanut(peanutFName.getText(), peanutLName.getText(), currentParent);
            ui.displayPeanut(newPeanut.getFirstName());

        }
    }

}




