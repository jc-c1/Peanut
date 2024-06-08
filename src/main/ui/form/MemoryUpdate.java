package ui.form;

import model.journal.Entry;
import model.journal.Memory;
import model.user.Parent;
import model.user.Peanut;
import ui.AppUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DateTimeException;


// Represents a JPanel component that contains the form to get user input to update an existing Memory
public class MemoryUpdate extends JPanel {
    private JButton submit;
    private AppUI ui;
    private JLabel getTitle;
    private JLabel getContent;
    private JLabel getImage;
    private JTextField memoryTitle;
    private JTextField memoryContent;
    private JTextField memoryImage;

    private Memory currentMemory;
    private String title;
    private String content;
    private String image;

    private Parent parent;
    private Peanut peanut;


    // EFFECTS: Constructs a new memory update form
    public MemoryUpdate(AppUI ui, Entry m) {
        this.ui = ui;
        this.currentMemory = (Memory) m;
        this.title = m.getTitle();
        this.content = m.getContent();
        this.image = ((Memory) m).getImage();
        initializeForm();
        setVisible(true);

    }

    // EFFECTS: display form label and field for input
    private void initializeForm() {
        getTitle = new JLabel("Title:");
        getContent = new JLabel("Content:");
        getImage = new JLabel("Image:");
        memoryTitle = new JTextField(15);
        memoryTitle.setText(title);
        memoryContent = new JTextField(15);
        memoryContent.setText(content);
        memoryImage = new JTextField(15);
        memoryImage.setText(image);
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

            currentMemory.addTitle(memoryTitle.getText());
            currentMemory.addContent(memoryContent.getText());
            currentMemory.addImage(memoryImage.getText());
            ui.displayMemory(currentMemory.getTitle());

        }
    }


}




