/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathevet.projetbatiment.projetbatiment.javafx;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author user
 */
public class DCanvas extends Pane {
    
    private MainPain main;
    
    private Canvas realCanvas ;
    
    public DCanvas(MainPain main) {
        this.main= main;
        this.realCanvas = new Canvas(this.getWidth(), this.getHeight());
        this.getChildren().add(this.realCanvas);
        System.out.println("w =" + this.getWidth() + "; h =" + this.getHeight());
        this.realCanvas.heightProperty().bind(this.heightProperty());
        this.realCanvas.heightProperty().addListener((o) -> {
            System.out.println("w =" + this.realCanvas.getWidth() + "; h =" + this.realCanvas.getHeight());
            this.redrawAll();
        });
        this.realCanvas.widthProperty().bind(this.widthProperty());
        this.realCanvas.widthProperty().addListener((o) -> {
            this.redrawAll();
        });
        this.redrawAll();
    }
    
    public void redrawAll () {
        
        GraphicsContext context = this.realCanvas.getGraphicsContext2D();
        context.setFill(Color.WHITE);
        context.fillRect(0,0, this.realCanvas.getWidth(), this.realCanvas.getHeight());
        
    }
    
}
