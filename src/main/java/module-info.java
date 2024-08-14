module ru.game.book.shelton {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires org.slf4j;
    requires static lombok;

    exports ru.game.book.shelton;
    exports ru.game.book.shelton.dto;
    exports ru.game.book.shelton.controller;
    opens ru.game.book.shelton.controller to javafx.fxml;
}