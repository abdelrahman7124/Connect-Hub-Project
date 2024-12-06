package Settings;

import Database.UserDatabase;
import Management.UserManager;
import SystemElements.User;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import newtry.Validation;

public class ChangePassword implements ActionListener, KeyListener {

    private JFrame frame = new JFrame("Change Password");
    private JButton button[] = new JButton[2];
    private User user;
    private JLabel label[] = new JLabel[3];
    private JPasswordField passwordField[] = new JPasswordField[3];
    UserManager manager=new UserManager();

    public ChangePassword(User user) {
        this.user = user;
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(500, 600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        button[0] = new JButton("Save");
        button[0].setBounds(140, 350, 200, 50);
        button[0].setBackground(Color.GREEN);
        button[1] = new JButton("Back");
        button[1].setBounds(140, 450, 200, 50);
        button[1].setBackground(Color.red);

        for (int i = 0; i < 2; i++) {
            button[i].setFocusable(false);
            button[i].setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
            button[i].addActionListener(this);
            button[i].setFont(new Font("Times New Roman", Font.PLAIN, 18));
            frame.add(button[i]);
        }

        label[0] = new JLabel("  Enter New Password");
        label[0].setBounds(30, 50, 200, 50);
        label[1] = new JLabel("Re-Enter New password");
        label[1].setBounds(30, 150, 200, 50);
        label[2] = new JLabel("    Your Old Password");
        label[2].setBounds(30, 250, 200, 50);
        for (int i = 0; i < 3; i++) {
            passwordField[i] = new JPasswordField();
            passwordField[i].setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
            passwordField[i].setFont(new Font("Times New Roman", Font.PLAIN, 18));
            passwordField[i].addKeyListener(this);
            frame.add(passwordField[i]);
            label[i].setFont(new Font("Times New Roman", Font.PLAIN, 20));
            frame.add(label[i]);
        }

        passwordField[0].setBounds(245, 60, 200, 27);
        passwordField[1].setBounds(245, 160, 200, 27);
        passwordField[2].setBounds(245, 260, 200, 27);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button[0]) {
            if (Validation.hashPassword(passwordField[2].getText()).equals(user.getPassword())) {
                if (!passwordField[0].getText().equals(passwordField[1].getText())) {
                    JOptionPane.showMessageDialog(null, "Non-matching passwords!", "Error", JOptionPane.ERROR_MESSAGE);
                    passwordField[0].setText("");
                    passwordField[1].setText("");
                } else if (!Validation.validPass(passwordField[0].getText())) {
                    JOptionPane.showMessageDialog(null, "Invalid Password,Try Again!", "Error", JOptionPane.ERROR_MESSAGE);
                    passwordField[0].setText("");
                    passwordField[1].setText("");
                } else if (user.getPassword().equals(passwordField[0].getText())) {
                    JOptionPane.showMessageDialog(null, "This password was used before!", "Error", JOptionPane.ERROR_MESSAGE);
                    passwordField[0].setText("");
                    passwordField[1].setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Password has been changed successfully", "Done", JOptionPane.INFORMATION_MESSAGE);
                    user.setPassword(passwordField[0].getText());
                    //Save in File
                   manager.saveAll();
                    frame.dispose();
                    new SettingsOptions(user);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Wrong password,Try again", "Error", JOptionPane.ERROR_MESSAGE);
                passwordField[2].setText("");
            }
        } else if (e.getSource() == button[1]) {
            frame.dispose();
            new Settings.SettingsOptions(user);
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
