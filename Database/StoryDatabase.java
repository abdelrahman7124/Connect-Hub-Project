package Database;

import SystemElements.ContentFactory;
import SystemElements.Story;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class StoryDatabase extends ContentDatabase<Story> {

    private static StoryDatabase instance = null;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private StoryDatabase() {
        fileName = STORIES_FILE;
        readFromFile();
    }

    public static StoryDatabase getInstance() {
        if (instance == null) {
            instance = new StoryDatabase();
        }
        return instance;
    }

    @Override
    public void readFromFile() {
        try {
            records.clear();
            String content = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(fileName)));
            JSONArray jsonArray = new JSONArray(content);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonStory = jsonArray.getJSONObject(i);
                LocalDateTime time = LocalDateTime.parse(jsonStory.getString("timeStamp"), formatter);
                if (!time.isBefore(LocalDateTime.now().minusHours(24))) {
                    Story story = ContentFactory.createStory(
                            jsonStory.getInt("id"),
                            jsonStory.getInt("userId"),
                            time,
                            jsonStory.getString("content"),
                            jsonStory.getString("photo")
                    );
                    records.add(story);
                }
            }
        } catch (IOException | JSONException e) {

        }
    }

    @Override
    public void saveToFile() {
        JSONArray jsonArray = new JSONArray();
        for (Story story : records) {
            JSONObject jsonStory = new JSONObject();
            jsonStory.put("id", story.getId());
            jsonStory.put("userId", story.getUserId());
            jsonStory.put("content", story.getContent());
            jsonStory.put("photo", story.getPhoto());
            jsonStory.put("timeStamp", story.getTimeStamp().format(formatter));
            jsonArray.put(jsonStory);
        }

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(jsonArray.toString(4)); // Pretty-print the JSON
        } catch (IOException e) {

        }
    }

}
