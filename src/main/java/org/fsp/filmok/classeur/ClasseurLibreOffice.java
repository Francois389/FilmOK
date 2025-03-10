/*
 * ClasseurLibreOffice.java                                  08 mars 2024
 * IUT de Rodez, pas de droit d'auteur
 */

package org.fsp.filmok.classeur;

import java.io.File;
import java.util.ArrayList;

/**
 * @author François de Saint Palais
 */
public class ClasseurLibreOffice implements Classeur{

    public ClasseurLibreOffice() {

    }

    public ClasseurLibreOffice(String nomFichier) {

    }

    @Override
    public void setCheminClasseur(String cheminClasseur) {

    }

    @Override
    public void setCheminClasseur(File classeur) {

    }

    @Override
    public String getNomFeuilleActive() {
        return null;
    }

    @Override
    public void setFeuilleActive(String nomFeuille) {

    }

    @Override
    public void setFeuilleActive(int indiceFeuille) {

    }

    @Override
    public ArrayList<String> getNomsFeuilles() {
        return null;
    }

    @Override
    public ArrayList<String> getNomsColonnes(String nomFeuille) {
        return null;
    }

    @Override
    public ArrayList<String> getNomsColonnes(int indiceFeuille) {
        return null;
    }

    public ArrayList<String> getNomsColonnes() {
        return null;
    }

    @Override
    public ArrayList<String> getValeurs(String nomColonne) {
        return null;
    }

    @Override
    public ArrayList<String> getValeurs(int indiceColonne) {
        return null;
    }

    @Override
    public void setValeursEcrase(String nomColonne, ArrayList<String> valeurs) {

    }

    @Override
    public void setValeursEcrase(int indiceColonne, ArrayList<String> valeurs) {

    }

    @Override
    public void addValeurs(String nomColonne, ArrayList<String> valeurs) {

    }

    @Override
    public void addValeurs(int indiceColonne, ArrayList<String> valeurs) {

    }

    @Override
    public boolean peutTraiter(String nomFichier) {
        return false;
    }

    @Override
    public Classeur creerClasseur(String nomFichier) {
        return new ClasseurLibreOffice(nomFichier);
    }
}
