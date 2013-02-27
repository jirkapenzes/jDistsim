package jDistsim.ui.panel.listener;

import java.awt.event.MouseEvent;

/**
 * Author: Jirka Pénzeš
 * Date: 13.2.13
 * Time: 15:58
 */
public interface PropertiesViewListener {

    public void onPinButtonClick(MouseEvent mouseEvent, Object sender);

    public void onAscendingButtonClick(MouseEvent mouseEvent, Object sender);

    public void onDescendingButtonClick(MouseEvent mouseEvent, Object sender);

    void onEditButtonClick(MouseEvent mouseEvent, Object sender);
}
