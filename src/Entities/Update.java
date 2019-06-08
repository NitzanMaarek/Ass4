package Entities;

public class Update {

    private String dateUpdated;
    private String description;
    private String initialDescription;
    private Update previousUpdate = null;

    public Update(String initialDescription){
        this.initialDescription = initialDescription;
        //handle Date.
    }

    public void addEvent(){
        System.out.println("Implement me");
    }

    public void getLatestUpdate(){
        System.out.println("Implement me");
    }

    public void changeDescription(){
        System.out.println("Implement me");
    }

}
