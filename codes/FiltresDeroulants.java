package interface_ui;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

public class FiltresDeroulants extends JComboBox<String> {
    public FiltresDeroulants() {
        super();
        addItem("Tous");
        setRenderer(new CustomRenderer());
        addPopupMenuListener(new PopupMenuListener() {
            @Override public void popupMenuWillBecomeVisible(PopupMenuEvent e) { mettreAJour(); }
            @Override public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {}
            @Override public void popupMenuCanceled(PopupMenuEvent e) {}
        });
    }

    public void mettreAJour() {
        removeAllItems();
        addItem("Tous");
    }

    public boolean estSelectionTous() {
        return getSelectedIndex() <= 0;
    }

    private static class CustomRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, 
                                                    int index, boolean isSelected, 
                                                    boolean cellHasFocus) {
            Component c = super.getListCellRendererComponent(list, value, index, 
                                                           isSelected, cellHasFocus);
            if (value == null) setText("");
            return c;
        }
    }
}