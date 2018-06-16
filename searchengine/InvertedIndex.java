package searchengine;

import searchengine.database.Database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InvertedIndex {

    public InvertedIndex() {

    }

    public List<Posting> getIndexList(String term) {
        List<Posting> postings = new ArrayList<>();
        try {
            Database.fillPostings(postings,term);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return postings;
    }

    public int getDF(String term) {
        try {
            return Database.getDF(term);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getSize() {
        try {
            return Database.getSize();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getLength(long did) {
        try {
            return Database.getLen(did);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}