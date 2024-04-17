/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathevet.projetbatiment.projetbatiment;

/**
 *
 * @author emma0
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Revetements {



    public static void main(String[] args) {
        String fichier = "revetement.txt"; 

      
        if (!fichierExiste(fichier)) {
            System.err.println("Le fichier n'existe pas ou n'est pas accessible.");
            return;
        }

       
        String[][] revetements = new String[18][6];

     
        try (BufferedReader br = new BufferedReader(new FileReader(fichier))) {
            String ligne;
            int index= 0;
            while ((ligne = br.readLine()) != null) {
                String[] elements = ligne.split("; ");
                for (int i = 0; i < elements.length && i < revetements[index].length; i++) {
                    revetements[index][i] = elements[i];
                }
                index++;
            }
        } catch (IOException e) {
            
            System.err.println("Erreur de lecture du fichier : " + e.getMessage());
            e.printStackTrace();
            return; 
        }

       
        for (int i = 0; i < revetements.length; i++) {
            for (int j = 0; j < revetements[i].length; j++) {
                System.out.print(revetements[i][j] + " ");
            }
            System.out.println();
        }
    }

  
    private static boolean fichierExiste(String fichier) {
        try {
            FileReader fileReader = new FileReader(fichier);
            fileReader.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}
