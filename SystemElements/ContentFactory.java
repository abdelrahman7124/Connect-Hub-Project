package SystemElements;

import java.time.LocalDateTime;

public class ContentFactory {

    public static Post createPost(int userId, String content, String photo) {
        return new Post(userId, content, photo);
    }
    
    public static Post createPost(int id, int userId, LocalDateTime timeStamp, String content, String photo){
        return new Post(id, userId, timeStamp, content, photo);
    }

    public static Story createStory(int userId, String content, String photo) {
        return new Story(userId, content, photo);
    }
    
    public static Story createStory(int id, int userId, LocalDateTime timeStamp, String content, String photo){
        return new Story(id, userId, timeStamp, content, photo);
    }
}