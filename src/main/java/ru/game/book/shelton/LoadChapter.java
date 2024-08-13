package ru.game.book.shelton;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.game.book.shelton.dto.Chapter;
import ru.game.book.shelton.dto.Chapters;
import ru.game.book.shelton.logger.GameLogger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class LoadChapter {
    private static final GameLogger logger = new GameLogger();
    private static final String SUCCESS = " IS SUCCESS";
    private static final String FAIL = " IS FAIL";

    public Chapter loadMessageFromJson(String blockName) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            InputStream jsonFile = getClass().getResourceAsStream("/ru/game/book/shelton/data.json");
            if (Objects.nonNull(jsonFile)) {
                logger.fileFound(SUCCESS);
                Chapters chapters = objectMapper.readValue(jsonFile, Chapters.class);
                Chapter chapter = chapters.getChapters().get(blockName);
                if (Objects.nonNull(chapter)) {
                    logger.loadChapter(blockName + SUCCESS);
                    return chapter;
                }
            } else {
                logger.fileFound(FAIL);
            }
        } catch (IOException e) {
            logger.readFileError(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        logger.loadChapter(blockName + FAIL);
        throw new IllegalArgumentException();
    }
}
