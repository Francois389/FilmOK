/*
 * ClasseurFilme.java                                  10 mars 2025
 * IUT de Rodez, pas de droit d'auteur
 */

package org.fsp.filmok.classeur;

import org.apache.poi.ss.usermodel.Row;
import org.fsp.filmok.film.Film;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author François de Saint Palais
 */
public class ClasseurFilme {
    public static final int UNSET_INDEX = -1;
    private static final Logger log = LoggerFactory.getLogger(ClasseurFilme.class);
    private final Map<String, Colum> colum;
    private final Classeur classeur;

    public ClasseurFilme(Classeur classeur) throws IOException {
        colum = new HashMap<>();
        this.classeur = classeur;

        FilmField[] fields = FilmField.values();
        for (int i = 0; i < fields.length; i++) {
            colum.put(fields[i].field, new Colum(fields[i].field, UNSET_INDEX));
        }
    }

    public void updateColum(FilmField field, int index) {
        updateColum(field.field, index);
    }

    public void updateColum(String field, int index) {
        if (index != UNSET_INDEX) {
            String columName = classeur.getNomsColonnes().get(index);
            colum.put(field, new Colum(columName, index));
        }
    }

    public List<Film> getFilms() {
        if (existColumUnset()) {
            selectIndexForRemainingColum();
        }

        return getClasseur().getRows().stream().skip(1).map(this::buildFromRow).collect(Collectors.toList());
    }

    private Film buildFromRow(Row row) {
        Date premiereSortie = new Date();
        String realisateur = "";
        int duree = 0;
        String resume = "";
        try {
            premiereSortie = row.getCell(colum.get(FilmField.DATE_SORTIE.field).index).getDateCellValue();
        } catch (Exception _) {
        }
        try {
            realisateur = row.getCell(colum.get(FilmField.REALISATEUR.field).index).getStringCellValue();
        } catch (Exception _) {
        }
        try {
            duree = (int) row.getCell(colum.get(FilmField.DUREE.field).index).getNumericCellValue();
        } catch (Exception _) {
        }
        try {
            resume = row.getCell(colum.get(FilmField.RESUME.field).index).getStringCellValue();
        } catch (Exception _) {
        }

        return new Film(
                row.getCell(colum.get(FilmField.TITRE.field).index).getStringCellValue(),
                premiereSortie,
                realisateur,
                duree,
                resume
        );
    }

    public Classeur getClasseur() {
        return classeur;
    }

    public boolean existColumUnset() {
        return colum.values().stream().anyMatch(colum -> colum.index == UNSET_INDEX);
    }

    public void selectIndexForRemainingColum() {
        Set<Map.Entry<String, Colum>> values = colum.entrySet();
        int maxIndex = values.stream().map(entry -> entry.getValue().index).max(Integer::compareTo).orElse(0);
        List<Map.Entry<String, Colum>> remainingColums = values.stream().filter(entry -> entry.getValue().index == UNSET_INDEX).toList();

        for (int i = 0; i < remainingColums.size(); i++) {
            colum.put(remainingColums.get(i).getKey(), new Colum(remainingColums.get(i).getKey(), ++maxIndex));
        }
    }

    public enum FilmField {
        TITRE("titre"),
        REALISATEUR("realisateur"),
        DUREE("duree"),
        DATE_SORTIE("premiereSortie"),
        RESUME("resume");

        private final String field;


        FilmField(String fieldName) {
            field = fieldName;
        }

    }


    public static class Colum {
        private final int index;
        private final String name;

        public Colum(String name, int index) {
            this.index = index;
            this.name = name;
        }

        @Override
        public final boolean equals(Object o) {
            if (!(o instanceof Colum colum)) return false;

            return index == colum.index;
        }

        @Override
        public int hashCode() {
            return index;
        }
    }
}
