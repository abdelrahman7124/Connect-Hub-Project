package Settings;

import SystemElements.User;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import newtry.NewsFeed;

public class SettingsOptions implements ActionListener {

//change name,change pass,view blocks(serach with unblock button),logout
    private User user;
    private JFrame frame = new JFrame("Settings");
    private JButton button[] = new JButton[5];

    public SettingsOptions(User user) {
        this.user = user;
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(500, 600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        button[0] = new JButton("Change User Name");
        button[0].setBounds(140, 50, 200, 50);
        button[1] = new JButton("Change Password");
        button[1].setBounds(140, 150, 200, 50);
        button[2] = new JButton("View blocked accounts");
        button[2].setBounds(140, 250, 200, 50);
        button[3] = new JButton("Logout");
        button[3].setBounds(140, 350, 200, 50);
        button[4] = new JButton("Back");
        button[4].setBounds(140, 450, 200, 50);
        button[4].setBackground(Color.red);
        for (int i = 0; i < 5; i++) {
            button[i].setFocusable(false);
            button[i].setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
            button[i].addActionListener(this);
            button[i].setFont(new Font("Times New Roman", Font.PLAIN, 18));
            frame.add(button[i]);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.dispose();
        if (e.getSource() == button[0]) {
            new ChangeName(user);
        } else if (e.getSource() == button[1]) {
            new ChangePassword(user);
        } else if (e.getSource() == button[2]) {
            new BlocksHandling(user);
        } else if (e.getSource() == button[3]) {
            new Logout(user);
        } else if (e.getSource() == button[4]) {
            new NewsFeed(user);
        }
    }
}
