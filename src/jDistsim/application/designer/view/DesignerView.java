package jDistsim.application.designer.view;

import jDistsim.core.model.EventToolbarModel;
import jDistsim.core.module.EventToolbarModule;
import jDistsim.core.simulation.event.description.CreateEventDescription;
import jDistsim.core.simulation.event.description.DisposeDescription;
import jDistsim.core.simulation.event.ui.preview.CreateUIEventPreview;
import jDistsim.core.simulation.event.ui.preview.DisposeUIEventPreview;
import jDistsim.ui.panel.EventToolbar;
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
        EventToolbarModel eventToolbarModel = new EventToolbarModel();
        eventToolbarModel
                .addEventToolbarModule(new EventToolbarModule(new CreateUIEventPreview(new CreateEventDescription())))
                .addEventToolbarModule(new EventToolbarModule(new DisposeUIEventPreview(new DisposeDescription())));

        contentPane.add(getMainFrame().getView(ToolbarView.class).getContentPane(), BorderLayout.NORTH);
        contentPane.add(getMainFrame().getView(StatusBarView.class).getContentPane(), BorderLayout.SOUTH);
        contentPane.add(getMainFrame().getView(InformationView.class).getContentPane(), BorderLayout.EAST);
        contentPane.add(new EventToolbar(eventToolbarModel), BorderLayout.WEST);
        contentPane.add(getMainFrame().getView(ModelSpaceView.class).getContentPane(), BorderLayout.CENTER);
        return contentPane;
    }
}
