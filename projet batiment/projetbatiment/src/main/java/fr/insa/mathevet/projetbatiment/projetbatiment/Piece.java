/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathevet.projetbatiment.projetbatiment;
import java.util.ArrayList;
/**
 *
 * @author emma0
 */
public class Piece {
    
    private int idPiece;
    private Sol sol;
    private Plafond plafond;
    private Coin ca;
    private Coin cb;
    private Sol surfacesol;
    private double hauteur;
    
    private Mur mur1;
    private Mur mur2;
    private Mur mur3;
    private Mur mur4;
    private List<Mur> listMurs;
    
    
    ArrayList<Mur> listMurs=new ArrayList<Mur>();

    public Piece(int idPiece, Sol sol, Plafond plafond, Coin ca, Coin cb, Sol surfacesol, double hauteur, Mur mur1, Mur mur2, Mur mur3, Mur mur4, <any> listMurs) {
        this.idPiece = idPiece;
        this.sol = sol;
        this.plafond = plafond;
        this.ca = ca;
        this.cb = cb;
        this.surfacesol = surfacesol;
        this.hauteur = hauteur;
        this.mur1 = mur1;
        this.mur2 = mur2;
        this.mur3 = mur3;
        this.mur4 = mur4;
        this.listMurs = listMurs;
    }

    public Sol getSurfacesol() {
        return surfacesol;
    }

    public void setSurfacesol(Sol surfacesol) {
        this.surfacesol = surfacesol;
    }


    
    



    
        
    }
    @Override
    public String toString() {
        return "Piece{" + "idPiece=" + idPiece + ", sol=" + sol + ", plafond=" + plafond + '}';
    }
    
    
}
