package interface_ui;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import niveau.BureauVote;
import vivant.Depute;

public class InsertButton extends JButton {
     JTextField nbVotesField;
     FiltreDepute filtreDepute;
     FiltreBureau filtreBureau;

    public InsertButton(JTextField nbVotesField, FiltreDepute filtreDepute, FiltreBureau filtreBureau) {
        super("Ajouter vote");
        this.nbVotesField = nbVotesField;
        this.filtreDepute = filtreDepute;
        this.filtreBureau = filtreBureau;
        configurerComportement();
    }

     void configurerComportement() {
        addActionListener(e -> {
            try {
                Depute dep = filtreDepute.getDeputeSelectionne();
                BureauVote bureau = filtreBureau.getBureauSelectionne();
                
                if (dep == null) throw new IllegalArgumentException("Aucun député sélectionné");
                if (bureau == null) throw new IllegalArgumentException("Aucun bureau sélectionné");

                int nb = Integer.parseInt(nbVotesField.getText().trim());
                if (nb <= 0) throw new IllegalArgumentException("Nombre de votes doit être positif");

                dep.ajouterVotes(nb);
                enregistrerVote(dep, bureau, nb);
                nbVotesField.setText("");
                
                JOptionPane.showMessageDialog(this, "Vote enregistré avec succès", 
                                           "Succès", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), 
                                           "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

     void enregistrerVote(Depute dep, BureauVote bureau, int nbVotes) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("../votes.txt", true))) {
            bw.write(String.format("%s;%s;%s;%d%n", 
                dep.getNom(), 
                dep.getGroupe().getNom(),
                bureau.getNom(), 
                nbVotes));
        }
    }
}