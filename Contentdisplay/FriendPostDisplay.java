package Contentdisplay;

import Database.PostDatabase;
import Database.UserDatabase;
import SystemElements.Content;
import SystemElements.Post;
import SystemElements.User;
import Management.ContentManager;
import Management.RelationshipManager;
import Management.UserManager;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class FriendPostDisplay extends JPanel {

    private ContentManager contentManager;
    private UserManager userManager;
    private User user;

    public FriendPostDisplay(User user) {
        // Initialize the Managers
        contentManager = new ContentManager();
        userManager = new UserManager();
        this.user = user;

        // Set up the JPanel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Load and display content
        displayPosts();
    }

    private void displayPosts() {

        ArrayList<Post> contents = contentManager.returnSortedPosts(user);

        for (Content content : contents) {

            // Create a panel for each post
            JPanel postPanel = new JPanel();
            postPanel.setLayout(null);
            postPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); // Add border
            postPanel.setPreferredSize(new Dimension(400, 300)); // Adjust size 
            User friend = userManager.getUser(content.getUserId()); // Assuming you have a method to get User by ID
            //User name label
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

            // profile Picture Label
            if (friend.getPhoto() != null && !friend.getPhoto().isEmpty()) {
                ImageIcon originalIcon = new ImageIcon(friend.getPhoto());
                Image scaledImage = originalIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                JLabel pictureLabel = new JLabel(scaledIcon);
                pictureLabel.setBounds(10, 10, 30, 30);
                pictureLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1)); // Add border to the image
                postPanel.add(pictureLabel);
            }
            add(postPanel); // Add the post panel to the main panel
        }
    }
}
