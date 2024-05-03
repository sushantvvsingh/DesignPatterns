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
    LoggerChain(LoggerChain nextLogger){
        this.nextLogger = nextLogger;
    }
    public void log(String message, LogType type, ILoggerSink sink){
        if(this.nextLogger != null)
            this.nextLogger.log(message,type, sink);
    }
}
class InfoLogger extends LoggerChain{


    InfoLogger(LoggerChain nextLogger) {
        super(nextLogger);
    }

    @Override
    public void log(String message, LogType type, ILoggerSink sink) {
        if(type == LogType.INFO){
            sink.log(type + " " +  message);
        }
        else super.log(message, type, sink);
    }
}
class DebugLogger extends LoggerChain{


    DebugLogger(LoggerChain nextLogger) {
        super(nextLogger);
    }

    @Override
    public void log(String message, LogType type, ILoggerSink sink) {
        if(type == LogType.DEBUG){
            sink.log(type + " " +  message);
        }
        else super.log(message, type, sink);
    }
}
class WarningLogger extends LoggerChain{


    WarningLogger(LoggerChain nextLogger) {
        super(nextLogger);
    }

    @Override
    public void log(String message, LogType type, ILoggerSink sink) {
        if(type == LogType.WARNING){
            sink.log(type + " " +  message);
        }
        else super.log(message, type, sink);
    }
}
class ErrorLogger extends LoggerChain{


    ErrorLogger(LoggerChain nextLogger) {
        super(nextLogger);
    }

    @Override
    public void log(String message, LogType type, ILoggerSink sink) {
        if(type == LogType.ERROR){
            sink.log(type + " " +  message);
        }
        else super.log(message, type, sink);
    }
}

public class LoggerDemo {
    public static void main(String[] args){
        ILoggerSink sink = new FileLogger("abc.txt");
        ILoggerSink sink1 = new ConsoleLogger();
        LoggerChain logger = new InfoLogger(new DebugLogger(new WarningLogger(new ErrorLogger(null))));
        logger.log("test message", LogType.INFO, sink1);
        logger.log("test message1", LogType.ERROR, sink);
        logger.log("test message2", LogType.DEBUG, sink1);
        logger.log("test message3", LogType.WARNING, sink);
    }
}
