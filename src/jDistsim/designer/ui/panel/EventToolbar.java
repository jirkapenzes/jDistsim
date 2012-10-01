package jDistsim.designer.ui.panel;

import jDistsim.core.simulation.event.description.DelayDescription;
import jDistsim.core.simulation.event.description.IEventDescription;
import jDistsim.designer.ui.UIConfiguration;
import jDistsim.designer.ui.control.event.*;
import jDistsim.utils.resource.TextResources;
import jDistsim.utils.ui.WrapLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Jirka Pénzeš
 * Date: 29.9.12
 * Time: 16:09
 */
public class EventToolbar extends ToolbarPanel {

    private List<ToolbarViewEventControl> eventControlList;

    public EventToolbar() {
        super(TextResources.TOOLBAR_EVENT_PANEL_TITLE);
        initializeComponents();
    }

    private void initializeComponents() {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));

        JPanel controls = new JPanel();
        controls.setLayout(new WrapLayout(FlowLayout.LEFT, 15, 0));
        controls.setBackground(Color.white);
        controls.setBorder(new EmptyBorder(10, 0, 0, 0));

        scrollPane.setViewportView(controls);
        add(scrollPane, BorderLayout.CENTER);
        add(new EventInformationPanel(), BorderLayout.SOUTH);

        eventControlList = new ArrayList<ToolbarViewEventControl>();
        eventControlList.add(new ToolbarViewEventControl(new CreateEvent(new DelayDescription())));
        eventControlList.add(new ToolbarViewEventControl(new DecideEvent(new DelayDescription())));
        eventControlList.add(new ToolbarViewEventControl(new ProcessEvent(new DelayDescription())));
        eventControlList.add(new ToolbarViewEventControl(new DelayEvent(new DelayDescription())));
        eventControlList.add(new ToolbarViewEventControl(new RecordEvent(new DelayDescription())));

        for (ToolbarViewEventControl toolbarViewEventControl : eventControlList) {
            controls.add(toolbarViewEventControl);
        }
    }


    private class EventInformationPanel extends JPanel {

        private IEventDescription eventDescription;
        private JLabel labelTitle;
        private JLabel labelDescription;

        public EventInformationPanel() {
            this(new DelayDescription());
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
