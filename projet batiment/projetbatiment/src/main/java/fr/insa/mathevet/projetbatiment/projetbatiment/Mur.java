/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathevet.projetbatiment.projetbatiment;

import java.lang.Math;


/**
 *
 * @author emma0
 */
public class Mur {
    private int idMur;
    private Coin debut;
    private Coin fin;
    private int nbrePortes; 
    private int nbreFenetres;
    private double hauteur;
    private Revetement revetements ;
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

    
    public double getHauteur() {
        return hauteur;
    }

    public Revetement getRevetements() {
        return revetements;
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
    

    
    @Override
    public String toString() {
        return "Mur{" + "idMur=" + idMur + ", debut=" + debut + ", fin=" + fin + ", nbrePortes=" + nbrePortes + ", nbreFenetres=" + nbreFenetres + '}';
    }


}
