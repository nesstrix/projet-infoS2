/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathevet.projetbatiment.projetbatiment;

/**
 *
 * @author emma0
 * 
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


public class Revetement {
    
   //on associe directement à chaque éléement du tableau son type, la première colonne sera les id, la deuxième le nom du rev, 
    // les suivantes ses spécificités d'application (sol, mur, plafond)
    public static uniRevetement[] remplirRevetements() {
        List<uniRevetement> revetements = new ArrayList<>();
        
         try (BufferedReader br = new BufferedReader(new FileReader("revetementss.txt"))) {
            String ligne;
            while ((ligne = br.readLine()) != null) {
                String[] elmt = ligne.split(";");
                 int id = Integer.parseInt(elmt[0].trim());
                String nom = elmt[1].trim();
                boolean pourMur = "1".equals(elmt[2].trim());            // les booleens true = 1 et false = 0 (du catalogue) +   .trim() sert à enlever les espaces dit inutiles
                boolean pourSol = "1".equals(elmt[3].trim());
                boolean pourPlafond = "1".equals(elmt[4].trim());
                double prixm2 = Double.parseDouble(elmt[5].trim());
                revetements.add(new uniRevetement(id, nom, pourMur, pourSol, pourPlafond, prixm2));
            }
            } catch (IOException e) {
            e.printStackTrace();
        }
         return revetements.toArray(new uniRevetement[0]);
    }
      public static void main(String[] args) {
        uniRevetement[] revetements = remplirRevetements();
        for (uniRevetement rev : revetements) {
            System.out.println("ID: " + rev.getId() + ", Nom: " + rev.getNom() + ", Pour Sol: " + rev.isPourSol() + ", Pour Plafond: " + rev.isPourPlafond() + ", Pour Mur: " + rev.isPourMur() + ", Prix m2: " + rev.getPrixm2());
        }
    }
}