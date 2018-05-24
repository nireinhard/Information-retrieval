package searchengine.database;

import searchengine.Document;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

    public static void close() throws SQLException {
        if (pstmt == null && pstmtTfs == null) {
            pstmt.close();
            pstmtTfs.close();
        }
        con.close();
    }

}
