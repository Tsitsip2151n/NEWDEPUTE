package vivant;

public class Groupe {
     String nom;
     int anneeFondation;

    public Groupe(String nom, int anneeFondation) {
        if (nom == null || nom.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom du groupe ne peut pas être vide");
        }
        this.nom = nom;
        this.anneeFondation = anneeFondation;
    }

    public String getNom() {
        return nom;
    }

    public int getAnneeFondation() {
        return anneeFondation;
    }

    @Override
    public String toString() {
        return nom + " (fondé en " + anneeFondation + ")";
    }

}
