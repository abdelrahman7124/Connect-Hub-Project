package SystemElements;

import java.time.LocalDate;
import newtry.Validation;

public class UserBuilder {

    private int id;
    private String fName, lName, email, password, status = "Online", Bio = "", photo = "Unknown.jpg", cover = "";
    private LocalDate dateOfbirth;

    public UserBuilder(String fName, String lName, String email, String password, LocalDate dateOfbirth) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.password = password;
        this.dateOfbirth = dateOfbirth;
    }

    public UserBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public UserBuilder setStatus(String status) {
        this.status = status;
        return this;
    }

    public UserBuilder setBio(String Bio) {
        this.Bio = Bio;
        return this;
    }

    public UserBuilder setPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    public UserBuilder setCover(String cover) {
        this.cover = cover;
        return this;
    }

    public User build() {
        if (id == 0) {
            return new User(fName, lName, email, password, dateOfbirth);
        } else {
            return new User(id, fName, lName, email, password, status, Bio, photo, cover, dateOfbirth);
        }
    }
}
