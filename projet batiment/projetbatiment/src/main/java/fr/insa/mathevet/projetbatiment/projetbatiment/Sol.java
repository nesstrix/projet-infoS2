/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathevet.projetbatiment.projetbatiment;

/**
 *
 * @author minasseessam
 */
import java.util.ArrayList ;
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;


public class Sol {
    
    private int idsol ;
    ArrayList<Coin> listCoin = new ArrayList<Coin>();
    private Mur mur1 ;
    private Mur mur2 ;

    public Sol(int idsol) {
        this.idsol = idsol;
        //this.longueur1 = longueur1;
        //this.longueur2 = longueur2;
    }

    @Override
    public String toString() {
        return "Sol{" + "idsol=" + idsol + '}';
    }
    

    public int getIdsol() {
        return idsol;
    }

    public void setIdsol(int idsol) {
        this.idsol = idsol;
    }
    
    //private int idsol ;
    //ArrayList<Coin> listCoin = new ArrayList<Coin>();
    //private Coin ca ;
    //private Coin cb ;
 
    
    
    //public Sol(int idsol) {
        //this.idsol = idsol;
    //}

    //@Override
    //public String toString() {
        //return "Sol{" + "idsol=" + idsol + ", listCoin=" + listCoin + '}';
    //}
    
    //public Coin getCa() {
        //return ca;
    //}

    //public Coin getCb() {
        //return cb;
    //}

    //public void setCa(Coin ca) {
        //this.ca = ca;
    //}

    //public void setCb(Coin cb) {
        //this.cb = cb;
    //}

    //public int getIdsol() {
        //return idsol;
    //}

    //public void setIdsol(int idsol) {
        //this.idsol = idsol;
    //}

    public ArrayList<Coin> getListCoin() {
        return listCoin;
    }

    public void setListCoin(ArrayList<Coin> listCoin) {
        this.listCoin = listCoin;
    }
    
//ArrayList<Coin> listCoin = new ArrayList<Coin>();
//listCoin.add(ca);
//listCoin.add(cb);

//public double Surfacesol ( Coin ca , Coin cb ){
    //double cax = ca.getX();
    //double cay = ca.getY();
    //double cbx = cb.getX();
    //double cby = cb.getY();
    //double surfacesol ;
    
    
    //if ((cay!= cby)&&(cbx!= cax)) {
        
    //double maxcx = Math.max(cax, cbx) ;
    //double maxcy = Math.max(cay, cby) ; 
    //double mincy = Math.min(cay, cby) ;
    //double mincx = Math.min(cax, cbx) ;     
    
    //return surfacesol = ((maxcx-mincx)*(maxcy-mincy));
    //}
   
  //}





    //public double getsurface(){
    //return Surface ;
  //}
}

