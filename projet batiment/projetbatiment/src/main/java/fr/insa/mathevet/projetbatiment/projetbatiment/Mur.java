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

    public Mur(int idMur, Coin debut, Coin fin) {
        this.idMur = idMur;
        this.debut = debut;
        this.fin = fin;
        this.nbrePortes = nbrePortes;
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
