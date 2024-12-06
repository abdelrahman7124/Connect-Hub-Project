package Management;

import Database.RelationshipDatabase;
import Database.UserDatabase;
import SystemElements.Relationship;
import SystemElements.User;
import java.util.ArrayList;

public class RelationshipManager {

    private RelationshipDatabase relationshpDatabase = RelationshipDatabase.getInstance();
    private UserDatabase userDatabase = UserDatabase.getInstance();

    public void request(int userId1, int userId2) {
        //id1 sends add to id
        //check they arn't in request or friendship or blocking
        relationshpDatabase.insertRelation(new Relationship("request", userId1, userId2));
        relationshpDatabase.saveToFile();
    }

    public void declineRequest(int userId1, int userId2) {
        //id1 decline id2 request
        relationshpDatabase.deleteRelation(userId2, userId1);
        relationshpDatabase.saveToFile();
    }

    public void addFriend(int userId1, int userId2) {
        //id1 accepts id2 request-->tranfer from request to friends
        relationshpDatabase.deleteRelation(userId2, userId1);
        relationshpDatabase.insertRelation(new Relationship("friend", userId1, userId2));
        relationshpDatabase.saveToFile();
    }

    public void unFriend(int userId1, int userId2) {
        //id1 unfriends id2 
        relationshpDatabase.deleteRelation(userId1, userId2);
        relationshpDatabase.deleteRelation(userId2, userId1);
        relationshpDatabase.saveToFile();
    }

    public void block(int userId1, int userId2) {
        //id1 blocked id2
        relationshpDatabase.deleteRelation(userId1, userId2);
        relationshpDatabase.insertRelation(new Relationship("block", userId1, userId2));
        relationshpDatabase.saveToFile();
    }

    public void unBlock(int userId1, int userId2) {
        //id1 unblocks id2
        relationshpDatabase.deleteRelation(userId1, userId2);
        relationshpDatabase.saveToFile();
    }

    public ArrayList<User> getUsersIBlocked(int id) {
        ArrayList<Relationship> totalRelations = relationshpDatabase.getMyRelations(id);
        ArrayList<User> target = new ArrayList<>();
        for (Relationship i : totalRelations) {
            if (i.getType().equals("block") && id == i.getId1()) {
                target.add(userDatabase.getRecord(i.getId2()));
            }
        }
        return target;
    }

    public ArrayList<User> getUserRequestedMe(int id) {
        ArrayList<Relationship> totalRelations = relationshpDatabase.getMyRelations(id);
        ArrayList<User> target = new ArrayList<>();
        for (Relationship i : totalRelations) {
            if (i.getType().equals("request") && id == i.getId2()) {
                target.add(userDatabase.getRecord(i.getId1()));
            }
        }
        return target;
    }

    public ArrayList<User> getFriends(int id) {
        ArrayList<Relationship> totalRelations = relationshpDatabase.getMyRelations(id);
        ArrayList<User> target = new ArrayList<>();
        for (Relationship i : totalRelations) {
            if (i.getType().equals("friend")) {
                if (id == i.getId1()) {
                    target.add(userDatabase.getRecord(i.getId2()));
                } else {
                    target.add(userDatabase.getRecord(i.getId1()));
                }
            }
        }
        return target;
    }

    public ArrayList<User> getUsersWithNoRelationships(int id) {
        ArrayList<User> users = userDatabase.getAllRecords();
        ArrayList<User> usersWithNoRelationshipIds = new ArrayList<>();
        for (User i : users) {
            if (id != i.getId()) {
                System.out.println("hi1");
                if (!relationshpDatabase.areInRelation(id, i.getId())) {
                    System.out.println("hi2");
                    usersWithNoRelationshipIds.add(i);
                }
            }
        }
        return usersWithNoRelationshipIds;

    }

    public void refresh() {
        relationshpDatabase.readFromFile();
    }
}
