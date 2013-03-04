package jDistsim.application.designer.model;

import jDistsim.utils.pattern.mvc.AbstractFrame;
import jDistsim.utils.pattern.mvc.AbstractModel;
import jDistsim.utils.pattern.observer.IObserver;
import jDistsim.utils.pattern.observer.Observable;

import javax.swing.*;

/**
 * Author: Jirka Pénzeš
 * Date: 8.2.13
 * Time: 22:15
 */
public class PropertiesModel extends AbstractModel implements IObserver {

    private JTree tree;
    private JTable table;

    public PropertiesModel(AbstractFrame mainFrame) {
        super(mainFrame);
        tree = new JTree();
        table = new JTable();
    }

    @Override
    public void initialize() {
        getMainFrame().getModel(ModelSpaceModel.class).addObserver(this);
    }

    public JTree getTree() {
        return tree;
    }

    public JTable getTable() {
        return table;
    }

    @Override
    public void update(Observable observable, Object arguments) {
        setChanged();
        notifyObservers();
    }
}
