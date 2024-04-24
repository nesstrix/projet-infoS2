/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package fr.insa.mathevet.projetbatiment.projetbatiment;

/**
 *
 * @author emma0
 */
import fr.insa.mathevet.projetbatiment.projetbatiment.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;

public class Projetbatiment {

    public static void main(String[] args) {

      
      System.out.println ("combien de pièces voulez-vous ?");
      int nbPieces = Lire.i();
      int compteurPiece = 0;
      int compteurSol = 0;
      int compteurPlafond = 0;
      for (int j=0; j < nbPieces; j++){
          int PieceId = ++compteurPiece; 
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
              List<Coin> listCoin = new ArrayList <>();
              listCoin.add(CoinDeb);
              listCoin.add(CoinFin);
              System.out.println("combien voulez-vous de portes ?");
              int nbrePortes = Lire.i();
              System.out.println("combien de fenêtres voulez-vous ?");
              int nbreFenetres = Lire.i();
              Mur Mur1 = new Mur(MurId, CoinDeb, CoinFin, 3.0);
              double surfacefenêtre = Mur1.surfaceFenetres(nbreFenetres);
              System.out.println("la surface fenêtre est : " + surfacefenêtre);
              double surfaceporte = Mur1.surfacePortes(nbrePortes);
              System.out.println("la surface porte est : " + surfaceporte);
              double surfacemur = Mur1.surface(surfaceporte, surfacefenêtre);
              System.out.println("la surface du mur est : " + surfacemur);
              List<Mur> listMurs = new ArrayList <>();
              listMurs.add(Mur1);
          }
          int sol = ++compteurSol ;
          int plafond = ++compteurPlafond ;
          Sol Sol1 = new Sol (sol);
          double surfacesol = Sol1.surfacesol();
          System.out.println("la surface du sol est :" + surfacesol);
          Piece Piece1 = new Piece(PieceId, sol, plafond, 3.0, listMurs);
          double surfacepiece = Piece1.surfacesol ()
          System.out.println ("la surface au sol de la piece est :" + surfacepiece);
          
      }


          
          
          
      }
    }

  
}
