package SystemElements;

import java.time.LocalDate;

public abstract class Content {

    protected int id;
    private int userId;
    private LocalDate timeStamp;
    private String content;
    private String photo;

    //constructor for new content
    public Content(int userId, String content, String photo) {
        this.userId = userId;
        timeStamp = LocalDate.now();
        this.content = content;
        this.photo = photo;
    }
    //constructor for read content from file 
    public Content(int id, int userId, LocalDate timeStamp, String content, String photo) {
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

    public LocalDate getTimeStamp() {
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
