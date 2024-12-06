package Gui;

import Contentdisplay.MyStoryDisplay;
import Contentdisplay.MyPostDisplay;
import Database.UserDatabase;
import SystemElements.User;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;
import newtry.NewsFeed;

public class Profile implements ActionListener {

    private JFrame frame = new JFrame("My Profile");
    private JButton[] buttons = new JButton[6];
    private JLabel profileName;
    private JLabel bio = new JLabel("Bio");
    private ImageIcon profilePicture;
    private ImageIcon coverPhoto;
    private JLabel coverLabel;
    User user;
    private JScrollPane posts;

    public Profile(User user) {
        this.user = user;
        profileName = new JLabel(user.getfName() + " " + user.getlName());
        Font font = new Font("Times New Roman", Font.PLAIN, 20);
        Border border = BorderFactory.createLineBorder(Color.black, 1, true);

        // Setting JFrame
        frame.setVisible(true);
        frame.setSize(500, 600);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Setting Profile Picture
        File imageProfile = new File(user.getPhoto());
        ImageIcon profile;
        if (imageProfile.exists()) {
            profile = new ImageIcon(user.getPhoto());
        } else {
            profile = new ImageIcon("Unknown.jpg");
        }
        Image scaledProfile = profile.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        profilePicture = new ImageIcon(scaledProfile);
        buttons[0] = new JButton(profilePicture);
        buttons[0].setBounds(59, 153, 91, 91);
        buttons[0].setBorder(border);

        // Setting Cover Photo
        ImageIcon cover = new ImageIcon(user.getCover());
        Image scaledCover = cover.getImage().getScaledInstance(400, 150, Image.SCALE_SMOOTH);
        coverPhoto = new ImageIcon(scaledCover);
        coverLabel = new JLabel(coverPhoto);
        coverLabel.setBounds(50, 27, 401, 151);
        coverLabel.setBorder(border);

        // Setting Change Profile picture button
        buttons[1] = new JButton("Change Profile");
        buttons[1].setBounds(155, 215, 95, 26);

        // Setting Change Cover Photo button
        buttons[2] = new JButton("Change Cover");
        buttons[2].setBounds(256, 215, 95, 26);

        // Setting Change Bio Button
        buttons[3] = new JButton("Change Bio");
        buttons[3].setBounds(355, 215, 95, 26);

        //setting back button
        buttons[4] = new JButton("Back");
        buttons[4].setBounds(10, 10, 40, 20);

        //view my stories button
        buttons[5] = new JButton("view Stories");
        buttons[5].setBounds(355, 185, 95, 26);

        // Setting Bio TextArea
        bio.setBounds(50, 255, 400, 43);
        bio.setFont(font);
        bio.setBorder(border);
        bio.setForeground(Color.black);
        bio.setText(user.getBio());

        // Setting Profile Name label
        profileName.setBounds(156, 175, 187, 35);
        profileName.setFont(font);

        // Set Posts Scroll Pane
        //create instance of postDisplay
        MyPostDisplay postDisplay = new MyPostDisplay(user);
        posts = new JScrollPane(postDisplay);
        posts.setBounds(50, 305, 400, 264);
        //posts.

        // Add Buttons
        for (JButton button : buttons) {
            button.setFocusable(false);
            button.setBorder(border);
            button.setFont(new Font("Times New Roman", Font.PLAIN, 12));
            button.addActionListener(this);
            frame.add(button);
        }

        frame.add(posts);
        frame.add(profileName);
        frame.add(bio);
        frame.add(coverLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Profile Picture is Clicked
        if (e.getSource() == buttons[0]) {
            JFrame pictureFrame = new JFrame("Profile Picture");

            pictureFrame.setSize(400, 500);
            pictureFrame.setVisible(true);
            pictureFrame.setLayout(null);
            pictureFrame.setLocationRelativeTo(null);
            pictureFrame.setResizable(false);

            Image scaledProfile = profilePicture.getImage().getScaledInstance(360, 460, Image.SCALE_SMOOTH);
            ImageIcon picture = new ImageIcon(scaledProfile);

            JLabel piclabel = new JLabel(picture);

            piclabel.setVisible(true);
            piclabel.setBounds(20, 20, 360, 460);

            pictureFrame.add(piclabel);
        } // Change Profile Picture Button is Clicked
        else if (e.getSource() == buttons[1]) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Pictures", "png", "jpg", "jpeg");
            fileChooser.setFileFilter(filter);
            fileChooser.setApproveButtonText("Choose Picture");
            fileChooser.setDialogTitle("Choose Profile Picture");

            int result = fileChooser.showSaveDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                ImageIcon profile = new ImageIcon(fileChooser.getSelectedFile().getAbsolutePath());
                Image scaledProfile = profile.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
                profilePicture = new ImageIcon(scaledProfile);
                buttons[0].setIcon(profilePicture);
                user.setPhoto(fileChooser.getSelectedFile().getAbsolutePath());
            }
        } // Change Cover Photo button is clicked
        else if (e.getSource() == buttons[2]) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Pictures", "png", "jpg", "jpeg");
            fileChooser.setFileFilter(filter);
            fileChooser.setApproveButtonText("Choose Cover");
            fileChooser.setDialogTitle("Choose Cover Photo");

            int result = fileChooser.showSaveDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                ImageIcon profile = new ImageIcon(fileChooser.getSelectedFile().getAbsolutePath());
                Image scaledProfile = profile.getImage().getScaledInstance(400, 150, Image.SCALE_SMOOTH);
                coverPhoto = new ImageIcon(scaledProfile);
                coverLabel.setIcon(coverPhoto);
                user.setCover(fileChooser.getSelectedFile().getAbsolutePath());
            }
        } // Change bio button is clicked
        else if (e.getSource() == buttons[3]) {
            String bioText = JOptionPane.showInputDialog("Enter Bio");

            bio.setText(bioText);
            user.setBio(bioText);
            //back button
        } else if (e.getSource() == buttons[4]) {
            UserDatabase u = UserDatabase.getInstance();
            u.saveToFile();
            frame.dispose();
            new NewsFeed(user);
        }
        else if (e.getSource() == buttons[5]){
            new MyStoryDisplay(user);
            frame.dispose();
        }
    }
    
}
