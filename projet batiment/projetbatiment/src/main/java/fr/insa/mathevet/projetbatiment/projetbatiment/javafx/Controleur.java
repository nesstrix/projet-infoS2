/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathevet.projetbatiment.projetbatiment.javafx;

import fr.insa.mathevet.projetbatiment.projetbatiment.Coin;
import fr.insa.mathevet.projetbatiment.projetbatiment.Projetbatiment;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 *
 * @author user
 */
public class Controleur {
    
    private MainPain vue;
    
    private int etat; 
    
    public Controleur (MainPain vue){
        this.vue = vue;
        this.changeEtat(30);
       
    }
    
    public void changeEtat(int nouvelEtat) {
        if (nouvelEtat == 30)
            this.vue.getB().setDisable(true);  
            this.vue.getC().setDisable(true);
        
    }

    void clicDansZoneDessin(MouseEvent t) {
        double id = t.getId();
        double px = t.getX();
        double py = t.getY();
        Color col= Color.color(Math.random(), Math.random(), Math.random());
        Projetbatiment model = this.vue.getModel();
        model.add(new Coin(id, px ,py));
    }
}
