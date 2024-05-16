/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathevet.projetbatiment.projetbatiment;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;
import javafx.scene.canvas.GraphicsContext;
/**
 *
 * @author emma0
 */
public class Piece {
    
    private int idPiece; 
    private int sol;
    private int plafond; 
    private double hauteur; 
    private List<Mur> listMurs;
    static int Nextid = 0; 

    public Piece(int idPiece, int sol, int plafond, double hauteur, List<Mur> listMurs ) {
        this.idPiece = Nextid ++;
        this.sol = sol;
        this.plafond = plafond;
        this.hauteur = hauteur;
        this.listMurs = listMurs;
    }

    public int getIdPiece() {
        return idPiece;
    }

    public void setIdPiece(int idPiece) {
        this.idPiece = idPiece;
    }

    public int getSol() {
        return sol;
    }

    public void setSol(int sol) {
        this.sol = sol;
    }

    public int getPlafond() {
        return plafond;
    }

    public void setPlafond(int plafond) {
        this.plafond = plafond;
    }

    public double getHauteur() {
        return hauteur;
    }

    public void setHauteur(double hauteur) {
        this.hauteur = hauteur;
    }

    public List<Mur> getListMurs() {
        return listMurs;
    }

    public void setListMurs(List<Mur> listMurs) {
        this.listMurs = listMurs;
    }

    public static int getNextid() {
        return Nextid;
    }

    public static void setNextid(int Nextid) {
        Piece.Nextid = Nextid;
    }
    
    

    
    @Override
    public String toString() {
        return "Piece{" + "idPiece=" + idPiece + ", sol=" + sol + ", plafond=" + plafond + ", hauteur=" + hauteur + ", listMurs=" + listMurs + '}';
    }
    //private int idPiece;
    //private Sol sol;
    //private Plafond plafond;
    //private Coin Coindeb;
    //private Coin Coinfin;
    //private Coin cc;
    //private Coin cd;
    //private Sol surfacesol;
    //private Plafond surfaceplafond;
    //private double hauteur;
    //static int NextID = 1;
    
    //private Mur mur1;
    //private Mur mur2;
    //private Mur mur3;
    //private Mur mur4;
    //private List<Mur> listMurs;
    
     //Coin ca, Coin cb, Coin cc,

    //public Piece(int idPiece, Sol sol, Mur mur1 , Mur mur2, Plafond plafond, double hauteur) {
        //this.idPiece = idPiece;
        //this.sol = sol;
        //this.plafond = plafond;
        //this.ca = ca;
        //this.cb = cb;
        //this.cc = cc;
        //this.cd = cd;
        //this.surfacesol = surfacesol;
        //this.hauteur = hauteur;
        //this.mur1 = new Mur(1, Coindeb, Coinfin,3);
        //this.mur2 = new Mur (2, Coindeb, Coinfin, 3);
        //this.mur3 = new Mur (3, cb, cd, 3);
        //this.mur4 = new Mur (4, cd, ca, 3);
        //this.listMurs = new Arraylist <mur1, mur2, mur3, mur4>;
        //this.listMurs = new ArrayList<>(Arrays.asList(mur1, mur2, mur3, mur4));
        //List<Mur> listMurs=new ArrayList<>();
        //listMurs.add(mur1);
        //listMurs.add(mur2);
        //listMurs.add(mur3);
        //listMurs.add(mur4);
    //}
    
    //public double Surfacepiece() {
        //return Sol.surfacesol;
    //}
    //public Sol getSurfacesol() {
     //   return surfacesol;
    //}

    //public void setSurfacesol(Sol surfacesol) {
        //this.surfacesol = surfacesol;
    //}

    //@Override
    //public String toString() {
        //return "Piece{" + "idPiece=" + idPiece + ", sol=" + sol + ", plafond=" + plafond + ", Coindeb=" + Coindeb + ", Coinfin=" + Coinfin + ", hauteur=" + hauteur + ", mur1=" + mur1 + ", mur2=" + mur2 + ", listMurs=" + listMurs + '}';
    //}

    
    
}
