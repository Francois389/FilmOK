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
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Charge et lance l'application.
 * @author François de Saint Palais
 */
public class FilmeOKApplication extends Application {


    /**
     * Le nom des fichiers fxml, sans l'extension, associés à leur Scene.
     */
    private static HashMap<View, Scene> scenes;

    /**
     * La fenêtre principale de l'application.
     */
    private static Stage fenetrePrincipale;

    @Override
    public void start(Stage stage) {
        chargementApplication();

        fenetrePrincipale = stage;
        changerScene(View.MAIN_VIEW);
        fenetrePrincipale.setTitle("FilmOK");
        fenetrePrincipale.setResizable(false);
        fenetrePrincipale.show();
    }

    /**
     * Enregistre les ressources et
     * charge les ressources de l'application.
     * Les ressources ne doivent dépendre de valeur initialisée par d'autre ressource.
     */
    private void chargementApplication() {
        scenes = new HashMap<>();

        // Chargement des ressources
        for (View view : View.values()) {
            loadScene(view);
        }
    }

    /**
     * Charge une scène et bascule la vue vers cette scène.
     * @param view le nom du fichier fxml, sans l'extension
     */
    public static void loadEtChangerScene(View view) {
        loadScene(view);
        changerScene(view);
    }

    /**
     * Bascule la vue vers une scène déjà chargée.
     * @param nomFichier le nom du fichier fxml, sans l'extension
     */
    public static void changerScene(View view) {
        fenetrePrincipale.setScene(scenes.get(view));
    }

    /**
     * Charge un fichier fxml en Scene et l'ajoute à la liste des scènes.
     * @param nomFichier le nom du fichier fxml, sans l'extension
     */
    private static void loadScene(View view) {
        if (!scenes.containsKey(view)) {
            FXMLLoader loader = new FXMLLoader(FilmeOKApplication.class.getResource(view.fichierExtension()));

            try {
                Scene scene = new Scene(loader.load());
                scenes.put(view, scene);
                System.out.printf("Chargement de la scène %s%n", loader.getLocation());

            } catch (IllegalStateException e) {
                System.out.printf("Nom de fichier ou chemin incorrect : %s%n", view.fichierExtension());
            } catch (LoadException e) {
                System.out.printf("Erreur dans le fichier fxml ou la méthode \"initialize\" du controleur : %s%n", loader.getLocation());
                e.printStackTrace();
            } catch (IOException e) {
                System.out.printf("Impossible de charger la scène %s%n", loader.getLocation());
                e.printStackTrace();
            } catch (Exception e) {
                System.out.printf("Erreur inconnue lors du chargement de la scène %s%n", loader.getLocation());
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public enum View {
        MAIN_VIEW("main-view"),
        PARAMETRE_CLASSEUR("parametreClasseur"),
        RESULTAT("resultat");

        private final String nomFichier;

        View(String nomFichier) {
            this.nomFichier = nomFichier;
        }

        public String fichier() {
            return nomFichier;
        }

        public String fichierExtension() {
            return String.format("%s.fxml", fichier());
        }
    }
}
