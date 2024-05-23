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
import java.util.ArrayList;
import java.util.List;


public class Mur {
    private int idMur;
    private Coin debut;
    private Coin fin;
    private int nbrePortes; 
    private int nbreFenetres;
    private double hauteur;
    private uniRevetement revetement ;
    Revetement rev ;
  
    //static int NextId = 1 ;

    public Mur(int idMur, Coin debut, Coin fin, double hauteur, Revetement rev) {
        this.idMur = idMur;
        this.debut = debut;
        this.fin = fin;
        this.nbrePortes = nbrePortes;
        this.nbreFenetres = nbreFenetres;
        this.hauteur = hauteur;
        this.rev = rev;
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
   
    public double Longueur (){
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
        double surfaceFenetres = (nbreFenetres)*1.20*1.20;
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

// créer un mur à partir d'une String, pour pouvoir récup les infos du document .txt informations_batimenet.txt

    public static Mur fromString(String str) {
        String[] parts = str.split(",");
        int idMur = Integer.parseInt(parts[0].trim());
        Coin debut = Coin.fromString(parts[1].trim() + "," + parts[2].trim() + "," + parts[3].trim());
        Coin fin = Coin.fromString(parts[4].trim() + "," + parts[5].trim() + "," + parts[6].trim());
        double hauteur = Double.parseDouble(parts[7].trim());
        int nbrePortes = Integer.parseInt(parts[8].trim());
        int nbreFenetres = Integer.parseInt(parts[9].trim());
        uniRevetement revetement = parts[10].trim().equals("null") ? null : uniRevetement.fromString(parts[10].trim());
        Mur mur = new Mur(idMur, debut, fin, hauteur, null);
        mur.setNbrePortes(nbrePortes);
        mur.setNbreFenetres(nbreFenetres);
        mur.setRevetement(revetement);
        return mur;
    }

    public static List<Mur> fromStringList(String str) {
        List<Mur> murs = new ArrayList<>();
        String[] murStrings = str.split("\\|");
        for (String murStr : murStrings) {
            murs.add(Mur.fromString(murStr.trim()));
        }
        return murs;
    }
}