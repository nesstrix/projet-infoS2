/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package fr.insa.mathevet.projetbatiment.projetbatiment;

/**
 *
 * @author emma0
 */

import java.util.Scanner;

public class Projetbatiment {

    public static void main(String[] args) {
      
      System.out.println("combien de murs voulez vous ?");
      int nbMurs = Lire.i();
      int compteurMur = 0;
      int compteurCoin = 0;
      for (int i=0; i < nbMurs; i++){
          int MurId = ++compteurMur;
          System.out.println ("quel est votre coin debut ?");
          int Coindebut = ++compteurCoin;
          double DebutX = Lire.d();
          double DebutY = Lire.d();
          Coin CoinDeb = new Coin(Coindebut, DebutX, DebutY);
          System.out.println("quel est votre coin fin ?");
          int Coinfin = ++compteurCoin;
          double FinX = Lire.d();
          double FinY = Lire.d();
          Coin CoinFin = new Coin(Coinfin, DebutX, DebutY);
          
          
           uniRevetement[] revetements = Revetement.remplirRevetements();
        Mur mur = new Mur(1, new Coin(1,0, 0), new Coin(2,10, 0), 2.5);

        Scanner scanner = new Scanner(System.in);
        mur.optionRev(revetements);

        System.out.println("Entrez l'ID du revêtement que vous souhaitez pour le mur: ");
        int idRevetement = scanner.nextInt();
        
        uniRevetement revetementChoisi = mur.choisirRevetement(revetements, idRevetement);
        if (revetementChoisi != null) {
            mur.setRevetement(revetementChoisi);
            System.out.println("Revetement choisi: " + revetementChoisi.getNom());
        } else {
            System.out.println("Aucun revêtement valide sélectionné.");
        }
        
          double surfacePortes = mur.surfacePortes();
        double surfaceFenetres = mur.surfaceFenetres();
        double surfaceMur = mur.surface(CoinDeb,CoinFin, surfacePortes, surfaceFenetres);

        // Calcul et affichage du prix du revêtement pour le mur
        mur.prixmur(surfaceMur);
      }
    }
}
