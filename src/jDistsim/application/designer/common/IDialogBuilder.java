package jDistsim.application.designer.common;

/**
 * Author: Jirka Pénzeš
 * Date: 22.2.13
 * Time: 0:45
 */
public interface IDialogBuilder {

    void buildErrorDialog(String message);

    int buildQuestionDialog(String message);
}
