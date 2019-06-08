package Entities;

import java.util.Set;

public class Event {

    private String title;
    private String datePublished;
    private String status;
    private Set<Category> categories;

    public Event(String title, Set<Category> categories){
        this.title = title;
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


}


