/*
 * Film.java                                  02 mars 2024
 * IUT de Rodez, pas de droit d'auteur
 */

package org.fsp.filmok.film;

import java.util.Date;

/**
 * Représente  un film
 *
 * @author François de Saint Palais
 */
public class Film {

    private String titre;
    private Date premiereSortie;
    private String realisateur;

    /**
     * La durée en minute du film
     */
    private int duree;

    private String resume;

    public Film(String titre, Date premiereSortie, String realisateur, int duree, String resume) {
        titreValide(titre);
        premiereSortieValide(premiereSortie);
        realisateurValide(realisateur);
        dureeValide(duree);

        this.titre = titre;
        this.premiereSortie = premiereSortie;
        this.realisateur = realisateur;
        this.duree = duree;
        this.resume = resume;
    }

    public Film(String titre) {
        this.titre = titre;
    }

    private void dureeValide(int duree) {
        if (duree < 0) {
            throw new IllegalArgumentException("La durée doit être positive");
        }
    }

    private void realisateurValide(String realisateur) {
        if (realisateur == null) {
            throw new IllegalArgumentException("Le réalisateur ne doit pas être null");
        }
    }

    private void premiereSortieValide(Date premiereSortie) {
        if (premiereSortie != null && premiereSortie.after(new Date())) {
            throw new IllegalArgumentException("La date de sortie ne peut pas être dans le futur");
        }
    }

    /**
     * Vérifie si le titre passé en paramètre est valide.
     * Sinon léve {@link IllegalArgumentException}
     * Est considéré comme valide un titre non vide, non blank et non null
     *
     * @param titre Le titre à tester
     * @throws IllegalArgumentException Si le titre est vide blank
     */
    private void titreValide(String titre) throws IllegalArgumentException {
        if (titre == null) {
            throw new IllegalArgumentException("Le titre ne doit pas être null");
        }
        if (titre.isEmpty() || titre.isBlank()) {
            throw new IllegalArgumentException("Le titre ne doit pas être vide");
        }
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        titreValide(titre);
        this.titre = titre;
    }

    public Date getPremiereSortie() {
        return premiereSortie;
    }

    public void setPremiereSortie(Date premiereSortie) {
        premiereSortieValide(premiereSortie);
        this.premiereSortie = premiereSortie;
    }

    public String getRealisateur() {
        return realisateur;
    }

    public void setRealisateur(String realisateur) {
        realisateurValide(realisateur);
        this.realisateur = realisateur;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        dureeValide(duree);
        this.duree = duree;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }
}
