package Settings;

import SystemElements.User;
import Management.RelationshipManager;
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
import newtry.NewsFeed;

public class BlocksHandling implements ActionListener {

    User user;
    ArrayList<User> blocks;
    RelationshipManager manager = new RelationshipManager();
    JFrame frame = new JFrame("Blocks");
    JButton button = new JButton("Back");
    JPanel container = new JPanel();
    ArrayList<JButton> buttons = new ArrayList<JButton>();

    public BlocksHandling(User user) {
        this.user = user;
        blocks = manager.getUsersIBlocked(user.getId());
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
        displayBlocked(mainPanel);
        frame.add(scrollPane);
        frame.add(button);

    }

    private void displayBlocked(JPanel mainPanel) {

        for (User user : blocks) {
            // Create a panel for each post
            JPanel blockPanel = new JPanel();
            blockPanel.setLayout(null);
            blockPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
            blockPanel.setPreferredSize(new Dimension(400, 200)); // Adjust size 

            // User name label
            JLabel nameLabel = new JLabel(user.getfName() + " " + user.getlName());
            nameLabel.setBounds(60, 13, 200, 20); // Set position and size
            nameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            blockPanel.add(nameLabel);

            JButton button = new JButton("Unblock");
            button.setBounds(280, 10, 150, 30);
            button.addActionListener(this);
            buttons.add(button);
            button.setFocusable(false);
            blockPanel.add(buttons.get(buttons.size() - 1));
            // Profile Picture Label
            if (user.getPhoto() != null && !user.getPhoto().isEmpty()) {
                ImageIcon originalIcon = new ImageIcon(user.getPhoto());
                Image scaledImage = originalIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                JLabel profilePictureLabel = new JLabel(scaledIcon);
                profilePictureLabel.setBounds(10, 10, 30, 30);
                profilePictureLabel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1)); // Add border to the image
                blockPanel.add(profilePictureLabel);
            }
            // Add the post panel to the main panel
            mainPanel.add(blockPanel);

        }
    }

    public void unBlock(int index) {
        buttons.get(index).setText("Unblocked");
        buttons.get(index).removeActionListener(this);
        manager.unBlock(user.getId(), blocks.get(index).getId());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            
            frame.dispose();
            new SettingsOptions( user);
        }
        for (int i = 0; i < buttons.size(); i++) {
            if (e.getSource() == buttons.get(i)) {
                unBlock(i);
                return;
            }
        }
    }

}
