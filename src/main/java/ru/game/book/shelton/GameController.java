package ru.game.book.shelton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import ru.game.book.shelton.dto.Chapter;
import ru.game.book.shelton.dto.Link;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameController {
    LoadChapter loadChapter = new LoadChapter();
    private List<Label> textLinks;
    private List<Button> links;

    @FXML
    private Button startButton;
    @FXML
    private Label chapterNumber;
    @FXML
    private TextArea chapterText;
    @FXML
    private Label textLink1;
    @FXML
    private Button link1;
    @FXML
    private Label textLink2;
    @FXML
    private Button link2;
    @FXML
    private Label textLink3;
    @FXML
    private Button link3;

    @FXML
    public void initialize() {
        textLinks = new ArrayList<>();
        textLinks.add(textLink1);
        textLinks.add(textLink2);
        textLinks.add(textLink3);
        links = new ArrayList<>();
        links.add(link1);
        links.add(link2);
        links.add(link3);
        chapterText.setText(loadChapter.loadMessageFromJson("0").getText());
    }

    @FXML
    protected void onStartButtonClick() {
        startButton.setVisible(false);
        link1.setText("1");
        link1.fire();
    }

    @FXML
    protected void onButtonClick(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String buttonText = clickedButton.getText();
        Chapter chapter = loadChapter.loadMessageFromJson(buttonText);

        textLinks.forEach(item -> {
            item.setText("");
            item.setVisible(false);
            item.setMaxHeight(0);
        });
        links.forEach(item -> {
            item.setText("");
            item.setVisible(false);
            item.setMaxHeight(0);
        });

//        startButton.setVisible(false);
        chapterNumber.setText(buttonText);
        chapterText.setText(chapter.getText());
        List<Link> listLinks = chapter.getLinks();
        int i = 0;
        if (Objects.nonNull(links)) {
            for (Link link : listLinks) {
                String textLink = link.getText();
                String linkValue = link.getLink();
                if (Objects.nonNull(linkValue) && !linkValue.isEmpty()) {
                    textLinks.get(i).setVisible(true);
                    textLinks.get(i).setText(textLink);
                    textLinks.get(i).setMaxHeight(links.get(i).getPrefHeight());
                    links.get(i).setVisible(true);
                    links.get(i).setText(linkValue);
                    links.get(i).setMaxHeight(links.get(i).getPrefHeight());
                    i++;
                }
            }
        }
    }
}