/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathevet.projetbatiment.projetbatiment.javafx;

import fr.insa.mathevet.projetbatiment.projetbatiment.Projetbatiment;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
/**
 *
 * @author user
 */
public class MainPain extends BorderPane {
    
    private Projetbatiment model;
    private Controleur controleur;

    public Projetbatiment getModel() {
        return model;
    }

    public Controleur getControleur() {
        return controleur;
    }
    
    
    private RadioButton rb;
    
    private Button b;
    private Button couleur;
    
    private Canvas c;
    
    public MainPain (){
        this(new Projetbatiment());
    }
    public MainPain(Projetbatiment model){
        this.model = model;
        //this.controleur = new Controleur(this);
        this.rb = new RadioButton ("rb");
        
        VBox vbGauche = new VBox (this.rb);
        
        this.setLeft(vbGauche); 
        
        this.b = new Button ("b");
        this.b.setOnAction((t) -> {
            System.out.println("bouton b cliqué");
        });
        this.b.setOnMouseEntered((t) -> {
            System.out.println("entered b en" + t.getX() + "," + t.getY());
        });
        
        //this.b.setOnMouseEntered(new EventHandler<MouseEvent>());
        //this.b.setOnAction(EventHandler<ActionEvent> () (
                //@ Override 
                //public void handle(ActionEvent t) (
                    //System.out.println("bouton b cliqué");
                //)
        //));
        VBox vbDroite = new VBox (this.b, this.couleur);
        this.setRight(vbDroite);
        
        //this.c = new DCanvas (this);
        this.setCenter(this.c);
        
    }
}
