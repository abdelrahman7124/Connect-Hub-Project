package Gui;

import Database.StoryDatabase;
import SystemElements.Story;
import SystemElements.User;
import Management.ContentManager;
import SystemElements.ContentFactory;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import newtry.NewsFeed;

public class Stories implements ActionListener, KeyListener {

    JFrame container = new JFrame("New story");
    JLabel[] label = new JLabel[2];
    JButton[] button = new JButton[3];
    JTextField textfield = new JTextField();
    JFileChooser fileChooser = new JFileChooser();
    User user;
    ContentManager contentManager = new ContentManager();

    public Stories(User user) {
        this.user = user;
        //set the basic structure
        container.setLayout(null);
        container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container.setVisible(true);
        container.setSize(500, 600);
        container.setResizable(false);
        container.setLocationRelativeTo(null);
        //add the label
        label[0] = new JLabel("Add description");
        label[0].setBounds(90, 100, 200, 40);
        label[1] = new JLabel("Add picture");
        label[1].setBounds(90, 250, 100, 40);
        //add the buttons
        button[0] = new JButton("Add");
        button[0].setBounds(145, 455, 200, 40);
        button[1] = new JButton("Back");
        button[1].setBounds(145, 515, 200, 40);
        button[2] = new JButton("Add a picture");
        button[2].setBounds(90, 300, 300, 20);

        for (int i = 0; i < 2; i++) {
            button[i].setFocusable(false);
            button[i].setFont(new Font("Times New Roman", Font.BOLD, 20));
            button[i].setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
            label[i].setFont(new Font("", Font.PLAIN, 18));
            button[i].addActionListener(this);
            container.add(button[i]);
            container.add(label[i]);
        }
        button[2].setFocusable(false);
        button[2].setFont(new Font("Times New Roman", Font.BOLD, 20));
        button[2].setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
        button[2].addActionListener(this);
        container.add(button[2]);
        //add the text fields
        textfield.setBounds(90, 150, 300, 20);
        textfield.addKeyListener(this);
        container.add(textfield);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button[0]) {
            //add a new story
            try {
                Story story = ContentFactory.createStory(user.getId(), textfield.getText(), fileChooser.getSelectedFile().toString());
                contentManager.newStory(story);
                container.dispose();
                new NewsFeed(user);
            } catch (NullPointerException nullPointerException) {
                Story story = ContentFactory.createStory(user.getId(), textfield.getText(), "");
                contentManager.newStory(story);
                container.dispose();
                new NewsFeed(user);
            }
        } else if (e.getSource() == button[1]) {
            new NewsFeed(user);
            container.dispose();
        } else if (e.getSource() == button[2]) {
            fileChooser.setDialogTitle("Picking photo");
            fileChooser.showSaveDialog(null);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            button[0].doClick();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
