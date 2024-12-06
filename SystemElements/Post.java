package SystemElements;

import Database.PostDatabase;
import SystemElements.Content;
import java.time.LocalDateTime;

public class Post extends Content {
    
    //constructor for new post
    public Post(int userId, String content, String photo) {
        super(userId, content, photo);
        setId();
    }

    //constructor for read post from file 
    public Post(int id, int userId, LocalDateTime timeStamp, String content, String photo) {
        super(id, userId, timeStamp, content, photo);
    }

    private void setId() {
        while (true) {
            int random = (int) (Math.random() * 10000) + 1;
            PostDatabase database = PostDatabase.getInstance();
            if (!database.contains(random)) {
                id = random;
                break;
            }
        }
    }
}
