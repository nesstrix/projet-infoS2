/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathevet.projetbatiment.projetbatiment.javafx;

/**
 *
 * @author emma0
 */

import fr.insa.mathevet.projetbatiment.projetbatiment.Coin;
import fr.insa.mathevet.projetbatiment.projetbatiment.Mur;
import fr.insa.mathevet.projetbatiment.projetbatiment.Piece;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Pan2D extends JPanel{
    
    
    private List<Piece> pieces ;  // recup la liste des pièces donc avec les coins etc 
    
    public Pan2D(List<Piece> pieces){
        this.pieces = pieces ; 
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        drawPieces(g);
        Graphics2D gl = (Graphics2D) g;
        gl.setStroke(new BasicStroke(5));   //pour que le trait du mur soit plus épais sinon pas très beau 
    }
    
    public void drawPieces(Graphics g){
        g.setColor(Color.BLACK);
        for (Piece piece : pieces){
            
            double totalX = 0;
            double totalY = 0;
            

            for (Mur mur : piece.getListMurs()){
                Coin debut = mur.getDebut();
                Coin fin = mur.getFin();
                //gl.setLineWidth(8);
                
                g.drawLine((int) debut.getX()*7, (int) debut.getY()*7, (int) fin.getX()*7, (int) fin.getY()*7);
                // on fait *7 sinon les pièces sont beaucoup trop petites sur le dessin
                totalX += debut.getX() * 10 + fin.getX() * 10;   
                totalY += debut.getY() * 10 + fin.getY() * 10;
                //murCount += 2;   //parce qu'un mur contient 2 coins 
                //pour compter nmb de coins totaux des murs
            }
            int centerX = (int) (totalX / 2);   // moy des coordonnées x y pour obtenir position moyenne
            int centerY = (int) (totalY / 2);

            // texte au centre de la pièce pour savoir laquelle elle est 
            g.drawString("Piece n°" + piece.getIdPiece(), centerX, centerY);
        
        }
    }
    
    public void setPieces(List<Piece> pieces){
        this.pieces = pieces ;
        repaint();
    }
    
}
