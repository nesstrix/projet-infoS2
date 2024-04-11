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
        String fichier = "revetement.txt"; // Mettez le chemin de votre fichier ici

        // Vérifier si le fichier existe
        if (!fichierExiste(fichier)) {
            System.err.println("Le fichier n'existe pas ou n'est pas accessible.");
            return;
        }

        // Déclaration et initialisation du tableau à deux dimensions
        String[][] revetements = new String[18][6];

        // Lecture du fichier
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
            // Gestion de l'exception s'il y a une erreur de lecture du fichier
            System.err.println("Erreur de lecture du fichier : " + e.getMessage());
            e.printStackTrace();
            return; // Arrêter l'exécution du programme
        }

        // Affichage du tableau pour vérification
        for (int i = 0; i < revetements.length; i++) {
            for (int j = 0; j < revetements[i].length; j++) {
                System.out.print(revetements[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Méthode pour vérifier si un fichier existe et est accessible en lecture
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
