package interface_ui;

import java.util.ArrayList;
import java.util.List;

import niveau.BureauVote;
import niveau.Commune;

public class FiltreBureau extends FiltresDeroulants {
     Commune commune;
     List<BureauVote> bureaux = new ArrayList<>();

    public void setCommune(Commune commune) {
        this.commune = commune;
        bureaux.clear();
        if (commune != null) {
            bureaux.addAll(commune.getBureaux());
        }
        setSelectedIndex(0);
    }

    @Override
    public void mettreAJour() {
        super.mettreAJour();
        bureaux.forEach(b -> addItem(b.getNom()));
    }

    public BureauVote getBureauSelectionne() {
        return estSelectionTous() || commune == null ? null : 
               bureaux.get(getSelectedIndex() - 1);
    }
}