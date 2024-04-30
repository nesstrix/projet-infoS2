/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathevet.projetbatiment.projetbatiment;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class Plafond {
    
    private int idPlafond;
    private int sol;
    ArrayList<Coin> listCoin=new ArrayList<Coin>();
    ArrayList<Revetement> listRevetement=new ArrayList<Revetement>();
    ArrayList<Sol> listSol=new ArrayList<Sol>();

    public Plafond(int idPlafond) {
        this.idPlafond = idPlafond;
    }
    
    //public int getSol();
    //Sol surface = new Sol();
    

    @Override
    public String toString() {
        return "Plafond{" + "idPlafond=" + idPlafond + ", listCoin=" + listCoin + ", listRevetement=" + listRevetement + '}';
    }
    
   //public double Surface (Sol sol ) {
       //double spalfond ;
       //splafond = sol.getsurfacesol();
       
       
   }
    
       

