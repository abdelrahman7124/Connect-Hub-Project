package FriendsHandling;

import Management.RelationshipManager;
import SystemElements.User;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Suggestions implements ActionListener {

    private RelationshipManager relationshipManager = new RelationshipManager();
    private User user;
    private JFrame frame = new JFrame("Suggestions");
    private JButton button = new JButton("Back");
    private JPanel container = new JPanel();
    ArrayList<JButton> buttons = new ArrayList<>();
    ArrayList<User> Suggestions;

    public Suggestions(User user) {
        this.user = user;
        Suggestions = relationshipManager.getUsersWithNoRelationships(user.getId());
        System.out.println(Suggestions.size());
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(500, 600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        button.setBounds(140, 490, 200, 50);
        button.setFocusable(false);
        button.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        button.setBackground(Color.red);
        button.addActionListener(this);
        button.setFont(new Font("Times New Roman", Font.PLAIN, 18));

        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBounds(10, 10, 470, 470);  // Set size and position for scroll pane
        displaySuggestions(mainPanel);
        frame.add(scrollPane);
        frame.add(button);
    }

    private void displaySuggestions(JPanel mainPanel) {

        for (User i : Suggestions) {
            // Create a panel for each post
            JPanel Panel = new JPanel();
            Panel.setLayout(null);
            Panel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
            Panel.setPreferredSize(new Dimension(450, 50)); // Adjust size 

            // User name label
            JLabel nameLabel = new JLabel(i.getfName() + " " + i.getlName());
            nameLabel.setBounds(60, 15, 150, 20); // Set position and size
            nameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            Panel.add(nameLabel);

            JButton button1 = new JButton("Add Friend");
            button1.setBounds(270, 10, 150, 30);
            button1.addActionListener(this);
            button1.setFont(new Font("Arial", Font.PLAIN, 16));
            buttons.add(button1);
            button1.setFocusable(false);
            Panel.add(buttons.get(buttons.size() - 1));

            // Profile Picture Label
            if (i.getPhoto() != null && !i.getPhoto().isEmpty()) {
                ImageIcon originalIcon = new ImageIcon(i.getPhoto());
                Image scaledImage = originalIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                JLabel profilePictureLabel = new JLabel(scaledIcon);
                profilePictureLabel.setBounds(10, 10, 30, 30);
                profilePictureLabel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1)); // Add border to the image
                Panel.add(profilePictureLabel);
            }
            // Add the post panel to the main panel
            mainPanel.add(Panel);

        }
    }

    public void actionAt(int index) {
        buttons.get(index).setText("Requested");
        buttons.get(index).removeActionListener(this);
        relationshipManager.request(user.getId(), Suggestions.get(index).getId());

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            frame.dispose();
            new FriendsOptions(user);
        }
        for (int i = 0; i < buttons.size(); i++) {
            if (e.getSource() == buttons.get(i)) {
                actionAt(i);
                return;
            }
        }

    }

}
