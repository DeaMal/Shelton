module ru.game.book.shelton {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires org.slf4j;
    requires static lombok;

    opens ru.game.book.shelton to javafx.fxml;
    exports ru.game.book.shelton;
    exports ru.game.book.shelton.dto;
}