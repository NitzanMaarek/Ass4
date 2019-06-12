package MVC.Model;


import Database.Database;
import Database.EventCRUD;
import Entities.Category;
import Entities.Event;
import Database.CategoryCRUD;
import java.util.List;
import java.util.Map;

public class Model {
    Database db = null;
    EventCRUD eventCRUD = null;
    CategoryCRUD categoryCRUD=null;


    public Model(){
        db = new Database();
        eventCRUD=new EventCRUD(db);
        categoryCRUD= new CategoryCRUD(db);

    }

    public List<Map<String,String>> readAllEvents() {
        return eventCRUD.readAllEvents();
    }

    public void insertEvent(Event event){
        eventCRUD.insertEvent(event);
    }

    public void insertCategory(Category category){
        categoryCRUD.insertCategory(category);
    }

    public List<String> readAllCategories(){
        return categoryCRUD.readAllCategories();
    }

    public List<Map<String,String>> searchEventByCategories(List<String> categories) {
        return null;
    }
}
