/*
 * VuePrincipalControleur.java                                  02 mars 2024
 * IUT de Rodez, pas de droit d'auteur
 */

package org.fsp.filmok.controleur;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import org.fsp.filmok.FilmeOKApplication;
import org.fsp.filmok.FilmeOKApplication.View;
import org.fsp.filmok.factorie.Classeur;
import org.fsp.filmok.factorie.ClasseurFactorie;
import org.fsp.filmok.modele.ModelePrincipal;

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

    private final ModelePrincipal modelePrincipal = ModelePrincipal.getInstance();
    private final ClasseurFactorie classeurFactorie = ClasseurFactorie.getInstance();

    @FXML
    private TextField inputCheminFichierExcel;

    @FXML
    public void handleValider() {
        String cheminClasseurSaisie = inputCheminFichierExcel.getText();
        System.out.printf("Chemin saisie : %s%n", cheminClasseurSaisie);

        //On vérifie si le chemin est valide,
        //si oui, on charge le classeur
        //sinon on affiche une boite de dialogue avec un message d'erreur.
        if (estClasseurValide(cheminClasseurSaisie)) {
            chargerClasseur(cheminClasseurSaisie);
            FilmeOKApplication.loadEtChangerScene(View.PARAMETRE_CLASSEUR);
        }
    }


    /**
     * Charge le classeur
     * Si le chargement échoue, affiche une boite de dialogue avec un message d'erreur
     *
     * @param cheminClasseurSaisie le chemin du classeur
     */
    private void chargerClasseur(String cheminClasseurSaisie) {
        try {
            Classeur classeur
                    = classeurFactorie.getClasseur(cheminClasseurSaisie);
            modelePrincipal.setClasseur(classeur);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Erreur lors du chargement du classeur");
            alert.showAndWait();
        }
    }

    /**
     * Vérifie si le chemin du classeur est valide
     * Affiche une boite de dialogue avec un message d'erreur s'il n'est pas valide
     *
     * @param cheminClasseur le chemin du classeur
     * @return true si le classeur est valide, false sinon
     */
    private boolean estClasseurValide(String cheminClasseur) {
        boolean estValide = true;
        if (cheminClasseur == null || cheminClasseur.isEmpty() || cheminClasseur.isBlank()) {
            estValide = false;
            ALERT_CHEMIN_CLASSEUR_VIDE.showAndWait();
        } else if (!cheminClasseur.endsWith(".xlsx")
                && !cheminClasseur.endsWith(".ods")) {
            estValide = false;
            ALERT_CLASSEUR_EXTENSION_INVALIDE.showAndWait();
        } else if (!new File(cheminClasseur).exists()) {
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
    }
}
