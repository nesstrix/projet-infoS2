/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathevet.projetbatiment.projetbatiment;




/**
 *
 * @author emma0
 */
import java.lang.Math;

public class Mur {
    private int idMur;
    private Coin debut;
    private Coin fin;
    private int nbrePortes; 
    private int nbreFenetres;
    private double hauteur;
    private uniRevetement revetement ;

    //static int NextId = 1 ;

    public Mur(int idMur, Coin debut, Coin fin, double hauteur) {
        this.idMur = idMur;
        this.debut = debut;
        this.fin = fin;
        this.nbrePortes = nbrePortes;
        this.nbreFenetres = nbreFenetres;
        this.hauteur = hauteur;
    }

    public int getIdMur() {
        return idMur;
    }

    public void setIdMur(int idMur) {
        this.idMur = idMur;
    }

    public Coin getDebut() {
        return debut;
    }

    public void setDebut(Coin debut) {
        this.debut = debut;
    }

    public Coin getFin() {
        return fin;
    }

    public void setFin(Coin fin) {
        this.fin = fin;
    }

    public int getNbrePortes() {
        return nbrePortes;
    }

    public void setNbrePortes(int nbrePortes) {
        this.nbrePortes = nbrePortes;
    }

    public int getNbreFenetres() {
        return nbreFenetres;
    }

    public void setNbreFenetres(int nbreFenetres) {
        this.nbreFenetres = nbreFenetres;
    }
     public uniRevetement getRevetement() {
        return revetement;
    }

    public void setRevetement(uniRevetement revetement) {
        this.revetement = revetement;
    }
  
    public double getHauteur() {
        return hauteur;
    }
   
    public double Longueur (Coin debut, Coin fin){
       double longueur = Math.sqrt(Math.pow(this.debut.getX() - this.fin.getX(),2) + Math.pow(this.debut.getY() - this.fin.getY(),2));
       return longueur;
    }
    
    public double surfacePortes (int nbrePortes) {
        //System.out.println ("Combien il y a-t-il de portes ? ");
        //nbrePortes=Lire.i(); 
        double surfacePortes = (nbrePortes)*0.90*2.10;
        return surfacePortes;
    }

    public double surfaceFenetres (int nbreFenetres){
        //System.out.println ("Combien il y a-t-il de fenetres ?");
        //nbreFenetres=Lire.i();
        double surfaceFenetres = (nbreFenetres)*0.90*2.10;
        return surfaceFenetres;
    }
    
    public double surface(double surfacePortes, double surfaceFenetres, double longueur) {
        double surface = Math.abs((longueur*2.5)-surfacePortes-surfaceFenetres);      
        return surface;
    }
    
   
public void rev(uniRevetement revetement){
    this.revetement=revetement ;
}

public void optionRev(uniRevetement[] revetements){
    System.out.println("Voici vos options de revêtements pour vos murs : ");
    for(uniRevetement rev : revetements){
        if (rev.isPourMur()){
            System.out.println("id : " +rev.getId()+ " : "+ rev.getNom() +" au prix de : "+ rev.getPrixm2());
        }
    }
}

public uniRevetement choisirRevetement(uniRevetement[] revetements, int choixId) {
        for (uniRevetement rev : revetements) {
            if (rev.getId() == choixId && rev.isPourMur()) {
                return rev;
            }
        }
        return null; // Retourne null si aucun revêtement correspondant n'est trouvé
    }
 
public double prixmur(double surface){
    System.out.println(surface* revetement.getPrixm2());
    return surface* revetement.getPrixm2();
    
}

    
    @Override
    public String toString() {
        return "Mur{" + "idMur=" + idMur + ", debut=" + debut + ", fin=" + fin + ", nbrePortes=" + nbrePortes + ", nbreFenetres=" + nbreFenetres + '}';
    }

}