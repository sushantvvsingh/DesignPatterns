package Logger;

import java.io.FileWriter;
import java.io.IOException;

enum LogType{
    INFO, DEBUG, WARNING, ERROR;
}

interface ILogger{
    void logMessage(String message);
}

class ConsoleDebugLogger implements ILogger{
    @Override
    public void logMessage(String message){
        System.out.println(LogType.DEBUG + " " + message);
    }
}

class ConsoleInfoLogger implements ILogger{
    @Override
    public void logMessage(String message){
        System.out.println(LogType.INFO + " " + message);
    }
}

class ConsoleWarningLogger implements ILogger{
    @Override
    public void logMessage(String message){
        System.out.println(LogType.WARNING + " " + message);
    }
}

class ConsoleErrorLogger implements ILogger{
    @Override
    public void logMessage(String message){
        System.out.println(LogType.ERROR + " " + message);
    }
}

class FileDebugLogger implements ILogger{
    String fileName;

    public FileDebugLogger(String fileName){
        this.fileName = fileName;
    }
    @Override
    public void logMessage(String message){
        try(FileWriter writer = new FileWriter(fileName)){
            writer.write(LogType.DEBUG + " " + message);
        } catch(IOException ex){
            System.out.println(ex.getStackTrace());
        }
    }
}

class FileInfoLogger implements ILogger{
    String fileName;

    public FileInfoLogger(String fileName){
        this.fileName = fileName;
    }
    @Override
    public void logMessage(String message){
        try(FileWriter writer = new FileWriter(fileName)){
            writer.write(LogType.INFO + " " + message);
        } catch(IOException ex){
            System.out.println(ex.getStackTrace());
        }
    }
}

class FileWarningLogger implements ILogger{
    String fileName;

    public FileWarningLogger(String fileName){
        this.fileName = fileName;
    }
    @Override
    public void logMessage(String message){
        try(FileWriter writer = new FileWriter(fileName)){
            writer.write(LogType.WARNING + " " + message);
        } catch(IOException ex){
            System.out.println(ex.getStackTrace());
        }
    }
}

class FileErrorLogger implements ILogger{
    String fileName;

    public FileErrorLogger(String fileName){
        this.fileName = fileName;
    }
    @Override
    public void logMessage(String message){
        try(FileWriter writer = new FileWriter(fileName)){
            writer.write(LogType.ERROR + " " + message);
        } catch(IOException ex){
            System.out.println(ex.getStackTrace());
        }
    }
}

interface ILoggerfactory{
    ILogger createDebugLogger();
    ILogger createInfoLogger();
    ILogger createWarningLoggert();
    ILogger createErrorLogger();
}


class ConsoleLoggerfactory implements ILoggerfactory{

    @Override
    public ILogger createDebugLogger() {
        return new ConsoleDebugLogger();
    }

    @Override
    public ILogger createInfoLogger() {
        return new ConsoleInfoLogger();
    }

    @Override
    public ILogger createWarningLoggert() {
        return new ConsoleWarningLogger();
    }

    @Override
    public ILogger createErrorLogger() {
        return new ConsoleErrorLogger();
    }
    
}

class FileLoggerFactory implements ILoggerfactory{

    private String fileName;
    public FileLoggerFactory(String fileName){
        this.fileName = fileName;
    }
    @Override
    public ILogger createDebugLogger() {
        return new FileDebugLogger(fileName);
    }

    @Override
    public ILogger createInfoLogger() {
        return new FileInfoLogger(fileName);
    }

    @Override
    public ILogger createWarningLoggert() {
        return new FileWarningLogger(fileName);
    }

    @Override
    public ILogger createErrorLogger() {
        return new FileDebugLogger(fileName);
    }
    
}

public class Main{
    public static void main(String[] Args)
    {

    }
}