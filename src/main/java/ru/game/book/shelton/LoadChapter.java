package ru.game.book.shelton;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.game.book.shelton.dto.Chapter;
import ru.game.book.shelton.dto.Chapters;
import ru.game.book.shelton.logger.GameLogger;

import java.io.InputStream;
import java.util.Objects;

public class LoadChapter {
    private static final GameLogger logger = new GameLogger();
    private static final String SUCCESS = " IS SUCCESS";
    private static final String FAIL = " IS FAIL";
    private Chapters chapters;

    public LoadChapter() {
        getChaptersFromJSON();
    }

    private void getChaptersFromJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            InputStream jsonFile = getClass().getResourceAsStream("/ru/game/book/shelton/data.json");
            if (Objects.nonNull(jsonFile)) {
                logger.loadFile(SUCCESS);
                chapters = objectMapper.readValue(jsonFile, Chapters.class);
            } else {
                logger.loadFile(FAIL);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new IllegalArgumentException();
        }
    }

    public Chapter getBlock(String blockName) {
        Chapter chapter = chapters.getChapters().get(blockName);
        if (Objects.nonNull(chapter)) {
            logger.loadChapter(blockName + SUCCESS);
        } else {
            logger.loadChapter(blockName + FAIL);
        }
        return chapter;
    }
}
