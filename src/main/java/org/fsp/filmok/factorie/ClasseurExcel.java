/*
 * ClasseurExcel.java                                  08 mars 2024
 * IUT de Rodez, pas de droit d'auteur
 */

package org.fsp.filmok.factorie;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.*;

/**
 * Classeur pour les fichiers Excel
 *
 * @author François de Saint Palais
 */
public class ClasseurExcel implements Classeur {

    private Workbook workbook;
    private Sheet feuilleActive;

    protected ClasseurExcel() throws IOException {
        workbook = WorkbookFactory.create(true);
    }

    public ClasseurExcel(String cheminClasseur) throws IOException {
        File classeur = new File(cheminClasseur);
        if (!classeur.exists()) {
            throw new IllegalArgumentException("Le fichier " + cheminClasseur + " n'existe pas");
        }
        if (!classeur.isFile()) {
            throw new IllegalArgumentException(cheminClasseur + " n'est pas un fichier");
        }

        workbook = WorkbookFactory.create(classeur);
        feuilleActive = workbook.getSheetAt(workbook.getActiveSheetIndex());
    }


    @Override
    public void setCheminClasseur(String cheminClasseur) {

    }

    @Override
    public void setCheminClasseur(File classeur) {

    }

    @Override
    public String getNomFeuilleActive() {
        return workbook.getSheetName(workbook.getActiveSheetIndex());
    }

    @Override
    public void setFeuilleActive(String nomFeuille) {
        workbook.setActiveSheet(workbook.getSheetIndex(nomFeuille));
        feuilleActive = workbook.getSheet(nomFeuille);
    }

    @Override
    public void setFeuilleActive(int indiceFeuille) {
        workbook.setActiveSheet(indiceFeuille);
        feuilleActive = workbook.getSheetAt(indiceFeuille);
    }

    @Override
    public ArrayList<String> getNomsFeuilles() {
        ArrayList<String> nomsFeuilles = new ArrayList<>();
        for (Sheet feuille : workbook) {
            nomsFeuilles.add(feuille.getSheetName());
        }
        return nomsFeuilles;
    }

    @Override
    public void creerFeuille(String nomFeuille) {

    }

    @Override
    public ArrayList<String> getNomsColonnes(String nomFeuille) {
        ArrayList<String> columnNames = new ArrayList<>();

        Sheet sheet = workbook.getSheet(nomFeuille);

        // On récupère les noms des colonnes
        for (Row row : sheet) {

            // On récupère les noms des colonnes
            for (Cell cell : row) {
                columnNames.add(cell.getStringCellValue());
            }
            break;
        }

        return columnNames;
    }

    @Override
    public ArrayList<String> getNomsColonnes(int indiceFeuille) {
        ArrayList<String> columnNames = new ArrayList<>();

        Sheet sheet = workbook.getSheetAt(indiceFeuille);

        // On récupère les noms des colonnes
        for (Row row : sheet) {

            // On récupère les noms des colonnes
            for (Cell cell : row) {
                columnNames.add(cell.getStringCellValue());
            }
            break;
        }

        return columnNames;
    }

    @Override
    public ArrayList<String> getNomsColonnes() {
        return getNomsColonnes(feuilleActive.getSheetName());
    }

    @Override
    public ArrayList<String> getValeurs(String nomColonne) {
        ArrayList<String> columnValues = new ArrayList<>();
        int indiceColonne = getNomsColonnes(feuilleActive.getSheetName()).indexOf(nomColonne);

        for (Row row : feuilleActive) {
            Cell cell = row.getCell(indiceColonne);
            columnValues.add(cell.getStringCellValue());
        }

        return columnValues;
    }

    @Override
    public ArrayList<String> getValeurs(int indiceColonne) {
        ArrayList<String> columnValues = new ArrayList<>();

        for (Row row : feuilleActive) {
            Cell cell = row.getCell(indiceColonne);
            columnValues.add(cell.getStringCellValue());
        }

        return columnValues;
    }

    @Override
    public void setValeursEcrase(String nomColonne, ArrayList<String> valeurs) {

        int indiceColonne = getNomsColonnes(feuilleActive.getSheetName()).indexOf(nomColonne);

        for (int i = 0; i < valeurs.size(); i++) {
            Row row = feuilleActive.getRow(i);
            Cell cell = row.getCell(indiceColonne);
            cell.setCellValue(valeurs.get(i));
        }

    }

    @Override
    public void setValeursEcrase(int indiceColonne, ArrayList<String> valeurs) {

    }

    @Override
    public void addValeurs(String nomColonne, ArrayList<String> valeurs) {
        ArrayList<String> ancienneValeur = getValeurs(nomColonne);
        ancienneValeur.addAll(valeurs);

        setValeursEcrase(nomColonne, ancienneValeur);
    }

    @Override
    public void addValeurs(int indiceColonne, ArrayList<String> valeurs) {
        ArrayList<String> ancienneValeur = getValeurs(indiceColonne);
        ancienneValeur.addAll(valeurs);

        setValeursEcrase(indiceColonne, ancienneValeur);
    }

    @Override
    public boolean peutTraiter(String nomFichier) {
        return nomFichier.endsWith(".xlsx") || nomFichier.endsWith(".xls");
    }

    @Override
    public Classeur creerClasseur(String nomFichier) throws IOException {
        return new ClasseurExcel(nomFichier);
    }
}
