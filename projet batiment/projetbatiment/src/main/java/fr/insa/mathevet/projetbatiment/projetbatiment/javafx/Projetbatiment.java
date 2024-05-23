/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package fr.insa.mathevet.projetbatiment.projetbatiment.javafx;

/**
 *
 * @author emma0
 * 
 * 
 */


// pour lancer le projet, il faut runfile ce document pour que ça marche, et non Run le projet
import fr.insa.mathevet.projetbatiment.projetbatiment.Coin;
import fr.insa.mathevet.projetbatiment.projetbatiment.Mur;
import fr.insa.mathevet.projetbatiment.projetbatiment.Piece;
import fr.insa.mathevet.projetbatiment.projetbatiment.Revetement;
import fr.insa.mathevet.projetbatiment.projetbatiment.Sol;
import fr.insa.mathevet.projetbatiment.projetbatiment.uniRevetement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Projetbatiment extends JFrame {
    private JTextField niveauxField;
    private int nbNiveaux = 0;
    private double coutTotal = 0;
    private HashMap<String, Double> ctparRevetement = new HashMap<>();
    private HashMap<String, Double> stparRevetement = new HashMap<>();
    private List<Piece> pieces = new ArrayList<>();
    private JRadioButton rbMaison;
    private JRadioButton rbImmeuble;
    private Map<Integer, List<Piece>> piecesParNiveau = new HashMap<>();
    private JComboBox<Integer> niveauComboBox;

    public Projetbatiment() {
        setTitle("Projet Batiment");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        panel();
    }

    private void panel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JLabel("Voulez-vous construire une maison ou un immeuble"));
        rbMaison = new JRadioButton("Maison");
        rbImmeuble = new JRadioButton("Immeuble");
        ButtonGroup gp = new ButtonGroup();
        gp.add(rbMaison);
        gp.add(rbImmeuble);
        JPanel radioPanel = new JPanel();
        radioPanel.add(rbMaison);
        radioPanel.add(rbImmeuble);

        panel.add(radioPanel);

        panel.add(new JLabel("Combien de niveaux voulez-vous? "));
        niveauxField = new JTextField();
        panel.add(niveauxField);

        JButton startButton = new JButton("Commencer");
        startButton.addActionListener(new StartButtonListener());
        panel.add(startButton);

        panel.add(new JLabel("Selectionnez le niveau à afficher: "));
        niveauComboBox = new JComboBox<>();
        panel.add(niveauComboBox);

        JButton saveButton = new JButton("Sauvegarder Devis");
        saveButton.addActionListener(new SaveButtonListener());
        panel.add(saveButton);

        JButton drawButton = new JButton("Plan 2D");
        drawButton.addActionListener(new DrawButtonListener());
        panel.add(drawButton);

        JButton projButton = new JButton("Continuer le projet précédent");
        projButton.addActionListener(new ProjButtonListener());
        panel.add(projButton);

        add(panel);
    }

    private class StartButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String type;
            if (rbMaison.isSelected()) {
                type = "Maison";
            } else if (rbImmeuble.isSelected()) {
                type = "Immeuble";
            } else {
                JOptionPane.showMessageDialog(null, "Veuillez sélectionner un type de construction.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                nbNiveaux = Integer.parseInt(niveauxField.getText());
                detpiece(type);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Veuillez entrer un nombre valide de niveaux.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class ProjButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            reprendreProj();
        }
    }

    private class SaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            sauvegardeDev();
            enregistrerinfos();
        }
    }

    private class DrawButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dessinerPlan2D();
        }
    }

    private void detpiece(String type) {
        niveauComboBox.removeAllItems();
        for (int k = 0; k < nbNiveaux; k++) {
            niveauComboBox.addItem(k + 1);
            String nbPiecesStr = JOptionPane.showInputDialog(null, "Combien de pièces voulez-vous au niveau " + (k + 1) + "?");
            try {
                int nbPieces = Integer.parseInt(nbPiecesStr);
                List<Piece> piecesNiveau = new ArrayList<>();
                for (int i = 0; i < nbPieces; i++) {
                    JOptionPane.showMessageDialog(null, "Configuration de la pièce numéro " + (i + 1) + " au niveau " + (k + 1));
                    List<Mur> murs = new ArrayList<>();
                    for (int j = 0; j < 4; j++) {
                        detmur(murs, i, j);
                    }
                    int solId = detsol(i, murs);
                    int plafondId = detplafond(i, murs);

                    Piece piece = new Piece(i, solId, plafondId, 2.5, murs);
                    piecesNiveau.add(piece);
                }
                piecesParNiveau.put(k + 1, piecesNiveau);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Veuillez entrer un nombre valide de pièces.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void detmur(List<Mur> murs, int i, int j) {
        String debutXi = JOptionPane.showInputDialog(null, "Mur " + (i + 1) + " " + (j + 1) + ": Entrez les coordonnées de début (x):");
        String debutYi = JOptionPane.showInputDialog(null, "Entrez les coordonnées de début (y):");
        String finXi = JOptionPane.showInputDialog(null, "Entrez les coordonnées de fin (x):");
        String finYi = JOptionPane.showInputDialog(null, "Entrez les coordonnées de fin (y):");
        String nbPortesi = JOptionPane.showInputDialog(null, "Nombre de portes sur ce mur:");
        String nbFenetresi = JOptionPane.showInputDialog(null, "Nombre de fenêtres sur ce mur:");

        try {
            double debutX = Double.parseDouble(debutXi);
            double debutY = Double.parseDouble(debutYi);
            double finX = Double.parseDouble(finXi);
            double finY = Double.parseDouble(finYi);
            int nbPortes = Integer.parseInt(nbPortesi);
            int nbFenetres = Integer.parseInt(nbFenetresi);

            Mur mur = new Mur(j, new Coin(j, debutX, debutY), new Coin(j, finX, finY), 2.5, null);
            mur.setNbrePortes(nbPortes);
            mur.setNbreFenetres(nbFenetres);

            uniRevetement[] revetements = Revetement.remplirRevetements();
            uniRevetement revetementChoi = choiRev(revetements, "mur");

            if (revetementChoi != null) {
                mur.setRevetement(revetementChoi);
                double longueur = mur.Longueur();
                double surfacePortes = mur.surfacePortes(nbPortes);
                double surfaceFenetres = mur.surfaceFenetres(nbFenetres);
                double surfaceMur = mur.surface(surfacePortes, surfaceFenetres, longueur);
                double cout = surfaceMur * revetementChoi.getPrixm2();
                coutTotal += cout;
                murs.add(mur);

                ctparRevetement.merge(revetementChoi.getNom(), cout, Double::sum);
                stparRevetement.merge(revetementChoi.getNom(), surfaceMur, Double::sum);

            } else {
                JOptionPane.showMessageDialog(null, "Aucun revêtement valide sélectionné pour ce mur.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Veuillez entrer des valeurs valides pour les coordonnées et le nombre de portes/fenêtres.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private int detsol(int i, List<Mur> murs) {
        uniRevetement[] revetements = Revetement.remplirRevetements();
        uniRevetement revetementChoi = choiRev(revetements, "sol");

        if (revetementChoi != null) {
            Sol sol = new Sol(i, murs.get(0), murs.get(1), null);
            double surfaceSol = sol.surface();
            double coutSol = surfaceSol * revetementChoi.getPrixm2();
            coutTotal += coutSol;

            ctparRevetement.merge(revetementChoi.getNom(), coutSol, Double::sum);
            stparRevetement.merge(revetementChoi.getNom(), surfaceSol, Double::sum);
            return revetementChoi.getId();
        } else {
            JOptionPane.showMessageDialog(null, "Aucun revêtement valide sélectionné pour le sol.");
            return -1;
        }
    }

    private int detplafond(int i, List<Mur> murs) {
        uniRevetement[] revetements = Revetement.remplirRevetements();
        uniRevetement revetementChoi = choiRev(revetements, "plafond");

        if (revetementChoi != null) {
            Sol plafond = new Sol(i, murs.get(0), murs.get(1), null);
            double surfacePlafond = plafond.surface();
            double coutPlafond = surfacePlafond * revetementChoi.getPrixm2();
            coutTotal += coutPlafond;

            ctparRevetement.merge(revetementChoi.getNom(), coutPlafond, Double::sum);
            stparRevetement.merge(revetementChoi.getNom(), surfacePlafond, Double::sum);
            return revetementChoi.getId();
        } else {
            JOptionPane.showMessageDialog(null, "Aucun revêtement valide sélectionné pour le plafond.");
            return -1;
        }
    }

    private uniRevetement choiRev(uniRevetement[] revetements, String type) {
        List<uniRevetement> options = new ArrayList<>();
        String message = "Options de revêtement pour le " + type + ":\n";
        for (uniRevetement rev : revetements) {
            if ((type.equals("mur") && rev.isPourMur()) ||
                (type.equals("sol") && rev.isPourSol()) ||
                (type.equals("plafond") && rev.isPourPlafond())) {
                options.add(rev);
                message += "ID: " + rev.getId() + " - " + rev.getNom() + " à " + rev.getPrixm2() + "€ par m²\n";
            }
        }
        String idRevetementStr = JOptionPane.showInputDialog(null, message + "Entrez l'ID du revêtement que vous souhaitez:");
        try {
            int idRevetement = Integer.parseInt(idRevetementStr);
            for (uniRevetement rev : options) {
                if (rev.getId() == idRevetement) {
                    return rev;
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Veuillez entrer un ID de revêtement valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    private void sauvegardeDev() {
        String type = rbMaison.isSelected() ? "Maison" : rbImmeuble.isSelected() ? "Immeuble" : "Inconnu";
        File file = new File("Devis.txt");
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("Devis pour le projet de construction\n\n");
            writer.write("Type de construction: " + type + "\n");
            writer.write("Nombre de niveaux: " + nbNiveaux + "\n");
            writer.write("Coût total global: " + coutTotal + " €\n");
            writer.write("Détails des coûts et surfaces par revêtement:\n");
            for (String revName : ctparRevetement.keySet()) {
                writer.write("Revêtement: " + revName + ", Coût Total: " + ctparRevetement.get(revName) + "€, Surface Totale: " + stparRevetement.get(revName) + " m²\n");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la sauvegarde du devis.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void enregistrerinfos() {
        File file = new File("informations_batiment.txt");
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("Informations sur les pièces du bâtiment :\n\n");
            for (Map.Entry<Integer, List<Piece>> entry : piecesParNiveau.entrySet()) {
                int niveau = entry.getKey();
                List<Piece> piecesNiveau = entry.getValue();
                writer.write("Niveau " + niveau + ":\n");
                for (Piece piece : piecesNiveau) {
                    writer.write(piece.toString() + "\n");
                }
                writer.write("\n");
            }
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(file);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la sauvegarde des informations du bâtiment.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void reprendreProj() {
        File file = new File("informations_batiment.txt");
        if (!file.exists()) {
            JOptionPane.showMessageDialog(this, "Aucun projet précédent trouvé.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            List<Piece> piecesNiveau = null;
            int currentNiveau = -1;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Niveau ")) {
                    if (piecesNiveau != null) {
                        piecesParNiveau.put(currentNiveau, piecesNiveau);
                    }
                    currentNiveau = Integer.parseInt(line.split(" ")[1].replace(":", ""));
                    piecesNiveau = new ArrayList<>();
                } else if (!line.trim().isEmpty()) {
                    Piece piece = Piece.fromString(line);
                    piecesNiveau.add(piece);
                }
            }
            if (piecesNiveau != null) {
                piecesParNiveau.put(currentNiveau, piecesNiveau);
            }
            updateNiveauComboBox();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la lecture du projet précédent.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateNiveauComboBox() {
        niveauComboBox.removeAllItems();
        for (Integer niveau : piecesParNiveau.keySet()) {
            niveauComboBox.addItem(niveau);
        }
    }

    private void dessinerPlan2D() {
        Integer niveauSelectionne = (Integer) niveauComboBox.getSelectedItem();
        if (niveauSelectionne != null) {
            List<Piece> piecesNiveau = piecesParNiveau.get(niveauSelectionne);

            JFrame planFrame = new JFrame("Plan 2D du bâtiment - Niveau " + niveauSelectionne);
            planFrame.setSize(800, 600);
            planFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            planFrame.add(new Pan2D(piecesNiveau));
            planFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un niveau.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Projetbatiment app = new Projetbatiment();
            int response = JOptionPane.showConfirmDialog(null, "Voulez-vous continuer le projet précédent?", "Choisir", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                app.reprendreProj();
            }
            app.setVisible(true);
        });
    }
}
