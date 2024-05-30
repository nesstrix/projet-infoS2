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


import fr.insa.mathevet.projetbatiment.projetbatiment.Coin;
import fr.insa.mathevet.projetbatiment.projetbatiment.Mur;
import fr.insa.mathevet.projetbatiment.projetbatiment.Piece;
import fr.insa.mathevet.projetbatiment.projetbatiment.Revetement;
import fr.insa.mathevet.projetbatiment.projetbatiment.Sol;
import fr.insa.mathevet.projetbatiment.projetbatiment.uniRevetement;
import fr.insa.mathevet.projetbatiment.projetbatiment.Plafond;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.util.List;


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
    private Map<Piece, Integer> pieceToAppartement = new HashMap<>();

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

    panel.add(new JLabel ("Selectionnez le niveau à afficher: "));
    niveauComboBox = new JComboBox <>();
    panel.add(niveauComboBox);
    
    JButton saveButton = new JButton("Sauvegarder Devis");
    saveButton.addActionListener(new SaveButtonListener());
    panel.add(saveButton);

    JButton drawButton = new JButton("Plan 2D");
    drawButton.addActionListener(new DrawButtonListener());
    panel.add(drawButton);

    JButton loadButton = new JButton("Charger Projet");
    loadButton.addActionListener(new LoadButtonListener());
    panel.add(loadButton);

    add(panel);
}
/*
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

        JButton loadButton = new JButton("Charger Projet");
        loadButton.addActionListener(new LoadButtonListener());
        panel.add(loadButton);

        add(panel);
    }    */
    
    

