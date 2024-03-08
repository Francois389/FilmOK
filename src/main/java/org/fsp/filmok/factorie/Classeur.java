package org.fsp.filmok.factorie;

import java.io.File;
import java.util.ArrayList;

public interface Classeur {

    public void setCheminClasseur(String cheminClasseur);
    public void setCheminClasseur(File classeur);

    public String getNomFeuilleActive();
    public void setFeuilleActive(String nomFeuille);
    public void setFeuilleActive(int indiceFeuille);
    public ArrayList<String> getNomsFeuilles();
    public void creerFeuille(String nomFeuille);

    public ArrayList<String> getNomsColonnes(String nomFeuille);
    public ArrayList<String> getNomsColonnes(int indiceFeuille);


    public ArrayList<String> getValeurs(String nomColonne);
    public ArrayList<String> getValeurs(int indiceColonne);

    /**
     * Modifie les valeurs d'une colonne du classeur
     * Écrase les valeurs précédentes
     * @param nomColonne Le nom de la colonne à modifier
     * @param valeurs La liste des valeurs à insérer
     */
    public void setValeursEcrase(String nomColonne, ArrayList<String> valeurs);
    /**
     * Modifie les valeurs d'une colonne du classeur
     * Écrase les valeurs précédentes
     * @param indiceColonne L'indice de la colonne à modifier
     * @param valeurs La liste des valeurs à insérer
     */
    public void setValeursEcrase(int indiceColonne, ArrayList<String> valeurs);

    /**
     * Ajoute des valeurs à une colonne du classeur
     * Les valeurs sont ajoutées à la suite des valeurs existantes
     * @param nomColonne Le nom de la colonne à modifier
     * @param valeurs La liste des valeurs à insérer
     */
    public void addValeurs(String nomColonne, ArrayList<String> valeurs);
    /**
     * Ajoute des valeurs à une colonne du classeur
     * Les valeurs sont ajoutées à la suite des valeurs existantes
     * @param indiceColonne L'indice de la colonne à modifier
     * @param valeurs La liste des valeurs à insérer
     */
    public void addValeurs(int indiceColonne, ArrayList<String> valeurs);


    boolean peutTraiter(String nomFichier);
}
