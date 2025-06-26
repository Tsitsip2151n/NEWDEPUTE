package interface_ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import niveau.*;
import vivant.*;


public class FenetreResultats extends JFrame {
     List<Region> regions;
     JTextArea resultArea;

    public FenetreResultats(List<Region> regions) {
        this.regions = new ArrayList<>(regions);
        initialiserUI();
    }

     void initialiserUI() {
        setTitle("Résultats des élections");
        setSize(850, 650); // Taille légèrement augmentée
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(5, 5)); // Ajout d'espacement
        
        JPanel filterPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        FiltreRegion filtreRegion = new FiltreRegion(regions);
        FiltreDistrict filtreDistrict = new FiltreDistrict();
        FiltreCommune filtreCommune = new FiltreCommune();
        FiltreBureau filtreBureau = new FiltreBureau();

        gbc.gridy = 0;
        ajouterComposant(filterPanel, new JLabel("Région:"), filtreRegion, gbc);
        gbc.gridy++;
        ajouterComposant(filterPanel, new JLabel("District:"), filtreDistrict, gbc);
        gbc.gridy++;
        ajouterComposant(filterPanel, new JLabel("Commune:"), filtreCommune, gbc);
        gbc.gridy++;
        ajouterComposant(filterPanel, new JLabel("Bureau:"), filtreBureau, gbc);

