package SystemElements;

import Database.StoryDatabase;
import Database.UserDatabase;
import java.time.LocalDate;

public class Story extends Content {

//constructor for new story
    public Story(int userId, String content, String photo) {
        super(userId, content, photo);
        setId();
    }
    
//constructor for read story from file 
    public Story(int id, int userId, LocalDate timeStamp, String content, String photo) {
        super(id, userId, timeStamp, content, photo);
    }

    private void setId() {
        while (true) {
            int random = (int) (Math.random() * 10000) + 1;
            StoryDatabase database = StoryDatabase.getInstance();
            if (!database.contains(random)) {
                id = random;
                break;
            }
        }
    }

}
