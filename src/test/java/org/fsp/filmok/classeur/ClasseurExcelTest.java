package org.fsp.filmok.classeur;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ClasseurExcelTest {

    private final static String NOM_FICHIER = "Classeur1.xlsx";
    private final static String directory = "src/test/ressources/";

    private ClasseurExcel classeurExcel;

    @BeforeEach
    void setUp() throws IOException {
        classeurExcel = new ClasseurExcel(directory + NOM_FICHIER);
    }

    @Test
    void chargerNominal() {
        //Given un chemin de fichier valide
        String absoluteCheminClasseur = directory + NOM_FICHIER;

        //When on charge le fichier
        //Then le fichier est chargé sans erreur
        assertDoesNotThrow(() -> new ClasseurExcel(absoluteCheminClasseur));
    }

    @Test
    void chargerFichierInexistant() {
        //Given un chemin de fichier inexistant
        String absoluteCheminClasseur = directory + "pasDeClasseur.xlsx";

        //When on charge le fichier
        //Then l'exception IllegalArgumentException est levée
        assertThrows(IllegalArgumentException.class,
                () -> new ClasseurExcel(absoluteCheminClasseur));
    }

    @Test
    void chargerFichierNonValide() {
        //Given un chemin de fichier valide mais qui n'est pas un classeur
        String absoluteCheminClasseur = directory + "fichier.txt";

        //When on charge le fichier
        //Then l'exception IllegalArgumentException est levée
        assertThrows(IllegalArgumentException.class,
                () -> new ClasseurExcel(absoluteCheminClasseur));
    }

    @Test
    void chargerDossier() {
        //Given un chemin de fichier qui est un dossier
        String absoluteCheminClasseur = directory;

        //When on charge le fichier
        //Then l'exception IllegalArgumentException est levée
        assertThrows(IllegalArgumentException.class,
                () -> new ClasseurExcel(absoluteCheminClasseur));
    }

    @Test
    void getNomFeuilleActive() throws IOException {
        //Given un classeur Excel
        //When on récupère le nom de la feuille active
        //Then le nom de la feuille active est "Feuil1" qui est le nom par défaut
        assertEquals("Feuil1", classeurExcel.getNomFeuilleActive());
    }

    @Test
    void testSetFeuilleActive() {
        //Given un nom de feuille
        String nomFeuille = "Feuil2";

        //When on change la feuille active
        classeurExcel.setFeuilleActive(nomFeuille);

        //Then la feuille active est "Feuil2"
        assertEquals(nomFeuille, classeurExcel.getNomFeuilleActive());
    }

    @Test
    void testGetNomsFeuilles() {
        //Given un classeur Excel
        //When on récupère les noms des feuilles
        //Then il y a trois feuilles "Feuil1", "Feuil2" et "Feuil3"
        assertEquals(3, classeurExcel.getNomsFeuilles().size());
        assertTrue(classeurExcel.getNomsFeuilles().contains("Feuil1"));
    }

    @Test
    void testGetNomsColonnes() throws IOException {
        // Given un classeur Excel avec une feuille "Feuil1" et "Titre", "Date" et "Réalisateur" inscrit dans la première ligne
        ArrayList<String> nomsColonnesAttendus = new ArrayList<>();
        nomsColonnesAttendus.add("Titre");
        nomsColonnesAttendus.add("Date");
        nomsColonnesAttendus.add("Réalisateur");

        // When on récupère les noms des colonnes
        // Then les noms des colonnes sont "Titre", "Date" et "Réalisateur"
        assertEquals(nomsColonnesAttendus, classeurExcel.getNomsColonnes("Feuil1"));
        assertEquals(nomsColonnesAttendus, classeurExcel.getNomsColonnes(0));
    }

    @Test
    void testGetValeurs() {
        // Given un classeur Excel avec une feuille "Feuil1" et une colonne "Titre" avec les valeurs "Avatar", "Dune" et "Avenger"
        ArrayList<String> valeursAttendues = new ArrayList<>();
        valeursAttendues.add("Avatar");
        valeursAttendues.add("Dune");
        valeursAttendues.add("Avenger");

        // When on récupère les valeurs de la colonne "Titre"
        // Then les valeurs sont "Avatar", "Dune" et "Avenger"
        assertEquals(valeursAttendues, classeurExcel.getValeurs("Titre"));
    }

    @Test
    void testSetValeurs() {
        // Given un nom de colonne et des valeurs
        String nomColonne = "Date";
        ArrayList<String> valeurs = new ArrayList<>();
        valeurs.add("2021-12-25");
        valeurs.add("2022-01-01");
        valeurs.add("2022-02-14");

        // When on change les valeurs de la colonne "Date"
        classeurExcel.setValeursEcrase(nomColonne, valeurs);

        // Then les valeurs de la colonne "Date" sont "2021-12-25", "2022-01-01" et "2022-02-14"
        assertEquals(valeurs, classeurExcel.getValeurs(nomColonne));
    }

    @Test
    void peutTraiter() throws IOException {
        ClasseurExcel classeurExcel = new ClasseurExcel();
        assertTrue(classeurExcel.peutTraiter(directory + NOM_FICHIER));

        assertFalse(classeurExcel.peutTraiter(directory + "fichier.txt"));
        assertFalse(classeurExcel.peutTraiter(directory + "Classeur.ods"));
        assertFalse(classeurExcel.peutTraiter(directory));
    }
}