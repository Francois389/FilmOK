/*
 * VuePrincipalControleur.java                                  02 mars 2024
 * IUT de Rodez, pas de droit d'auteur
 */

package org.fsp.filmok;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;

/**
 * @author François de Saint Palais
 */
public class VuePrincipalControleur {
    private static final Alert ALERT_CHEMIN_CLASSEUR_VIDE
            = new Alert(Alert.AlertType.ERROR, "Chemin de classeur vide");
    private static final Alert ALERT_CLASSEUR_EXTENSION_INVALIDE
            = new Alert(Alert.AlertType.ERROR, "Le fichier doit être un classeur Excel ou LibreOffice");
    private static final Alert ALERT_CLASSEUR_INEXISTANT
            = new Alert(Alert.AlertType.ERROR, "Classeur inexistant. Vérifiez le chemin");

    @FXML
    private TextField inputCheminFichierExcel;

    @FXML
    private TableView<Film> tableFilms;

    public void chargerFichierExcel() {
        // TODO
    }

    @FXML
    public void handleValider() {
        // TODO
        System.out.println("Valider");
        String cheminClasseurSaisie = inputCheminFichierExcel.getText();
        System.out.println(cheminClasseurSaisie);
        if (estClasseurValide(cheminClasseurSaisie)) {
            chargerClasseur(cheminClasseurSaisie);
        }
    }

    private void chargerClasseur(String cheminClasseurSaisie) {
        // TODO
    }

    /**
     * Vérifie si le chemin du classeur est valide
     * Affiche une boite de dialogue avec un message d'erreur s'il n'est pas valide
     * @param cheminClasseur
     * @return
     */
    private boolean estClasseurValide(String cheminClasseur) {
        boolean estValide = true;
        if (cheminClasseur == null || cheminClasseur.isEmpty() || cheminClasseur.isBlank()) {
            estValide = false;
            ALERT_CHEMIN_CLASSEUR_VIDE.showAndWait();
        } else if (   ! cheminClasseur.endsWith(".xlsx")
                   && ! cheminClasseur.endsWith(".ods")){
            estValide = false;
            ALERT_CLASSEUR_EXTENSION_INVALIDE.showAndWait();
        } else if (! new File(cheminClasseur).exists()) {
            estValide = false;
            ALERT_CLASSEUR_INEXISTANT.showAndWait();
        }
        return estValide; // TODO STUB
    }

    @FXML
    public void handleParcourir() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir un fichier Excel");
        fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Classeur Excel", "*.xlsx"),
        new FileChooser.ExtensionFilter("LibreOffice Classeur", "*.ods"),
        new FileChooser.ExtensionFilter("All Files", "*.*"));
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            inputCheminFichierExcel.setText(file.getAbsolutePath());
        }
    }

    @FXML
    void initialize() {
        // TODO
    }
}
