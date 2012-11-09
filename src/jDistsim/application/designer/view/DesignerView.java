package jDistsim.application.designer.view;

import jDistsim.utils.pattern.mvc.AbstractFrame;
import jDistsim.utils.pattern.mvc.AbstractView;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 27.10.12
 * Time: 16:11
 */
public class DesignerView extends AbstractView<JPanel> {

    public DesignerView(AbstractFrame mainFrame) {
        super(mainFrame);
    }

    @Override
    protected JPanel layout() {
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(getMainFrame().getView(ToolbarView.class).getContentPane(), BorderLayout.NORTH);
        contentPane.add(getMainFrame().getView(StatusBarView.class).getContentPane(), BorderLayout.SOUTH);
        contentPane.add(getMainFrame().getView(PropertiesView.class).getContentPane(), BorderLayout.EAST);
        contentPane.add(getMainFrame().getView(ToolboxView.class).getContentPane(), BorderLayout.WEST);
        contentPane.add(getMainFrame().getView(WorkSpaceView.class).getContentPane(), BorderLayout.CENTER);
        return contentPane;
    }
}
