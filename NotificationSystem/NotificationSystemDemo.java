package NotificationSystem;

import java.util.ArrayList;
import java.util.List;

enum UserPreference{
    PHONE, EMAIL;
}

class User{
    private int id;
    private String name;
    private String emailAddress;
    private String phoneNumber;
    UserPreference preference;

    public User(int id, String name, String emailAddress, String phoneNumber, UserPreference preference) {
        this.id = id;
        this.name = name;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.preference = preference;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public UserPreference getUserPreference() {
        return preference;
    }
}

class UserManager{
    public static volatile UserManager userManagerInstance = null;
    private List<User> users = new ArrayList<>();
    private UserManager(){}
    public static UserManager getUserManager(){
        if(userManagerInstance == null){
            synchronized(UserManager.class){
                if(userManagerInstance == null){
                    userManagerInstance = new UserManager();
                }
            }
        }
        return userManagerInstance;
    }

    public List<User> getUsers(){
        return this.users;
    }

    public void addUser(User user){
        this.users.add(user);
    }

    public void removeUser(User user){
        this.users.remove(user);
    }
}

interface INotification{
    Boolean sendNotification(User user);
}

class EmailNotification implements INotification{

    @Override
    public Boolean sendNotification(User user) {
        System.out.println("Notification sent to " + user.getName() + " on " + user.getEmailAddress() + " via Email");
        return true;
    }
}

class PhoneNotification implements INotification{

    @Override
    public Boolean sendNotification(User user) {
        System.out.println("Notification sent to " + user.getName() + " on " + user.getPhoneNumber() + " via Phone");
        return true;
    }
}

public class NotificationSystemDemo {
    public static void main(String[] args){
        UserManager userManager = UserManager.getUserManager();
        User user1 = new User(1, "Sushant", "sushant@gmail.com", "+91850000000", UserPreference.EMAIL);
        User user2 = new User(1, "Akash", "akash@gmail.com", "+91851110000", UserPreference.PHONE);
        userManager.addUser(user1);
        userManager.addUser(user2);
        INotification phoneNotification = new PhoneNotification();
        INotification emailNotification = new EmailNotification();
        for(User user: userManager.getUsers()){
            if(user.getUserPreference() == UserPreference.EMAIL){
                emailNotification.sendNotification(user);
            }
            else if(user.getUserPreference() == UserPreference.PHONE){
                phoneNotification.sendNotification(user);
            }
        }
    }
}
