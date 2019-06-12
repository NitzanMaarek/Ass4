package MVC.Model;


import Database.Database;
import Database.EventCRUD;
import Entities.Event;

import java.util.List;
import java.util.Map;

public class Model {
    Database db = null;
    EventCRUD eventCRUD = null;


    public Model(){
        db = new Database();
        eventCRUD=new EventCRUD(db);

    }

    public List<Map<String,String>> readAllEvents() {
        return eventCRUD.readAllEvents();
    }

    public void insertEvent(Event event){
        eventCRUD.insertEvent(event);
    }

    public List<Map<String,String>> searchEventByCategories(List<String> categories) {
        return null;
    }
}
