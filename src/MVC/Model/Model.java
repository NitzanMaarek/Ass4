package MVC.Model;


import Database.Database;
import Database.EventCRUD;
import Entities.Event;

public class Model {
    Database db = null;
    EventCRUD eventCRUD = null;


    public Model(){
        db = new Database();
        eventCRUD=new EventCRUD(db);

    }
}
