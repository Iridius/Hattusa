package view.filetree;

import javax.swing.*;

public class ContextMenu extends JPopupMenu {
    private JMenuItem _miView;
    private JMenuItem _miEdit;
    private JMenuItem _miDelete;
    private JMenuItem _miConstruct;
    private JMenuItem _miDeconstruct;

    public ContextMenu(){
        _miView = new JMenuItem("Просмотр");
        _miEdit = new JMenuItem("Редактировать...");
        _miDelete = new JMenuItem("Удалить");
        _miConstruct = new JMenuItem("Собрать");
        _miDeconstruct = new JMenuItem("Разобрать...");

        this.add(_miView);
        this.add(_miEdit);
        this.add(_miDelete);
        this.add(new Separator());
        this.add(_miConstruct);
        this.add(_miDeconstruct);
    }
}