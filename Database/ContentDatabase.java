package Database;

import SystemElements.Content;
import java.util.ArrayList;

public abstract class ContentDatabase<T extends Content>  implements Database{

    protected String fileName;

    protected ArrayList<T> records = new ArrayList<>();


    private int getIndex(int id) {
        for (int i = 0; i < records.size(); i++) {
            if (records.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public boolean contains(int id) {
        if (getIndex(id) != -1) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<T> returnAllRecords() {
        return records;
    }

    public T getRecord(int id) {
        if (getIndex(id) != -1) {
            return records.get(getIndex(id));
        } else {
            return null;
        }
    }

    public void insertRecord(T record) {
        records.add(record);
    }

    public void deleteRecord(T record) {
        records.remove(record);
    }

    
}
