package ru.game.book.shelton.controller;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Modality;
import ru.game.book.shelton.LoadChapter;
import ru.game.book.shelton.Parameters;
import ru.game.book.shelton.dto.Chapter;
import ru.game.book.shelton.dto.Link;
import ru.game.book.shelton.logger.GameLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class GameController {
    private static final GameLogger logger = new GameLogger();
    private final LoadChapter loadChapter = new LoadChapter();
    private final Parameters parameters = new Parameters();

    @FXML
    public VBox root;
    @FXML
    public VBox vBoxLinks;
    @FXML
    private Button startButton;
    @FXML
    private Button infoButton;
    @FXML
    private Label chapterNumber;
    @FXML
    private TextArea chapterText;
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
    private ImageView image;
    @FXML
    private Pane pane;

    public void initialize() {
        chapterText.setStyle("-fx-font-size: 14px");
        showImage("shelton", 0, 0, false);
    }

    private void showImage(String fileName, int offsetWidth, int offsetHeight, boolean keepAspectRatio) {
        image.setPreserveRatio(keepAspectRatio);
        Image picture = loadChapter.getImageFromFile(fileName);
        double width = picture.getWidth();
        double height = picture.getHeight();
        double rootWidth = root.getPrefWidth();
        double rootHeight = root.getPrefHeight();
        double scale = Math.min((rootWidth - 40 - offsetWidth) / width, (rootHeight - 80 - offsetHeight) / height);
        image.setImage(picture);
        image.fitWidthProperty().bind(Bindings.subtract(root.widthProperty(), rootWidth - (int) (width * scale)));
        image.fitHeightProperty().bind(Bindings.subtract(root.heightProperty(), rootHeight - (int) (height * scale)));
    }

    private void hideImage() {
        image.setImage(null);
        image.fitHeightProperty().unbind();
        image.fitWidthProperty().unbind();
        image.setFitHeight(0);
        image.setFitWidth(0);
    }

    @FXML
    protected void onStartButtonClick() {
        boolean confirmation = true;
        if (startButton.getText().equals("_Restart")) {
            confirmation = openConfirmationDialog();
        }
        if (confirmation) {
            logger.testLogging(startButton.getText());
            hideImage();
            startButton.setText("_Restart");
            parameters.init();
            mastery.setText(parameters.getCurrentMastery() + "/" + parameters.getMastery());
            stamina.setText(parameters.getCurrentStamina() + "/" + parameters.getStamina());
            luck.setText(parameters.getCurrentLuck() + "/" + parameters.getLuck());
            money.setText(parameters.getMoney().toString());
            food.setText(parameters.getFood().toString());

            Button button = new Button("0");
            button.setOnAction(this::onButtonClick);
            vBoxLinks.getChildren().add(button);
            button.fire();
        }
    }

    @FXML
    protected void onButtonClick(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String buttonText = clickedButton.getText();
        Chapter chapter = loadChapter.getBlock(buttonText);
        if (Objects.nonNull(chapter)) {
            chapterNumber.setText(buttonText);
            chapterText.setText("\t" + chapter.getText());

            addListAction(chapter);

            Integer cash = chapter.getMoney();
            if (Objects.nonNull(cash)) {
                parameters.setMoney(parameters.getMoney() + cash);
                logger.moneyChange(String.valueOf(cash));
                money.setText(parameters.getMoney().toString());
            }

            String pictureName = chapter.getPicture();
            if (Objects.nonNull(pictureName)) {
                image.setPreserveRatio(true);
                showImage(pictureName, 0, 110, true);
            }
        }
    }

    private void addListAction(Chapter chapter) {
        vBoxLinks.getChildren().clear();

        List<Link> listLinks = Optional.ofNullable(chapter.getLinks()).orElse(new ArrayList<>());

        for (Link link : listLinks) {
            String textLink = link.getText();
            String linkValue = link.getLink();
            if (Objects.nonNull(linkValue) && !linkValue.isEmpty()) {
                Label label = new Label(textLink);
                Button button = new Button(linkValue);
                button.setOnAction(this::onButtonClick);
                HBox hbox = new HBox(10);
                hbox.getChildren().addAll(label, button);
                hbox.setAlignment(Pos.CENTER_LEFT);
                HBox.setMargin(button,  new Insets(3,0,2,0));
                vBoxLinks.getChildren().add(hbox);
            }
        }
    }

    private boolean openConfirmationDialog() {
        AtomicBoolean confirmation = new AtomicBoolean(false);
        Stage confirmationStage = new Stage();
        confirmationStage.initModality(Modality.APPLICATION_MODAL);
        Image icon = loadChapter.getImageFromFile("restart");
        confirmationStage.getIcons().add(icon);
        confirmationStage.setTitle("Restart");

        Label label = new Label("Начать заново?");
        Button yesButton = new Button("Да");
        Button noButton = new Button("Нет");

        yesButton.setOnAction(action -> {
            confirmation.set(true);
            confirmationStage.close();
        });

        noButton.setOnAction(action -> confirmationStage.close());

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        HBox hBox = new HBox(10, yesButton, noButton);
        hBox.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(label, hBox);

        confirmationStage.setScene(new Scene(layout, 210, 100));
        confirmationStage.showAndWait();
        return confirmation.get();
    }
}