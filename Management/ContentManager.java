package Management;

import Database.PostDatabase;
import Database.StoryDatabase;
import Database.UserDatabase;
import SystemElements.Content;
import SystemElements.Post;
import SystemElements.Story;
import SystemElements.User;
import java.util.ArrayList;

public class ContentManager {

    public ContentManager() {
    }

    PostDatabase postDatabase = PostDatabase.getInstance();
    StoryDatabase storyDatabase = StoryDatabase.getInstance();
    RelationshipManager relationshipManager = new  RelationshipManager();

    public void newPost(Post post) {
        postDatabase.insertRecord(post);
        postDatabase.saveToFile();
    }

    public void newStory(Story story) {
        storyDatabase.insertRecord(story);
        storyDatabase.saveToFile();
    }

    public void removeStory(Story story) {
        storyDatabase.deleteRecord(story);
        storyDatabase.saveToFile();
    }

    public ArrayList<Post> getPostsOF(int id) {
        ArrayList<Post> total = postDatabase.returnAllRecords();
        ArrayList<Post> target = new ArrayList<>();
        for (Post i : total) {
            if (i.getUserId() == id) {
                target.add(i);
            }
        }
        return target;
    }

    public ArrayList<Story> getStoriesOF(int id) {
        ArrayList<Story> total = storyDatabase.returnAllRecords();
        ArrayList<Story> target = new ArrayList<>();
        for (Story i : total) {
            if (i.getUserId() == id) {
                target.add(i);
            }
        }
        return target;
    }

    public void refresh() {
        postDatabase.readFromFile();
        storyDatabase.readFromFile();
    }

    public ArrayList<Post> returnSortedPosts(User user) {
        ArrayList<User> friends = relationshipManager.getFriends(user.getId());
        ArrayList<Post> allPosts = new ArrayList<>();
        // Collect all posts from friends
        for (User friend : friends) {
            ArrayList<Post> contents;
            contents = getPostsOF(friend.getId());
            allPosts.addAll(contents);
        }
        // Sort posts by timestamp
        allPosts.sort((post1, post2) -> post1.getTimeStamp().compareTo(post2.getTimeStamp()));
        return allPosts;
    }
}
