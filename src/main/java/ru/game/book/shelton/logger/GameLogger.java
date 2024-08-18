package ru.game.book.shelton.logger;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GameLogger {
    private static final String CHAPTER_LOAD = "Load chapter # {}";
    private static final String FILE_LOAD = "Loading file {}";
    private static final String TEST_LOGGING = "Test Value is: {}";
    private static final String OTHER_ERROR = "ERROR: {}";
    private static final String CHANGE_MONEY = "Changes in the Money parameter: {}";
    private static final String CHANGE_FOOD = "Changes in the Food parameter: {}";
    private static final String CHANGE_MASTERY = "Changes in the Mastery parameter: {}";
    private static final String CHANGE_STAMINA = "Changes in the Stamina parameter: {}";
    private static final String CHANGE_LUCK = "Changes in the Luck parameter: {}";

    public void loadChapter(String incomingString) {
        log.info(CHAPTER_LOAD, incomingString);
    }

    public void loadFile(String incomingString) {
        log.info(FILE_LOAD, incomingString);
    }

    public void testLogging(String incomingString) {
        log.info(TEST_LOGGING, incomingString);
    }

    public void moneyChange(String incomingString) {
        log.info(CHANGE_MONEY, incomingString);
    }

    public void foodChange(String incomingString) {
        log.info(CHANGE_FOOD, incomingString);
    }

    public void masteryChange(String incomingString) {
        log.info(CHANGE_MASTERY, incomingString);
    }

    public void staminaChange(String incomingString) {
        log.info(CHANGE_STAMINA, incomingString);
    }

    public void luckChange(String incomingString) {
        log.info(CHANGE_LUCK, incomingString);
    }

    public void error(String incomingString) {
        log.info(OTHER_ERROR, incomingString);
    }
}
