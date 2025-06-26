package niveau;

import java.util.ArrayList;
import java.util.List;

public class Region {
     String nom;
     List<District> districts;

    public Region(String nom) {
        if (nom == null || nom.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom de la région ne peut pas être vide");
        }
        this.nom = nom;
        this.districts = new ArrayList<>();
    }

    public void addDistrict(District d) {
        if (d == null) {
            throw new IllegalArgumentException("Le district ne peut pas être null");
        }
        districts.add(d);
    }

    public String getNom() {
        return nom;
    }

    public List<District> getDistricts() {
        return new ArrayList<>(districts);
    }

    @Override
    public String toString() {
        return nom;
    }
}