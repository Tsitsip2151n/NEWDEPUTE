package interface_ui;

import java.util.ArrayList;
import java.util.List;

import niveau.District;
import niveau.Region;

public class FiltreDistrict extends FiltresDeroulants {
    Region region;
     List<District> districts = new ArrayList<>();

    public void setRegion(Region region) {
        this.region = region;
        districts.clear();
        if (region != null) {
            districts.addAll(region.getDistricts());
        }
        setSelectedIndex(0);
    }

    @Override
    public void mettreAJour() {
        super.mettreAJour();
        districts.forEach(d -> addItem(d.getNom()));
    }

    public District getDistrictSelectionne() {
        return estSelectionTous() || region == null ? null : 
               districts.get(getSelectedIndex() - 1);
    }
}