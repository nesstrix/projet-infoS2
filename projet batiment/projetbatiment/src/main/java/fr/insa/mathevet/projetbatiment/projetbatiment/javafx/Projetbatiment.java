/*
 *Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package fr.insa.mathevet.projetbatiment.projetbatiment.javafx;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */



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

/**
 *
 * @author emma0
 * 
 * 
 */

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
    
    
    
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Projetbatiment app = new Projetbatiment();
            app.setVisible(true);
        });
    }

    
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
    //Crée un groupe de boutons pour les boutons radio afin de s'assurer qu'un seul peut être sélectionné à la fois
    ButtonGroup gp = new ButtonGroup();
    gp.add(rbMaison);
    gp.add(rbImmeuble);
    //panneau pour y mettre les boutons
    JPanel radioPanel = new JPanel();
    radioPanel.add(rbMaison);
    radioPanel.add(rbImmeuble);
    // on l'ajoute au panneau principal
    panel.add(radioPanel);

    
    panel.add(new JLabel("Combien de niveaux voulez-vous? "));
    niveauxField = new JTextField();   // champ de texte pour entrer le nombre de niveaux
    panel.add(niveauxField);       // ce champ apparait sur la panneau principal

    
    // création du boutton pour commencer auqel on ajoute un .addActionListener pour suivre les événements sur celui ci 
    JButton startButton = new JButton("Commencer");
    startButton.addActionListener(new StartButtonListener());
    panel.add(startButton);

    panel.add(new JLabel ("Selectionnez le niveau à afficher: "));
    niveauComboBox = new JComboBox <>();   // créations d'un menu déroulant pour selectuonner le niveau
    panel.add(niveauComboBox);
    
    JButton saveButton = new JButton("Sauvegarder Devis");
    saveButton.addActionListener(new SaveButtonListener());
    panel.add(saveButton);

    JButton drawButton = new JButton("Plan 2D");
    drawButton.addActionListener(new DrawButtonListener());
    panel.add(drawButton);

    JButton loadButton = new JButton("Charger Projet");
    loadButton.addActionListener(new ChargerButtonListener());
    panel.add(loadButton);
    add(panel);
}
  

    
private class ChargerButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        // trouvé sur un blog ça sert à choisir un fichier dans l'ordi de l'utilisateur 
        JFileChooser fileChooser = new JFileChooser();
        //permet de choisir le fichier
        int result = fileChooser.showOpenDialog(null);
        // veréfication si un fichier a bien été selectionné
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile(); 
            // appelle de la méthode sur le fichier
            chargerInfosProjet(selectedFile);
        }
    }   }


    private class StartButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String type;
            //le bouton radio "Maison" est sélectionné
            if (rbMaison.isSelected()) {
                type = "Maison";
            } else if (rbImmeuble.isSelected()) {
                type = "Immeuble";
            } else {  //aucun des boutons radio n'est sélectionné, affiche un message d'erreur et termine l'exécution
                JOptionPane.showMessageDialog(null, "Veuillez sélectionner un type de construction.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                nbNiveaux = Integer.parseInt(niveauxField.getText());
                //la méthode detpiece avec le type de construction sélectionné
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

    
    
    private void sauvegardeDev() {
        //  type de construction en fonction de la sélection des boutons radio
        String type = rbMaison.isSelected() ? "Maison" : rbImmeuble.isSelected() ? "Immeuble" : "Inconnu";
        File file = new File("Devis.txt");
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("Devis pour le projet de construction\n\n");
            writer.write("Type de construction: " + type + "\n");
            writer.write("Nombre de niveaux: " + nbNiveaux + "\n");
            writer.write("Coût total global: " + coutTotal + " €\n");
            writer.write("Détails des coûts et surfaces par revêtement:\n");
            // on regarder chaque revêtement pour écrire ses détails (coût et surface) dans le fichier
            for (String revName : ctparRevetement.keySet()) {
                writer.write("Revêtement: " + revName + ", Coût Total: " + ctparRevetement.get(revName) + "€, Surface Totale: " + stparRevetement.get(revName) + " m²\n");
            }   // trouver aussi sur un blog, pour ouvrir le fichier Devis.txt 
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(file);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la sauvegarde du devis.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    private class DrawButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dessinerPlan2D();
        }
    }

    
    
    private void detpiece(String type) {
        //on vide le menu déroulant pour notre nouveau projet
        niveauComboBox.removeAllItems();
        //boucle par niveau
        for (int k = 0; k < nbNiveaux; k++) {
            //rajoute une ligne dans le menu déroulant à chaque nouveau niveau
            niveauComboBox.addItem(k + 1);
            if (type.equals("Immeuble")) {
                // Demande à l'utilisateur combien d'appartements il veut à ce niveau
                // grâce à l'utilisation d'une boite de dialogue et avec showInputDialog ça permet à l'utilisateur de rentrer quelque chose, null : c'est pour l'affichage nous elle s'ouvre du coup un milieu de l'écran 
                String nbAppartementsStr = JOptionPane.showInputDialog(null, "Combien d'appartements voulez-vous au niveau " + (k + 1) + " ?");
                try {
                    int nbAppartements = Integer.parseInt(nbAppartementsStr);
                    //à la ligne précédente, on utilise Integer qui nous permet de manipuler la valeur entrée par l'utilisateur pour ensuite la conventir en entier 
                    List<Piece> piecesNiveau = new ArrayList<>();
                    //boucle pour chaque appartement
                    for (int a = 0; a < nbAppartements; a++) {
                        String nbPiecesStr = JOptionPane.showInputDialog(null, "Combien de pièces voulez-vous dans l'appartement " + (a + 1) + " au niveau " + (k + 1) + " ?");
                        try {
                            int nbPieces = Integer.parseInt(nbPiecesStr);
                            for (int i = 0; i < nbPieces; i++) {   //boucle pour chaque pièce
                                JOptionPane.showMessageDialog(null, "Configuration de la pièce numéro " + (i + 1) + " dans l'appartement " + (a + 1) + " au niveau " + (k + 1));
                                List<Mur> murs = new ArrayList<>();
                                for (int j = 0; j < 4; j++) {  //boucle pour configurer les 4 murs
                                    detmur(murs, i, j);
                                }
                                
                                // on determine les id de sol et plafond
                                int solId = detsol(i, murs);
                                int plafondId = detplafond(i, murs);  
                                
                                // créer un nouvel objet oiece avec les murs et toutes les infos récupérer avant
                                Piece piece = new Piece(i, solId, plafondId, 2.5, murs);
                                piecesNiveau.add(piece);
                                
                                //associe la pièce à un appartement
                                pieceToAppartement.put(piece, a);
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Veuillez entrer un nombre valide de pièces.", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    }    //on associe les pièces config au niveau
                    piecesParNiveau.put(k + 1, piecesNiveau);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Veuillez entrer un nombre valide d'appartements.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } else {  // Si le type de construction est autre que "Immeuble"
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
                        pieceToAppartement.put(piece, 0);   // Associe la pièce à l'appartement (0 pour les maisons)
                    }
                    piecesParNiveau.put(k + 1, piecesNiveau);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Veuillez entrer un nombre valide de pièces.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        // Sauvegarde les informations du projet dans le fichier "projet_batiment.txt"
        File file = new File("projet_batiment.txt");
        sauvegarderInfosProjet(file);
    }

    
    
    private void detmur(List<Mur> murs, int i, int j) {
        
        //lä on demande les coordonnées de début et de fin du mur, nb de porte et nb de fenetre
        String debutXi = JOptionPane.showInputDialog(null, "Mur " + (i + 1) + " " + (j + 1) + ": Entrez les coordonnées de début (x):");
        String debutYi = JOptionPane.showInputDialog(null, "Entrez les coordonnées de début (y):");
        String finXi = JOptionPane.showInputDialog(null, "Entrez les coordonnées de fin (x):");
        String finYi = JOptionPane.showInputDialog(null, "Entrez les coordonnées de fin (y):");
        String nbPortesi = JOptionPane.showInputDialog(null, "Nombre de portes sur ce mur:");
        String nbFenetresi = JOptionPane.showInputDialog(null, "Nombre de fenêtres sur ce mur:");

        
        //lä comme au dessus, on convertir ce que l'utilisateur a entré en double ou int
        try {
            double debutX = Double.parseDouble(debutXi);
            double debutY = Double.parseDouble(debutYi);
            double finX = Double.parseDouble(finXi);
            double finY = Double.parseDouble(finYi);
            int nbPortes = Integer.parseInt(nbPortesi);
            int nbFenetres = Integer.parseInt(nbFenetresi);

            
            // créer nouvel objet mur avec ses caractéristiques récupérées avant + hauteur du mur fixé à 2.5 m
            Mur mur = new Mur(j, new Coin(j, debutX, debutY), new Coin(j, finX, finY), 2.5, null);
            mur.setNbrePortes(nbPortes);
            mur.setNbreFenetres(nbFenetres);

            
            // on remplit un tableau de revetements pour que l'utilisateur puisse choisir les revetements parmi les revetements POUR mur 
            uniRevetement[] revetements = Revetement.remplirRevetements();
            uniRevetement revetementChoi = choiRev(revetements, "mur");

            if (revetementChoi != null) {
                // Si un revêtement valide est sélectionné, le définit pour le mur
                mur.setRevetement(revetementChoi);
                double longueur = mur.Longueur();
                double surfacePortes = mur.surfacePortes(nbPortes);
                double surfaceFenetres = mur.surfaceFenetres(nbFenetres);
                double surfaceMur = mur.surface(surfacePortes, surfaceFenetres, longueur);
                double cout = surfaceMur * revetementChoi.getPrixm2();
                coutTotal += cout;
                murs.add(mur);

                
                
                //cout par revetement, ici on stocke le cout total pourchaque type de revetement, même chose pour surface par revetement 
                ctparRevetement.merge(revetementChoi.getNom(), cout, Double::sum);   // Double::sum sur un blog, il fait la somme de deux double l'ancien et le nouveau 
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
        List<uniRevetement> options = new ArrayList<>();  // on crée une liste pour stocker les revêtements disponibles pour mur sol ou plafond 
        String message = "Options de revêtement pour le " + type + ":\n";
        
        //on parcourt tous les revetements disponibles et on verifier si le re^vetement est approprié pour le type (mur, sol, plafond)
        for (uniRevetement rev : revetements) {
            if ((type.equals("mur") && rev.isPourMur()) ||
                (type.equals("sol") && rev.isPourSol()) ||
                (type.equals("plafond") && rev.isPourPlafond())) {
                options.add(rev);   //on l'ajout à la liste des options possibles
                message += "ID: " + rev.getId() + " - " + rev.getNom() + " à " + rev.getPrixm2() + "€ par m²\n";
            }
        }
         // Affiche une boîte de dialogue pour demander à l'utilisateur d'entrer l'ID du revêtement souhaité
        String idRevetementStr = JOptionPane.showInputDialog(null, message + "Entrez l'ID du revêtement que vous souhaitez:");
        try {
            int idRevetement = Integer.parseInt(idRevetementStr);
            for (uniRevetement rev : options) {
                 // verification l'ID du revêtement correspond à celui entré par l'utilisateur, retourne ce revêtement
                if (rev.getId() == idRevetement) {
                    return rev;
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Veuillez entrer un ID de revêtement valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    
      
   // méthode qui n'aboutit pas à ce que l'on voulait par ce que j'arrive pas à écrire l'id du revetement que l'utilisateur à choisi pour le plafond ou le sol du coup 
    // pour plus tard il nous fait un devis mais que pour les murs sans compter les sols et plafonds
private void sauvegarderInfosProjet(File file) {
    try (FileWriter writer = new FileWriter(file)) {
       
        if ( rbMaison.isSelected()){
            writer.write("maison  \n");
        }  else {
            writer.write("immeuble   \n");
        }

        writer.write(nbNiveaux + "\n");

        for (int niveau : piecesParNiveau.keySet()) {
            List<Piece> piecesNiveau = piecesParNiveau.get(niveau);
            if (rbImmeuble.isSelected()) {
                int nbAppartements = (int) piecesNiveau.stream().map(pieceToAppartement::get).distinct().count();   //récupère l'identifiant d'appartement pour une pièce donnée en un entier grâce à (int), on enlèbe les doublons , count() nous donne le nb d'appartements discincts à chaque niveau
                writer.write(nbAppartements + "\n");

                for (int appartementId = 0; appartementId < nbAppartements; appartementId++) {
                    List<Piece> piecesAppartement = new ArrayList<>();
                    for (Piece piece : piecesNiveau) {
                        if (pieceToAppartement.get(piece) == appartementId) { // on vérifie si  la pièce actuelle appartient à l'appartement en cours de traitement alors on l'ajoute à la liste piecesAppartement
                            piecesAppartement.add(piece);
                        }
                    }

                    // on écrit le nmb de pièce dans l'appartement 
                    writer.write(piecesAppartement.size() + "\n");

                    for (Piece piece : piecesAppartement) {
                        writer.write(piece.getIdPiece() + "\n");
                        // on écrit le détail pour chaque mur de la pièce
                        for (Mur mur : piece.getListMurs()) {
                           
                            writer.write(mur.getDebut().getX()  +  "\n" );
                            writer.write(mur.getDebut().getY()  +  "\n" );
                            
                            writer.write(mur.getFin().getX()  +  "\n" );
                            writer.write(mur.getFin().getY()  +  "\n" );
                            
                            
                            //j'ai récupéré les méthodes de calcul de surface de la classe Mur
                            double longueur = Math.sqrt(Math.pow(mur.getFin().getX() - mur.getDebut().getX(), 2) + Math.pow(mur.getFin().getY() - mur.getDebut().getY(), 2));
                            double surfacePortes = mur.getNbrePortes() * 0.90 * 2.10;
                            double surfaceFenetres = mur.getNbreFenetres() * 1.20 * 1.20;
                            double surface = (longueur * 2.5) - surfacePortes - surfaceFenetres;

                            writer.write(surface + "\n");
                            writer.write(mur.getRevetement().getPrixm2() + "\n"); 
                        }
                        
                        
                        double longueurSol = Math.sqrt(Math.pow(piece.getListMurs().get(1).getDebut().getX() - piece.getListMurs().get(0).getDebut().getX(), 2) + Math.pow(piece.getListMurs().get(1).getDebut().getY() - piece.getListMurs().get(0).getDebut().getY(), 2));
                        double largeurSol = Math.sqrt(Math.pow(piece.getListMurs().get(3).getDebut().getX() - piece.getListMurs().get(0).getDebut().getX(), 2) + Math.pow(piece.getListMurs().get(3).getDebut().getY() - piece.getListMurs().get(0).getDebut().getY(), 2));
                        double surfaceSol = longueurSol * largeurSol;
                        // ici est le problème du devis pour le sol et plafond, j'arrive pas à faire en sorte de récuperer le prix du sol 
                        double prixm2Sol =0;
                        //double prixm2Sol = sol.getRevetement().getPrixm2();  
                        // ça ne marche pas je comprends pas 
                        
                        writer.write(surfaceSol + "\n");
                        

                        // du coup c'est la même chose pour le plafond
                        double surfacePlafond = longueurSol * largeurSol;  
                        double prixm2Plafond =0 ;
                        double coutPlafond = surfacePlafond * prixm2Plafond;
                        writer.write(surfacePlafond + "\n");
                       
                    }
                }
            } else {   // Si c'est une maison, écrit le nombre de pièces du niveau
                writer.write(piecesNiveau.size() + "\n");
                for (Piece piece : piecesNiveau) {
                    writer.write(piece.getIdPiece() + "\n");
                    writer.write(piece.getListMurs().size() + "\n");
                    for (Mur mur : piece.getListMurs()) {
                       
                            writer.write(mur.getDebut().getX()  +  "\n" );
                            writer.write(mur.getDebut().getY()  +  "\n" );
                            
                            writer.write(mur.getFin().getX()  +  "\n" );
                            writer.write(mur.getFin().getY()  +  "\n" );
                            
                            
                        double longueur = Math.sqrt(Math.pow(mur.getFin().getX() - mur.getDebut().getX(), 2) + Math.pow(mur.getFin().getY() - mur.getDebut().getY(), 2));
                        double surfacePortes = mur.getNbrePortes() * 0.90 * 2.10;
                        double surfaceFenetres = mur.getNbreFenetres() * 1.20 * 1.20;
                        double surface = (longueur * mur.getHauteur()) - surfacePortes - surfaceFenetres;
                        
                        writer.write(surface + "\n");
                        writer.write(mur.getRevetement().getPrixm2() + "\n"); 
                    }
                    
                    double longueurSol = Math.sqrt(Math.pow(piece.getListMurs().get(1).getDebut().getX() - piece.getListMurs().get(0).getDebut().getX(), 2) + Math.pow(piece.getListMurs().get(1).getDebut().getY() - piece.getListMurs().get(0).getDebut().getY(), 2));
                    double largeurSol = Math.sqrt(Math.pow(piece.getListMurs().get(3).getDebut().getX() - piece.getListMurs().get(0).getDebut().getX(), 2) + Math.pow(piece.getListMurs().get(3).getDebut().getY() - piece.getListMurs().get(0).getDebut().getY(), 2));
                    double surfaceSol = longueurSol * largeurSol;
                    //double prixm2Sol = sol.getRevetement().getPrixm2() + "\n"; 
                    writer.write(surfaceSol + "\n");
                    //writer.write(prixm2Sol+ "\n");

                    double surfacePlafond = longueurSol * largeurSol; 
                    double prixm2Plafond = 0;
                    writer.write(surfacePlafond + "\n");
                    writer.write(prixm2Plafond + "\n");
                }
            }
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Erreur lors de la sauvegarde des informations du projet.", "Erreur", JOptionPane.ERROR_MESSAGE);
    }
}

// cette méthode sert à charger les infos du projet précédent et en calculer le devis 
private void chargerInfosProjet(File file) {
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        String line;
        
        // on lit les lignes 
        String type = reader.readLine().trim(); 
        nbNiveaux = Integer.parseInt(reader.readLine().trim()); 
        
        piecesParNiveau.clear(); 
        ctparRevetement.clear();
        stparRevetement.clear();
        coutTotal = 0; 

        
        //on parcourt chaque niveau 
        for (int niveau = 1; niveau <= nbNiveaux; niveau++) {
            List<Piece> piecesNiveau = new ArrayList<>();
            if (type.equals("immeuble")) {
                // on lit le nm d'appartements à ce niveau 
                int nbAppartements = Integer.parseInt(reader.readLine().trim()); 
                System.out.println("Level " + niveau + ": " + nbAppartements + " apartments"); 
                
                for (int appartement = 0; appartement < nbAppartements; appartement++) {
                    int nbPieces = Integer.parseInt(reader.readLine().trim()); //on lit la valeur à la ligne qui va correspondre au nmb de pièces
                    System.out.println("Apartment " + appartement + ": " + nbPieces + " pieces"); 
                    for (int pieceIndex = 0; pieceIndex < nbPieces; pieceIndex++) {
                        int pieceId = Integer.parseInt(reader.readLine().trim());
                        List<Mur> murs = new ArrayList<>();
                        //int nbMurs = Integer.parseInt(reader.readLine().trim());
                        for (int murIndex = 0; murIndex < 3; murIndex++) {
                            // on lit les détails de chaque mur de la pièce
                            double debutX = Double.parseDouble(reader.readLine().trim());
                            double debutY = Double.parseDouble(reader.readLine().trim());
                            double finX = Double.parseDouble(reader.readLine().trim());
                            double finY = Double.parseDouble(reader.readLine().trim());
                            double surface = Double.parseDouble(reader.readLine().trim());
                            double prixm2 = Double.parseDouble(reader.readLine().trim());

                            Mur mur = new Mur(murIndex, new Coin(murIndex, debutX, debutY), new Coin(murIndex, finX, finY), 2.5, null);
                            uniRevetement revetement = new uniRevetement(murIndex, "Revetement_" + murIndex, true, true, true, prixm2);
                            mur.setRevetement(revetement);

                            double cout = surface * prixm2;
                            coutTotal += cout;

                            murs.add(mur);

                            ctparRevetement.merge(revetement.getNom(), cout, Double::sum);
                            stparRevetement.merge(revetement.getNom(), surface, Double::sum);

                           
                        }

                        //ne marche pas du coup à cause du problème de la méthode sauvegarderInfosProjet
                        double surfaceSol = Double.parseDouble(reader.readLine().trim());
                        double prixm2sol = Double.parseDouble(reader.readLine().trim());
                        double coutSol = surfaceSol * prixm2sol ;
                        coutTotal += coutSol;

                        double surfacePlafond = Double.parseDouble(reader.readLine().trim());
                        double prixm2plafond = Double.parseDouble(reader.readLine().trim());
                        double coutPlafond = surfacePlafond * prixm2plafond ;
                        coutTotal += coutPlafond;
                       
                        
                        Piece piece = new Piece(pieceId, 0, 0, 2.5, murs); 
                        piecesNiveau.add(piece);
                        pieceToAppartement.put(piece, appartement);
                    }
                }
            } else {
                int nbPieces = Integer.parseInt(reader.readLine().trim());
                System.out.println("Level " + niveau + ": " + nbPieces + " pieces"); // Debug statement
                for (int pieceIndex = 0; pieceIndex < nbPieces; pieceIndex++) {
                    int pieceId = Integer.parseInt(reader.readLine().trim());
                    List<Mur> murs = new ArrayList<>();
                    int nbMurs = Integer.parseInt(reader.readLine().trim());
                    for (int murIndex = 0; murIndex < nbMurs; murIndex++) {
                        
                            double debutX = Double.parseDouble(reader.readLine().trim());
                            double debutY = Double.parseDouble(reader.readLine().trim());
                            double finX = Double.parseDouble(reader.readLine().trim());
                            double finY = Double.parseDouble(reader.readLine().trim());
                            

                        double surface = Double.parseDouble(reader.readLine().trim());
                        double prixm2 = Double.parseDouble(reader.readLine().trim());

                        Mur mur = new Mur(murIndex, new Coin(murIndex, debutX, debutY), new Coin(murIndex, finX, finY), 2.5, null);
                        uniRevetement revetement = new uniRevetement(murIndex, "Revetement_" + murIndex, true, true, true, prixm2);
                        mur.setRevetement(revetement);

                        double cout = surface * prixm2;
                        coutTotal += cout;

                        murs.add(mur);

                        ctparRevetement.merge(revetement.getNom(), cout, Double::sum);
                        stparRevetement.merge(revetement.getNom(), surface, Double::sum);
                    }

                    Piece piece = new Piece(pieceId, 0, 0, 2.5, murs); 
                    piecesNiveau.add(piece);
                    pieceToAppartement.put(piece, 0);   // pour la maison toutes les pièces appartiennent au même appartement donc d'id 0
                }
            }
            piecesParNiveau.put(niveau, piecesNiveau);
        }

        Devisviatxt(type); // appelle de la méthode pour afficher le devis (special si à partir de l'ancien projet
        JOptionPane.showMessageDialog(this, "Projet chargé.", "Succès", JOptionPane.INFORMATION_MESSAGE);
    } catch (IOException ex) {
        ex.printStackTrace(); 
        JOptionPane.showMessageDialog(this, "Erreur lors du chargement du projet", "Erreur", JOptionPane.ERROR_MESSAGE);
    }
}



private void Devisviatxt(String type) {
    File file = new File("Devisviatxt.txt");
    try (FileWriter writer = new FileWriter(file)) {
        writer.write("Devis pour le projet de construction\n\n");
        writer.write("Type de construction: " + type + "\n");
        writer.write("Nombre de niveaux: " + nbNiveaux + "\n");
        writer.write("Coût total global: " + coutTotal + " €\n");
       
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().open(file);
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Erreur lors de la sauvegarde du devis.", "Erreur", JOptionPane.ERROR_MESSAGE);
    }
}


    private void updateNiveauComboBox() {
        niveauComboBox.removeAllItems();
        for (int i = 1; i <= nbNiveaux; i++) {
            niveauComboBox.addItem(i);
        }
    }
    
    // enregistre les informations du batiment, en réalité on ne s'en sert pas, mais on peut il lire les informations de celui ci 
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
        //on récupère le niveau selection dans le menu déroulant
        Integer niveauSelectionne = (Integer) niveauComboBox.getSelectedItem(); 
        if (niveauSelectionne != null) {
            //on récupère la liste des pieces pour le niveau selectionné
            List<Piece> piecesNiveau = piecesParNiveau.get(niveauSelectionne);

            //on crée une nouvelle fen^tre pour afficher le plan 2D
            JFrame planFrame = new JFrame("Plan 2D du bâtiment - Niveau " + niveauSelectionne);
            planFrame.setSize(800, 600);
            planFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            planFrame.add(new Pan2D(piecesNiveau, pieceToAppartement));
            planFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un niveau.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
} 


