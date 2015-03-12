package view;

import view.filetree.ContextMenu;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class PopClickListener extends MouseAdapter {
    public void mousePressed(MouseEvent me){

        JTree tree = (JTree)me.getSource();
        TreePath currentPath = tree.getPathForLocation(me.getX(), me.getY());
        if (currentPath != null) {
            tree.setSelectionPath(currentPath);
        }

        //if (me.isPopupTrigger())
        //    doPop(me);
        //TreePath tp = ((JTree)(me.getSource())).getPathForLocation(me.getX(), me.getY());
        //if (tp != null)
            //jtf.setText(tp.toString());
        //    System.out.println(tp.toString());
        //else
        //    System.out.println("");
    }

    public void mouseReleased(MouseEvent e){
        if (e.isPopupTrigger())
            doPop(e);
    }

    private void doPop(MouseEvent e){
        ContextMenu menu = new ContextMenu();
        menu.show(e.getComponent(), e.getX(), e.getY());
    }
}
