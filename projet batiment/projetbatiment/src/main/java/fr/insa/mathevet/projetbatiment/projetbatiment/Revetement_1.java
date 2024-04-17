/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package insa.devisbatiment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.Normalizer;
import javafx.scene.paint.Color;

/**
 *
 * @author Elève
 */
public class Revetement {
    private String TypeRevetement;
    private String Endroit;
    private double prix;
    private Color color;

    public Revetement(String TypeRevetement, String Endroit) {
        this.TypeRevetement = TypeRevetement;
        this.Endroit = Endroit;
        color = Color.BLACK;
        prix = lectureRevetement();
    }

    public String getTypeRevetement() {
        return TypeRevetement;
    }

    public double getPrix() {
        return prix;
    }

    public void setRevetement(String TypeRevetement){
        this.TypeRevetement = TypeRevetement;
        prix = lectureRevetement();
    }

    private double lectureRevetement() {
        if (TypeRevetement.equals(" ")) {
            return 0;
        }
        if(TypeRevetement.equals("Liège 1") || TypeRevetement.equals("Liège 2") )
            color = Color.ORANGE.darker();
        if(TypeRevetement.equals("Plaquettes de parement")||TypeRevetement.equals("Vinyle"))
            color = Color.ORANGE;
        if(TypeRevetement.equals("Papier peint")||TypeRevetement.equals("Stratifié"))
            color = Color.ORANGE.brighter();
        if(TypeRevetement.equals("Gazon")||TypeRevetement.equals("Peinture 1"))
            color = Color.GREEN.brighter();
        if(TypeRevetement.equals("Carrelage 1")||TypeRevetement.equals("Peinture 2"))
            color = Color.BROWN.brighter();
        if(TypeRevetement.equals("Carrelage 2")||TypeRevetement.equals("Peinture 3"))
            color = Color.BROWN;
        if(TypeRevetement.equals("Lambris 1"))
            color = Color.BROWN.darker();
        if(TypeRevetement.equals("Lambris 2"))
            color = Color.BLUE.brighter();
        if(TypeRevetement.equals("Marbre"))
            color = Color.BLUE;
        if(TypeRevetement.equals("Moquette"))
            color = Color.GREEN.darker();
        if(TypeRevetement.equals("Parquet")||TypeRevetement.equals("Crépie"))
            color = Color.BLUE.darker();
       
        try {
            FileReader fr = new FileReader("Revetement.txt");
            BufferedReader br = new BufferedReader(fr);
            String ligne = "";
            while ((ligne = br.readLine()) != null) {
                String[] decompose = new String[10];
                decompose = ligne.split(";");
                String s = Normalizer
                        .normalize(TypeRevetement, Normalizer.Form.NFD)
                        .replaceAll("[^\\p{ASCII}]", "");
                if (decompose[1].equals(s)) {
                    if (Endroit.equals("Mur") && decompose[2].equals("1")) {
                        return Double.valueOf(decompose[5]);
                    } else if (Endroit.equals("Sol") && decompose[3].equals("1")) {
                        return Double.valueOf(decompose[5]);
                    } else if (Endroit.equals("Plafond") && decompose[4].equals("1")) {
                        return Double.valueOf(decompose[5]);
                    }
                }
            }

            br.close();
        } catch (Exception e) {
            System.out.println("erreur" + e);
        }
        return 0;
    }
    @Override
    public String toString(){
        return TypeRevetement + " " + Endroit;
    }
    public Color getColor(){
        return color;
    }


}