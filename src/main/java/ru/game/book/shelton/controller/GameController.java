package ru.game.book.shelton.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import ru.game.book.shelton.LoadChapter;
import ru.game.book.shelton.Parameters;
import ru.game.book.shelton.dto.Chapter;
import ru.game.book.shelton.dto.Link;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameController {
    private final LoadChapter loadChapter = new LoadChapter();
    private final Parameters parameters = new Parameters();
    private List<Label> textLinks;
    private List<Button> links;

    @FXML
    private Button startButton;
    @FXML
    private Button infoButton;
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
    private Label luck;
    @FXML
    private Label mastery;
    @FXML
    private Label money;
    @FXML
    private Label stamina;
    @FXML
    private Label food;

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
    }

    @FXML
    protected void onStartButtonClick() {
        startButton.setText("Restart");
        parameters.init();
        mastery.setText(parameters.getCurrentMastery() + "/" + parameters.getMastery());
        stamina.setText(parameters.getCurrentStamina() + "/" + parameters.getStamina());
        luck.setText(parameters.getCurrentLuck() + "/" + parameters.getLuck());
        money.setText(parameters.getMoney().toString());
        food.setText(parameters.getFood().toString());
        link1.setText("0");
        link1.fire();
    }

    @FXML
    protected void onButtonClick(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String buttonText = clickedButton.getText();
        Chapter chapter = loadChapter.getBlock(buttonText);
        if (Objects.nonNull(chapter)) {
            cleanLinks();
            chapterNumber.setText(buttonText);
            chapterText.setText("\t" + chapter.getText());
            List<Link> listLinks = chapter.getLinks();
            int i = 0;
            for (Link link : listLinks) {
                String textLink = link.getText();
                String linkValue = link.getLink();
                if (Objects.nonNull(linkValue) && !linkValue.isEmpty()) {
                    links.get(i).setVisible(true);
                    links.get(i).setText(linkValue);
                    links.get(i).setMaxHeight(-1);
                    HBox.setMargin(links.get(i), new Insets(3,0,2,0));
                    textLinks.get(i).setVisible(true);
                    textLinks.get(i).setText(textLink);
                    textLinks.get(i).setMaxHeight(-1);
                    i++;
                }
            }
        }
    }

    @FXML
    protected void cleanLinks() {
        textLinks.forEach(item -> {
            item.setText("");
            item.setVisible(false);
            item.setMaxHeight(0);
        });
        links.forEach(item -> {
            item.setText("");
            item.setVisible(false);
            item.setMaxHeight(0);
            HBox.setMargin(item, new Insets(0,0,0,0));
        });
    }
}