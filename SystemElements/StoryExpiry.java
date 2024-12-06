package SystemElements;

import Database.StoryDatabase;
import Management.ContentManager;

import java.time.LocalDateTime;

public class StoryExpiry implements Runnable {

    private final Story story;

    public StoryExpiry(Story story) {
        this.story = story;
        start();
    }

    private void start() {
        Thread thread = new Thread(this);
        thread.setDaemon(true); // Makes sure the thread doesn't block JVM shutdown
        thread.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000 * 60 * 60); // Check every hour
                if (LocalDateTime.now().isAfter(story.getCreationTime().plusHours(24))) {
                    expireStory();
                    break; // Exit the thread after expiring the story
                }
            } catch (InterruptedException e) {
                System.err.println("StoryExpiryChecker interrupted: " + e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }

    private void expireStory() {
        ContentManager contentManager = new ContentManager();
        contentManager.removeStory(story);
    }
}
