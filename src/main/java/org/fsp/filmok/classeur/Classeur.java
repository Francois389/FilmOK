package org.fsp.filmok.classeur;

import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface Classeur {

    void setCheminClasseur(String cheminClasseur);

    void setCheminClasseur(File classeur);

    String getNomFeuilleActive();

    void setFeuilleActive(String nomFeuille);

    void setFeuilleActive(int indiceFeuille);

    ArrayList<String> getNomsFeuilles();

    ArrayList<String> getNomsColonnes(String nomFeuille);

    ArrayList<String> getNomsColonnes(int indiceFeuille);

    ArrayList<String> getNomsColonnes();


    ArrayList<String> getValeurs(String nomColonne);

    ArrayList<String> getValeurs(int indiceColonne);

    /**
     * Modifie les valeurs d'une colonne du classeur
     * Écrase les valeurs précédentes
     *
     * @param nomColonne Le nom de la colonne à modifier
     * @param valeurs    La liste des valeurs à insérer
     */
    void setValeursEcrase(String nomColonne, ArrayList<String> valeurs);

    /**
     * Modifie les valeurs d'une colonne du classeur
     * Écrase les valeurs précédentes
     *
     * @param indiceColonne L'indice de la colonne à modifier
     * @param valeurs       La liste des valeurs à insérer
     */
    void setValeursEcrase(int indiceColonne, ArrayList<String> valeurs);

    /**
     * Ajoute des valeurs à une colonne du classeur
     * Les valeurs sont ajoutées à la suite des valeurs existantes
     *
     * @param nomColonne Le nom de la colonne à modifier
     * @param valeurs    La liste des valeurs à insérer
     */
    void addValeurs(String nomColonne, ArrayList<String> valeurs);

    /**
     * Ajoute des valeurs à une colonne du classeur
     * Les valeurs sont ajoutées à la suite des valeurs existantes
     *
     * @param indiceColonne L'indice de la colonne à modifier
     * @param valeurs       La liste des valeurs à insérer
     */
    void addValeurs(int indiceColonne, ArrayList<String> valeurs);


    boolean peutTraiter(String nomFichier);

    Classeur creerClasseur(String nomFichier) throws IOException;

    List<Row> getRows();
}
