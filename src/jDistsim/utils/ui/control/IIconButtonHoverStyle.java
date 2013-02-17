package jDistsim.utils.ui.control;

import jDistsim.ui.control.button.ImageButton;

/**
 * Author: Jirka Pénzeš
 * Date: 3.10.12
 * Time: 0:10
 */
public interface IIconButtonHoverStyle {

    public void applyHoverStyle(ImageButton imageButton);

    public void applyPressedStyle(ImageButton imageButton);

    public void applyReleaseStyle(ImageButton imageButton);

}