        configurerListeners(filtreRegion, filtreDistrict, filtreCommune, filtreBureau);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 13)); 
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setPreferredSize(new Dimension(800, 500)); // Taille ajustée

        JButton showResultsButton = new JButton("Afficher les résultats");
        showResultsButton.addActionListener(e -> afficherResultats(
            filtreRegion.getRegionSelectionnee(),
            filtreDistrict.getSelectedIndex() > 0 ? filtreDistrict.getSelectedIndex() - 1 : null,
            filtreCommune.getSelectedIndex() > 0 ? filtreCommune.getSelectedIndex() - 1 : null,
            filtreBureau.getSelectedIndex() > 0 ? filtreBureau.getSelectedIndex() - 1 : null
        ));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(showResultsButton);

        mainPanel.add(filterPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        add(mainPanel);
    }

     void ajouterComposant(JPanel panel, JLabel label, JComponent component, GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.weightx = 0.3;
        panel.add(label, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        panel.add(component, gbc);
    }

     void configurerListeners(FiltreRegion filtreRegion, FiltreDistrict filtreDistrict,
                                   FiltreCommune filtreCommune, FiltreBureau filtreBureau) {
        filtreRegion.addActionListener(e -> {
            Region region = filtreRegion.getRegionSelectionnee();
            filtreDistrict.setRegion(region);
            filtreCommune.setDistrict(null);
            filtreBureau.setCommune(null);
        });

        filtreDistrict.addActionListener(e -> {
            if (filtreDistrict.getSelectedIndex() > 0 && filtreRegion.getRegionSelectionnee() != null) {
                District district = filtreRegion.getRegionSelectionnee()
                    .getDistricts().get(filtreDistrict.getSelectedIndex() - 1);
                filtreCommune.setDistrict(district);
            }
            filtreBureau.setCommune(null);
        });

        filtreCommune.addActionListener(e -> {
            if (filtreCommune.getSelectedIndex() > 0 && filtreDistrict.getSelectedIndex() > 0 
                && filtreRegion.getRegionSelectionnee() != null) {
                Commune commune = filtreRegion.getRegionSelectionnee()
                    .getDistricts().get(filtreDistrict.getSelectedIndex() - 1)
                    .getCommunes().get(filtreCommune.getSelectedIndex() - 1);
                filtreBureau.setCommune(commune);
            }
        });
    }

     void afficherResultats(Region region, Integer districtIndex, Integer communeIndex, Integer bureauIndex) {
        resultArea.setText("");
        
        if (region == null) {
            resultArea.append("Veuillez sélectionner une région\n");
            return;
        }

        if (districtIndex == null) {
            resultArea.append("=== RÉSULTATS PAR DISTRICT ===\n\n");
            for (District district : region.getDistricts()) {
                afficherResultatsDistrict(district);
            }
            return;
        }

        District district = region.getDistricts().get(districtIndex);
        
        if (communeIndex == null) {
            resultArea.append("=== RÉSULTATS PAR COMMUNE ===\n\n");
            for (Commune commune : district.getCommunes()) {
                afficherResultatsCommune(commune);
            }
            return;
        }

        Commune commune = district.getCommunes().get(communeIndex);
        
        if (bureauIndex == null) {
            resultArea.append("=== RÉSULTATS PAR BUREAU ===\n\n");
            for (BureauVote bureau : commune.getBureaux()) {
                afficherResultatsBureau(bureau);
            }
            return;
        }

        BureauVote bureau = commune.getBureaux().get(bureauIndex);
        afficherResultatsBureau(bureau);
    }

     void afficherResultatsDistrict(District district) {
        resultArea.append("\n=== DISTRICT: " + district.getNom().toUpperCase() + " ===\n");
        
        Map<String, Integer> votesParCandidat = new HashMap<>();
        Map<String, Groupe> groupesParCandidat = new HashMap<>();
        
        for (Commune commune : district.getCommunes()) {
            for (BureauVote bureau : commune.getBureaux()) {
                for (Depute depute : bureau.getDeputes()) {
                    String cle = depute.getNom() + "|" + depute.getGroupe().getNom();
                    votesParCandidat.merge(cle, depute.getNbVotes(), Integer::sum);
                    groupesParCandidat.putIfAbsent(cle, depute.getGroupe());
                }
            }
        }
        
        afficherResultatsAgreges(votesParCandidat, groupesParCandidat);
    }

     void afficherResultatsCommune(Commune commune) {
        resultArea.append("\n=== COMMUNE: " + commune.getNom().toUpperCase() + " ===\n");
        
        Map<String, Integer> votesParCandidat = new HashMap<>();
        Map<String, Groupe> groupesParCandidat = new HashMap<>();
        
        for (BureauVote bureau : commune.getBureaux()) {
            for (Depute depute : bureau.getDeputes()) {
                String cle = depute.getNom() + "|" + depute.getGroupe().getNom();
                votesParCandidat.merge(cle, depute.getNbVotes(), Integer::sum);
                groupesParCandidat.putIfAbsent(cle, depute.getGroupe());
            }
        }
        
        afficherResultatsAgreges(votesParCandidat, groupesParCandidat);
    }

     void afficherResultatsBureau(BureauVote bureau) {
        resultArea.append("\n=== BUREAU: " + bureau.getNom().toUpperCase() + " ===\n");
        
        List<Depute> deputes = new ArrayList<>(bureau.getDeputes());
        deputes.sort((d1, d2) -> Integer.compare(d2.getNbVotes(), d1.getNbVotes()));
        
        for (int i = 0; i < deputes.size(); i++) {
            Depute dep = deputes.get(i);
            resultArea.append(String.format("  %d. %-5s %-20s %5d votes%n", 
                i+1, dep.getNom(), "(" + dep.getGroupe().getNom() + ")", dep.getNbVotes()));
        }
        resultArea.append("\n");
    }

     void afficherResultatsAgreges(Map<String, Integer> votesParCandidat, 
                                       Map<String, Groupe> groupesParCandidat) {
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(votesParCandidat.entrySet());
        entries.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));
        
        for (int i = 0; i < entries.size(); i++) {
            String[] parts = entries.get(i).getKey().split("\\|");
            String candidat = parts[0];
            Groupe groupe = groupesParCandidat.get(entries.get(i).getKey());
            int votes = entries.get(i).getValue();
            
            resultArea.append(String.format("  %d. %-5s %-20s %5d votes%n", 
                i+1, candidat, "(" + groupe.getNom() + ")", votes));
        }
        resultArea.append("\n");
    }
}