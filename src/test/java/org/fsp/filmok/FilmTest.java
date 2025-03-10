package org.fsp.filmok;

import org.fsp.filmok.film.Film;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class FilmTest {

    private String titre;
    private Date premiereSortie;
    private String realisateur;
    private int duree;
    private String resume;

    @BeforeEach
    void setUp() {
        this.titre = "Le seigneur des anneaux";
        this.premiereSortie = new Date();
        this.realisateur = "Peter Jackson";
        this.duree = 180;
        this.resume = "Un film de fantasy";
    }

    @Test
    void testFilmNominal() {
        // Given un titre, une date de sortie, un réalisateur, une durée et un résumé qui sont tous valides

        // When, on crée un film
        // Then le film est créé sans erreur
        assertDoesNotThrow(() -> new Film(titre, premiereSortie, realisateur, duree, resume));
    }

    @Test
    void testFilmTitreVideOuNull() {
        // Given un titre vide
        String titreVide = "";

        // When, on crée un film
        // Then une exception est levée
        assertThrows(IllegalArgumentException.class, () -> new Film(titreVide, premiereSortie, realisateur, duree, resume));

        // Given un titre null
        String titreNull = null;

        // When, on crée un film
        // Then une exception est levée
        assertThrows(IllegalArgumentException.class, () -> new Film(titreNull, premiereSortie, realisateur, duree, resume));
    }

    @Test
    void testFilmRealisateurVideOuNull() {
        // Given un réalisateur vide
        String realisateurVide = "";

        // When, on crée un film
        // Then une exception est levée
        assertThrows(IllegalArgumentException.class, () -> new Film(titre, premiereSortie, realisateurVide, duree, resume));

        // Given un réalisateur null
        String realisateurNull = null;

        // When, on crée un film
        // Then une exception est levée
        assertThrows(IllegalArgumentException.class, () -> new Film(titre, premiereSortie, realisateurNull, duree, resume));
    }

    @Test
    void testFilmDureeNegative() {
        // Given une durée négative
        int dureeNegative = -1;

        // When, on crée un film
        // Then une exception est levée
        assertThrows(IllegalArgumentException.class, () -> new Film(titre, premiereSortie, realisateur, dureeNegative, resume));
    }

    @Test
    void testFilmDateSortieFutur() {
        // Given une date de sortie dans le futur
        Date dateSortieFutur = new Date(System.currentTimeMillis() + 1000);

        // When, on crée un film
        // Then une exception est levée
        assertThrows(IllegalArgumentException.class, () -> new Film(titre, dateSortieFutur, realisateur, duree, resume));
    }
}