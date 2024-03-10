/*
 * ClasseurFactorie.java                                  08 mars 2024
 * IUT de Rodez, pas de droit d'auteur
 */

package org.fsp.filmok.factorie;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author François de Saint Palais
 */
public class ClasseurFactorie {

    private static ClasseurFactorie instance;

    private ArrayList<Classeur> classeurs;

    private ClasseurFactorie() {
        try {
            classeurs = new ArrayList<>();
            classeurs.add(new ClasseurExcel());
            classeurs.add(new ClasseurLibreOffice());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ClasseurFactorie getInstance() {
        if (instance == null) {
            instance = new ClasseurFactorie();
        }
        return instance;
    }

    public Classeur getClasseur(String nomFichier) throws IOException {
        Classeur classeurResultat = null;
        for (Classeur classeur : classeurs) {
            if (classeur.peutTraiter(nomFichier)) {

                classeurResultat = classeur.creerClasseur(nomFichier);
            }
        }
        if (classeurResultat == null) {
            throw new IllegalArgumentException("Aucun classeur ne peut traiter le fichier " + nomFichier);
        }
        return classeurResultat;
    }


}
