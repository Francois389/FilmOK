/*
 * VuePrincipalControleur.java                                  02 mars 2024
 * IUT de Rodez, pas de droit d'auteur
 */

package org.fsp.filmok;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;

/**
 * @author François de Saint Palais
 */
public class VuePrincipalControleur {
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
        System.out.println(inputCheminFichierExcel.getText());
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
