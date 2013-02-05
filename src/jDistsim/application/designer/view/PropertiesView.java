package jDistsim.application.designer.view;

import jDistsim.ui.panel.InternalPanel;
import jDistsim.ui.panel.ModulesPanel;
import jDistsim.ui.panel.PropertiesPanel;
import jDistsim.utils.pattern.mvc.AbstractFrame;
import jDistsim.utils.pattern.mvc.AbstractView;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 27.10.12
 * Time: 13:24
 */
public class PropertiesView extends AbstractView<JPanel> {

    public PropertiesView(AbstractFrame mainFrame) {
        super(mainFrame);
    }

    @Override
    protected JPanel layout() {
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        InternalPanel internalPanel1 = new PropertiesPanel();
        InternalPanel internalPanel2 = new ModulesPanel();

        // internalPanel1.showNothing();

        contentPane.add(internalPanel1, BorderLayout.CENTER);
        contentPane.add(internalPanel2, BorderLayout.SOUTH);
        return contentPane;
    }
}