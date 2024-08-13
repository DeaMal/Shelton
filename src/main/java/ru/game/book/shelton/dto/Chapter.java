package ru.game.book.shelton.dto;

import lombok.Data;

import java.util.List;

@Data
public class Chapter {
    private String text;
    private List<Link> links;
    private Integer money;
}
