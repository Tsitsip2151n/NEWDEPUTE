package interface_ui;

import java.util.ArrayList;
import java.util.List;

import niveau.BureauVote;
import vivant.Depute;

public class FiltreDepute extends FiltresDeroulants {
    BureauVote bureau;
    List<Depute> deputes = new ArrayList<>();

    public void setBureau(BureauVote bureau) {
        this.bureau = bureau;
        deputes.clear();
        if (bureau != null) {
            deputes.addAll(bureau.getDeputes());
        }
        setSelectedIndex(0);
    }

    @Override
    public void mettreAJour() {
        super.mettreAJour();
        deputes.forEach(d -> addItem(d.getNom()));
    }

    public Depute getDeputeSelectionne() {
        return estSelectionTous() || bureau == null ? null : 
               deputes.get(getSelectedIndex() - 1);
    }
}