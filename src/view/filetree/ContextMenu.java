package view.filetree;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContextMenu extends JPopupMenu {
    private JMenuItem _miView;
    private JMenuItem _miConstruct;
    private JMenuItem _miDeconstruct;

    public ContextMenu(){
        _miView = new JMenuItem("Просмотр");
        _miConstruct = new JMenuItem("Собрать");

        _miDeconstruct = new JMenuItem("Разобрать...");
        _miDeconstruct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //new ScriptView();
            }
        });

        this.add(_miView);
        this.add(new Separator());
        this.add(_miConstruct);
        this.add(_miDeconstruct);
    }
}