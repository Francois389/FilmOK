/*
 * ClasseurExcel.java                                  08 mars 2024
 * IUT de Rodez, pas de droit d'auteur
 */

package org.fsp.filmok.classeur;

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
        try {
            workbook = WorkbookFactory.create(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ClasseurExcel(String cheminClasseur) throws IOException {
        File classeur = new File(cheminClasseur);
        if (!classeur.exists()) {
            throw new IllegalArgumentException(String.format("Le fichier %s n'existe pas", cheminClasseur));
        }
        if (!classeur.isFile()) {
            throw new IllegalArgumentException(String.format("%s n'est pas un fichier", cheminClasseur));
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
        int indiceColonne = getNomsColonnes(feuilleActive.getSheetName()).indexOf(nomColonne);
        return getValeurs(indiceColonne);
    }

    @Override
    public ArrayList<String> getValeurs(int indiceColonne) {
        ArrayList<String> columnValues = new ArrayList<>();

        for (Row row : feuilleActive) {
            Cell cell = row.getCell(indiceColonne);
            columnValues.add(cell.getStringCellValue());
        }

        // On retire le nom de la colonne
        columnValues.removeFirst();

        return columnValues;
    }

    @Override
    public void setValeursEcrase(String nomColonne, ArrayList<String> valeurs) {
        int indiceColonne = getNomsColonnes(feuilleActive.getSheetName()).indexOf(nomColonne);
        setValeursEcrase(indiceColonne, valeurs);
    }

    @Override
    public void setValeursEcrase(int indiceColonne, ArrayList<String> valeurs) {
        for (int i = 1; i <= valeurs.size(); i++) {
            Row row = feuilleActive.getRow(i);
            Cell cell = row.createCell(indiceColonne);
            cell.setCellValue(valeurs.get(i - 1));
        }
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
