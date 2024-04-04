/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathevet.projetbatiment.projetbatiment;

/**
 *
 * @author emma0
 * 
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Revetement {
    
    
    private int idRevetement ;
    private String designation ; 
    private boolean pourMur;
    private boolean pourSol;
    private boolean pourPlafond;
    private double prixUnitaire ;

    public Revetement(int idRevetement, String designation, boolean pourMur, boolean pourSol, boolean pourPlafond, double prixUnitaire) {
        this.idRevetement = idRevetement;
        this.designation = designation;
        this.pourMur = pourMur;
        this.pourSol = pourSol;
        this.pourPlafond = pourPlafond;
        this.prixUnitaire = prixUnitaire;
    }
    
    ArrayList<String> listIndex = new ArrayList<String>(); 
    
    try { 
    BufferedReader br = new BufferedReader(new FileReader("revetement.txt")); 
    String line "";
    split = line.split";";
     while ((line = br.readLine()) != null) {
         String
     }
    
    
}
    public List index ()
            buffered list lire la ligne correspondante et faire return index, 
    
    1 =new Revetement(index);
    

}
        
    

    
    
    catch(FileNotFoundException err) {
    System.out.println("Erreur :le fichier n'existe pas ! \n"+err);
}
    catch (IOException err) {
    System.out.prinln
}
