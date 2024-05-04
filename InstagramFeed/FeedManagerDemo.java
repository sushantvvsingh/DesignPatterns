package InstagramFeed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

enum UserType{
    NORMAL, CELEBRITY;
}

class User{
    private int id;
    private String name;
    private String username;
    private String email;
    private String password;
    private UserType userType;
    
    public User(int id, String name, String username, String email, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
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
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public UserType getUserType() {
        return userType;
    }
    public void setUserType(UserType userType) {
        this.userType = userType;
    }
    
    
}

class Post{
    int id;
    List<String> comments = new ArrayList<>();
    int likes;
    String content;
    String url;
    
    public Post(int id, String content) {
        this.id = id;
        this.content = content;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public List<String> getComments() {
        return comments;
    }
    public void setComments(List<String> comments) {
        this.comments = comments;
    }
    public int getLikes() {
        return likes;
    }
    public void setLikes(int likes) {
        this.likes = likes;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    
}

class UserManager{
    private static volatile UserManager userManager = null;
    private UserManager(){};
    private List<User> users = new ArrayList<>();
    private Map<Integer, List<Integer>> followers = new HashMap<>();
    private Map<Integer, List<Integer>> followees = new HashMap<>();
    public static UserManager getUserManager(){
        if(userManager == null){
            synchronized(UserManager.class){
                if(userManager == null){
                    userManager = new UserManager();
                }
            }
        }
        return userManager;
    }
    public void addUser(User user){
        this.users.add(user);
    }
    public void deleteUser(User user){
        this.users.remove(user);
    }
    public User getUserbyID(int userID){
        return this.users.stream().filter(user -> user.getId() == userID).findFirst().get();
    }
    public void addFollower(int followerID, int followeeID){
        if(!this.followers.containsKey(followerID)){
            this.followers.put(followerID, new ArrayList<>());
        }
        if(!this.followees.containsKey(followeeID)){
            this.followees.put(followeeID, new ArrayList<>());
        }
        this.followers.get(followeeID).add(followerID);
        this.followees.get(followerID).add(followeeID);
    }
}

class PostManager{
    private static volatile PostManager postManager = null;
    private PostManager(){};
    private Map<Integer, List<Post>> userPost = new HashMap<>();
    private UserManager userManager;
    private FeedManager feedManager;
    public static PostManager getPostmanager(){
        if(postManager == null){
            synchronized(PostManager.class){
                if(postManager == null){
                    postManager = new PostManager();
                }
            }
        }
        return postManager;
    }
    public void setUserManager(UserManager userManager){
        this.userManager = userManager;
    }

    public void setFeedManager(FeedManager feedManager){
        this.feedManager = feedManager;
    }
    public Post createPost(int userID, int id, String content){
        Post post = new Post(id, content);
        return post;
    }
    public void addPost(int userID, Post post){
        if(!this.userPost.containsKey(userID)){
            this.userPost.put(userID, new ArrayList<>());
        }
        this.userPost.get(userID).add(post);
        this.feedManager.createFeed(this.userManager.getUserbyID(userID), post);
    }
}

class FeedManager{
    private static volatile FeedManager feedManager = null;
    private FeedManager(){};
    private ICreateFeed createFeed;
    public static FeedManager getPostmanager(){
        if(feedManager == null){
            synchronized(FeedManager.class){
                if(feedManager == null){
                    feedManager = new FeedManager();
                }
            }
        }
        return feedManager;
    }
    public void setCreateFeed(ICreateFeed createFeed){
        this.createFeed = createFeed;
    }
    public void createFeed(User user, Post post){
        
    }
}

interface ICreateFeed{
    List<Post> createFeed(int userID, Post post);
}

class CreateFeedCache implements ICreateFeed{

    @Override
    public List<Post> createFeed(int userID, Post post) {
        return null;
    }
}

class CreateFeedOnTheFly implements ICreateFeed{

    @Override
    public List<Post> createFeed(int userID, Post post) {
        return null;
    }
}

public class FeedManagerDemo {
    public static void main(String[] args){

    }
}
