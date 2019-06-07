import java.util.Set;

public class Organization {

    private String name;
    private Set<User> users;
    private Set<Event> events;
    private Admin adminUser;

    public Organization(String name){
        this.name = name;
    }

    public Admin getAdminUser(){
        return adminUser;
    }

    public Set<User> getNormalUsers(){
        return users;
    }

    public void addUser(User user){
        this.users.add(user);
    }


}

