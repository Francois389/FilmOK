/*
 * Modele.java                                  09 mars 2024
 * IUT de Rodez, pas de droit d'auteur
 */

package org.fsp.filmok;

import org.fsp.filmok.classeur.Classeur;
import org.fsp.filmok.classeur.ClasseurFilme;
import org.fsp.filmok.film.Film;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author François de Saint Palais
 */
public class ModelePrincipal {

    private static final Logger log = LoggerFactory.getLogger(ModelePrincipal.class);
    private static ModelePrincipal instance;

    private ClasseurFilme classeur;

    private ArrayList<Film> films;

    private ModelePrincipal() {
    }

    public static ModelePrincipal getInstance() {
        if (instance == null) {
            instance = new ModelePrincipal();
        }
        return instance;
    }

    public ClasseurFilme getClasseur() {
        return classeur;
    }

    public void setClasseur(Classeur classeur) throws IOException {
        this.classeur = new ClasseurFilme(classeur);
        log.info("{}", classeur);
    }

    public List<Film> getFilms() {
        return classeur.getFilms();
    }

    public void setFilms(ArrayList<Film> films) {
        this.films = films;
    }
}
