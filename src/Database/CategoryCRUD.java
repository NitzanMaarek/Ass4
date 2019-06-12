package Database;

import Entities.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryCRUD {
    private Database db = null;

    public CategoryCRUD(Database db){
        this.db = db;
    }

    public void insertCategory(Category category){
        Connection conn = db.getConn();
        String sqlInsert = "INSERT INTO categories(name) VALUES(?)";
        try(PreparedStatement pstmt = conn.prepareStatement(sqlInsert)){
            pstmt.setString(1, category.getName());
            pstmt.executeUpdate();
        }

        catch (SQLException E){
            System.out.println(E.getMessage());
        }
        db.closeConnection();
    }


    public List<String> readAllCategories(){
        List<String> toReturn = new ArrayList<>();
        Connection conn = db.getConn();

        String sql = "SELECT * " +
                "FROM categories";
        try (PreparedStatement pstm = conn.prepareStatement(sql)){
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                String cat = rs.getString(1);
                toReturn.add(cat);
            }
        }
        catch (SQLException E){
            System.out.println(E.getMessage());
        }
        db.closeConnection();
        return toReturn;
    }



}
