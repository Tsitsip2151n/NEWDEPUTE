package interface_ui;

import java.util.ArrayList;
import java.util.List;

import niveau.Commune;
import niveau.District;

public class FiltreCommune extends FiltresDeroulants {
    District district;
     List<Commune> communes = new ArrayList<>();

    public void setDistrict(District district) {
        this.district = district;
        communes.clear();
        if (district != null) {
            communes.addAll(district.getCommunes());
        }
        setSelectedIndex(0);
    }

    @Override
    public void mettreAJour() {
        super.mettreAJour();
        communes.forEach(c -> addItem(c.getNom()));
    }

    public Commune getCommuneSelectionnee() {
        return estSelectionTous() || district == null ? null : 
               communes.get(getSelectedIndex() - 1);
    }
}