package Database;

import SystemElements.Post;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PostDatabase extends ContentDatabase<Post> {

    private static PostDatabase instance = null;

    private PostDatabase() {
        filePath = FilesNames.POSTS_FILE;
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
            records.clear();
            String content = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(filePath)));
            JSONArray jsonArray = new JSONArray(content);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonPost = jsonArray.getJSONObject(i);
                Post post = new Post(
                        jsonPost.getInt("id"),
                        jsonPost.getInt("userId"),
                        LocalDate.parse(jsonPost.getString("timeStamp")),
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
        JSONArray jsonArray = new JSONArray();
        for (Post post : records) {
            JSONObject jsonStory = new JSONObject();
            jsonStory.put("id", post.getId());
            jsonStory.put("userId", post.getUserId());
            jsonStory.put("content", post.getContent());
            jsonStory.put("photo", post.getPhoto());
            jsonStory.put("timeStamp", post.getTimeStamp().toString());
            jsonArray.put(jsonStory);
        }

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(jsonArray.toString(4)); // Pretty-print the JSON
        } catch (IOException e) {

        }
    }

}
