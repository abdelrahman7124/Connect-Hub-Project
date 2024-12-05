package Database;

import SystemElements.Story;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class StoryDatabase extends ContentDatabase<Story> {

    private static StoryDatabase instance = null;

    private StoryDatabase() {
        filePath = FilesNames.STORIES_FILE;
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
            String content = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(filePath)));
            JSONArray jsonArray = new JSONArray(content);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonStory = jsonArray.getJSONObject(i);
                Story story = new Story(
                        jsonStory.getInt("id"),
                        jsonStory.getInt("userId"),
                        LocalDate.parse(jsonStory.getString("timeStamp")),
                        jsonStory.getString("content"),
                        jsonStory.getString("photo")
                );
                records.add(story);
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
            jsonStory.put("timeStamp", story.getTimeStamp().toString());
            jsonArray.put(jsonStory);
        }

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(jsonArray.toString(4)); // Pretty-print the JSON
        } catch (IOException e) {

        }
    }

}
