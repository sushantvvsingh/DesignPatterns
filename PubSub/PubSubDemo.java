package PubSub;

import java.util.ArrayList;
import java.util.List;

interface IObservable{
     void addObserver(IObserver observer);
     void removeObserver(IObserver observer);
     void notifyObserver();
     String getData();
}

class TopicA implements IObservable{

    private List<IObserver> observers = new ArrayList<>();
    private String data;

    @Override
    public void addObserver(IObserver observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for (IObserver observer : this.observers) {
            observer.updateObserver();
        }
    }
    @Override
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }   
    
}

class TopicB implements IObservable{

    private List<IObserver> observers = new ArrayList<>();
    private String data;

    @Override
    public void addObserver(IObserver observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for (IObserver observer : this.observers) {
            observer.updateObserver();
        }
    }
    @Override 
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }  
}

interface IObserver{
    void updateObserver();
}

class UserMetadata{
    private int id;
    private String name;
    private String emailAddress;
    private String phoneNumber;
    public UserMetadata(int id, String name, String emailAddress, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
    }
    public String getName(){
        return this.name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
}

class User implements IObserver{

    private UserMetadata userMetadata;
    private IObservable observable;
    private Notificationfactory notificationfactory;
    private NotificationType notificationType;

    public User(UserMetadata userMetadata, IObservable observable, 
    Notificationfactory notificationfactory, NotificationType notificationType) {
        this.userMetadata = userMetadata;
        this.observable = observable;
        this.notificationfactory = notificationfactory;
        this.notificationType = notificationType;
    }

    @Override
    public void updateObserver() {
        String newData = this.observable.getData();
        this.notificationfactory.sendNotification(this.notificationType, userMetadata , newData);
    }

}

enum NotificationType{
    PHONE, EMAIL;
}

class Notificationfactory{
    private INotification notificationInst;

    public Boolean sendNotification(NotificationType type, UserMetadata user, String message){
        if(type == NotificationType.EMAIL){
            this.notificationInst = new EmailNotification();
        }
        else if(type == NotificationType.PHONE){
            this.notificationInst = new PhoneNotification();
        }
        else this.notificationInst = null;
        if(this.notificationInst == null){
            return false;
        }
        this.notificationInst.sendNotification(user, message);
        return true;
    }
}

interface INotification{
    Boolean sendNotification(UserMetadata user, String message);
}

class EmailNotification implements INotification{

    @Override
    public Boolean sendNotification(UserMetadata user, String message) {
        System.out.println(message + " Notification sent to " + user.getName() + " on " + user.getEmailAddress() + " via Email");
        return true;
    }
}

class PhoneNotification implements INotification{

    @Override
    public Boolean sendNotification(UserMetadata user, String message) {
        System.out.println(message + " Notification sent to " + user.getName() + " on " + user.getPhoneNumber() + " via Phone");
        return true;
    }
}

public class PubSubDemo {
    public static void main(String[] args){
        Notificationfactory notificationfactory = new Notificationfactory();
        TopicA topicA = new TopicA();
        TopicB topicB = new TopicB();
        UserMetadata userMetadata1 = new UserMetadata(1, "Sushant", "sushant@gmail.com", "+91888888");
        UserMetadata userMetadata2 = new UserMetadata(1, "SushantNew", "SushantNew@gmail.com", "+918800008");
        User user1 = new User(userMetadata1, topicA, notificationfactory, NotificationType.EMAIL);
        User user2 = new User(userMetadata2, topicB, notificationfactory, NotificationType.PHONE);
        topicA.addObserver(user1);
        topicB.addObserver(user2);
        topicA.setData("Message from topic A");
        topicB.setData("Message from topic B");
        topicA.notifyObserver();
        topicB.notifyObserver();
    }
    
}
