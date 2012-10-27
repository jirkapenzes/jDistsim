package jDistsim.application.designer.model;

import jDistsim.utils.pattern.mvc.AbstractModel;

/**
 * Author: Jirka Pénzeš
 * Date: 28.10.12
 * Time: 0:10
 */
public class StatusBarModel extends AbstractModel {

    private String leftContentText;
    private String rightContentText;

    public StatusBarModel() {
        this(new String(), new String());
    }

    public StatusBarModel(String leftContentText, String rightContentText) {
        this.leftContentText = leftContentText;
        this.rightContentText = rightContentText;
    }

    public String getLeftContentText() {
        return leftContentText;
    }

    public void setLeftContentText(String leftContentText) {
        this.leftContentText = leftContentText;
        notifyObservers();
    }

    public String getRightContentText() {
        return rightContentText;
    }

    public void setRightContentText(String rightContentText) {
        this.rightContentText = rightContentText;
        notifyObservers();
    }
}
