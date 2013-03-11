package jDistsim.ui;

import javax.swing.*;
import java.awt.event.*;

/**
 * Author: Jirka Pénzeš
 * Date: 24.9.12
 * Time: 21:22
 */
public class MenuBar extends JMenuBar {

    private IMenuListener menuListener;

    public MenuBar(IMenuListener menuListener) {
        initializeMenuBar();
        this.menuListener = menuListener;
    }

    private void initializeMenuBar() {
        JMenu fileMenu = makeFileMenu();
        JMenu viewMenu = makeViewMenu();
        JMenu runMenu = makeRun();
        JMenu settingsMenu = makeSettings();
        JMenu aboutMenu = makeAboutMenu();

        add(fileMenu);
        add(viewMenu);
        add(runMenu);
        add(settingsMenu);
        add(aboutMenu);
    }

    private JMenu makeFileMenu() {
        JMenu fileMenu = new JMenu("File");
        final JMenuItem newModel = new JMenuItem("New model");
        final JMenuItem openModel = new JMenuItem("Open model");
        final JMenuItem saveModel = new JMenuItem("Save model");
        final JMenuItem exit = new JMenuItem("Exit");

        fileMenu.add(newModel);
        fileMenu.add(openModel);
        fileMenu.add(saveModel);
        fileMenu.addSeparator();
        fileMenu.add(exit);

        return fileMenu;
    }

    private JMenu makeAboutMenu() {
        JMenu runMenu = new JMenu("Help");
        final JMenuItem help = new JCheckBoxMenuItem("Open help");
        final JMenuItem about = new JCheckBoxMenuItem("About application");
        runMenu.add(help);
        runMenu.add(about);
        return runMenu;
    }

    private JMenu makeViewMenu() {
        JMenu viewMenu = new JMenu("View");
        final JCheckBoxMenuItem toolbox = new JCheckBoxMenuItem("Toolbox");
        final JCheckBoxMenuItem properties = new JCheckBoxMenuItem("Properties");
        final JCheckBoxMenuItem modules = new JCheckBoxMenuItem("Modules navigator");
        final JCheckBoxMenuItem information = new JCheckBoxMenuItem("Model information");

        toolbox.setSelected(true);
        properties.setSelected(true);
        modules.setSelected(true);
        information.setSelected(true);

        toolbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                menuListener.viewToolboxOnClick(toolbox.isSelected());
            }
        });
        properties.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                menuListener.viewPropertiesClick(properties.isSelected());
            }
        });
        modules.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                menuListener.viewModulesNavigatorOnClick(modules.isSelected());
            }
        });
        information.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                menuListener.viewModelInformationOnClick(information.isSelected());
            }
        });

        viewMenu.add(toolbox);
        viewMenu.add(properties);
        viewMenu.add(modules);
        viewMenu.add(information);
        return viewMenu;
    }

    private JMenu makeRun() {
        JMenu runMenu = new JMenu("Run");
        final JMenuItem run = new JCheckBoxMenuItem("Run");
        final JMenuItem stop = new JCheckBoxMenuItem("Stop");
        final JMenuItem settings = new JCheckBoxMenuItem("Settings");

        runMenu.add(run);
        runMenu.addSeparator();
        runMenu.add(stop);
        runMenu.add(settings);
        return runMenu;
    }

    private JMenu makeSettings() {
        JMenu settingsMenu = new JMenu("Settings");
        final JMenuItem localSettings = new JCheckBoxMenuItem("Local settings");
        final JMenuItem addModel = new JCheckBoxMenuItem("Add distribute model");
        final JMenuItem editModel = new JCheckBoxMenuItem("Edit distribute model");
        final JMenuItem removeModel = new JCheckBoxMenuItem("Remove distribute model");

        settingsMenu.add(localSettings);
        settingsMenu.addSeparator();
        settingsMenu.add(addModel);
        settingsMenu.add(editModel);
        settingsMenu.add(removeModel);
        return settingsMenu;
    }
}
