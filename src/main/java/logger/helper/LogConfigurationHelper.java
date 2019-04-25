package logger.helper;


import logger.entities.enums.LogLevel;

public class LogConfigurationHelper {

    private static LogConfigurationHelper helper;

    private String prefix;
    private LogLevel loglevel;

    private LogConfigurationHelper(String prefix, LogLevel level) {
        this.prefix = prefix;
        this.loglevel = level;
    }

    public static LogConfigurationHelper getInstance(String prefix, LogLevel level){
        if(helper==null){
            helper =  new LogConfigurationHelper( prefix, level);
        }
        return helper;
    }

    public boolean isLogLevelPrintable(LogLevel level){
        //add condtion
        if(this.loglevel==LogLevel.ERROR) {
            return true;
        }
        if(this.loglevel==LogLevel.DEBUG && (level == LogLevel.DEBUG || level == LogLevel.INFO  )) {
            return true;
        }
        if(this.loglevel==LogLevel.INFO && level == LogLevel.INFO) {
            return true;
        }
        return false;
    }

    public String getPrefix() {
        if(prefix.equals("Timestamp"))
            return String.valueOf(System.currentTimeMillis());
        else
            return "";
    }

}
