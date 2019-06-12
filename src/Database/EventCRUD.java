package Database;

import Entities.Event;
import sun.plugin.javascript.navig.LinkArray;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<Map<String,String>> readAllEvents(){
        List<Map<String,String>> toReturn = new ArrayList<>();
        Connection conn = db.getConn();

        String sql = "SELECT * " +
                "FROM events";
        try (PreparedStatement pstm = conn.prepareStatement(sql)){
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                String exId =String.valueOf(rs.getInt(1));
                String title = rs.getString(2);
                String date = rs.getString(3);
                String rep = rs.getString(4);
                String status = rs.getString(5);
                String categories = rs.getString(6);
                Map<String,String> tmp = new HashMap<>();
                tmp.put("id",exId);
                tmp.put("title",title);
                tmp.put("datePublished",date);
                tmp.put("repName",rep);
                tmp.put("status",status);
                tmp.put("organization",categories);
                toReturn.add(tmp);
//                exchange.setExchangeId(Integer.toString(exId));
//                toReturn.add(exchange);
            }
        }
        catch (SQLException E){
            System.out.println(E.getMessage());
        }
        db.closeConnection();
        return toReturn;
    }

    public int getLastId(){
        Connection conn = db.getConn();
        int toReturn=0;

        ResultSet rs;
        String sql = "SELECT * " +
                "    FROM    events" +
                "    WHERE   id = (SELECT MAX(ID)  FROM events);";

        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
             rs = pstmt.executeQuery();
             toReturn = rs.getInt(1);

        }
        catch (SQLException E){
            System.out.println(E.getMessage());
        }
    return toReturn;
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
