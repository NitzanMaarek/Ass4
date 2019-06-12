package MVC.Controller;

import Entities.Event;
import MVC.Model.Model;

import java.util.List;
import java.util.Map;

public class Controller {

    private Model myModel;

    public Controller(Model myModel){
        this.myModel = myModel;
    }

    public List<Map<String,String>> readAllEvents() {
        return myModel.readAllEvents();
    }

    public List<Map<String,String>> searchEventByCategories(List<String> categories) {
        return myModel.searchEventByCategories(categories);
    }

    public List<String> getAllCategories() {
        return null;
    }

    public void addEvent(Map<String, String> event) {
        myModel.addEvent(event);
    }
}
