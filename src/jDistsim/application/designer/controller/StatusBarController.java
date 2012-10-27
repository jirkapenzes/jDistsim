package jDistsim.application.designer.controller;

import jDistsim.application.designer.model.StatusBarModel;
import jDistsim.application.designer.view.StatusBarView;
import jDistsim.utils.logging.LogMessage;
import jDistsim.utils.logging.Logger;
import jDistsim.utils.logging.handlers.ILoggerHandler;
import jDistsim.utils.pattern.mvc.AbstractController;
import jDistsim.utils.pattern.mvc.AbstractFrame;
import jDistsim.utils.pattern.observer.IObserver;


/**
 * Author: Jirka Pénzeš
 * Date: 28.10.12
 * Time: 0:10
 */
public class StatusBarController extends AbstractController<StatusBarModel> implements ILoggerHandler, IObserver {

    public StatusBarController(AbstractFrame mainFrame, StatusBarModel statusBarModel) {
        super(mainFrame, statusBarModel);
        Logger.getLoggerHandlerManager().addHandler(this);
        getModel().addObserver(this);
    }

    @Override
    public void publish(LogMessage logMessage) {
        getModel().setLeftContentText(logMessage.toString());
    }

    @Override
    public void update() {
        StatusBarView view = getMainFrame().getView(StatusBarView.class);
        view.getContentPane().setLabelLeftText(getModel().getLeftContentText());
        view.getContentPane().setLabelRightText(getModel().getRightContentText());
    }
}
