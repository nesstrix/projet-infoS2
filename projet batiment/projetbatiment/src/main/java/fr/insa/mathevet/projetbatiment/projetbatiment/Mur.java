/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathevet.projetbatiment.projetbatiment;

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

    
    public double surfacePortes (int nbrePortes) {
        System.out.println ("Combien il y a-t-il de portes ? ");
        nbrePortes=Lire.i(); 
        double surfacePortes = (this.nbrePortes)*0.90*2.10;
        return surfacePortes;
    }

    public double surfaceFenetres (int nbreFenetres){
        System.out.println ("Combien il y a-t-il de fenetres ?");
        nbreFenetres=Lire.i();
        double surfaceFenetres = (this.nbreFenetres)*0.90*2.10;
        return surfaceFenetres;
    }
    
    public double surface(Coin debut, Coin fin, double surfacePortes, double surfaceFenetres) {
        double surface = ((this.debut.x - this.fin.x)*2.5)-surfacePortes-surfaceFenetres;      
        return surface;
    }

    @Override
    public String toString() {
        return "Mur{" + "idMur=" + idMur + ", debut=" + debut + ", fin=" + fin + ", nbrePortes=" + nbrePortes + ", nbreFenetres=" + nbreFenetres + '}';
    }


}
