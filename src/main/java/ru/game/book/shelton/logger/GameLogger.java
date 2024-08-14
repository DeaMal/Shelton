package ru.game.book.shelton.logger;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GameLogger {
    private static final String CHAPTER_LOAD = "Load chapter # {}";
    private static final String FILE_LOAD = "Loading file {}";
    private static final String TEST_LOGGING = "Test Value is: {}";
    private static final String OTHER_ERROR = "ERROR: {}";

    public void loadChapter(String incomingString) {
        log.info(CHAPTER_LOAD, incomingString);
    }

    public void loadFile(String incomingString) {
        log.info(FILE_LOAD, incomingString);
    }

    public void testLogging(String incomingString) {
        log.info(TEST_LOGGING, incomingString);
    }

    public void error(String incomingString) {
        log.info(OTHER_ERROR, incomingString);
    }
}
