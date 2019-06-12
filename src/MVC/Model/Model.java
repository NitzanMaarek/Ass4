package MVC.Model;


import Database.Database;
import Database.EventCRUD;
import Database.EventscateogiresconnectionsCRUD;
import Entities.Category;
import Entities.Event;
import Database.CategoryCRUD;

import javax.print.DocFlavor;
import java.util.List;
import java.util.Map;

public class Model {
    Database db = null;
    EventCRUD eventCRUD = null;
    CategoryCRUD categoryCRUD=null;
    EventscateogiresconnectionsCRUD eventscateogiresconnectionsCRUD = null;


    public Model(){
        db = new Database();
        eventCRUD=new EventCRUD(db);
        categoryCRUD= new CategoryCRUD(db);
        eventscateogiresconnectionsCRUD = new EventscateogiresconnectionsCRUD(db);

    }

    public List<Map<String,String>> readAllEvents() {
        return eventCRUD.readAllEvents();
    }


    public void insertEvent(Event event){
        eventCRUD.insertEvent(event);
        int lastIndex =  eventCRUD.getLastId();
        if (event.getCategories()!=null) {
            for (Category category : event.getCategories()) {
                insertEventsCateogiresConnections(lastIndex, category.getName());
            }
        }
    }


    public void insertCategory(Category category){
        categoryCRUD.insertCategory(category);
    }

    public List<String> readAllCategories(){
        return categoryCRUD.readAllCategories();
    }

    public void insertEventsCateogiresConnections(int id , String category){
        eventscateogiresconnectionsCRUD.insertEventsCateogiresConnections(id,category);
    }

    public List<String> readEventsCateogiresConnectionsByID(int eventID){
        return eventscateogiresconnectionsCRUD.readEventsCateogiresConnectionsByID(eventID);
    }


    public List<Map<String,String>> searchEventByCategories(List<String> categories) {
        return null;
    }
}
