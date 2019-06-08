package Entities;

public class User {

    private String name;
    private int rank;               //between 1 - 10
    private String accountStatus;   //activated, deactivated, locked
    private String email;
    private String password;
    private int warningCounter;     //3 warnings means demotion in rank
    private int passAttempts;       //i think 3 pass attempts means account status should be locked.
    private Organization organization;
    private EntityController entityController = EntityController.getInstance();


    public User(){

    }

    public User(String name, int rank, String email, String password, Organization organization){
        this.name = name;
        this.rank = rank;
        this.email = email;
        this.password = password;
        this.organization = organization;
    }



    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public void setWarningCounter(int warningCounter) {
        this.warningCounter = warningCounter;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void changePassword(String password){
        this.password = password;
    }

    public void setAsRepresentative(){
        System.out.println("Implement me");
    }

    public void askForGrades(){
        System.out.println("Implement me");
    }

    public void addLink(){
        System.out.println("Implement me");
    }

    public void getAccountAttributes(){
        System.out.println("Implement me");
    }

    public void addEvent(){
        System.out.println("Implement me");
    }

    public void addComplaint(){
        System.out.println("Implement me");
    }

    public void addUpdate(){
        System.out.println("Implement me");
    }

    public void addCategoryToEvent(){
        System.out.println("Implement me");
    }

    public void getEvents(){
        System.out.println("Implement me");
    }
}
