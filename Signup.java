package Start;

import SystemElements.User;
import Management.UserManager;
import SystemElements.UserBuilder;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDate;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import newtry.NewsFeed;
import newtry.Validation;

public class Signup implements ActionListener, KeyListener {

    JFrame frame = new JFrame("Sign Up");
    JPanel panel1 = new JPanel();//for labels
    JPanel panel2 = new JPanel();//for textfields
    JLabel label[] = new JLabel[5];
    JTextField textField[] = new JTextField[4];
    JPasswordField passwordField = new JPasswordField();
    JButton button[] = new JButton[2];
    UserManager manager = new UserManager();

    public Signup() {
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(500, 600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        panel1.setBounds(40, 25, 190, 420);
        panel1.setLayout(new GridLayout(5, 1, 20, 20));
        panel2.setBounds(260, 42, 170, 385);
        panel2.setLayout(new GridLayout(5, 1, 20, 60));

        label[0] = new JLabel("      First Name");
        label[1] = new JLabel("      Last Name");
        label[2] = new JLabel("          Email");
        label[3] = new JLabel("       Password");
        label[4] = new JLabel("Date Of Birth(D-M-Y)");

        for (int i = 0; i < 5; i++) {
            if (i < 3) {
                textField[i] = new JTextField();
                textField[i].setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
                textField[i].setFont(new Font("Times New Roman", Font.PLAIN, 18));
                textField[i].addKeyListener(this);
                panel2.add(textField[i]);
            } else if (i == 3) {
                passwordField.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
                passwordField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
                passwordField.addKeyListener(this);
                panel2.add(passwordField);
            } else if (i == 4) {
                textField[3] = new JTextField();
                textField[3].setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
                textField[3].setFont(new Font("Times New Roman", Font.PLAIN, 18));
                textField[3].addKeyListener(this);
                panel2.add(textField[3]);
            }
            label[i].setFont(new Font("", Font.PLAIN, 18));
            panel1.add(label[i]);
        }
        button[0] = new JButton("Sign up");
        button[0].setBounds(145, 455, 200, 40);
        button[0].setBackground(Color.GREEN);
        button[1] = new JButton("Back");
        button[1].setBounds(145, 515, 200, 40);
        button[1].setBackground(Color.red);
        for (int i = 0; i < 2; i++) {
            button[i].setFocusable(false);
            button[i].setFont(new Font("Times New Roman", Font.BOLD, 20));
            button[i].setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
            button[i].addActionListener(this);
            frame.add(button[i]);
        }
        frame.add(panel1);
        frame.add(panel2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //sign up button (vaildations+adding to file users.json)-->Newsfeed but empty one
        if (e.getSource() == button[0]) {
            if (!Validation.validName(textField[1].getText())) {
                JOptionPane.showMessageDialog(panel1, "Please Enter Valid Last Name");
            } else if (!Validation.validName(textField[0].getText())) {
                JOptionPane.showMessageDialog(panel1, "Please Enter Valid First Name");
            } else if (!Validation.validEmail(textField[2].getText())) {
                JOptionPane.showMessageDialog(panel1, "Please Enter Valid Email");
            } else if (!Validation.validPass(passwordField.getText())) {
                JOptionPane.showMessageDialog(panel1, "Please Enter Valid Password");
            } else if (!Validation.validDate(textField[3].getText())) {
                JOptionPane.showMessageDialog(panel1, "Please Enter Valid Date");
            } else {
                User user = new UserBuilder(textField[0].getText(), textField[1].getText(), textField[2].getText(), passwordField.getText(), LocalDate.now()).build();
                if (manager.signUp(user)) {
                    frame.dispose();
                    new NewsFeed(user);
                }
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
