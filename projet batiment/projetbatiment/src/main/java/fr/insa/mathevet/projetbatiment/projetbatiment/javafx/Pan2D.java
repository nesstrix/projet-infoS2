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

public class Pan2D extends JPanel {

    private List<Piece> pieces;
    private Map<Piece, Integer> pieceToAppartement;
    private Map<Integer, Color> appartementCouleurs;

    public Pan2D(List<Piece> pieces, Map<Piece, Integer> pieceToAppartement) {
        this.pieces = pieces;
        this.pieceToAppartement = pieceToAppartement;
        this.appartementCouleurs = new HashMap<>();
        assignColorsToApartments();
    }

    private void assignColorsToApartments() {
        Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.ORANGE, Color.MAGENTA, Color.CYAN, Color.PINK, Color.YELLOW, Color.GRAY};
        int colorIndex = 0;

        for (Map.Entry<Piece, Integer> entry : pieceToAppartement.entrySet()) {
            int idAppartement = entry.getValue();
            if (!appartementCouleurs.containsKey(idAppartement)) {
                appartementCouleurs.put(idAppartement, colors[colorIndex % colors.length]);
                colorIndex++;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawPieces(g);
        drawLegend(g);
    }

    private void drawLegend(Graphics g) {
        int x = 10;
        int y = 10;
        int width = 20;
        int height = 20;
        int spacing = 30;

        g.setFont(new Font("Arial", Font.PLAIN, 12));

        for (Map.Entry<Integer, Color> entry : appartementCouleurs.entrySet()) {
            int idAppartement = entry.getKey();
            Color color = entry.getValue();

            g.setColor(color);
            g.fillRect(x, y, width, height);

            g.setColor(Color.BLACK);
            g.drawRect(x, y, width, height);
            g.drawString("Appartement " + idAppartement, x + width + 5, y + height - 5);

            y += spacing;
        }
    }

    private void drawPieces(Graphics g) {
        double minX = Double.MAX_VALUE, minY = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE, maxY = Double.MIN_VALUE;

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

        double pieceWidth = (maxX - minX) * 20;
        double pieceHeight = (maxY - minY) * 20;

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        int offsetX = (int) ((panelWidth - pieceWidth) / 2 - minX * 20);
        int offsetY = (int) ((panelHeight - pieceHeight) / 2 - minY * 20);

        Graphics2D gl = (Graphics2D) g;
        gl.setStroke(new BasicStroke(5));

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

    public void setPieces(List<Piece> pieces, Map<Piece, Integer> pieceToAppartement) {
        this.pieces = pieces;
        this.pieceToAppartement = pieceToAppartement;
        assignColorsToApartments();
        repaint();
    }
}


