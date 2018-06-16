package searchengine.database;

import searchengine.Document;
import searchengine.Posting;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ir
 *
 * @AUTHOR niklasreinhard
 * 24.05.18.
 */
public class Database {

    private static int counter = 0;

    private static Connection con = JDBC.getConnection();

    private static PreparedStatement pstmt;
    private static PreparedStatement pstmtTfs;

    public static void insert(Document doc) throws SQLException {
        if (pstmt == null && pstmtTfs == null) {
            String sql = "Insert into docs (did, title, url) VALUES(?, ?, ?)";
            String sqlTfs = "Insert into tfs (did, term, tf) VALUES(?, ?, ?)";
            pstmt = con.prepareStatement(sql);
            pstmtTfs = con.prepareStatement(sqlTfs);
        }

        if (counter != 0 && counter % 10 == 0) {
            System.err.println("DATENBANK SCHREIBEN");
            pstmt.executeBatch();
            pstmtTfs.executeBatch();
        }

        counter += 1;

        pstmt.setLong(1, doc.getId());
        pstmt.setString(2, doc.getTitle());
        pstmt.setString(3, doc.getUrl());

        pstmt.addBatch();

        for (Map.Entry<String, Long> entry : doc.contentFrequency().entrySet()) {
            pstmtTfs.setLong(1, doc.getId());
            pstmtTfs.setString(2, entry.getKey());
            pstmtTfs.setLong(3, entry.getValue());
            pstmtTfs.addBatch();
        }

    }

    public static void fillPostings(List<Posting> postings, String term) throws SQLException {
        String sql = "Select did, tf From tfs Where term = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, term);
        ResultSet rSet = pst.executeQuery();
        do {
            postings.add(new Posting(rSet.getLong("did"), rSet.getInt("tf")));
        } while (rSet.next());
    }

    public static int getDF(String term) throws SQLException {
        String sql = "Select df From dfs Where term = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, term);
        ResultSet rSet = pst.executeQuery();
        if (rSet.first()) return rSet.getInt("df");
        return 0;
    }

    public static int getSize() throws SQLException {
        String sql = "Select size From d";
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rSet = pst.executeQuery();
        if (rSet.first()) return rSet.getInt("df");
        return 0;
    }

    public static int getLen(long did) throws SQLException {
        String sql = "Select len From dls Where did = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setLong(1, did);
        ResultSet rSet = pst.executeQuery();
        if (rSet.first()) return rSet.getInt("df");
        return 0;
    }

    public static void close() throws SQLException {
        if (pstmt == null && pstmtTfs == null) {
            pstmt.close();
            pstmtTfs.close();
        }
        con.close();
    }

}
