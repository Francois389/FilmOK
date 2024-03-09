/*
 * FilmeOKApplication.java                                  02 mars 2024
 * IUT de Rodez, pas de droit d'auteur
 */

package org.fsp.filmok;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.fsp.filmok.controleur.VuePrincipalControleur;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Charge e lance l'application.
 * @author François de Saint Palais
 */
public class FilmeOKApplication extends Application {

    private static HashMap<String, Scene> scenes;
    private static ArrayList<String> ressources;
    private static Stage fenetrePrincipale;

    @Override
    public void start(Stage stage) throws Exception {
        chargementApplication();



        fenetrePrincipale = stage;
        changerScene("main-view.fxml");
        fenetrePrincipale.setTitle("FilmOK");
        fenetrePrincipale.setResizable(false);
        fenetrePrincipale.show();
    }

    private void chargementApplication() {
        scenes = new HashMap<>();
        ressources = new ArrayList<String>();

        ressources.add("main-view.fxml");
        ressources.add("parametreClasseur.fxml");

        for (String ressource : ressources) {
            loadScene(ressource);
        }

    }

    public static void changerScene(String nomFichier) {
        if (!scenes.containsKey(nomFichier)) {
            throw new IllegalArgumentException("La scène " + nomFichier + " n'a pas été chargée");
        }
        fenetrePrincipale.setScene(scenes.get(nomFichier));
    }

    private void loadScene(String nomFichier) {
        if (!scenes.containsKey(nomFichier)) {
            FXMLLoader loader = new FXMLLoader(Vue.class.getResource(nomFichier));
            try {
                Scene scene = new Scene(loader.load());
                scenes.put(nomFichier, scene);
                System.out.println("Chargement de la scène " + loader.getLocation());
            } catch (Exception e) {
                System.out.println("Impossible de charger la scène " + loader.getLocation());
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
