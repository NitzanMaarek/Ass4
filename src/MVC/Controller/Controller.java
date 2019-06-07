package MVC.Controller;

public final class Controller {
    private static Controller instance = null;

    private Controller(){
        System.out.println("MVC.Controller.Controller initialized - DELETE THIS MESSAGE");
    }

    public static Controller getInstance(){
        if(instance == null){
            instance = new Controller();
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
