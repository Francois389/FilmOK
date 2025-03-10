/*
 * Resultat.java                                  10 mars 2024
 * IUT de Rodez, pas de droit d'auteur
 */

package org.fsp.filmok.controleur;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * @author François de Saint Palais
 */
public class ResultatControleur {

    @FXML
    public TableView<LigneFilm> tabResultat;

    @FXML
    void initialize() {
        tabResultat.setPlaceholder(new Label("Pas de filme trouv\u00E9"));

        tabResultat.setRowFactory(tv -> {
            TableRow<LigneFilm> row = new TableRow<LigneFilm>();
            row.prefHeight(50);
            return row;
        });

        TableColumn<LigneFilm, String> colonneTitre = new TableColumn<>("Titre");
        TableColumn<LigneFilm, String> colonneDateSortie = new TableColumn<>("Date de sortie");
        TableColumn<LigneFilm, String> colonneRealisateur = new TableColumn<>("R\u00E9alisateur");
        TableColumn<LigneFilm, String> colonneDuree = new TableColumn<>("Dur\u00E9e");
        TableColumn<LigneFilm, String> colonneResume = new TableColumn<>("R\u00E9sum\u00E9");

        colonneTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        colonneDateSortie.setCellValueFactory(new PropertyValueFactory<>("dateSortie"));
        colonneRealisateur.setCellValueFactory(new PropertyValueFactory<>("realisateur"));
        colonneDuree.setCellValueFactory(new PropertyValueFactory<>("duree"));
        colonneResume.setCellValueFactory(new PropertyValueFactory<>("resume"));

        tabResultat.getColumns().addAll(colonneTitre, colonneDateSortie,
                colonneRealisateur, colonneDuree, colonneResume);

        miseAJourTableau();

    }

    private void miseAJourTableau() {

    }

    record LigneFilm(String titre, String dateSortie, String realisateur, String duree, String resume) {
    }
}
