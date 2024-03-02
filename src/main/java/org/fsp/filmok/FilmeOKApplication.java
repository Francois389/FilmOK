/*
 * FilmeOKApplication.java                                  02 mars 2024
 * IUT de Rodez, pas de droit d'auteur
 */

package org.fsp.filmok;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Charge e lance l'application.
 * @author François de Saint Palais
 */
public class FilmeOKApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(VuePrincipalControleur.class.getResource("main-view.fxml"));

        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("FilmOK");

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
