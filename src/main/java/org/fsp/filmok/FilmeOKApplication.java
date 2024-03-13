/*
 * FilmeOKApplication.java                                  02 mars 2024
 * IUT de Rodez, pas de droit d'auteur
 */

package org.fsp.filmok;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.fsp.filmok.controleur.VuePrincipalControleur;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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

    public static void loadEtChangerScene(String nomFichier) {
        loadScene(nomFichier);
        changerScene(nomFichier);
    }

    public static void changerScene(String nomFichier) {
        if (!scenes.containsKey(nomFichier)) {
            throw new IllegalArgumentException("La scène " + nomFichier + " n'a pas été chargée");
        }
        fenetrePrincipale.setScene(scenes.get(nomFichier));
    }

    private static void loadScene(String nomFichier) {
        if (!scenes.containsKey(nomFichier)) {
            FXMLLoader loader = new FXMLLoader(FilmeOKApplication.class.getResource(nomFichier));
            try {
                Scene scene = new Scene(loader.load());
                scenes.put(nomFichier, scene);
                System.out.println("Chargement de la scène " + loader.getLocation());
            } catch (IllegalStateException e) {
                System.out.println("Nom de fichier ou chemin incorrect : " + nomFichier);
            } catch (LoadException e) {
                System.out.println("Erreur dans le fichier fxml ou la méthode \"initialize\" du controleur : " + loader.getLocation());
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("Impossible de charger la scène " + loader.getLocation());
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("Erreur inconnue lors du chargement de la scène " + loader.getLocation());
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
