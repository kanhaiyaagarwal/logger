package logger.entities;

import static org.junit.Assert.*;

import org.junit.Test;

import logger.entities.enums.LogLevel;

public class LogDriverTest {



    @Test
    public void log() {

        LogDriver driver = LogDriver.init(LogLevel.INFO, "Timestamp");

        driver.log(LogLevel.INFO , "This is a sample log");
    }
}