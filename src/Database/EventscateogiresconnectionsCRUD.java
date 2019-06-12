package Database;

import javax.print.DocFlavor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventscateogiresconnectionsCRUD {

    private Database db = null;

    public EventscateogiresconnectionsCRUD(Database db) {
        this.db = db;
    }


    public void insertEventsCateogiresConnections(int id, String category) {

        Connection conn = db.getConn();
        String sqlInsert = "INSERT INTO eventscateogiresconnections(event_id,category_name ) VALUES(?,?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sqlInsert)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, category);
            pstmt.executeQuery();
        } catch (SQLException E) {
            System.out.println(E.getMessage());
        }
        db.closeConnection();
    }


    public List<String> readEventsCateogiresConnectionsByID(int eventId) {
        List<String> toReturn = new ArrayList<>();
        Connection conn = db.getConn();

        String sql = "SELECT category_name " +
                "FROM eventscateogiresconnections" +
                "WHERE event_id =(?)";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, eventId);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String category = rs.getString(1);
                String tmp = category;
                toReturn.add(tmp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.closeConnection();
        return toReturn;
    }
}


