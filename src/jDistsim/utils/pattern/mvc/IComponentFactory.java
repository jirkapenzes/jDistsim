package jDistsim.utils.pattern.mvc;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Author: Jirka Pénzeš
 * Date: 26.10.12
 * Time: 17:33
 */
public interface IComponentFactory {

    public JFrame frame(String title, JComponent contentPane, JMenuBar menuBar);

    public JFrame showFrame(JFrame frame);

    public JFrame centerFrame(JFrame frame);

    public JPanel panel(LayoutManager layoutManager, String title);

    public JLabel label(String text, Font font);

    public JTextField textField(int columnSize, boolean editable);

    public JButton button(String label, ActionListener actionListener);

    public JPanel buttons(JButton... buttons);

    public JTable table(TableModel tableModel);

}
