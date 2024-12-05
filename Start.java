package Start;

import Database.*;
import SystemElements.Relationship;
import SystemElements.User;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Start implements ActionListener {

    private JFrame frame = new JFrame("Connect Hub");
    private JButton button[] = new JButton[2];
    private JLabel l = new JLabel("  Welcome to Connect Hub");
    UserDatabase userDatabase = UserDatabase.getInstance();
    StoryDatabase storyDatabase = StoryDatabase.getInstance();
    PostDatabase postDatabase = PostDatabase.getInstance();
    RelationshipDatabase relationshipDatabase = RelationshipDatabase.getInstance();

    //Gui Design in the constructor
    public Start() {
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(500, 600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        l.setBounds(100, 35, 300, 40);
        l.setFont(new Font("Times New Roman", Font.ITALIC, 26));
        l.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        l.setForeground(Color.BLACK);
        button[0] = new JButton("Sign up");
        button[0].setBounds(145, 200, 200, 50);
        button[1] = new JButton("Log in");
        button[1].setBounds(145, 400, 200, 50);

        for (int i = 0; i < 2; i++) {
            button[i].setFocusable(false);
            button[i].setFont(new Font("Times New Roman", Font.BOLD, 20));
            button[i].setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
            button[i].addActionListener(this);
            frame.add(button[i]);
        }
        frame.add(l);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //sign up button
        if (e.getSource() == button[0]) {
            frame.dispose();
            new Signup();
        } //log in button
        else if (e.getSource() == button[1]) {
            frame.dispose();
            new Login();
        }
    }

    public static void main(String[] args) {
//        RelationshipDatabase r = RelationshipDatabase.getInstance();
//        r.readFromFile();
//        r.insertRelation(new Relationship("block", 8782, 7112));
//        r.saveToFile();
        new Start();
    }

}
