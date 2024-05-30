/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package fr.insa.mathevet.projetbatiment.projetbatiment;

/**
 *
 * @author emma0
 * 
 * 
 */

/*
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Projetbatiment {

    public static void main(String[] args) {
        
        uniRevetement[] revetements = Revetement.remplirRevetements();

        System.out.println("Voulez-vous construire une maison ou un immeuble");
        System.out.println("Si vous voulez une maison, répondez 1, si vous voulez un immeuble, répondez 2");
        int typeConstruction = Lire.i();
        int nbNiveaux = 0;
        int compteurPiece = 0;
        int compteurSol = 0;
        int compteurPlafond = 0;
        int compteurMur = 0;
        int compteurCoin = 0;

        if (typeConstruction == 1) {
            System.out.println("Combien de niveaux voulez-vous pour la maison?");
            nbNiveaux = Lire.i();
        } else {
            System.out.println("Combien de niveaux voulez-vous dans l'immeuble?");
            nbNiveaux = Lire.i();  //1 niveau = 1 appart
            
        }

        //System.out.println("Combien de pièces voulez-vous?");
        //int nbPieces = Lire.i();
        HashMap<String, Double> ctparRevetement = new HashMap<>();
        HashMap<String, Double> stparRevetement = new HashMap<>();
        double coutTotal = 0;

        
        
        for (int k = 0; k < nbNiveaux; k++){
            System.out.println("Combien de pièces voulez-vous?");
            int nbPieces = Lire.i();
            List<Piece> listPieces = new ArrayList<>();
        
        for (int i = 0; i < nbPieces; i++) {
            System.out.println("Configuration de la pièce numéro " + (i + 1));
            List<Mur> murs = new ArrayList<>();
            
            

            for (int j = 0; j < 4; j++) {
                System.out.println("Mur "+(i+1)+" "+ (j + 1) + ": Entrez les coordonnées de début (x puis y):");
                int Coindebut = ++compteurCoin;
                double debutX = Lire.d();
                double debutY = Lire.d();
                Coin debut = new Coin(Coindebut, debutX, debutY);

                System.out.println("Entrez les coordonnées de fin (x puis y):");
                int Coinfin = ++compteurCoin;
                double finX = Lire.d();
                double finY = Lire.d();
                Coin fin = new Coin(Coinfin, finX, finY);

                System.out.println("Nombre de portes sur ce mur:");
                int nbPortes = Lire.i();

                System.out.println("Nombre de fenêtres sur ce mur:");
                int nbFenetres = Lire.i();

                Mur mur = new Mur(j, debut, fin, 2.5, null);  // Hauteur fixe pour simplification
                mur.setNbrePortes(nbPortes);
                mur.setNbreFenetres(nbFenetres);

               
                System.out.println("Options de revêtement pour ce mur:");
                for (uniRevetement rev : revetements) {
                    if (rev.isPourMur()) {
                        System.out.println("ID: " + rev.getId() + " - " + rev.getNom() + " à " + rev.getPrixm2() + "€ par m²");
                    }
                }
                
                System.out.println("Entrez l'ID du revêtement que vous souhaitez pour ce mur:");
                int idRevetement = Lire.i();
                uniRevetement revetementChoi = null ;
             
                for (uniRevetement rev : revetements) {
                    if (rev.getId() == idRevetement && rev.isPourMur()) {
                      mur.setRevetement(rev);
                      revetementChoi = rev ;
                      System.out.println("Revêtement choisi : " + rev.getNom());
                      
                  }
                }
                      if (revetementChoi != null){
                        double longueur =mur.Longueur() ;
                        double surfacePortes = mur.surfacePortes(nbPortes);
                        double surfaceFenetres = mur.surfaceFenetres(nbFenetres);
                        double surfaceMur = mur.surface(surfacePortes, surfaceFenetres, longueur);
                        double cout = surfaceMur * revetementChoi.getPrixm2();
                        coutTotal += cout;
                        System.out.println("Surface du mur après déductions : " + surfaceMur + " m²");
                        System.out.println("Coût pour revêtir le mur : " + cout + "€");
                        murs.add(mur);
                        
                        ctparRevetement.merge( revetementChoi.getNom(), cout, Double::sum);
                        stparRevetement.merge(revetementChoi.getNom(), surfaceMur, Double::sum);

                } else {
                    System.out.println("Aucun revêtement valide sélectionné pour ce mur.");
                }
                        
              murs.add(mur);
                
               
            }
                        
            System.out.println("Options de revêtement pour le sol:");
            for (uniRevetement rev : revetements) {
                if (rev.isPourSol()) {
                    System.out.println("ID: " + rev.getId() + " - " + rev.getNom() + " à " + rev.getPrixm2() + "€ par m²");
                }
            }

            
            System.out.println("Entrez l'ID du revêtement que vous souhaitez pour le sol:");
            int idRevetementSol = Lire.i();
            for (uniRevetement revS : revetements) {
                if (revS.getId() == idRevetementSol && revS.isPourSol()) {
                    Sol sol = new Sol(i, murs.get(0), murs.get(1), null);
                    double surfaceSol = sol.surface();
                    double coutSol = surfaceSol * revS.getPrixm2();
                    coutTotal += coutSol;
                    System.out.println("Revêtement choisi pour le sol: " + revS.getNom() + ", Surface du sol: " + surfaceSol + " m², Coût: " + coutSol + "€");
                    
                    ctparRevetement.merge(revS.getNom(), coutSol, Double::sum);
                    stparRevetement.merge(revS.getNom(), surfaceSol, Double::sum);
                  }
                
            } 
                
                System.out.println("Options de revêtement pour le plafond:");
                for (uniRevetement revP : revetements) {
                if (revP.isPourPlafond()) {
                    System.out.println("ID: " + revP.getId() + " - " + revP.getNom() + " à " + revP.getPrixm2() + "€ par m²");
                }
            }
            

            System.out.println("Entrez l'ID du revêtement que vous souhaitez pour le plafond:");
            int idRevetementPlafond = Lire.i();
            for (uniRevetement rev : revetements) {
                if (rev.getId() == idRevetementPlafond && rev.isPourPlafond()) {
                    Sol sol = new Sol(i, murs.get(0), murs.get(1), null);   // on peut garder la surface du sol, car surface sol et plafond sont les mêmes
                    double surfaceSol = sol.surface();
                    double coutPlafond = surfaceSol * rev.getPrixm2();
                    coutTotal += coutPlafond;
                    System.out.println("Revêtement choisi pour le plafond: " + rev.getNom() + ", Surface du plafond: " + surfaceSol + " m², Coût: " + coutPlafond + "€");
                    
                    ctparRevetement.merge(rev.getNom(), coutPlafond, Double::sum);
                    stparRevetement.merge(rev.getNom(), surfaceSol, Double::sum);
                  }
            }
            
            if (i == nbPieces - 1) {  // Après la dernière pièce
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("Devis.txt"))) {
                    writer.write("Coût total global: " + coutTotal + " €\n");
                    writer.write("Détails des coûts et surfaces par revêtement:\n");
                    ctparRevetement.forEach((revName, cost) -> {
                        try {
                            writer.write("Revêtement: " + revName + ", Coût Total: " + cost + "€, Surface Totale: " + stparRevetement.get(revName) + " m²\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                    writer.flush();
                    System.out.println("Devis sauvegardé dans 'Devis.txt'.");
                } catch (IOException e) {
                    System.out.println("Erreur lors de l'écriture dans le fichier : " + e.getMessage());
                }
        
            
       }
    
}
    }
}
    
}
*/        