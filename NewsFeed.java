package newtry;

import Contentdisplay.FriendPostDisplay;
import FriendsHandling.FriendsOptions;
import Gui.Posts;
import Gui.Profile;
import Gui.Stories;
import Contentdisplay.FriendStoryDisplay;
import Settings.SettingsOptions;
import SystemElements.User;
import Management.ContentManager;
import Management.UserManager;
import Management.RelationshipManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class NewsFeed implements ActionListener {

    private JFrame frame = new JFrame("News feed");
    private JPanel panel[] = new JPanel[3];
    private JButton button[] = new JButton[9];
    private User user;
    private FriendPostDisplay postDisplay;

    //Gui building 
    public NewsFeed(User user) {
        this.user = user;
        for (int i = 0; i < 3; i++) {
            panel[i] = new JPanel();
            panel[i].setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
            panel[i].setLayout(null);

        }
        panel[0].setBounds(0, 0, 500, 50);// my profile,notifications,search,friends,refresh,settings
        panel[1].setBounds(0, 50, 500, 50);// content handling (posts stories)
        panel[2].setBounds(0, 100, 500, 500);// newsfeed using scroll
        //initial handling for loading profile photo
        File imageProfile = new File(user.getPhoto());
        ImageIcon image;
        if (imageProfile.exists()) {
            image = new ImageIcon(user.getPhoto());
        } else {
            image = new ImageIcon("Unknown.jpg");
        }
        Image scaled = image.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon newImage = new ImageIcon(scaled);

        button[0] = new JButton(newImage);
        button[0].setBounds(45, 10, 30, 30);
        button[1] = new JButton(new ImageIcon("notification.png"));
        button[1].setBounds(120, 10, 30, 30);
        button[2] = new JButton(new ImageIcon("search.png"));
        button[2].setBounds(195, 10, 30, 30);
        button[3] = new JButton(new ImageIcon("request.png"));
        button[3].setBounds(270, 10, 30, 30);
        button[4] = new JButton(new ImageIcon("refresh.png"));
        button[4].setBounds(345, 10, 30, 30);
        button[5] = new JButton(new ImageIcon("settings.png"));
        button[5].setBounds(420, 10, 30, 30);

        button[6] = new JButton("New Post");
        button[6].setBounds(65, 10, 80, 30);
        button[7] = new JButton("New Story");
        button[7].setBounds(210, 10, 80, 30);
        button[8] = new JButton("View Stories");
        button[8].setBounds(355, 10, 80, 30);

        for (int i = 0; i < 9; i++) {
            button[i].setFocusable(false);
            button[i].setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
            button[i].addActionListener(this);
            if (i < 6) {
                panel[0].add(button[i]);
                button[i].setFont(new Font("Times New Roman", Font.PLAIN, 11));
            } else {
                panel[1].add(button[i]);
                button[i].setFont(new Font("Times New Roman", Font.PLAIN, 14));
            }
        }

        // Create an instance of FriendPostDisplay
        postDisplay = new FriendPostDisplay(user);
        // Wrap FriendPostDisplay inside a JScrollPane
        JScrollPane scrollPane = new JScrollPane(postDisplay);  // Wrap FriendPostDisplay in JScrollPane
        scrollPane.setBounds(0, 10, 480, 450);  // Set size and position for scroll pane

        panel[2].add(scrollPane);  // Add the JScrollPane to the panel

        // Set up the frame
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(500, 600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        // Add panels to the frame
        frame.add(panel[0]);
        frame.add(panel[1]);
        frame.add(panel[2]);
        button[1].removeActionListener(this);//just for this phase
        button[2].removeActionListener(this);//just for this phase
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button[0]) {
            new Profile(user);
        } else if (e.getSource() == button[1]) {
                 //new NotificationsHandling(user);
        } else if (e.getSource() == button[2]) {
               //new SerachHandling(user);
        } else if (e.getSource() == button[3]) {
            new FriendsOptions(user);
        } else if (e.getSource() == button[4]) {
            RelationshipManager r = new RelationshipManager();
            r.refresh();
            UserManager l = new UserManager();
            l.refresh();
            ContentManager c = new ContentManager();
            c.refresh();
            new NewsFeed(user);
        } else if (e.getSource() == button[5]) {
            new SettingsOptions(user);
        } else if (e.getSource() == button[6]) {
            new Posts(user);
        } else if (e.getSource() == button[7]) {
            new Stories(user);
        } else if (e.getSource() == button[8]) {
            new FriendStoryDisplay(user);
        }
        frame.dispose();
    }

}
