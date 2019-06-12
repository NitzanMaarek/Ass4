package Database;

import Entities.Event;
import sun.plugin.javascript.navig.LinkArray;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventCRUD {
    private Database db = null;

    public EventCRUD(Database db){
        this.db = db;
    }

    public void insertEvent(Event event){
        Connection conn = db.getConn();
        String sqlInsert = "INSERT INTO events(title,time_published ,representative_user_name,status,organization ) VALUES(?,?,?,?,?)";
            List<String> list = convertToListFormat(event);
        try(PreparedStatement pstmt = conn.prepareStatement(sqlInsert)){
            pstmt.setString(1, list.get(0));
            pstmt.setString(2, list.get(1));
            pstmt.setString(3, list.get(2));
            pstmt.setString(4, list.get(3));
            pstmt.setString(5, list.get(4));
            pstmt.executeUpdate();

        }
        catch (SQLException E){
            System.out.println(E.getMessage());
        }
        db.closeConnection();

    }

    private List<String> convertToListFormat(Event event) {
        List<String> toReturn = new ArrayList();
        toReturn.add(event.getTitle());//0
        toReturn.add(event.getDatePublished());//1
        toReturn.add(event.getRep().getName());//2
        toReturn.add(event.getStatus());//3
        toReturn.add(event.getOrganization().getName());//4
        return toReturn;

    }


}
