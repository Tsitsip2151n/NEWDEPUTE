package niveau;

import java.util.ArrayList;
import java.util.List;

public class Commune {
     String nom;
     List<BureauVote> bureaux;

    public Commune(String nom) {
        if (nom == null || nom.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom de la commune ne peut pas être vide");
        }
        this.nom = nom;
        this.bureaux = new ArrayList<>();
    }

    public void addBureau(BureauVote b) {
        if (b == null) {
            throw new IllegalArgumentException("Le bureau ne peut pas être null");
        }
        bureaux.add(b);
    }

    public String getNom() {
        return nom;
    }

    public List<BureauVote> getBureaux() {
        return new ArrayList<>(bureaux);
    }

    @Override
    public String toString() {
        return nom;
    }
}