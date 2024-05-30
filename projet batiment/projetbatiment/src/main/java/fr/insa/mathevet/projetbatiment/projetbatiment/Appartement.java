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
public class Appartement {
    
    private int idAppartement;
    private int idNiveauAppartement;
    ArrayList<Piece> listPieces=new ArrayList<Piece>();

    public int getIdAppartement() {
        return idAppartement;
    }

    public void setIdAppartement(int idAppartement) {
        this.idAppartement = idAppartement;
    }

    public int getIdNiveauAppartement() {
        return idNiveauAppartement;
    }

    public void setIdNiveauAppartement(int idNiveauAppartement) {
        this.idNiveauAppartement = idNiveauAppartement;
    }

    public ArrayList<Piece> getListPieces() {
        return listPieces;
    }

    public void setListPieces(ArrayList<Piece> listPieces) {
        this.listPieces = listPieces;
    }

    
}
