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
        
      System.out.println("Voulez-vous une maison ou un appartement?");
      System.out.println ("si vous voulez une maison répondre 1, si vous voulez un immeuble répondre 2");
      int l = Lire.i();
      if (l == 1) {
          System.out.println ("combien de niveau voulez-vous?");
          int n = Lire.i();
      } else {
          System.out.println("combien d'appartement voulez-vous?");
          int a = Lire.i();
      }
      //for (int i=0; i < n; i++ ){
          
      //}
      System.out.println ("combien de pièces voulez-vous ?");
      int nbPieces = Lire.i();
      int compteurPiece = 0;
      int compteurSol = 0;
      int compteurPlafond = 0;
      int compteurMur = 0;
      int compteurCoin = 0;
      for (int j=0; j < nbPieces; j++){
          int idPiece= ++compteurPiece;  
          
          System.out.println ("quel est votre premier coin ?");
          int Coindebut = ++compteurCoin;
          double DebutX = Lire.d();
          double DebutY = Lire.d();
          Coin Coin1 = new Coin(Coindebut, DebutX, DebutY);
          
          System.out.println("quel est votre deuxième coin ?");
          int Coinmid = ++compteurCoin;
          double midX = Lire.d();
          double midY = Lire.d();
          Coin Coin2 = new Coin(Coinmid, midX, midY);
          
          System.out.println("quel est votre troisième coin ?");
          int Coinfin = ++compteurCoin;
          double FinX = Lire.d();
          double FinY = Lire.d();
          Coin Coin3 = new Coin(Coinfin, FinX, FinY);
          
          List<Coin> listCoin = new ArrayList <>();
          listCoin.add(Coin1);
          listCoin.add(Coin2);
          listCoin.add(Coin3);
          
          int MurId = ++compteurMur;
          System.out.println("combien voulez-vous de portes pour le mur ?");
          int nbrePortes = Lire.i();
          System.out.println("combien de fenêtres voulez-vous pour le mur ?");
          int nbreFenetres = Lire.i();
          Mur Mur1 = new Mur(MurId, Coin1, Coin2, 3.0);
          
          double surfacefenêtre = Mur1.surfaceFenetres(nbreFenetres);
          System.out.println("la surface fenêtre est : " + surfacefenêtre);
          double surfaceporte = Mur1.surfacePortes(nbrePortes);
          System.out.println("la surface porte est : " + surfaceporte);
          double longueur1 = Mur1.Longueur(Coin1, Coin2);
          System.out.println("la longueur du mur " + MurId + " est : " + longueur1);
          double surfacemur1 = Mur1.surface(surfaceporte, surfacefenêtre , longueur1);
          System.out.println("la surface du mur " + MurId + " est : " + surfacemur1);
          
          List<Mur> listMurs = new ArrayList <>();
          listMurs.add(Mur1);
          
          MurId = ++compteurMur;
          System.out.println("combien voulez-vous de portes pour le mur ?");
          int nbrePortes2 = Lire.i();
          System.out.println("combien de fenêtres voulez-vous pour le mur ?");
          int nbreFenetres2 = Lire.i();
          Mur Mur2 = new Mur(MurId, Coin2, Coin3, 3.0);
          
          double surfacefenêtre2 = Mur2.surfaceFenetres(nbreFenetres2);
          System.out.println("la surface fenêtre est : " + surfacefenêtre2);
          double surfaceporte2 = Mur2.surfacePortes(nbrePortes2);
          System.out.println("la surface porte est : " + surfaceporte2);
          double longueur2 = Mur2.Longueur(Coin2, Coin3);
          System.out.println("la longueur du mur " + MurId + " est : " + longueur2);
          double surfacemur2 = Mur2.surface(surfaceporte2, surfacefenêtre2, longueur2);
          System.out.println("la surface du mur " + MurId + " est : " + surfacemur2);
         
          listMurs.add(Mur2);
          
          int sol = ++compteurSol ;
          int plafond = ++compteurPlafond ;
          Sol Sol1 = new Sol (sol);
          double surfacesol = longueur1 * longueur2;
          System.out.println("la surface du sol " + sol + " est :" + surfacesol);
          System.out.println("la surface du plafond " + plafond + " est :" + surfacesol);
          Piece Piece1 = new Piece(idPiece, sol, plafond, 3.0, listMurs);
          //double surfacepiece = Piece1.Surfacesol(Coin1, Coin3);
          System.out.println ("la surface au sol de la piece " + idPiece + " est :" + surfacesol);
          
          List<Piece> listPieces = new ArrayList <>();
          System.out.println(listMurs);
      }
    }
}
