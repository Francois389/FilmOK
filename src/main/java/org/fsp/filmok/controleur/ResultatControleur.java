/*
 * Resultat.java                                  10 mars 2024
 * IUT de Rodez, pas de droit d'auteur
 */

package org.fsp.filmok.controleur;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import org.fsp.filmok.ModelePrincipal;
import org.fsp.filmok.film.Film;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author François de Saint Palais
 */
public class ResultatControleur {

    @FXML
    public TableView<LigneFilm> tabResultat;

    private ModelePrincipal modelePrincipal;

    @FXML
    void initialize() {
        modelePrincipal = ModelePrincipal.getInstance();
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

        colonneTitre.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().titre()));
        colonneDateSortie.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().dateSortie()));
        colonneRealisateur.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().realisateur()));
        colonneDuree.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().duree()));
        colonneResume.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().resume()));

        tabResultat.getColumns().addAll(colonneTitre, colonneDateSortie,
                colonneRealisateur, colonneDuree, colonneResume);

        miseAJourTableau();

    }

    private void miseAJourTableau() {
        List<LigneFilm> films = modelePrincipal.getFilms().stream().map(LigneFilm::build).toList();

        tabResultat.getItems().addAll(films);
    }

    record LigneFilm(String titre, String dateSortie, String realisateur, String duree, String resume) {
        public static LigneFilm build(Film filme) {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            DateFormat dureeFormat = new SimpleDateFormat("hh:mm");

            return new LigneFilm(
                    filme.getTitre(),
                    dateFormat.format(filme.getPremiereSortie()),
                    filme.getRealisateur(),
                    dureeFormat.format(filme.getDuree()),
                    filme.getResume()
            );
        }
    }
}
