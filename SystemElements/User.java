package SystemElements;

import Database.UserDatabase;
import java.time.LocalDate;
import newtry.Validation;

public class User {

    private int id;
    private String fName, lName, email, password, status, Bio, photo, cover;
    private LocalDate dateOfbirth;

    //constructor for signing up only
    public User(String fName, String lName, String email, String password, LocalDate dateOfbirth) {
        setId();
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.password = Validation.hashPassword(password);
        this.dateOfbirth = dateOfbirth;
        this.status = "Online";
        photo = "Unknown.jpg";
        cover = "";
        Bio = "";
    }
//constructor for loading from file only

    public User(int id, String fName, String lName, String email, String password, String status, String Bio, String photo, String cover, LocalDate dateOfbirth) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.password = password;
        this.status = status;
        this.Bio = Bio;
        this.photo = photo;
        this.cover = cover;
        this.dateOfbirth = dateOfbirth;
    }

    public int getId() {
        return id;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getStatus() {
        return status;
    }

    public String getBio() {
        return Bio;
    }

    public String getPhoto() {
        return photo;
    }

    public String getCover() {
        return cover;
    }

    public LocalDate getDateOfbirth() {
        return dateOfbirth;
    }

    public void setBio(String Bio) {
        this.Bio = Bio;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setPassword(String password) {
        this.password = Validation.hashPassword(password);
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    private void setId() {
        while (true) {
            int random = (int) (Math.random() * 10000) + 1;
            UserDatabase database = UserDatabase.getInstance();
            if (!database.contains(random)) {
                id = random;
                break;
            }
        }
    }
}
