package RateLimiter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

class Token{
    private int tokenCount;
    private int timeStamp;
    Token(int tokenCount){
        this.tokenCount = tokenCount;
        this.timeStamp = (int) (new Date().getTime()/1000);
    }
    public int getTokenCount() {
        return tokenCount;
    }
    public void setTokenCount(int tokenCount) {
        this.tokenCount = tokenCount;
    }
    public int getTimeStamp() {
        return timeStamp;
    }
    public void setTimeStamp(int timeStamp) {
        this.timeStamp = timeStamp;
    }
    
}

class TokenManager{
    private static volatile TokenManager tokenManagerInstance = null;
    final private static int allowedTokenCount = 5;
    private Map<Integer, Token> userTokenCount = new HashMap<>();
    private TokenManager(){};
    public static TokenManager getTokenManagerInstance(){
        if(tokenManagerInstance == null){
            synchronized(TokenManager.class){
                if(tokenManagerInstance == null){
                    tokenManagerInstance = new TokenManager();
                }
            }
        }
        return tokenManagerInstance;
    }
    
    public int getTokenCount() {
        return allowedTokenCount;
    }
    void addUserToken(int userID){
        this.userTokenCount.put(userID, new Token(allowedTokenCount));
    }

    Token getTokenCountByUserID(int userID){
        return this.userTokenCount.get(userID);
    }

    void updateTokenByuserID(int userID, Token token){
        this.userTokenCount.put(userID, token);
    }

    void refillToken(int userID){
        Token currUserToken = getTokenCountByUserID(userID);
        int currTimeStamp = (int) (new Date().getTime()/1000);;
        System.out.println(currUserToken.getTimeStamp() + 20000   + " - " + currTimeStamp);
        if(currUserToken.getTimeStamp() + 1 > currTimeStamp){
            System.out.println("Refilling.....\n");
            currUserToken.setTokenCount(allowedTokenCount);
            currUserToken.setTimeStamp(currTimeStamp);
            updateTokenByuserID(userID, currUserToken);
        }
    }
}

interface IRateLimiterStrategy{
    int canRequestBeServed(int clientID);
}

class TokenBucket implements IRateLimiterStrategy{
    final static int allowedTokenCount = 5;
    TokenManager tokenManager;
    TokenBucket(TokenManager tokenManager){
        this.tokenManager = tokenManager;
    }
    @Override
    public int canRequestBeServed(int clientID){
        int httpCode = 200;
        tokenManager.refillToken(clientID);
        Token userToken = tokenManager.getTokenCountByUserID(clientID);
        if(userToken.getTokenCount() > 0){
            userToken.setTokenCount(userToken.getTokenCount() - 1);
            tokenManager.updateTokenByuserID(clientID, userToken);
        }
        else 
            httpCode = 429;
        return httpCode;
    }
}

class LeakyTokenBucket implements IRateLimiterStrategy{
    @Override
    public int canRequestBeServed(int clientID){
        int httpCode = 200;
        return httpCode;
    }
}

public class RateLimiterDemo {
    public static void main(String[] args) throws InterruptedException{
        TokenManager tokenManager = TokenManager.getTokenManagerInstance();
        int clientID = 1;
        tokenManager.addUserToken(clientID);
        IRateLimiterStrategy rateLimiterStrategy = new TokenBucket(tokenManager);
        int canAllow = rateLimiterStrategy.canRequestBeServed(clientID);
        System.out.println(canAllow);
        canAllow = rateLimiterStrategy.canRequestBeServed(clientID);
        System.out.println(canAllow);
        canAllow = rateLimiterStrategy.canRequestBeServed(clientID);
        System.out.println(canAllow);
        canAllow = rateLimiterStrategy.canRequestBeServed(clientID);
        System.out.println(canAllow);
        canAllow = rateLimiterStrategy.canRequestBeServed(clientID);
        System.out.println(canAllow);
        canAllow = rateLimiterStrategy.canRequestBeServed(clientID);
        System.out.println(canAllow);
        TimeUnit.SECONDS.sleep(10);
        canAllow = rateLimiterStrategy.canRequestBeServed(clientID);
        System.out.println(canAllow);
        canAllow = rateLimiterStrategy.canRequestBeServed(clientID);
        System.out.println(canAllow);
        canAllow = rateLimiterStrategy.canRequestBeServed(clientID);
        System.out.println(canAllow);
        canAllow = rateLimiterStrategy.canRequestBeServed(clientID);
        System.out.println(canAllow);
        canAllow = rateLimiterStrategy.canRequestBeServed(clientID);
        System.out.println(canAllow);
        canAllow = rateLimiterStrategy.canRequestBeServed(clientID);
        System.out.println(canAllow);
        canAllow = rateLimiterStrategy.canRequestBeServed(clientID);
        System.out.println(canAllow);
        canAllow = rateLimiterStrategy.canRequestBeServed(clientID);
        System.out.println(canAllow);
        canAllow = rateLimiterStrategy.canRequestBeServed(clientID);
        System.out.println(canAllow);
        canAllow = rateLimiterStrategy.canRequestBeServed(clientID);
        System.out.println(canAllow);
        
    }
}
