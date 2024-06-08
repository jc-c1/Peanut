package ui.form;

import model.user.Parent;
import ui.AppUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// Represents a JPanel component that contains the form to get user input to create a new Parent
public class ParentCreate extends JPanel {
    private JButton submit;
    private AppUI ui;
    private JLabel getFName;
    private JLabel getLName;
    private JTextField parentFName;
    private JTextField parentLName;


    // EFFECTS: Constructs a new parent create form
    public ParentCreate(AppUI ui) {
        this.ui = ui;
        initializeForm();
        setVisible(true);
    }

    // EFFECTS: display form label and field for input
    private void initializeForm() {
        getFName = new JLabel("First Name:");
        getLName = new JLabel("Last Name:");
        parentFName = new JTextField(15);
        parentLName = new JTextField(15);
        submit = new JButton("Submit");
        submit.addActionListener(new SubmitInfo());
        add(getFName);
        add(parentFName);
        add(getLName);
        add(parentLName);
        add(submit);
    }

    // Represents action listener for button submission
    private class SubmitInfo implements ActionListener {

        // MODIFIES: ui
        // EFFECTS: receives parent info from form and add it to ui upon submission
        @Override
        public void actionPerformed(ActionEvent e) {
            Parent newParent = new Parent(parentFName.getText(), parentLName.getText());
            ui.addParent(newParent);
            ui.displayParent(newParent.getFirstName());
        }
    }

}




