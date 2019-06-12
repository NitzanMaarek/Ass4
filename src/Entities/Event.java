package Entities;

import java.util.Date;
import java.util.Set;

public class Event {

    private String id;
    private String title;
    private String datePublished;
    private String status;
    private User rep;
    private Organization organization;
    private Set<Category> categories;


    public Event(String title,User rep,Organization organization, Set<Category> categories){
        this.title = title;
        this.rep= rep;
        this.organization = organization;
        this.datePublished = new Date().toString();
        status="in progress";
        if(categories!=null){
            this.categories.addAll(categories);
        }
    }

    public Event(int id ,String title, String datePublished,User rep,Organization organization, Set<Category> categories){
        this.title = title;
        this.datePublished=datePublished;
        this.rep= rep;
        this.organization = organization;
        this.categories.addAll(categories);
    }


    public void getParticipants(){
        System.out.println("Implement me");
    }

    public void getReport(){
        System.out.println("Implement me");
    }

    public void addCategory(Category category){
        this.categories.add(category);
    }

    public void getUpdates(){
        System.out.println("Implement me");
    }

    public void getLatestUpdate(){
        System.out.println("Implement me");
    }

    public Set<Category> getCategory(){
        return this.categories;
    }

    public void editUpdate(){
        System.out.println("Implement me");
    }

    public String getTitle() {
        return title;
    }

    public String getDatePublished() {
        return datePublished;
    }

    public String getStatus() {
        return status;
    }

    public User getRep() {
        return rep;
    }

    public Organization getOrganization() {
        return organization;
    }

    public String getId() {
        return id;
    }
}


