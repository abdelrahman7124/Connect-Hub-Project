package SystemElements;

public class Relationship {
    private String type;
    private int id1;
    private int id2;

    //3 relation types: request , friend , block     else: no
    public Relationship(String type, int id1, int id2) {
        this.type = type;
        this.id1 = id1;
        this.id2 = id2;
    }

    public String getType() {
        return type;
    }

    public int getId1() {
        return id1;
    }

    public int getId2() {
        return id2;
    }
    
    
}
