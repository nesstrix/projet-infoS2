/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathevet.projetbatiment.projetbatiment;

/**
 *
 * @author emma0
 */
public class uniRevetement {
    
    private int id;
    private String nom;
    private boolean pourSol;  
    private boolean pourPlafond; 
    private boolean pourMur; 
    private double prixm2;

    public uniRevetement (int id, String nom, boolean pourMur, boolean pourSol, boolean pourPlafond, double prixm2) {
        this.id = id;
        this.nom= nom;
        this.pourSol = pourSol;
        this.pourPlafond = pourPlafond;
        this.pourMur = pourMur;
        this.prixm2 = prixm2;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public boolean isPourSol() {
        return pourSol;
    }

    public boolean isPourPlafond() {
        return pourPlafond;
    }

    public boolean isPourMur() {
        return pourMur;
    }

    public double getPrixm2() {
        return prixm2;
    }
}
