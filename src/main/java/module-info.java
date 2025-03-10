module org.fsp.moviechecker {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.poi.poi;


    opens org.fsp.filmok to javafx.fxml;
    exports org.fsp.filmok;
    exports org.fsp.filmok.factorie;
    opens org.fsp.filmok.factorie to javafx.fxml;
    exports org.fsp.filmok.modele;
    opens org.fsp.filmok.modele to javafx.fxml;
    exports org.fsp.filmok.controleur;
    opens org.fsp.filmok.controleur to javafx.fxml;
    exports org.fsp.filmok.film;
    opens org.fsp.filmok.film to javafx.fxml;
}