private class LoadButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            chargerInfosProjet(selectedFile);
        }
    }
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

    private class LoadButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                loadProject(file);
            }
        }
    }

    private void detpiece(String type) {
        niveauComboBox.removeAllItems();
        for (int k = 0; k < nbNiveaux; k++) {
            niveauComboBox.addItem(k + 1);
            if (type.equals("Immeuble")) {
                String nbAppartementsStr = JOptionPane.showInputDialog(null, "Combien d'appartements voulez-vous au niveau " + (k + 1) + " ?");
                try {
                    int nbAppartements = Integer.parseInt(nbAppartementsStr);
                    List<Piece> piecesNiveau = new ArrayList<>();
                    for (int a = 0; a < nbAppartements; a++) {
                        String nbPiecesStr = JOptionPane.showInputDialog(null, "Combien de pièces voulez-vous dans l'appartement " + (a + 1) + " au niveau " + (k + 1) + " ?");
                        try {
                            int nbPieces = Integer.parseInt(nbPiecesStr);
                            for (int i = 0; i < nbPieces; i++) {
                                JOptionPane.showMessageDialog(null, "Configuration de la pièce numéro " + (i + 1) + " dans l'appartement " + (a + 1) + " au niveau " + (k + 1));
                                List<Mur> murs = new ArrayList<>();
                                for (int j = 0; j < 4; j++) {
                                    detmur(murs, i, j);
                                }
                                int solId = detsol(i, murs);
                                int plafondId = detplafond(i, murs);

                                Piece piece = new Piece(i, solId, plafondId, 2.5, murs);
                                piecesNiveau.add(piece);
                                pieceToAppartement.put(piece, a);
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Veuillez entrer un nombre valide de pièces.", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    piecesParNiveau.put(k + 1, piecesNiveau);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Veuillez entrer un nombre valide d'appartements.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                String nbPiecesStr = JOptionPane.showInputDialog(null, "Combien de pièces voulez-vous au niveau " + (k + 1) + " ?");
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
                        pieceToAppartement.put(piece, 0);
                    }
                    piecesParNiveau.put(k + 1, piecesNiveau);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Veuillez entrer un nombre valide de pièces.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        File file = new File("projet_batiment.txt");
        sauvegarderInfosProjet(file);
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
            //Plafond plafond = new Plafond(i, murs.get(0), murs.get(1), null);
            Plafond plafond = new Plafond(i, murs.get(0), murs.get(1), null);
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
    
    
    
    
    private void sauvegarderInfosProjet(File file) {
    try (FileWriter writer = new FileWriter(file)) {
        writer.write(nbNiveaux + "\n");
        for (int niveau : piecesParNiveau.keySet()) {
            List<Piece> piecesNiveau = piecesParNiveau.get(niveau);
            int nbAppartements = (int) piecesNiveau.stream().map(pieceToAppartement::get).distinct().count();
            writer.write(nbAppartements + "\n");
            for (int appartementId = 0; appartementId < nbAppartements; appartementId++) {
                   List<Piece> piecesAppartement = new ArrayList<>();
                   for (Piece piece : piecesNiveau) {
                    if (pieceToAppartement.get(piece) == appartementId) {
                        piecesAppartement.add(piece);
                    }
                }
                writer.write(piecesAppartement.size() + "\n");
                for (Piece piece : piecesAppartement) {
                    writer.write(piece.getIdPiece() + "\n");
                    writer.write(piece.getSol() + "\n");
                    writer.write(piece.getPlafond() + "\n");
                    writer.write(piece.getHauteur() + "\n");
                    for (Mur mur : piece.getListMurs()) {
                        writer.write(mur.getIdMur() + "\n");
                        writer.write(mur.getDebut().getX() + "\n");
                        writer.write(mur.getDebut().getY() + "\n");
                        writer.write(mur.getFin().getX() + "\n");
                        writer.write(mur.getFin().getY() + "\n");
                        writer.write(mur.getNbrePortes() + "\n");
                        writer.write(mur.getNbreFenetres() + "\n");
                    }
                }
            }
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Erreur lors de la sauvegarde des informations du projet.", "Erreur", JOptionPane.ERROR_MESSAGE);
    }
}

/*
    private void sauvegarderInfosProjet(File file) {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(nbNiveaux + "\n");
            for (int niveau : piecesParNiveau.keySet()) {
                List<Piece> piecesNiveau = piecesParNiveau.get(niveau);
                int nbAppartements = (int) piecesNiveau.stream().map(pieceToAppartement::get).distinct().count();
                writer.write(nbAppartements + "\n");
                for (int appartementId = 0; appartementId < nbAppartements; appartementId++) {
                    List<Piece> piecesAppartement = piecesNiveau.stream()
                        .filter(p -> pieceToAppartement.get(p).equals(idAppartement))
                        .collect(Collectors.toList());
                    writer.write(piecesAppartement.size() + "\n");
                    for (Piece piece : piecesAppartement) {
                        writer.write(piece.getIdPiece() + "\n");
                        writer.write(piece.getSol() + "\n");
                        writer.write(piece.getPlafond() + "\n");
                        writer.write(piece.getHauteur() + "\n");
                        for (Mur mur : piece.getListMurs()) {
                            writer.write(mur.getIdMur() + "\n");
                            writer.write(mur.getDebut().getX() + "\n");
                            writer.write(mur.getDebut().getY() + "\n");
                            writer.write(mur.getFin().getX() + "\n");
                            writer.write(mur.getFin().getY() + "\n");
                            writer.write(mur.getNbrePortes() + "\n");
                            writer.write(mur.getNbreFenetres() + "\n");
                        }
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la sauvegarde des informations du projet.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }             */
    
    

    private void loadProject(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            piecesParNiveau.clear();
            pieceToAppartement.clear();
            nbNiveaux = Integer.parseInt(reader.readLine());
            for (int i = 0; i < nbNiveaux; i++) {
                List<Piece> piecesNiveau = new ArrayList<>();
                int nbAppartements = Integer.parseInt(reader.readLine());
                for (int j = 0; j < nbAppartements; j++) {
                    int nbPieces = Integer.parseInt(reader.readLine());
                    for (int k = 0; k < nbPieces; k++) {
                        int pieceId = Integer.parseInt(reader.readLine());
                        int solId = Integer.parseInt(reader.readLine());
                        int plafondId = Integer.parseInt(reader.readLine());
                        double hauteur = Double.parseDouble(reader.readLine());
                        List<Mur> murs = new ArrayList<>();
                        for (int l = 0; l < 4; l++) {
                            int murId = Integer.parseInt(reader.readLine());
                            Coin debut = new Coin(l, Double.parseDouble(reader.readLine()), Double.parseDouble(reader.readLine()));
                            Coin fin = new Coin(l, Double.parseDouble(reader.readLine()), Double.parseDouble(reader.readLine()));
                            int nbPortes = Integer.parseInt(reader.readLine());
                            int nbFenetres = Integer.parseInt(reader.readLine());
                            Mur mur = new Mur(murId, debut, fin, hauteur, null);
                            mur.setNbrePortes(nbPortes);
                            mur.setNbreFenetres(nbFenetres);
                            murs.add(mur);
                        }
                        Piece piece = new Piece(pieceId, solId, plafondId, hauteur, murs);
                        piecesNiveau.add(piece);
                        pieceToAppartement.put(piece, j);
                    }
                }
                piecesParNiveau.put(i + 1, piecesNiveau);
            }
            niveauxField.setText(String.valueOf(nbNiveaux));
            updateNiveauComboBox();
            dessinerPlan2D();
            sauvegardeDev();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Erreur lors du chargement du projet.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateNiveauComboBox() {
        niveauComboBox.removeAllItems();
        for (int i = 1; i <= nbNiveaux; i++) {
            niveauComboBox.addItem(i);
        }
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
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(file);
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
                Map<Integer, List<Piece>> appartements = new HashMap<>();
                
                for (Piece piece : piecesNiveau) {
                    Integer appartementId = pieceToAppartement.get(piece);
                    if (!appartements.containsKey(appartementId)) {
                        appartements.put(appartementId, new ArrayList<>());
                    }
                    appartements.get(appartementId).add(piece);
                }
                
                for (Map.Entry<Integer, List<Piece>> appartEntry : appartements.entrySet()) {
                    Integer appartementId = appartEntry.getKey();
                    List<Piece> piecesAppartement = appartEntry.getValue();
                    for (Piece piece : piecesAppartement) {
                        writer.write("Appartement " + appartementId + " - " + piece.toString() + "\n");
                    }
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

    private void dessinerPlan2D() {
        Integer niveauSelectionne = (Integer) niveauComboBox.getSelectedItem(); 
        if (niveauSelectionne != null) {
            List<Piece> piecesNiveau = piecesParNiveau.get(niveauSelectionne);

            JFrame planFrame = new JFrame("Plan 2D du bâtiment - Niveau " + niveauSelectionne);
            planFrame.setSize(800, 600);
            planFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            planFrame.add(new Pan2D(piecesNiveau, pieceToAppartement));
            planFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un niveau.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Projetbatiment app = new Projetbatiment();
            app.setVisible(true);
        });
    }
}

class Pan2D extends JPanel {

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

        for (Integer idAppartement : pieceToAppartement.values()) {
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

    public void drawPieces(Graphics g) {
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
    
    
   private void chargerInfosProjet(File file) {
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        nbNiveaux = Integer.parseInt(reader.readLine().trim());
        piecesParNiveau.clear();
        pieceToAppartement.clear();
        ctparRevetement.clear();
        stparRevetement.clear();
        coutTotal = 0;

        for (int niveau = 1; niveau <= nbNiveaux; niveau++) {
            int nbAppartements = Integer.parseInt(reader.readLine().trim());
            List<Piece> piecesNiveau = new ArrayList<>();

            for (int appartementId = 0; appartementId < nbAppartements; appartementId++) {
                int nbPieces = Integer.parseInt(reader.readLine().trim());

                for (int i = 0; i < nbPieces; i++) {
                    int idPiece = Integer.parseInt(reader.readLine().trim());
                    int sol = Integer.parseInt(reader.readLine().trim());
                    int plafond = Integer.parseInt(reader.readLine().trim());
                    double hauteur = Double.parseDouble(reader.readLine().trim());

                    List<Mur> murs = new ArrayList<>();
                    for (int j = 0; j < 4; j++) { // Assuming 4 walls per piece
                        int idMur = Integer.parseInt(reader.readLine().trim());
                        double debutX = Double.parseDouble(reader.readLine().trim());
                        double debutY = Double.parseDouble(reader.readLine().trim());
                        double finX = Double.parseDouble(reader.readLine().trim());
                        double finY = Double.parseDouble(reader.readLine().trim());
                        int nbPortes = Integer.parseInt(reader.readLine().trim());
                        int nbFenetres = Integer.parseInt(reader.readLine().trim());

                        Coin debut = new Coin(j, debutX, debutY);
                        Coin fin = new Coin(j, finX, finY);
                        Mur mur = new Mur(idMur, debut, fin, hauteur, null);
                        mur.setNbrePortes(nbPortes);
                        mur.setNbreFenetres(nbFenetres);
                        murs.add(mur);
                    }

                    Piece piece = new Piece(idPiece, sol, plafond, hauteur, murs);
                    piecesNiveau.add(piece);
                    pieceToAppartement.put(piece, appartementId);

                    // Calculer le coût pour chaque pièce et chaque mur
                    double surfaceSol = piece.getListMurs().get(0).Longueur() * piece.getListMurs().get(1).Longueur();
                    Revetement solRevetement = getRevetementById(sol); // Méthode pour obtenir le revêtement par ID
                    double coutSol = surfaceSol * solRevetement.getPrixm2();
                    double surfacePlafond = surfaceSol;
                    Revetement plafondRevetement = getRevetementById(plafond); // Méthode pour obtenir le revêtement par ID
                    double coutPlafond = surfacePlafond * plafondRevetement.getPrixm2();

                    coutTotal += coutSol + coutPlafond;
                    ctparRevetement.merge(solRevetement.getNom(), coutSol, Double::sum);
                    stparRevetement.merge(solRevetement.getNom(), surfaceSol, Double::sum);
                    ctparRevetement.merge(plafondRevetement.getNom(), coutPlafond, Double::sum);
                    stparRevetement.merge(plafondRevetement.getNom(), surfacePlafond, Double::sum);

                    for (Mur mur : murs) {
                        double longueurMur = mur.Longueur();
                        double surfacePortes = mur.surfacePortes(mur.getNbrePortes());
                        double surfaceFenetres = mur.surfaceFenetres(mur.getNbreFenetres());
                        double surfaceMur = mur.surface(surfacePortes, surfaceFenetres, longueurMur);
                        Revetement murRevetement = getRevetementById(mur.getRevetement().getId()); // Méthode pour obtenir le revêtement par ID
                        double coutMur = surfaceMur * murRevetement.getPrixm2();

                        coutTotal += coutMur;
                        ctparRevetement.merge(murRevetement.getNom(), coutMur, Double::sum);
                        stparRevetement.merge(murRevetement.getNom(), surfaceMur, Double::sum);
                    }
                }
            }

            piecesParNiveau.put(niveau, piecesNiveau);
        }

        JOptionPane.showMessageDialog(this, "Projet chargé avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Erreur lors du chargement des informations du projet.", "Erreur", JOptionPane.ERROR_MESSAGE);
    }
}

// Méthode pour obtenir un revêtement par son ID
private Revetement getRevetementById(int id) {
    // Remplissez cette méthode pour retourner un Revetement en fonction de l'ID
    // Par exemple, vous pouvez avoir une liste ou une base de données de revêtements à partir de laquelle vous récupérez l'objet
    for (uniRevetement rev : Revetement.remplirRevetements()) {
        if (rev.getId() == id) {
            return new Revetement(rev.getId(), rev.getNom(), rev.getPrixm2(), rev.isPourMur(), rev.isPourSol(), rev.isPourPlafond());
        }
    }
    return null;
}

    
}





/*
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
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
    private Map<Piece, Integer> pieceToAppartement = new HashMap<>();


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

        panel.add(new JLabel ("Selectionnez le niveau à afficher: "));
        niveauComboBox = new JComboBox <>();
        panel.add(niveauComboBox);
        
        JButton saveButton = new JButton("Sauvegarder Devis");
        saveButton.addActionListener(new SaveButtonListener());
        panel.add(saveButton);

        JButton drawButton = new JButton("Plan 2D");
        drawButton.addActionListener(new DrawButtonListener());
        panel.add(drawButton);

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
            if (type.equals("Immeuble")) {
                String nbAppartementsStr = JOptionPane.showInputDialog(null, "Combien d'appartements voulez-vous au niveau " + (k + 1) + " ?");
                try {
                    int nbAppartements = Integer.parseInt(nbAppartementsStr);
                    List<Piece> piecesNiveau = new ArrayList<>();
                    for (int a = 0; a < nbAppartements; a++) {
                        String nbPiecesStr = JOptionPane.showInputDialog(null, "Combien de pièces voulez-vous dans l'appartement " + (a + 1) + " au niveau " + (k + 1) + " ?");
                        try {
                            int nbPieces = Integer.parseInt(nbPiecesStr);
                            //List<Piece> piecesNiveau = new ArrayList<>();
                            for (int i = 0; i < nbPieces; i++) {
                                JOptionPane.showMessageDialog(null, "Configuration de la pièce numéro " + (i + 1) + " dans l'appartement " + (a + 1) + " au niveau " + (k + 1));
                                List<Mur> murs = new ArrayList<>();
                                for (int j = 0; j < 4; j++) {
                                    detmur(murs, i, j);
                                }
                                int solId = detsol(i, murs);
                                int plafondId = detplafond(i, murs);

                                Piece piece = new Piece(i, solId, plafondId, 2.5, murs);
                                piecesNiveau.add(piece);
                                pieceToAppartement.put(piece,a);
                            }
                         } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Veuillez entrer un nombre valide de pièces.", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    piecesParNiveau.put(k + 1, piecesNiveau);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Veuillez entrer un nombre valide d'appartements.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                String nbPiecesStr = JOptionPane.showInputDialog(null, "Combien de pièces voulez-vous au niveau " + (k + 1) + " ?");
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
                        pieceToAppartement.put(piece, 0);
                    }
                    piecesParNiveau.put(k + 1, piecesNiveau);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Veuillez entrer un nombre valide de pièces.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
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

    private void reprendreProj(){
        
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
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(file);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la sauvegarde du devis.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

   /* private void enregistrerinfos() {
        File file = new File("informations_batiment.txt");
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("Informations sur les éléments du bâtiment :\n\n");
            for (Map.Entry<Integer, List<Piece>> entry : piecesParNiveau.entrySet() ){
                int niveau = entry.getKey();
                List<Piece> piecesNiveau = entry.getValue();
                writer.write("Niveau"+ niveau + ":\n");
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
    }   */
  /*  
    
 private void enregistrerinfos() {
    File file = new File("informations_batiment.txt");
    try (FileWriter writer = new FileWriter(file)) {
        writer.write("Informations sur les pièces du bâtiment :\n\n");
        for (Map.Entry<Integer, List<Piece>> entry : piecesParNiveau.entrySet()) {
            int niveau = entry.getKey();
            List<Piece> piecesNiveau = entry.getValue();
            writer.write("Niveau " + niveau + ":\n");
            Map<Integer, List<Piece>> appartements = new HashMap<>();
            
            for (Piece piece : piecesNiveau) {
                Integer appartementId = pieceToAppartement.get(piece);
                if (!appartements.containsKey(appartementId)) {
                    appartements.put(appartementId, new ArrayList<>());
                }
                appartements.get(appartementId).add(piece);
            }
            
            for (Map.Entry<Integer, List<Piece>> appartEntry : appartements.entrySet()) {
                Integer appartementId = appartEntry.getKey();
                List<Piece> piecesAppartement = appartEntry.getValue();
                for (Piece piece : piecesAppartement) {
                    writer.write("Appartement " + appartementId + " - " + piece.toString() + "\n");
                }
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
private void dessinerPlan2D() {
    Integer niveauSelectionne = (Integer) niveauComboBox.getSelectedItem(); 
    if (niveauSelectionne != null) {
        List<Piece> piecesNiveau = piecesParNiveau.get(niveauSelectionne);

        JFrame planFrame = new JFrame("Plan 2D du bâtiment - Niveau " + niveauSelectionne);
        planFrame.setSize(800, 600);
        planFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        planFrame.add(new Pan2D(piecesNiveau, pieceToAppartement));
        planFrame.setVisible(true);
    } else {
        JOptionPane.showMessageDialog(this, "Veuillez sélectionner un niveau.", "Erreur", JOptionPane.ERROR_MESSAGE);
    }
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Projetbatiment app = new Projetbatiment();
            app.setVisible(true);
        });
    }
}


*/