package Contentdisplay;

import SystemElements.Content;
import SystemElements.Story;
import SystemElements.User;
import Management.ContentManager;
import Management.RelationshipManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import newtry.NewsFeed;

public class FriendStoryDisplay implements ActionListener {

    private ContentManager contentManager;
    private RelationshipManager relationshipManager;

    private JButton backButton = new JButton("Back");
    private JFrame frame = new JFrame("Stories");
    private User user;

    public FriendStoryDisplay(User user) {
        this.user = user;

        // Initialize the Managers
        contentManager = new ContentManager();
        relationshipManager = new RelationshipManager();

        // Set up the JFrame
        frame = new JFrame("Stories");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout()); // Use BorderLayout for the main frame

        // Create a main panel to hold all post panels
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); // Use BoxLayout for vertical stacking

        // Wrap the main panel inside a JScrollPane
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBounds(10, 50, 470, 500);  // Set size and position for scroll pane

        // Load and display content
        displayPosts(mainPanel);

        // Add the scroll pane to the frame
        frame.add(scrollPane);

        // set Back button
        backButton.setBounds(10, 10, 40, 20);
        backButton.addActionListener(this);
        backButton.setFocusable(false);

        // Create a panel for the button and add it to the frame
        JPanel buttonPanel = new JPanel(); // Create a panel for the button
        buttonPanel.add(backButton); // Add the button to the panel
        frame.add(buttonPanel); // Add the button panel to the top of the frame
        frame.setVisible(true);

    }

    private void displayPosts(JPanel mainPanel) {
        ArrayList<User> friends = relationshipManager.getFriends(user.getId());

        for (User friend : friends) {
            ArrayList<Story> contents = contentManager.getStoriesOF(friend.getId());

            for (Content content : contents) {
                // Create a panel for each post
                JPanel postPanel = new JPanel();
                postPanel.setLayout(null);
                postPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); // Add border
                postPanel.setPreferredSize(new Dimension(300, 300)); // Adjust size 

                // User name label
                JLabel nameLabel = new JLabel(friend.getfName() + " " + friend.getlName());
                nameLabel.setBounds(60, 10, 200, 20); // Set position and size
                nameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
                postPanel.add(nameLabel);

                // Timestamp Label
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                JLabel timeStampLabel = new JLabel(content.getTimeStamp().format(formatter));
                timeStampLabel.setBounds(60, 30, 200, 20); // Set position and size
                timeStampLabel.setFont(new Font("Arial", Font.PLAIN, 10));
                postPanel.add(timeStampLabel);

                // Content Label
                JLabel contentLabel = new JLabel(content.getContent());
                contentLabel.setBounds(30, 70, 440, 20); // Set position and size
                postPanel.add(contentLabel);

                // Picture Label
                if (content.getPhoto() != null && !content.getPhoto().isEmpty()) {
                    ImageIcon originalIcon = new ImageIcon(content.getPhoto());
                    Image scaledImage = originalIcon.getImage().getScaledInstance(480, 150, Image.SCALE_SMOOTH);
                    ImageIcon scaledIcon = new ImageIcon(scaledImage);
                    JLabel pictureLabel = new JLabel(scaledIcon);
                    pictureLabel.setBounds(30, 100, 400, 150);
                    pictureLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1)); // Add border to the image
                    postPanel.add(pictureLabel);
                } else {
                    postPanel.setPreferredSize(new Dimension(400, 100)); // Adjust size when there is no picture
                }

                // Profile Picture Label
                if (friend.getPhoto() != null && !friend.getPhoto().isEmpty()) {
                    ImageIcon originalIcon = new ImageIcon(friend.getPhoto());
                    Image scaledImage = originalIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
                    ImageIcon scaledIcon = new ImageIcon(scaledImage);
                    JLabel profilePictureLabel = new JLabel(scaledIcon);
                    profilePictureLabel.setBounds(10, 10, 30, 30);
                    profilePictureLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1)); // Add border to the image
                    postPanel.add(profilePictureLabel);
                }

                // Add the post panel to the main panel
                mainPanel.add(postPanel);
            }

            // Revalidate and repaint the main panel to update the UI
            mainPanel.revalidate();
            mainPanel.repaint();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            frame.dispose();
            new NewsFeed(user);
        }
    }

}
