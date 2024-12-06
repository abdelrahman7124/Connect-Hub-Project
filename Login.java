package Start;

import SystemElements.User;
import UserManager.LogingManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import newtry.NewsFeed;

public class Login implements ActionListener, KeyListener {

    JLabel label[] = new JLabel[2];
    JTextField textField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    private JFrame frame = new JFrame("Log in");
    private JButton button[] = new JButton[2];
    LogingManager manager = new LogingManager();

    //Gui Design in the constructor
    public Login() {
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(500, 600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        label[0] = new JLabel("  Email");
        label[0].setBounds(90, 100, 100, 40);
        textField.setBounds(210, 108, 200, 30);
        textField.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
        textField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        textField.addKeyListener(this);

        label[1] = new JLabel("Password");
        label[1].setBounds(90, 250, 100, 40);
        passwordField.setBounds(210, 258, 200, 30);
        passwordField.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
        passwordField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        passwordField.addKeyListener(this);

        button[0] = new JButton("Log in");
        button[0].setBounds(145, 400, 200, 40);
        button[0].setBackground(Color.GREEN);
        button[1] = new JButton("Back");
        button[1].setBounds(145, 460, 200, 40);
        button[1].setBackground(Color.red);
        for (int i = 0; i < 2; i++) {
            button[i].setFocusable(false);
            button[i].setFont(new Font("Times New Roman", Font.BOLD, 20));
            button[i].setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
            button[i].addActionListener(this);
            label[i].setFont(new Font("", Font.PLAIN, 18));
            frame.add(label[i]);
            frame.add(button[i]);
        }
        frame.add(passwordField);
        frame.add(textField);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //log in (check in file Users.txt) -->Newsfeed
        if (e.getSource() == button[0]) {
            User user = manager.login(textField.getText(), passwordField.getText());
            if (user != null) {
                frame.dispose();
                new NewsFeed(user);
            }
        } //Back button (To the start frame)
        else if (e.getSource() == button[1]) {
            frame.dispose();
            new Start();
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
