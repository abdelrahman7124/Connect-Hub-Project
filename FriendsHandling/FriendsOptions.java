package FriendsHandling;

import Management.RelationshipManager;
import Management.UserManager;
import SystemElements.User;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import newtry.NewsFeed;

public class FriendsOptions implements ActionListener {

    private User user;
    private JFrame frame = new JFrame("Friends Options");
    private JButton button[] = new JButton[4];
    private JPanel panel = new JPanel();

    public FriendsOptions(User user) {
        this.user = user;
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(500, 600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        panel.setBounds(150, 50, 200, 450);
        panel.setLayout(new GridLayout(4, 1, 100, 40));
        button[0] = new JButton("My Friends");
        button[0].setBounds(145, 200, 200, 50);
        button[1] = new JButton("Requests");
        button[1].setBounds(145, 400, 200, 50);
        button[2] = new JButton("Suggestions");
        button[2].setBounds(145, 200, 200, 50);
        button[3] = new JButton("Back");
        button[3].setBounds(145, 400, 200, 50);

        for (int i = 0; i < 4; i++) {
            button[i].setFocusable(false);
            button[i].setFont(new Font("Times New Roman", Font.BOLD, 20));
            button[i].setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
            button[i].addActionListener(this);
            panel.add(button[i]);
        }
        frame.add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button[0]) {
            frame.dispose();
            new Friends(user);
        } else if (e.getSource() == button[1]) {
            frame.dispose();
            new Requests(user);
        } else if (e.getSource() == button[2]) {
            frame.dispose();
            new Suggestions(user);
        } else if (e.getSource() == button[3]) {
            frame.dispose();
            new NewsFeed(user);
        }
    }

}
