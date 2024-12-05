package Database;

import SystemElements.User;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import newtry.Validation;
import org.json.JSONArray;
import org.json.JSONObject;

public class UserDatabase {

    private String fileName = FilesNames.USERS_FILE;
    private ArrayList<User> users = new ArrayList<>();
    private static UserDatabase instance = null;

    private UserDatabase() {
        readFromFile();
    }

    public static UserDatabase getInstance() {
        if (instance == null) {
            instance = new UserDatabase();
        }
        return instance;
    }

    // Load user data from JSON file
    public void readFromFile() {
        try {
            users.clear();
            String content = new String(Files.readAllBytes(Paths.get(fileName)));
            if (content.trim().isEmpty()) {
                return;
            }
            JSONArray jsonArray = new JSONArray(content);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonUser = jsonArray.getJSONObject(i);
                User user = new User(
                        jsonUser.getInt("id"),
                        jsonUser.getString("fName"),
                        jsonUser.getString("lName"),
                        jsonUser.getString("email"),
                        jsonUser.getString("password"),
                        jsonUser.getString("status"),
                        jsonUser.getString("Bio"),
                        jsonUser.getString("photo"),
                        jsonUser.getString("cover"),
                        LocalDate.parse(jsonUser.getString("dateOfbirth"))
                );
                users.add(user);
            }
        } catch (IOException e) {

        }
    }

    // Save user data to JSON file
    public void saveToFile() {
        try (FileWriter writer = new FileWriter(fileName)) {
            JSONArray jsonArray = new JSONArray();

            for (User user : users) {
                JSONObject jsonUser = new JSONObject();
                jsonUser.put("id", user.getId());
                jsonUser.put("fName", user.getfName());
                jsonUser.put("lName", user.getlName());
                jsonUser.put("email", user.getEmail());
                jsonUser.put("password", user.getPassword());
                jsonUser.put("status", user.getStatus());
                jsonUser.put("Bio", user.getBio());
                jsonUser.put("photo", user.getPhoto());
                jsonUser.put("cover", user.getCover());
                jsonUser.put("dateOfbirth", user.getDateOfbirth().toString());
                jsonArray.put(jsonUser);
            }
            writer.write(jsonArray.toString(4));
        } catch (IOException e) {

        }
    }

    private int getIndex(int id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public boolean contains(int id) {
        if (getIndex(id) == -1) {
            return false;
        }
        return true;
    }

    public void insertRecord(User user) {
        users.add(user);
    }

    public User getRecord(int id) {
        if (getIndex(id) != -1) {
            return users.get(getIndex(id));
        }
        return null;
    }

    public User getRecord(String email) {
        for (User i : users) {
            if (i.getEmail().equals(email)) {
                return i;
            }
        }
        return null;
    }

    public ArrayList<User> getAllUsers() {
        return users;
    }

}
