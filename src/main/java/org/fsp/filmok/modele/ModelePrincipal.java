/*
 * Modele.java                                  09 mars 2024
 * IUT de Rodez, pas de droit d'auteur
 */

package org.fsp.filmok.modele;

import org.fsp.filmok.factorie.Classeur;
import org.fsp.filmok.film.Film;

import java.util.ArrayList;

/**
 * @author François de Saint Palais
 */
public class ModelePrincipal {

    private static ModelePrincipal instance;

    private Classeur classeur;

    private int colonneTitre;
    private int colonneDateSortie;
    private int colonneRealisateur;
    private int colonneDuree;
    private int colonneResume;

    private ArrayList<Film> films;

    private ModelePrincipal() {}

    public static ModelePrincipal getInstance() {
        if (instance == null) {
            instance = new ModelePrincipal();
        }
        return instance;
    }

    public void setClasseur(Classeur classeur) {
        this.classeur = classeur;
    }

    public Classeur getClasseur() {
        return classeur;
    }

    public int getColonneTitre() {
        return colonneTitre;
    }

    public void setColonneTitre(int colonneTitre) {
        this.colonneTitre = colonneTitre;
    }

    public int getColonneDateSortie() {
        return colonneDateSortie;
    }

    public void setColonneDateSortie(int colonneDateSortie) {
        this.colonneDateSortie = colonneDateSortie;
    }

    public int getColonneRealisateur() {
        return colonneRealisateur;
    }

    public void setColonneRealisateur(int colonneRealisateur) {
        this.colonneRealisateur = colonneRealisateur;
    }

    public int getColonneDuree() {
        return colonneDuree;
    }

    public void setColonneDuree(int colonneDuree) {
        this.colonneDuree = colonneDuree;
    }

    public int getColonneResume() {
        return colonneResume;
    }

    public void setColonneResume(int colonneResume) {
        this.colonneResume = colonneResume;
    }

    public ArrayList<Film> getFilms() {
        return films;
    }

    public void setFilms(ArrayList<Film> films) {
        this.films = films;
    }
}
