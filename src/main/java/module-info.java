module org.fsp.moviechecker {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.fsp.moviechecker to javafx.fxml;
    exports org.fsp.moviechecker;
}