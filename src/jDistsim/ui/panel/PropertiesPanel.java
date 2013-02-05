package jDistsim.ui.panel;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Jirka Pénzeš
 * Date: 5.2.13
 * Time: 0:19
 */
public class PropertiesPanel extends InternalPanel {

    private JTable table;
    final Object rows[][] = {
            {"identifier", "delay_1"},
            {"input max capacity", Integer.MAX_VALUE},
            {"occupied input", 2},
            {"input max capacity", Integer.MAX_VALUE},
            {"output max capacity", 1},
            {"occupied output", "1"},
            {"distributed", "false"},
            {"correct", "true"},
            {"location x", "54"},
            {"location y", "357"},
            {"delay time", "20"}
    };
    final Object headers[] = {"Property", "Value"};

    public PropertiesPanel() {
        super("Properties");

        showNothing();
        setFooterBorderLine(true);
        setBackground(Color.white);
        initialize();
    }

    private void initialize() {
        table = new JTable(rows, headers);
        table.setEnabled(false);
        table.setFocusable(false);
        table.setRowSelectionAllowed(false);
        table.setBackground(Color.white);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(null);
        scrollPane.setBackground(Color.white);

        add(scrollPane, BorderLayout.CENTER);
    }


}
