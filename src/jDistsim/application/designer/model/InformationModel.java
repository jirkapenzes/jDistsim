package jDistsim.application.designer.model;

import jDistsim.application.designer.MainFrame;
import jDistsim.utils.pattern.mvc.AbstractModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;

/**
 * Author: Jirka Pénzeš
 * Date: 15.2.13
 * Time: 17:57
 */
public class InformationModel extends AbstractModel {

    private boolean logPanelScrollToEnd;
    private boolean logPanelWordWrap;
    private JTextArea outputPanelTextArea;
    private JTextArea outputDialogTextArea;
    private JTable entitiesInfoTable;
    private JTable remoteModelsTable;
    private DefaultTableModel localStatisticsTable;
    private DefaultTableModel networkStatisticsTable;
    private DefaultTableModel modulesStatisticsTable;
    private DefaultTableModel messagesStatisticsTable;
    private JTable environmentTable;

    public InformationModel(MainFrame mainFrame) {
        super(mainFrame);
        outputPanelTextArea = new JTextArea("output is empty");
        outputDialogTextArea = new JTextArea("output is empty");
        entitiesInfoTable = new JTable();
        remoteModelsTable = new JTable();

        localStatisticsTable = new DefaultTableModel(new Vector(), new Vector<Vector<String>>());
        networkStatisticsTable = new DefaultTableModel(new Vector(), new Vector<Vector<String>>());
        modulesStatisticsTable = new DefaultTableModel(new Vector(), new Vector<Vector<String>>());
        messagesStatisticsTable = new DefaultTableModel(new Vector(), new Vector<Vector<String>>());
        environmentTable = new JTable();
    }

    @Override
    public void initialize() {
        logPanelScrollToEnd = true;
    }

    public boolean isLogPanelScrollToEnd() {
        return logPanelScrollToEnd;
    }

    public void setLogPanelScrollToEnd(boolean logPanelScrollToEnd) {
        this.logPanelScrollToEnd = logPanelScrollToEnd;
    }

    public void setLogPanelWordWrap(boolean logPanelWordWrap) {
        this.logPanelWordWrap = logPanelWordWrap;
    }

    public boolean isLogPanelWordWrap() {
        return logPanelWordWrap;
    }

    public JTextArea getOutputPanelTextArea() {
        return outputPanelTextArea;
    }

    public void setOutputPanelTextArea(JTextArea outputPanelTextArea) {
        this.outputPanelTextArea = outputPanelTextArea;
    }

    public JTable getEntitiesInfoTable() {
        return entitiesInfoTable;
    }

    public void setEntitiesInfoTable(JTable entitiesInfoTable) {
        this.entitiesInfoTable = entitiesInfoTable;
    }

    public JTable getRemoteModelsTable() {
        return remoteModelsTable;
    }

    public JTextArea getOutputDialogTextArea() {
        return outputDialogTextArea;
    }

    public DefaultTableModel getLocalStatisticsTable() {
        return localStatisticsTable;
    }

    public DefaultTableModel getNetworkStatisticsTable() {
        return networkStatisticsTable;
    }

    public DefaultTableModel getModulesStatisticsTable() {
        return modulesStatisticsTable;
    }

    public DefaultTableModel getMessagesStatisticsTable() {
        return messagesStatisticsTable;
    }

    public JTable getEnvironmentTable() {
        return environmentTable;
    }

    public void setMessagesStatisticsTable(DefaultTableModel messagesStatisticsTable) {
        this.messagesStatisticsTable = messagesStatisticsTable;
    }

    public void setModulesStatisticsTable(DefaultTableModel modulesStatisticsTable) {
        this.modulesStatisticsTable = modulesStatisticsTable;
    }

    public void setNetworkStatisticsTable(DefaultTableModel networkStatisticsTable) {
        this.networkStatisticsTable = networkStatisticsTable;
    }

    public void setLocalStatisticsTable(DefaultTableModel localStatisticsTable) {
        this.localStatisticsTable = localStatisticsTable;
    }
}
