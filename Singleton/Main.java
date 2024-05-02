package Singleton;

class SingletonDemo{
    private static volatile SingletonDemo singletonDemo;
    private SingletonDemo(){};
    public static SingletonDemo getSingletonInstance(){
        if(singletonDemo == null){
            synchronized(SingletonDemo.class){
                if(singletonDemo == null){
                    singletonDemo = new SingletonDemo();
                }
            }
        }
        return singletonDemo;
    }
}

public class Main {
    
}
