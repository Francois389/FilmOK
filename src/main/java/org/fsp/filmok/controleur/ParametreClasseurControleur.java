/*
 * ParametreClasseurControleur.java                                  09 mars 2024
 * IUT de Rodez, pas de droit d'auteur
 */

package org.fsp.filmok.controleur;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import org.fsp.filmok.FilmeOKApplication;
import org.fsp.filmok.factorie.Classeur;
import org.fsp.filmok.modele.ModelePrincipal;

import java.util.ArrayList;

/**
 * @author François de Saint Palais
 */
public class ParametreClasseurControleur {
    @FXML
    public ComboBox<String> colonnesTitre;
    @FXML
    public ComboBox<String> colonnesDateSorti;
    @FXML
    public ComboBox<String> colonnesRealisateur;
    @FXML
    public ComboBox<String> colonnesDuree;
    @FXML
    public ComboBox<String> colonnesResume;
    @FXML
    public ComboBox<String> listeFeuilles;
    @FXML
    public GridPane containerChoixColonnes;


    private static final ModelePrincipal modelePrincipal = ModelePrincipal.getInstance();

    private Classeur classeur;

    @FXML
    void initialize() {
        classeur = modelePrincipal.getClasseur();

        containerChoixColonnes.setDisable(true);

        ArrayList<String> feuilles = classeur.getNomsFeuilles();
        listeFeuilles.getItems().addAll(feuilles);

        listeFeuilles.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                classeur.setFeuilleActive(newValue);
                containerChoixColonnes.setDisable(false);

                remplirListe();
            }
        });
    }

    private void remplirListe() {
        ArrayList<String> colonnes = classeur.getNomsColonnes();

        if (colonnes.isEmpty()) {
            containerChoixColonnes.setDisable(true);
        } else {
            containerChoixColonnes.setDisable(false);

            colonnesTitre.getItems().clear();
            colonnesTitre.getItems().addAll(colonnes);

            colonnesDateSorti.getItems().clear();
            colonnesDateSorti.getItems().addAll(colonnes);

            colonnesRealisateur.getItems().clear();
            colonnesRealisateur.getItems().addAll(colonnes);

            colonnesDuree.getItems().clear();
            colonnesDuree.getItems().addAll(colonnes);

            colonnesResume.getItems().clear();
            colonnesResume.getItems().addAll(colonnes);
        }

    }

    @FXML
    public void confirmer() {
        modelePrincipal.setColonneTitre(colonnesTitre.getSelectionModel().getSelectedIndex());
        modelePrincipal.setColonneDateSortie(colonnesDateSorti.getSelectionModel().getSelectedIndex());
        modelePrincipal.setColonneRealisateur(colonnesRealisateur.getSelectionModel().getSelectedIndex());
        modelePrincipal.setColonneDuree(colonnesDuree.getSelectionModel().getSelectedIndex());
        modelePrincipal.setColonneResume(colonnesResume.getSelectionModel().getSelectedIndex());

        FilmeOKApplication.loadEtChangerScene("resultat");
    }

}
