/*
 * ParametreClasseurControleur.java                                  09 mars 2024
 * IUT de Rodez, pas de droit d'auteur
 */

package org.fsp.filmok.controleur;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import org.fsp.filmok.FilmeOKApplication;
import org.fsp.filmok.factorie.Classeur;
import org.fsp.filmok.modele.ModelePrincipal;

import java.util.ArrayList;

/**
 * @author François de Saint Palais
 */
public class ParametreClasseurControleur {
    @FXML
    public ComboBox colonnesTitre;
    @FXML
    public ComboBox colonnesDateSorti;
    @FXML
    public ComboBox colonnesRealisateur;
    @FXML
    public ComboBox colonnesDuree;
    @FXML
    public ComboBox colonnesResume;
    @FXML
    public ComboBox listeFeuilles;


    private static final ModelePrincipal modelePrincipal = ModelePrincipal.getInstance();

    private Classeur classeur;

    @FXML
    void initialize() {
        classeur = modelePrincipal.getClasseur();

        colonnesTitre.setDisable(true);
        colonnesDateSorti.setDisable(true);
        colonnesRealisateur.setDisable(true);
        colonnesDuree.setDisable(true);
        colonnesResume.setDisable(true);

        ArrayList<String> feuilles = classeur.getNomsFeuilles();
        listeFeuilles.getItems().addAll(feuilles);
    }

}
