package MVC.Model;


import Database.Database;
import Database.EventCRUD;
import Entities.Event;

import java.util.List;

public class Model {
    Database db = null;
    EventCRUD eventCRUD = null;


    public Model(){
        db = new Database();
        eventCRUD=new EventCRUD(db);

    }

    public List<Event> readAllEvents() {
        return null;
        //        return eventCRUD.read
    }

    public List<Event> searchEventByCategories(List<String> categories) {
        return null;
    }
}
