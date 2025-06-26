package interface_ui;

import java.util.List;

import niveau.Region;

public class FiltreRegion extends FiltresDeroulants {
     List<Region> regions;

    public FiltreRegion(List<Region> regions) {
        this.regions = List.copyOf(regions);
    }

    @Override
    public void mettreAJour() {
        super.mettreAJour();
        regions.forEach(r -> addItem(r.getNom()));
    }

    public Region getRegionSelectionnee() {
        return estSelectionTous() ? null : regions.get(getSelectedIndex() - 1);
    }
}