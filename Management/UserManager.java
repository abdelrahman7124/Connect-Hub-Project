package Management;

import Database.UserDatabase;
import SystemElements.User;
import javax.swing.JOptionPane;
import newtry.Validation;

public class UserManager {

    UserDatabase userDatabase = UserDatabase.getInstance();

    public UserManager() {
    }

    public boolean signUp(User user) {
        int check = checkEmailPassword(user.getEmail(), user.getPassword());
        if (check == -1) {
            userDatabase.insertRecord(user);
            userDatabase.saveToFile();
            return true;
        }
        JOptionPane.showMessageDialog(null, "This email is already connected to an account ", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    public User login(String email, String password) {
        int check = checkEmailPassword(email, password);
        if (check == -1) {
            JOptionPane.showMessageDialog(null, "This email isn't connected to any accounts yet", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        } else if (check == 0) {
            JOptionPane.showMessageDialog(null, "Incorrect password", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        User user = userDatabase.getRecord(email);
        user.setStatus("Online");
        userDatabase.saveToFile();
        return user;
    }

    public User getUser(int id) {
        return userDatabase.getRecord(id);
    }

    public void logout(User user) {
        user.setStatus("Offline");
        userDatabase.saveToFile();
    }

    private int checkEmailPassword(String email, String password) {
        User user = userDatabase.getRecord(email);
        if (user == null) {
            return -1;
        } else if (!Validation.hashPassword(password).equals(user.getPassword())) {
            return 0;
        }
        return 1;
    }

    public void refresh() {
        userDatabase.readFromFile();
    }

}
