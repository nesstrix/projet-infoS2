/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathevet.projetbatiment.projetbatiment.javafx;

import fr.insa.mathevet.projetbatiment.projetbatiment.Coin;
import fr.insa.mathevet.projetbatiment.projetbatiment.Mur;
import fr.insa.mathevet.projetbatiment.projetbatiment.Piece;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Pan2D extends JPanel {

    private List<Piece> pieces;
    private Map<Piece, Integer> pieceToAppartement;
    private Map<Integer, Color> appartementCouleurs;
    private Random random;

    public Pan2D(List<Piece> pieces, Map<Piece, Integer> pieceToAppartement) {
        this.pieces = pieces;
        this.pieceToAppartement = pieceToAppartement;
        this.appartementCouleurs = new HashMap<>();
        this.random = new Random(); 
        couleurAppartements();
    }

    
    //on assigne une couleur à chaque appartement
    private void couleurAppartements() {
        Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.ORANGE, Color.MAGENTA, Color.CYAN, Color.PINK, Color.YELLOW, Color.GRAY};
        int colorIndex = 0;

        for (Map.Entry<Piece, Integer> entry : pieceToAppartement.entrySet()) {
            int idAppartement = entry.getValue();
            if (!appartementCouleurs.containsKey(idAppartement)) {
                appartementCouleurs.put(idAppartement, couleuraléatoire());
                colorIndex++;
            }
        }
    }

    
    // Génère une couleur aléatoire
    private Color couleuraléatoire() {
        int r = random.nextInt(256); // Génère une valeur rouge entre 0 et 255
        int g = random.nextInt(256); // Génère une valeur verte entre 0 et 255
        int b = random.nextInt(256); // Génère une valeur bleue entre 0 et 255
        return new Color(r, g, b); // Retourne une nouvelle couleur avec les valeurs générées
    }
    
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawPieces(g);
        Legende(g);
    }

    private void Legende(Graphics g) {
        int x = 10;
        int y = 10;
        int largeur = 20;
        int hauteur = 20;
        int espace = 30;

        g.setFont(new Font("Arial", Font.PLAIN, 12));

        for (Map.Entry<Integer, Color> entry : appartementCouleurs.entrySet()) {
            int idAppartement = entry.getKey();
            Color color = entry.getValue();

            g.setColor(color);
            g.fillRect(x, y, largeur, hauteur);

            g.setColor(Color.BLACK);
            g.drawRect(x, y, largeur, hauteur);
            g.drawString("Appartement " + idAppartement, x +largeur + 5, y + hauteur - 5);

            y += espace;
        }
    }

    private void drawPieces(Graphics g) {
        double minX = Double.MAX_VALUE, minY = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE, maxY = Double.MIN_VALUE;
        
        
        // on calcule les coordonnées minimales et maximales pour déterminer l'échelle et la position
        for (Piece piece : pieces) {
            for (Mur mur : piece.getListMurs()) {
                Coin debut = mur.getDebut();
                Coin fin = mur.getFin();

                minX = Math.min(minX, Math.min(debut.getX(), fin.getX()));
                minY = Math.min(minY, Math.min(debut.getY(), fin.getY()));
                maxX = Math.max(maxX, Math.max(debut.getX(), fin.getX()));
                maxY = Math.max(maxY, Math.max(debut.getY(), fin.getY()));
            }
        }
        
        // Calcule la largeur et la hauteur du plan en fonction des coordonnées
        double piecelargeur = (maxX - minX) * 20;
        double piecehauteur = (maxY - minY) * 20;

        int panellargeur = getWidth();
        int panelhauteur = getHeight();

        
        //calcul pour centrer le dessin au milieu 
        int offsetX = (int) ((panellargeur - piecelargeur) / 2 - minX * 20);
        int offsetY = (int) ((panelhauteur - piecehauteur) / 2 - minY * 20);

        
        //nous permet d'avir une épaisseur de mur correcte, blog ; https://openclassrooms.com/forum/sujet/epaisseur-de-line-24839
        Graphics2D gl = (Graphics2D) g;
        gl.setStroke(new BasicStroke(5));  // determine le style du trait

        //dessine chaque pièce
        for (Piece piece : pieces) {
            g.setColor(appartementCouleurs.get(pieceToAppartement.get(piece)));

            int[] xPoints = new int[piece.getListMurs().size()];
            int[] yPoints = new int[piece.getListMurs().size()];
            int pointIndex = 0;

            for (Mur mur : piece.getListMurs()) {
                Coin debut = mur.getDebut();
                Coin fin = mur.getFin();

                int debutX = (int) (debut.getX() * 20 + offsetX);
                int debutY = (int) (debut.getY() * 20 + offsetY);
                int finX = (int) (fin.getX() * 20 + offsetX);
                int finY = (int) (fin.getY() * 20 + offsetY);

                xPoints[pointIndex] = debutX;
                yPoints[pointIndex] = debutY;
                pointIndex++;
            }

            g.fillPolygon(xPoints, yPoints, pointIndex);
            //dessine murs des pieces
            for (Mur mur : piece.getListMurs()) {
                Coin debut = mur.getDebut();
                Coin fin = mur.getFin();

                int debutX = (int) (debut.getX() * 20 + offsetX);
                int debutY = (int) (debut.getY() * 20 + offsetY);
                int finX = (int) (fin.getX() * 20 + offsetX);
                int finY = (int) (fin.getY() * 20 + offsetY);

                g.setColor(Color.BLACK);
                g.drawLine(debutX, debutY, finX, finY);
            }

            
            // Calcule le centre de la pièce pour y dessiner l'ID de la pièce
            double totalX = 0;
            double totalY = 0;
            int coinCount = 0;

            for (Mur mur : piece.getListMurs()) {
                Coin debut = mur.getDebut();
                Coin fin = mur.getFin();

                totalX += debut.getX() + fin.getX();
                totalY += debut.getY() + fin.getY();
                coinCount += 2;
            }

            int centerX = (int) ((totalX / coinCount) * 20 + offsetX);
            int centerY = (int) ((totalY / coinCount) * 20 + offsetY);

            FontMetrics fm = g.getFontMetrics();
            int textWidth = fm.stringWidth("Piece n°" + piece.getIdPiece());
            int textHeight = fm.getHeight();

            g.drawString("Piece n°" + piece.getIdPiece(), centerX - textWidth / 2, centerY + textHeight / 2);
        }
    }
    // Met à jour les pièces et les appartements, puis redessine le panneau
    public void setPieces(List<Piece> pieces, Map<Piece, Integer> pieceToAppartement) {
        this.pieces = pieces;
        this.pieceToAppartement = pieceToAppartement;
        couleurAppartements();
        repaint();
    }
}  


