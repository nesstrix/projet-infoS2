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
    private int sol;
    private int plafond;
    ArrayList<Mur> listMurs=new ArrayList<Mur>();

    public Piece(int idPiece, int sol, int plafond) {
        this.idPiece = idPiece;
        this.sol = sol;
        this.plafond = plafond;
    }

    public double surfaceSol (){
        
    }
    @Override
    public String toString() {
        return "Piece{" + "idPiece=" + idPiece + ", sol=" + sol + ", plafond=" + plafond + '}';
    }
    
    
}
