module org.fsp.moviechecker {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.poi.poi;


    opens org.fsp.filmok to javafx.fxml;
    exports org.fsp.filmok;
    exports org.fsp.filmok.classeur;
    exports org.fsp.filmok.controleur;
    opens org.fsp.filmok.controleur to javafx.fxml;
    exports org.fsp.filmok.film;
}