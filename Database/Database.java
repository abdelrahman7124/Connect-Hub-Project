package Database;

public interface Database {

    public final static String USERS_FILE = "users.json";
    public final static String POSTS_FILE = "posts.json";
    public final static String STORIES_FILE = "stories.json";
    public final static String RELATIONSHIPS_FILE = "relationships.json";

    public void saveToFile();

    public void readFromFile();
}
