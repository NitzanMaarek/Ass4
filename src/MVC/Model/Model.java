package MVC.Model;


import Database.Database;
import Database.EventCRUD;
import Database.EventscateogiresconnectionsCRUD;
import Entities.*;

import java.util.*;

import Database.CategoryCRUD;

import javax.print.DocFlavor;

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

    public void addEvent(Map<String, String> event) {
        String[] categories = event.get("categories").split(",");
        Set<Category> categoriesSet = new HashSet<>();
        for(int i=0; i<categories.length; i++){
            categoriesSet.add(new Category(categories[i]));
        }
        Organization org = new Organization("Fire Department");
        User user = new User("Fire Department user", org);
        String title = event.get("title");
        Update update = new Update(event.get("update"));
        List<Update> updates = new ArrayList<>();
        updates.add(update);
        Event eventToAdd = new Event(title, user, org, categoriesSet, updates);
        insertEvent(eventToAdd);
    }

    public void createNewUser(){
System.out.println("Implement me");
    }
    public void fileComplaint(){
        System.out.println("Implement me");
    }
    public void sendNotice(){
        System.out.println("Implement me");
    }
    public void giveFeedback(){
        System.out.println("Implement me");
    }
    public void sendGrades(){
        System.out.println("Implement me");
    }
    public void displayAccountSettings(){
        System.out.println("Implement me");
    }
    public void changePassword(){
        System.out.println("Implement me");
    }
    public void createEvent(){
        System.out.println("Implement me");
    }
    public void addEventUpdate(){
        System.out.println("Implement me");
    }
    public void chooseUser(){
        System.out.println("Implement me");
    }
    public void displayCategories(){
        System.out.println("Implement me");
    }
    public void displayEvenets(){
        System.out.println("Implement me");
    }
    public void displayPreviousEventUpdates(){
        System.out.println("Implement me");
    }

}
