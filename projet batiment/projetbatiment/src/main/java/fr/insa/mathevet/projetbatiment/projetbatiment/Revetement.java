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
import java.nio.file.Path ;
import java.util.ArrayList;
import java.io.FileNotFoundException;


public class Revetement {
    
    
   
  public static void main(String[] args) throws FileNotFoundException {
          String[][] revetements = new String[18][6];
          
        try  {
            BufferedReader br = new BufferedReader(new FileReader("revetementss.txt"));
            String ligne;
            int i = 0;
            while ((ligne = br.readLine()) != null) {  
            
                String[] elmt = ligne.split(";");

                for (int j = 0; j < elmt.length; j++) {
                    revetements[i][j] = elmt[j];
                }
                i++;
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        
        for (int i = 0; i < revetements.length; i++) {
            for (int j = 0; j < revetements[i].length; j++) {
                System.out.print(revetements[i][j] + " ");
            }
            System.out.println();
        }
    }
 
	}

