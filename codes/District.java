package niveau;

import java.util.ArrayList;
import java.util.List;

public class District {
     String nom;
     List<Commune> communes;
     boolean deuxDeputes;

    public District(String nom, boolean deuxDeputes) {
        if (nom == null || nom.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom du district ne peut pas être vide");
        }
        this.nom = nom;
        this.communes = new ArrayList<>();
        this.deuxDeputes = deuxDeputes;
    }

    public void addCommune(Commune c) {
        if (c == null) {
            throw new IllegalArgumentException("La commune ne peut pas être null");
        }
        communes.add(c);
    }

    public String getNom() {
        return nom;
    }

    public List<Commune> getCommunes() {
        return new ArrayList<>(communes);
    }

    public boolean afficheDeuxDeputes() {
        return deuxDeputes;
    }

    @Override
    public String toString() {
        return nom;
    }
}