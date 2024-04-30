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
import java.util.ArrayList;
import java.util.List;

public class Projetbatiment {

    public static void main(String[] args) {
        
        uniRevetement[] revetements = Revetement.remplirRevetements();

        System.out.println("Voulez-vous construire une maison ou un appartement?");
        System.out.println("Si vous voulez une maison, répondez 1, si vous voulez un appartement, répondez 2");
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
            System.out.println("Combien d'appartements voulez-vous dans l'immeuble?");
            nbNiveaux = Lire.i();  //1 niveau = 1 appart
        }

        System.out.println("Combien de pièces voulez-vous?");
        int nbPieces = Lire.i();

        List<Piece> listPieces = new ArrayList<>();
        
        for (int i = 0; i < nbPieces; i++) {
            System.out.println("Configuration de la pièce numéro " + (i + 1));
            List<Mur> murs = new ArrayList<>();

            for (int j = 0; j < 4; j++) {
                System.out.println("Mur " + (j + 1) + ": Entrez les coordonnées de début (x puis y):");
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

                // Création du mur
                Mur mur = new Mur(j, debut, fin, 2.5);  // Hauteur fixe pour simplification
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
                            double longueur =mur.Longueur(debut, fin) ;
                    double surfacePortes = mur.surfacePortes(nbPortes);
                    double surfaceFenetres = mur.surfaceFenetres(nbFenetres);
                    double surfaceMur = mur.surface(surfacePortes, surfaceFenetres, longueur);
                    double cout = surfaceMur * revetementChoi.getPrixm2();
                    System.out.println("Surface du mur après déductions : " + surfaceMur + " m²");
                    System.out.println("Coût pour revêtir le mur : " + cout + "€");
                    murs.add(mur);
                } else {
                    System.out.println("Aucun revêtement valide sélectionné pour ce mur.");
                }
                        
                // Calcul et affichage de la surface et du coût
                double longueur = mur.Longueur(debut, fin);
                double surfacePortes = mur.surfacePortes(nbPortes);
                double surfaceFenetres = mur.surfaceFenetres(nbFenetres);
                double surfaceMur = mur.surface(surfacePortes, surfaceFenetres, longueur);
                System.out.println("Surface du mur après déductions : " + surfaceMur + " m²");
                murs.add(mur);
            }

            // Création de la pièce avec les murs configurés
            Piece piece = new Piece(i, 0, 0, 2.5, murs);  // IDs sol et plafond non utilisés pour simplification
            listPieces.add(piece);
        }

       
        System.out.println("Voici les pièces créées :");
        for (Piece piece : listPieces) {
            System.out.println(piece);
        }
    }
}