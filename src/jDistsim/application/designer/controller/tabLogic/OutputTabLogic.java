package jDistsim.application.designer.controller.tabLogic;

import jDistsim.application.designer.controller.InformationController;
import jDistsim.application.designer.view.InformationView;
import jDistsim.core.simulation.simulator.SimulatorEnvironment;
import jDistsim.core.simulation.simulator.Writer;
import jDistsim.core.simulation.simulator.entity.Attribute;
import jDistsim.ui.control.LogTextArea;
import jDistsim.ui.control.button.ImageButton;
import jDistsim.ui.dialog.SimulatorOutputDialog;
import jDistsim.ui.panel.listener.OutputTabListener;
import jDistsim.ui.panel.tab.OutputTabPanel;
import jDistsim.utils.logging.Logger;
import jDistsim.utils.pattern.observer.IObserver;
import jDistsim.utils.pattern.observer.Observable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseEvent;
import java.util.Vector;

/**
 * Author: Jirka Pénzeš
 * Date: 2.3.13
 * Time: 14:39
 */
public class OutputTabLogic implements OutputTabListener, Writer, IObserver {

    private enum EnvState {Local, Network, Distributed, Modules}

    private final InformationController controller;
    private ImageButton currentActiveImageButton;
    private EnvState currentState;

    public OutputTabLogic(InformationController controller) {
        this.controller = controller;
        currentState = EnvState.Local;
        onLocalEnvironmentButtonClick(controller.getMainFrame().getView(InformationView.class).getContentPane().getOutputTabPanel().getLocalStatistics(), null);
    }

    @Override
    public void write(String text) {
        writeToTextArea(controller.getModel().getOutputPanelTextArea(), text);
        writeToTextArea(controller.getModel().getOutputDialogTextArea(), text);
    }

    private void writeToTextArea(JTextArea textArea, String text) {
        textArea.append(text + "\n");

        if (!text.contains("-----"))
            textArea.setCaretPosition(textArea.getDocument().getLength());
    }

    @Override
    public void clear() {
        controller.getModel().getOutputPanelTextArea().setText("");
        controller.getModel().getOutputDialogTextArea().setText("");
    }

    @Override
    public void onTrashButtonClick(Object sender, MouseEvent mouseEvent) {
        controller.getModel().getOutputDialogTextArea().setText(new String());
        controller.getModel().getOutputPanelTextArea().setText(new String());
        Logger.log("Clear simulator UI output");
    }

    @Override
    public void onScrollToEndButtonClick(Object sender, LogTextArea logTextArea, MouseEvent mouseEvent) {
        ImageButton imageButton = (ImageButton) sender;
        imageButton.setActive(!imageButton.isActive());
        logTextArea.setAutoCaretPosition(imageButton.isActive());
        controller.getModel().setLogPanelScrollToEnd(imageButton.isActive());
        Logger.log("Set scroll to end on simulator output textarea: " + imageButton.isActive());
    }

    @Override
    public void onCopyToClipboardButtonClick(Object sender, MouseEvent mouseEvent) {
        String logText = controller.getModel().getOutputPanelTextArea().getText();
        StringSelection selection = new StringSelection(logText);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
        Logger.log("Log from simulator output copy to clipboard");
    }

    @Override
    public void onSimulatorOutputDialogOpenButtonClick(Object sender, MouseEvent mouseEvent) {
        SimulatorOutputDialog dialog = new SimulatorOutputDialog(controller.getMainFrame().getFrame(), this, controller.getModel().getOutputDialogTextArea());
        dialog.showDialog(false);
    }

    @Override
    public void onLocalEnvironmentButtonClick(Object sender, MouseEvent mouseEvent) {
        currentState = EnvState.Local;
        setActiveTable((ImageButton) sender);
    }

    @Override
    public void onModulesEnvironmentButtonClick(Object sender, MouseEvent mouseEvent) {
        currentState = EnvState.Modules;
        setActiveTable((ImageButton) sender);
    }

    @Override
    public void onNetworkEnvironmentButtonClick(Object sender, MouseEvent mouseEvent) {
        currentState = EnvState.Network;
        setActiveTable((ImageButton) sender);

    }

    @Override
    public void onMessagesEnvironmentButtonClick(Object sender, MouseEvent mouseEvent) {
        currentState = EnvState.Distributed;
        setActiveTable((ImageButton) sender);
    }

    private void setActiveTable(ImageButton button) {
        if (currentActiveImageButton != null) {
            currentActiveImageButton.setActive(false);
        }
        currentActiveImageButton = button;
        currentActiveImageButton.setActive(true);

        setTableModelAndRender();
    }

    @Override
    public void update(Observable observable, Object arguments) {
        SimulatorEnvironment environment = (SimulatorEnvironment) observable;

        Vector<String> columns = new Vector<>();
        columns.addElement("text");

        controller.getModel().setLocalStatisticsTable(new DefaultTableModel(makeRows(environment.getSimulatorAttributes()), columns));
        controller.getModel().setModulesStatisticsTable(new DefaultTableModel(makeRows(environment.getModulesAttributes()), columns));
        controller.getModel().setNetworkStatisticsTable(new DefaultTableModel(makeRows(environment.getNetworkAttributes()), columns));
        controller.getModel().setMessagesStatisticsTable(new DefaultTableModel(makeRows(environment.getDistributedAttributes()), columns));
        setTableModelAndRender();
    }

    private void setTableModelAndRender() {
        OutputTabPanel outputTabPanel = controller.getMainFrame().getView(InformationView.class).getContentPane().getOutputTabPanel();
        switch (currentState) {
            case Local:
                controller.getModel().getEnvironmentTable().setModel(controller.getModel().getLocalStatisticsTable());
                break;
            case Modules:
                controller.getModel().getEnvironmentTable().setModel(controller.getModel().getModulesStatisticsTable());
                break;
            case Network:
                controller.getModel().getEnvironmentTable().setModel(controller.getModel().getNetworkStatisticsTable());
                break;
            case Distributed:
                controller.getModel().getEnvironmentTable().setModel(controller.getModel().getMessagesStatisticsTable());
                break;
        }
        outputTabPanel.renderEnvironmentTable();
    }

    private Vector<Vector<String>> makeRows(Iterable<Attribute> attributes) {
        Vector<Vector<String>> rows = new Vector<>();
        for (Attribute attribute : attributes) {
            Vector<String> row = new Vector<>();
            row.addElement(attribute.toString());
            rows.addElement(row);
        }
        return rows;
    }
}

