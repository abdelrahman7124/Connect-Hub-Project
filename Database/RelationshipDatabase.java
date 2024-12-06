package Database;

import SystemElements.Relationship;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class RelationshipDatabase implements Database {

    private String fileName = RELATIONSHIPS_FILE;
    private ArrayList<Relationship> relations = new ArrayList<>();

    private static RelationshipDatabase instance = null;

    private RelationshipDatabase() {
        readFromFile();
    }

    public static RelationshipDatabase getInstance() {
        if (instance == null) {
            instance = new RelationshipDatabase();
        }
        return instance;
    }

    public void readFromFile() {
        try {
            relations.clear();
            String content = new String(Files.readAllBytes(Paths.get(fileName)));
            JSONArray jsonArray = new JSONArray(content);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonRelationship = jsonArray.getJSONObject(i);
                String type = jsonRelationship.getString("type");
                int id1 = jsonRelationship.getInt("id1");
                int id2 = jsonRelationship.getInt("id2");
                Relationship relationship = new Relationship(type, id1, id2);
                relations.add(relationship);
            }
        } catch (IOException e) {
        }
    }

    public void saveToFile() {
        JSONArray jsonArray = new JSONArray();
        for (Relationship relationship : relations) {
            JSONObject jsonRelationship = new JSONObject();
            jsonRelationship.put("type", relationship.getType());
            jsonRelationship.put("id1", relationship.getId1());
            jsonRelationship.put("id2", relationship.getId2());
            jsonArray.put(jsonRelationship);
        }
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(jsonArray.toString(4));
        } catch (IOException e) {

        }
    }

    private int getIndex(int id1, int id2) {
        for (int i = 0; i < relations.size(); i++) {
            if (relations.get(i).getId1() == id1 && relations.get(i).getId2() == id2) {
                return i;
            }
        }
        return -1;
    }

    public boolean areInRelation(int id1, int id2) {
        if (getIndex(id1, id2) != -1) {
            return true;
        } else if (getIndex(id2, id1) != -1) {
            return true;
        }
        return false;
    }

    public Relationship getRelationship(int id1, int id2) {
        if (getIndex(id1, id2) != -1) {
            return relations.get(getIndex(id1, id2));
        } else if (getIndex(id2, id1) != -1) {
            return relations.get(getIndex(id2, id1));
        }
        return null;
    }

    public void insertRelation(Relationship relation) {
        relations.add(relation);
    }

    public void deleteRelation(int id1, int id2) {
        Relationship r = getRelationship(id1, id2);
        if (r != null) {
            relations.remove(r);
        }
        Relationship r1 = getRelationship(id2, id1);
        if (r1 != null) {
            relations.remove(r1);
        }
    }

    public ArrayList<Relationship> getMyRelations(int id) {
        ArrayList<Relationship> myRelations = new ArrayList<>();
        for (int i = 0; i < relations.size(); i++) {
            if (id == relations.get(i).getId1() || id == relations.get(i).getId2()) {
                myRelations.add(relations.get(i));
            }
        }
        return myRelations;
    }
}
