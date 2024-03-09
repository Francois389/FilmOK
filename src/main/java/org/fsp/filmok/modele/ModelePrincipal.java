/*
 * Modele.java                                  09 mars 2024
 * IUT de Rodez, pas de droit d'auteur
 */

package org.fsp.filmok.modele;

import org.fsp.filmok.factorie.Classeur;

/**
 * @author François de Saint Palais
 */
public class ModelePrincipal {

    private static ModelePrincipal instance;

    private Classeur classeur;

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
}
