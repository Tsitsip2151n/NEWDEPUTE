package niveau;

import java.util.ArrayList;
import java.util.List;
import vivant.Depute;

public class BureauVote {
     String nom;
     List<Depute> deputes;
     boolean deuxDeputes;

    public BureauVote(String nom, boolean deuxDeputes) {
        if (nom == null || nom.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom du bureau ne peut pas être vide");
        }
        this.nom = nom;
        this.deputes = new ArrayList<>();
        this.deuxDeputes = deuxDeputes;
    }

    public void addDepute(Depute d) {
        if (d == null) {
            throw new IllegalArgumentException("Le député ne peut pas être null");
        }
        deputes.add(d);
    }

    public String getNom() {
        return nom;
    }

    public List<Depute> getDeputes() {
        return new ArrayList<>(deputes);
    }

    public boolean afficheDeuxDeputes() {
        return deuxDeputes;
    }

    @Override
    public String toString() {
        return nom;
    }
}