/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathevet.projetbatiment.projetbatiment;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;
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
    private Coin cc;
    private Coin cd;
    private Sol surfacesol;
    private double hauteur;
    
    private Mur mur1;
    private Mur mur2;
    private Mur mur3;
    private Mur mur4;
    private List<Mur> listMurs;
    
    

    public Piece(int idPiece, Coin ca, Coin cb, Coin cc, Coin cd, Sol surfacesol, double hauteur) {
        this.idPiece = idPiece;
        this.sol = sol;
        this.plafond = plafond;
        this.ca = ca;
        this.cb = cb;
        this.cc = cc;
        this.cd = cd;
        this.surfacesol = surfacesol;
        this.hauteur = hauteur;
        this.mur1 = new Mur(1, ca, cc,3);
        this.mur2 = new Mur (2, cc, cb, 3);
        this.mur3 = new Mur (3, cb, cd, 3);
        this.mur4 = new Mur (4, cd, ca, 3);
        //this.listMurs = new Arraylist <mur1, mur2, mur3, mur4>;
        //this.listMurs = new ArrayList<>(Arrays.asList(mur1, mur2, mur3, mur4));
        List<Mur> listMurs=new ArrayList<>();
        listMurs.add(mur1);
        listMurs.add(mur2);
        listMurs.add(mur3);
        listMurs.add(mur4);
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
