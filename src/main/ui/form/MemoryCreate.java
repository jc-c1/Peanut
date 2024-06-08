package ui.form;

import model.journal.Memory;
import model.user.Parent;
import model.user.Peanut;
import ui.AppUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DateTimeException;


// Represents a JPanel component that contains the form to get user input to create a new Memory
public class MemoryCreate extends JPanel {
    private JButton submit;
    private AppUI ui;
    private JLabel getTitle;
    private JLabel getContent;
    private JLabel getImage;
    private JTextField memoryTitle;
    private JTextField memoryContent;
    private JTextField memoryImage;

    private Parent parent;
    private Peanut peanut;


    // EFFECTS: Constructs a new memory create form
    public MemoryCreate(AppUI ui) {
        this.ui = ui;
        initializeForm();
        setVisible(true);
    }

    // EFFECTS: display form label and field for input
    private void initializeForm() {
        getTitle = new JLabel("Title:");
        getContent = new JLabel("Content:");
        getImage = new JLabel("Image");
        memoryTitle = new JTextField(15);
        memoryContent = new JTextField(15);
        memoryImage = new JTextField(15);
        submit = new JButton("Submit");
        submit.addActionListener(new SubmitInfo());
        add(getTitle);
        add(memoryTitle);
        add(getContent);
        add(memoryContent);
        add(getImage);
        add(memoryImage);
        add(submit);
    }

    // Represents action listener for button submission
    private class SubmitInfo implements ActionListener {

        // MODIFIES: ui
        // EFFECTS: receives memory info from form and add it to ui upon submission
        @Override
        public void actionPerformed(ActionEvent e) {

            Memory newMemory = new Memory(peanut, parent);
            newMemory.addTitle(memoryTitle.getText());
            newMemory.addContent(memoryContent.getText());
            newMemory.addImage(memoryImage.getText());
            ui.addMemory(newMemory);
            ui.displayMemory(newMemory.getTitle());

        }
    }




}




