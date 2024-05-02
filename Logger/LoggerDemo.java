package Logger;

import java.io.FileWriter;
import java.io.IOException;

enum LogType{
    INFO, DEBUG, WARNING, ERROR;  
}

interface ILoggerSink{
    void log(String message);
}


class ConsoleLogger implements ILoggerSink{
    @Override
    public void log(String message){
        System.out.println(message);
    }
}

class FileLogger implements ILoggerSink{
    String fileName;

    public FileLogger(String fileName){
        this.fileName = fileName;
    }
    @Override
    public void log(String message){
        try(FileWriter writer = new FileWriter(fileName, true)){
            writer.write(message + '\n');
        } catch(IOException ex){
            System.out.println(ex.getStackTrace());
        }
    }
}

abstract class LoggerChain{

    private LoggerChain nextLogger;
    protected ILoggerSink sink;
    LoggerChain(LoggerChain nextLogger, ILoggerSink sink){
        this.nextLogger = nextLogger;
        this.sink = sink;
    }
    public void log(String message, LogType type){
        if(this.nextLogger != null)
            this.nextLogger.log(message,type);
    }
}
class InfoLogger extends LoggerChain{


    InfoLogger(LoggerChain nextLogger, ILoggerSink sink) {
        super(nextLogger, sink);
    }

    @Override
    public void log(String message, LogType type) {
        if(type == LogType.INFO){
            sink.log(type + " " +  message);
        }
        else super.log(message, type);
    }
}
class DebugLogger extends LoggerChain{


    DebugLogger(LoggerChain nextLogger, ILoggerSink sink) {
        super(nextLogger, sink);
    }

    @Override
    public void log(String message, LogType type) {
        if(type == LogType.DEBUG){
            sink.log(type + " " +  message);
        }
        else super.log(message, type);
    }
}
class WarningLogger extends LoggerChain{


    WarningLogger(LoggerChain nextLogger, ILoggerSink sink) {
        super(nextLogger, sink);
    }

    @Override
    public void log(String message, LogType type) {
        if(type == LogType.WARNING){
            sink.log(type + " " +  message);
        }
        else super.log(message, type);
    }
}
class ErrorLogger extends LoggerChain{


    ErrorLogger(LoggerChain nextLogger, ILoggerSink sink) {
        super(nextLogger, sink);
    }

    @Override
    public void log(String message, LogType type) {
        if(type == LogType.ERROR){
            sink.log(type + " " +  message);
        }
        else super.log(message, type);
    }
}

public class LoggerDemo {
    public static void main(String[] args){
        ILoggerSink sink = new FileLogger("abc.txt");
        ILoggerSink sink1 = new ConsoleLogger();
        LoggerChain logger = new InfoLogger(
            new DebugLogger(
                new WarningLogger(
                    new ErrorLogger(null, sink), sink1), sink), sink1);
        logger.log("test message", LogType.INFO);
        logger.log("test message1", LogType.ERROR);
        logger.log("test message2", LogType.DEBUG);
        logger.log("test message3", LogType.WARNING);
    }
}
