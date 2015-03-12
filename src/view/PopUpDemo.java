package view;

import javax.swing.*;

class PopUpDemo extends JPopupMenu {
    JMenuItem anItem;
    public PopUpDemo(){
        anItem = new JMenuItem("MenuItem");
        add(anItem);
    }
}