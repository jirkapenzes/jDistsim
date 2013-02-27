package jDistsim.ui.dialog;

import jDistsim.application.designer.common.UIConfiguration;
import jDistsim.core.modules.Module;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Author: Jirka Pénzeš
 * Date: 24.2.13
 * Time: 16:43
 */
public abstract class BaseModuleSettingsDialog<TModule extends Module> extends JDialog {

    protected GridBagConstraints constraints;
    protected TModule module;

    private IDialogComponentFactory componentFactory;

    private JPanel contentPane;
    private GridBagLayout gridBag;
    private JButton okButton;
    private JButton cancelButton;

    public BaseModuleSettingsDialog(JFrame parent, TModule module) {
        this(parent, new DialogComponentFactory(), module);
    }

    public BaseModuleSettingsDialog(JFrame parent, IDialogComponentFactory componentFactory, TModule module) {
        super(parent);
        this.componentFactory = componentFactory;
        this.module = module;
        buildUI();
    }

    protected void build(JComponent component) {
        gridBag.setConstraints(component, constraints);
        contentPane.add(component);
    }

    protected IDialogComponentFactory getComponentFactory() {
        return componentFactory;
    }

    private void buildUI() {
        setLayout(new BorderLayout());
        setTitle("Module settings");
        setResizable(false);
        setIconImage(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(new Dimension(300, 240));
        setModal(true);
        setLocationRelativeTo(getParent());

        gridBag = new GridBagLayout();
        contentPane = new JPanel(gridBag);
        contentPane.setBackground(Color.WHITE);
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        JPanel gridPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        gridPanel.setBackground(Color.white);
        gridPanel.add(contentPane);
        add(gridPanel, BorderLayout.CENTER);

        buildWindowHeader();
        buildWindowBottom();
        buildWindowBody();
    }

    protected abstract void buildWindowBody();

    private void buildWindowBottom() {
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER, 3, 3));
        bottom.setPreferredSize(new Dimension(getWidth(), 30));
        bottom.setBorder(new MatteBorder(1, 0, 0, 0, new Color(184, 184, 184)));

        okButton = componentFactory.makeButton("Ok");
        cancelButton = componentFactory.makeButton("Cancel");

        okButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                onOkButtonClick(mouseEvent);
            }
        });

        cancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                System.out.println("do!");
                onCancelButtonMouseClick(mouseEvent);
            }
        });

        bottom.add(okButton);
        bottom.add(cancelButton);
        add(bottom, BorderLayout.SOUTH);
    }

    private void onCancelButtonMouseClick(MouseEvent mouseEvent) {
        setVisible(false);
        dispose();
    }

    private void onOkButtonClick(MouseEvent mouseEvent) {
        okButtonLogic();
        onCancelButtonMouseClick(mouseEvent);
    }

    protected abstract void okButtonLogic();

    private void buildWindowHeader() {
        JLabel label = new JLabel(module.getIdentifier());
        label.setFont(UIConfiguration.getInstance().getDefaultFont(13));

        JPanel titlePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics graphics) {
                super.paintComponent(graphics);
                Graphics2D graphics2D = (Graphics2D) graphics;
                graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                GradientPaint gradientPaint = new GradientPaint(0, 0, new Color(246, 246, 246), 0, getHeight(), new Color(220, 220, 220));
                graphics2D.setPaint(gradientPaint);
                graphics2D.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 8));
        titlePanel.setPreferredSize(new Dimension(getWidth(), 30));
        titlePanel.setBorder(new MatteBorder(0, 0, 1, 0, new Color(184, 184, 184)));
        titlePanel.add(label);

        add(titlePanel, BorderLayout.NORTH);
    }


    private JLabel makeLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(UIConfiguration.getInstance().getDefaultFont(true, 12));
        return label;
    }

}
