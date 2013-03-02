package jDistsim.ui.dialog;

import jDistsim.core.simulation.modules.lib.create.Create;
import jDistsim.ui.TypeInputValidator;
import jDistsim.ui.control.button.ImageButton;
import jDistsim.utils.resource.Resources;
import jDistsim.utils.ui.control.IconBackgroundColorHoverStyle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Author: Jirka Pénzeš
 * Date: 24.2.13
 * Time: 17:03
 */
public class CreateModuleSettingsDialog extends BaseModuleSettingsDialog<Create> {

    private JTextField entityNameTextField;
    private JTextField valueTextField;
    private JTextField entitiesPerIntervalTextField;
    private JTextField maxArrivalsTextField;
    private JTextField firstCreationTextField;
    private JComboBox timeBetweenArrivalsTypeComboBox;

    private String currentIcon;
    private ArrayList<ImageButton> buttons;

    public CreateModuleSettingsDialog(JFrame parent, Create module) {
        super(parent, module);
    }

    @Override
    protected void initializeUI() {
        entityNameTextField.setText(module.getBaseEntityName());
        valueTextField.setText(String.valueOf(module.getArrivalsTypeValue()));
        entitiesPerIntervalTextField.setText(String.valueOf(module.getEntityPerInterval()));
        maxArrivalsTextField.setText(String.valueOf(module.getMaxArrivals()));
        firstCreationTextField.setText(String.valueOf(module.getFirsCreation()));
        currentIcon = module.getIconName();
        buttons.get(getIconIndex(currentIcon)).setActive(true);
    }

    @Override
    protected void buildWindowBody() {
        entityNameTextField = getComponentFactory().makeTextField();
        valueTextField = getComponentFactory().makeTextField();
        entitiesPerIntervalTextField = getComponentFactory().makeTextField();
        maxArrivalsTextField = getComponentFactory().makeTextField();
        firstCreationTextField = getComponentFactory().makeTextField();

        build(getComponentFactory().makeLabel("Entity name"));
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        build(getComponentFactory().makeLabel("Icon"));
        constraints.insets = new Insets(0, 0, 8, 5);
        constraints.gridwidth = 1;
        build(entityNameTextField);
        constraints.gridwidth = GridBagConstraints.REMAINDER;

        build(makeIconsPanel());
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.gridwidth = 1;
        build(getComponentFactory().makeLabel("Type"));
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        build(getComponentFactory().makeLabel("Value"));

        constraints.insets = new Insets(0, 0, 8, 5);
        constraints.gridwidth = 1;
        timeBetweenArrivalsTypeComboBox = new JComboBox(new Object[]{"Constant", "Random (expo)"});
        if (module.getArrivalsType() == Create.TimeBetweenArrivalsType.Constant)
            timeBetweenArrivalsTypeComboBox.setSelectedIndex(0);
        else
            timeBetweenArrivalsTypeComboBox.setSelectedIndex(1);
        build(timeBetweenArrivalsTypeComboBox);
        build(valueTextField);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        build(new JLabel());

        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.gridwidth = 1;
        build(getComponentFactory().makeLabel("Entities per arrival"));
        build(getComponentFactory().makeLabel("Max arrivals"));
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        build(getComponentFactory().makeLabel("Firs creation"));

        constraints.insets = new Insets(0, 0, 8, 5);
        constraints.gridwidth = 1;
        build(entitiesPerIntervalTextField);
        build(maxArrivalsTextField);
        constraints.gridwidth = GridBagConstraints.RELATIVE;
        build(firstCreationTextField);
    }

    @Override
    protected boolean okButtonLogic() {
        try {
            TypeInputValidator validator = new TypeInputValidator();
            double maxArrivals = validator.validateDouble(maxArrivalsTextField.getText(), "Max arrivals");
            int entitiesPerInterval = validator.validateInteger(entitiesPerIntervalTextField.getText(), "Entities per interval");
            String entityName = validator.validateString(entityNameTextField.getText(), "Entity name");
            double value = validator.validateDouble(valueTextField.getText(), "Value");
            double firstCreation = validator.validateDouble(firstCreationTextField.getText(), "First creation");
            String iconName = getSelectedIcon();

            module.setMaxArrivals(maxArrivals);
            module.setEntityPerInterval(entitiesPerInterval);
            module.setBaseEntityName(entityName);
            module.setArrivalsTypeValue(value);
            module.setFirsCreation(firstCreation);
            module.setIconName(iconName);

            if (timeBetweenArrivalsTypeComboBox.getSelectedIndex() == 0) {
                module.setArrivalsType(Create.TimeBetweenArrivalsType.Constant);
                module.setArrivalsTypeValue((int) value);
            } else {
                module.setArrivalsType(Create.TimeBetweenArrivalsType.Random_Expo);
            }

            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    private JPanel makeIconsPanel() {
        JPanel iconsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));

        IconBackgroundColorHoverStyle hoverStyle = new IconBackgroundColorHoverStyle();
        int padding = 2;

        buttons = new ArrayList<>();
        buttons.add(new ImageButton(Resources.getImage("system/entity/truck.png"), hoverStyle, new Dimension(24, 24), padding));
        buttons.add(new ImageButton(Resources.getImage("system/entity/box.png"), hoverStyle, new Dimension(24, 24), padding));
        buttons.add(new ImageButton(Resources.getImage("system/entity/floppy.png"), hoverStyle, new Dimension(24, 24), padding));
        buttons.add(new ImageButton(Resources.getImage("system/entity/message.png"), hoverStyle, new Dimension(24, 24), padding));
        buttons.add(new ImageButton(Resources.getImage("system/entity/data.png"), hoverStyle, new Dimension(24, 24), padding));
        buttons.add(new ImageButton(Resources.getImage("system/entity/sql.png"), hoverStyle, new Dimension(24, 24), padding));

        for (int index = 0; index < buttons.size(); index++) {
            final ImageButton imageButton = buttons.get(index);
            final int finalIndex = index;
            imageButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent mouseEvent) {
                    for (ImageButton btn : buttons)
                        btn.setActive(false);

                    imageButton.setActive(true);
                    currentIcon = getIconName(finalIndex);
                }
            });
            iconsPanel.add(imageButton);
        }
        iconsPanel.setBackground(Color.white);
        return iconsPanel;
    }

    private String getSelectedIcon() {
        for (int index = 0; index < buttons.size(); index++) {
            ImageButton imageButton = buttons.get(index);
            if (imageButton.isActive())
                return getIconName(index);
        }
        return getIconName(-1);
    }

    private String getIconName(int iconIndex) {
        switch (iconIndex) {
            case 0:
                return "truck";
            case 1:
                return "box";
            case 2:
                return "floppy";
            case 3:
                return "message";
            case 4:
                return "data";
            case 5:
                return "sql";
            default:
                return "truck";
        }
    }

    private int getIconIndex(String iconName) {
        switch (iconName) {
            case "truck":
                return 0;
            case "box":
                return 1;
            case "floppy":
                return 2;
            case "message":
                return 3;
            case "data":
                return 4;
            case "sql":
                return 5;
            default:
                return 0;
        }
    }
}
