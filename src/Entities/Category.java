package Entities;

import java.util.Set;

public class Category {
    private String name;
    private Set<Event> events;

    public Category(String name){
        this.name = name;
    }

    public void addEvent(Event event){
        this.events.add(event);
    }

    public String getName() {
        return name;
    }
}
