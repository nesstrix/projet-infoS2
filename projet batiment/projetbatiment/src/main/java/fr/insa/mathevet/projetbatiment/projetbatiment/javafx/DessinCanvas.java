/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathevet.projetbatiment.projetbatiment.javafx;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
/**
 *
 * @author user
 */
public class DessinCanvas extends Canvas{
    
    public DessinCanvas (double w, double h){
        super(w,h);
        GraphicsContext context = this.getGraphicsContext2D();
        context.setFill(Color.WHITE);
        context.fillRect(0,0, this.getWidth(), this.getHeight());
        
        
    }
    
}
