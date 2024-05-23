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
import fr.insa.mathevet.projetbatiment.projetbatiment.Niveau;
import fr.insa.mathevet.projetbatiment.projetbatiment.Piece;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Pan2D extends JPanel{
    
    
    private List<Piece> pieces ;// recup la liste des pièces donc avec les coins etc 
    
    
    public Pan2D(List<Piece> pieces){
        this.pieces = pieces ; 
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        drawPieces(g);
        Graphics2D gl = (Graphics2D) g;  // nous permettra de definir la largeur des traits
        gl.setStroke(new BasicStroke(5));   //pour que le trait du mur soit plus épais sinon pas très beau 
    }
    
    public void drawPieces(Graphics g){
        double minX = Double.MAX_VALUE, minY = Double.MAX_VALUE; //Calculer les limites des pièces 
        double maxX = Double.MIN_VALUE, maxY = Double.MIN_VALUE;
        g.setColor(Color.BLACK);
        for (Piece piece : pieces){  //itérations par pièces    
            
            for (Mur mur : piece.getListMurs()){
                Coin debut = mur.getDebut();
                Coin fin = mur.getFin();
                //gl.setLineWidth(8);
                
                //on calcule les coordonnées minimales et maximales pour determnier les grandeurs du dessin
                minX = Math.min(minX, Math.min(debut.getX(), fin.getX()));      
                minY = Math.min(minY, Math.min(debut.getY(), fin.getY()));
                maxX = Math.max(maxX, Math.max(debut.getX(), fin.getX()));
                maxY = Math.max(maxY, Math.max(debut.getY(), fin.getY()));
                               
                //g.drawLine((int) debut.getX()*7, (int) debut.getY()*7, (int) fin.getX()*7, (int) fin.getY()*7);
                // on fait *7 sinon les pièces sont beaucoup trop petites sur le dessin
                //totalX += debut.getX() * 10 + fin.getX() * 10;   
                //totalY += debut.getY() * 10 + fin.getY() * 10;
                //murCount += 2;   //parce qu'un mur contient 2 coins 
                //pour compter nmb de coins totaux des murs
            }

        }

        double pieceWidth = (maxX - minX)*20;  // taille des pièces dessinées 
        double pieceHeight = (maxY - minY)*20;
        
        int panelWildth = getWidth();  // taille du panneau 
        int panelHeight = getHeight();
        
        int offsetX = (int) ((panelWildth - pieceWidth)/2 - minX*10);  // déterminer les décalages pour centrer les pièces 
        int offsetY = (int) ((panelHeight - pieceHeight)/2 - minY*10);
        
        g.setColor(Color.BLACK);
        Graphics2D gl = (Graphics2D) g;
        gl.setStroke(new BasicStroke(5));
        
        for (Piece piece : pieces){  // écrire au milieu de chaque pièce son id
            double totalX = 0;
            double totalY = 0;
            int coinCount = 0;
            
            for (Mur mur : piece.getListMurs()){
                Coin debut = mur.getDebut();
                Coin fin = mur.getFin();
            
                int debutX = (int) (debut.getX()*10 + offsetX);
                int debutY = (int) (debut.getY()*10 + offsetY);
                int finX = (int) (fin.getX()*10 + offsetX);
                int finY = (int) (fin.getY()*10 + offsetY);
                
                g.drawLine(debutX, debutY, finX,finY);
                
                totalX += debut.getX() + fin.getX();
                totalY += debut.getY() + fin.getY();
                coinCount += 2;
        }
        
        int centerX = (int) ((totalX / coinCount)*10 + offsetX);
        int centerY = (int) ((totalY / coinCount)*10 + offsetY);  
        
        FontMetrics fm = g.getFontMetrics(); // ajustement pour centrer le texte 
        int textWidth = fm.stringWidth("Piece n°" + piece.getIdPiece());
        int textHeight = fm.getHeight();
        
        g.drawString("Piece n°" + piece.getIdPiece(), centerX - textWidth /2, centerY + textHeight /2);
        
    }
    }
    
    
    public void setPieces(List<Piece> pieces){
        this.pieces = pieces ;
        repaint();
    }
    
    
}
