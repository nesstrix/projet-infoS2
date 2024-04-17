/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package fr.insa.mathevet.projetbatiment.projetbatiment;

/**
 *
 * @author emma0
 */
import fr.insa.mathevet.projetbatiment.projetbatiment.*;

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
          Coin CoinFin = new Coin(Coinfin, FinX, FinY);
          System.out.println("combien voulez-vous de portes ?");
          int nbrePortes = Lire.i();
          System.out.println("combien de fenêtres voulez-vous ?");
          int nbreFenetres = Lire.i();
          Mur Mur1 = new Mur(MurId, CoinDeb, CoinFin, 3.0);
          double surfacefenêtre = Mur1.surfaceFenetres(nbreFenetres);
          double surfaceporte = Mur1.surfacePortes(nbrePortes);
          double surfacemur = Mur1.surface(surfaceporte, surfacefenêtre);
          

          
          
          
      }
    }

  
}
