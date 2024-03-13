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

    public Mur(int idMur, Coin debut, Coin fin) {
        this.idMur = idMur;
        this.debut = debut;
        this.fin = fin;
    }

    public double surface(Coin debut, Coin fin) {
        double surface = (this.debut.x - this.fin.x)*2.5;      
        return surface;
    }

    @Override
    public String toString() {
        return "Mur{" + "idMur=" + idMur + ", debut=" + debut + ", fin=" + fin + ", surface=" + surface(debut, fin) + '}'; 
    
    }
     
   
    
   
}
