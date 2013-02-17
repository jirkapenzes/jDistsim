package jDistsim.ui.panel.listener;

import jDistsim.utils.ui.IPanelListener;

import java.awt.event.MouseEvent;

/**
 * Author: Jirka Pénzeš
 * Date: 15.2.13
 * Time: 16:51
 */
public interface LogTabListener extends IPanelListener {

    public void onTrashButtonClick(Object sender, MouseEvent mouseEvent);

    public void onWordWrapButtonClick(Object sender, MouseEvent mouseEvent);

    public void onScrollToEndButtonClick(Object sender, MouseEvent mouseEvent);

    public void onCopyToClipboardButtonClick(Object sender, MouseEvent mouseEvent);

}
