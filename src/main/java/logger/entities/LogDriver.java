package logger.entities;


import logger.entities.enums.LogLevel;
import logger.helper.LogConfigurationHelper;

public final class LogDriver {

    private static LogDriver logDriver;
    private static LogConfigurationHelper logConfigurationHelper;
    private LogOutput logOutput;


    private LogDriver(LogLevel level, String prefix) {
        logConfigurationHelper = LogConfigurationHelper.getInstance(prefix, level);
        logOutput = new LogOutput();
    }

    public static LogDriver init(LogLevel level, String prefix){
        if(logDriver==null){
            logDriver = new LogDriver(level, prefix);
        }
        return logDriver;
    }

    public void log(LogLevel level, String message){

        if(!logConfigurationHelper.isLogLevelPrintable(level)){
            return;
        }
        String printMessage;
        if(logConfigurationHelper.getPrefix().isEmpty()){
            printMessage = message;
        } else {
            printMessage = logConfigurationHelper.getPrefix() + " " + message;

        }
        logOutput.print(printMessage);
    }
}
