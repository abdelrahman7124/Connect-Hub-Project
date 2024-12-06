package SystemElements;

import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class Content {

    protected int id;
    private int userId;
    protected LocalDateTime timeStamp;
    private String content;
    private String photo;

    //constructor for new content
    public Content(int userId, String content, String photo) {
        this.userId = userId;
        timeStamp = LocalDateTime.now();
        this.content = content;
        this.photo = photo;
    }
    //constructor for read content from file 
    public Content(int id, int userId, LocalDateTime timeStamp, String content, String photo) {
        this.id = id;
        this.userId = userId;
        this.timeStamp = timeStamp;
        this.content = content;
        this.photo = photo;
    }
    

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public String getContent() {
        return content;
    }

    public String getPhoto() {
        return photo;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

}
