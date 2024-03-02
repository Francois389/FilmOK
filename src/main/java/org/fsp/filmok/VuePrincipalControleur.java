/*
 * VuePrincipalControleur.java                                  02 mars 2024
 * IUT de Rodez, pas de droit d'auteur
 */

package org.fsp.filmok;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * @author François de Saint Palais
 */
public class VuePrincipalControleur {
    @FXML
    private TextField inputCHeminFichierExcel;

    @FXML
    private TableView<Film> tableFilms;

    public void chargerFichierExcel() {
        // TODO
    }

    @FXML
    public void handleValider() {
        // TODO
        System.out.println("Valider");
    }

    @FXML
    void initialize() {
        // TODO
    }
}
