/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathevet.projetbatiment.projetbatiment;


/**
 *
 * @author minasseessam , emma0
 */

public class Sol {
    
    int idSol;
    Mur mur1;
    Mur mur2;
    Revetement rev;
    private uniRevetement revetement ;
    
    
       public Sol(int idSol, Mur Longueur, Mur largeur, Revetement rev) {
        this.idSol = idSol;
        this.mur1 = largeur;
        this.mur2 = Longueur;
        this.rev = rev;
    }
  
  
    @Override
    public String toString() {
        return "Sol{" + "idSol=" + idSol + ", mur1 = " + mur1 + ", mur2 = " + mur2 + ", rev = " + rev +  '}';
    }
    
    public double surface(){
        double surface = (this.mur1.Longueur()) * (this.mur2.Longueur());
        return surface ;
    }
    
    
public void rev(uniRevetement revetement){
    this.revetement=revetement ;
}

public void optionRev(uniRevetement[] revetements){
    System.out.println("Voici vos options de revêtements pour votre sol : ");
    for(uniRevetement rev : revetements){
        if (rev.isPourSol()){
            System.out.println("id : " +rev.getId()+ " : "+ rev.getNom() +" au prix de : "+ rev.getPrixm2());
        }
    }
}

public uniRevetement choisirRevetement(uniRevetement[] revetements, int choixId) {
        for (uniRevetement rev : revetements) {
            if (rev.getId() == choixId && rev.isPourSol()) {
                return rev;
            }
        }
        return null;
    }
 
public double prixsol(double surface){
    System.out.println(surface* revetement.getPrixm2());
    return surface* revetement.getPrixm2();
    
}


    public int getIdSol() {
        return idSol;
    }

    public void setIdsol(int idsol) {
        this.idSol = idSol;
    }
    
   }