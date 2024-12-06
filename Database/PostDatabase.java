package Database;

import SystemElements.ContentFactory;
import SystemElements.Post;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PostDatabase extends ContentDatabase<Post> {

    private static PostDatabase instance = null;

    private PostDatabase() {
        fileName = POSTS_FILE;
        readFromFile();
    }

    public static PostDatabase getInstance() {
        if (instance == null) {
            instance = new PostDatabase();
        }
        return instance;
    }

    @Override
    public void readFromFile() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            records.clear();
            String content = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(fileName)));
            JSONArray jsonArray = new JSONArray(content);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonPost = jsonArray.getJSONObject(i);
                Post post = ContentFactory.createPost(
                        jsonPost.getInt("id"),
                        jsonPost.getInt("userId"),
                        LocalDateTime.parse(jsonPost.getString("timeStamp"), formatter),
                        jsonPost.getString("content"),
                        jsonPost.getString("photo")
                );
                records.add(post);
            }
        } catch (IOException | JSONException e) {

        }
    }

    @Override
    public void saveToFile() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        JSONArray jsonArray = new JSONArray();
        for (Post post : records) {
            JSONObject jsonStory = new JSONObject();
            jsonStory.put("id", post.getId());
            jsonStory.put("userId", post.getUserId());
            jsonStory.put("content", post.getContent());
            jsonStory.put("photo", post.getPhoto());
            jsonStory.put("timeStamp", post.getTimeStamp().format(formatter));
            jsonArray.put(jsonStory);
        }

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(jsonArray.toString(4)); // Pretty-print the JSON
        } catch (IOException e) {

        }
    }
   
}
