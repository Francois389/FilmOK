module org.fsp.moviechecker {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.fsp.filmok to javafx.fxml;
    exports org.fsp.filmok;
}