package ru.game.book.shelton.dto;

import lombok.Data;

import java.util.Map;

@Data
public class Chapters {
    private Map<String, Chapter> chapters;
}
