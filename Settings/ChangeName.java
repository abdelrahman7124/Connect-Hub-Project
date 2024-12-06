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
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import newtry.Validation;

public class ChangeName implements ActionListener, KeyListener {

    private JFrame frame = new JFrame("Change Name");
    private JButton button[] = new JButton[2];
    private User user;
    private JLabel label[] = new JLabel[3];
    private JTextField textField[] = new JTextField[2];
    private JPasswordField passwordField = new JPasswordField();
    UserManager manager = new UserManager();

    public ChangeName(User user) {
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

            textField[i] = new JTextField();
            textField[i].setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
            textField[i].setFont(new Font("Times New Roman", Font.PLAIN, 18));
            textField[i].addKeyListener(this);
            frame.add(textField[i]);
        }

        textField[0].setBounds(220, 60, 200, 27);
        textField[1].setBounds(220, 160, 200, 27);
        passwordField.setBounds(220, 260, 200, 27);
        passwordField.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
        passwordField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        passwordField.addKeyListener(this);
        frame.add(passwordField);

        label[0] = new JLabel("Enter First Name");
        label[0].setBounds(50, 50, 200, 50);
        label[1] = new JLabel(" Enter Last Name");
        label[1].setBounds(50, 150, 200, 50);
        label[2] = new JLabel("  Your Password");
        label[2].setBounds(50, 250, 200, 50);
        for (int i = 0; i < 3; i++) {
            label[i].setFont(new Font("Times New Roman", Font.PLAIN, 20));
            frame.add(label[i]);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button[0]) {
            if (Validation.hashPassword(passwordField.getText()).equals(user.getPassword())) {
                if (!Validation.validName(textField[0].getText())) {
                    JOptionPane.showMessageDialog(null, "Invalid First Name,Try again", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!Validation.validName(textField[1].getText())) {
                    JOptionPane.showMessageDialog(null, "Invalid Last Name,Try again", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "User Name has been changed successfully", "Done", JOptionPane.INFORMATION_MESSAGE);
                    user.setfName(textField[0].getText());
                    user.setlName(textField[1].getText());
                    //Save in File
                    manager.saveAll();
                    frame.dispose();
                    new SettingsOptions(user);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Wrong password,Try again", "Error", JOptionPane.ERROR_MESSAGE);
                passwordField.setText("");
            }
        } else if (e.getSource() == button[1]) {
            frame.dispose();
            new SettingsOptions(user);
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
