package ui.display;

import model.journal.Entry;
import model.journal.Memory;
import model.user.Parent;
import model.user.Peanut;
import ui.AppUI;

import javax.swing.*;
import java.awt.*;


// represent the Memory screen, lists the title and content of the Memory
public class MemoryDisplay extends JPanel {
    private Entry mem;

    // EFFECTS: Constructs Memory Screen displaying title and content
    public MemoryDisplay(Entry m) {
        this.mem = m;
        JLabel title = new JLabel(this.mem.getTitle());
        title.setFont(new Font("Arial", Font.PLAIN, 24));
        add(title);
        Memory memoryImg = (Memory) m;
        String memImg = memoryImg.getImage();

        ImageIcon image1 = new ImageIcon("./data/" + memImg);

        Image image = image1.getImage(); // transform it
        Image newImg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        image1 = new ImageIcon(newImg);

        add(new JLabel(image1));

//        frame.add(new JLabel(new ImageIcon("Path/To/Your/Image.png")));

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel contentInfo = new JLabel(this.mem.getContent());
        add(contentInfo);
    }
}
