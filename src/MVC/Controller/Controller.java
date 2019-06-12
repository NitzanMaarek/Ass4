package MVC.Controller;

import Entities.Event;
import MVC.Model.Model;

import java.util.List;

public class Controller {

    private Model myModel;

    public Controller(Model myModel){
        this.myModel = myModel;
    }

    public List<Event> readAllEvents() {
        return myModel.readAllEvents();
    }

    public List<Event> searchEventByCategories(List<String> categories) {
        return myModel.searchEventByCategories(categories);
    }
}
