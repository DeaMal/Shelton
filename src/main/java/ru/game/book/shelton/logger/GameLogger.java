package ru.game.book.shelton.logger;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GameLogger {
    private static final String CHAPTER_LOAD = "Load chapter # {}";
    private static final String FILE_FOUND = "Found JSON file{}";
    private static final String READ_FILE_ERROR = "ERROR read JSON file: {}";
    private static final String OTHER_ERROR = "ERROR: {}";

    public void loadChapter(String incomingString) {
        log.info(CHAPTER_LOAD, incomingString);
    }

    public void fileFound(String incomingString) {
        log.info(FILE_FOUND, incomingString);
    }

    public void readFileError(String incomingString) {
        log.info(READ_FILE_ERROR, incomingString);
    }

    public void error(String incomingString) {
        log.info(OTHER_ERROR, incomingString);
    }
}
