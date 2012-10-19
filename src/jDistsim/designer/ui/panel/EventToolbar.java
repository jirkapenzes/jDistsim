package jDistsim.designer.ui.panel;

import jDistsim.core.model.EventToolbarModel;
import jDistsim.core.module.IEventToolbarModule;
import jDistsim.core.simulation.event.description.EmptyDescription;
import jDistsim.core.simulation.event.description.IEventDescription;
import jDistsim.designer.ui.UIConfiguration;
import jDistsim.designer.ui.control.event.ToolbarEventPreviewControl;
import jDistsim.utils.logging.Logger;
import jDistsim.utils.resource.TextResources;
import jDistsim.utils.ui.WrapLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 29.9.12
 * Time: 16:09
 */
public class EventToolbar extends InternalPanel {

    private EventToolbarModel eventToolbarModel;

    public EventToolbar(EventToolbarModel eventToolbarModel) {
        super(TextResources.TOOLBAR_EVENT_PANEL_TITLE);
        Logger.log("Initialize event toolbar panel");
        this.eventToolbarModel = eventToolbarModel;
        initializeComponents();
    }

    private void initializeComponents() {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));

        JPanel controls = new JPanel();
        controls.setLayout(new WrapLayout(FlowLayout.LEFT, 10, 0));
        controls.setBackground(Color.white);
        controls.setBorder(new EmptyBorder(10, 0, 0, 0));

        scrollPane.setViewportView(controls);
        add(scrollPane, BorderLayout.CENTER);
        add(new EventInformationPanel(), BorderLayout.SOUTH);

        for (IEventToolbarModule toolbarModule : eventToolbarModel.getModules()) {
            controls.add(new ToolbarEventPreviewControl(toolbarModule.getEventPreview()));
        }
    }


    private class EventInformationPanel extends JPanel {

        private IEventDescription eventDescription;
        private JLabel labelTitle;
        private JLabel labelDescription;

        public EventInformationPanel() {
            this(new EmptyDescription());
        }

        public EventInformationPanel(IEventDescription eventDescription) {
            initializeComponents();
            setEventDescription(eventDescription);
        }

        private void initializeComponents() {
            setBorder(new EmptyBorder(10, 5, 10, 5));
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            Font font = UIConfiguration.getInstance().getDefaultFont();
            labelTitle = new JLabel();
            labelTitle.setFont(font);

            labelDescription = new JLabel();
            labelDescription.setFont(font);

            add(labelTitle);
            add(labelDescription);
        }

        public IEventDescription getEventDescription() {
            return eventDescription;
        }

        public void setEventDescription(IEventDescription eventDescription) {
            this.eventDescription = eventDescription;

            labelTitle.setText(getEventDescription().getTitle());
            labelDescription.setText(getEventDescription().getDescription());

        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.setColor(new Color(192, 192, 192));
            g.drawLine(0, 0, getWidth(), 0);
            g.setColor(new Color(240, 240, 240));
            g.fillRect(0, 1, getWidth(), getHeight());
        }
    }
}
