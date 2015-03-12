package view;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class PopClickListener extends MouseAdapter {
    public void mousePressed(MouseEvent me){
        //if (e.isPopupTrigger())
        //    doPop(e);
        TreePath tp = ((JTree)(me.getSource())).getPathForLocation(me.getX(), me.getY());
        if (tp != null)
            //jtf.setText(tp.toString());
            System.out.println(tp.toString());
        else
            System.out.println("");
    }

    public void mouseReleased(MouseEvent e){
        if (e.isPopupTrigger())
            doPop(e);
    }

    private void doPop(MouseEvent e){
        PopUpDemo menu = new PopUpDemo();
        menu.show(e.getComponent(), e.getX(), e.getY());
    }
}
