package Entities;

public final class EntityController {
    private static EntityController instance = null;


    private EntityController(){

    }

    public static EntityController getInstance(){
        if(instance == null){
            instance = new EntityController();
        }
        return instance;
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
