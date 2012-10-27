package jDistsim.application.designer.controller;

import jDistsim.application.designer.model.StatusBarModel;
import jDistsim.application.designer.view.StatusBarView;
import jDistsim.utils.event.ActionArgument;
import jDistsim.utils.event.ActionObjectListener;
import jDistsim.utils.gc.MemoryWatcher;
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

    private final MemoryWatcher memoryWatcher;

    public StatusBarController(AbstractFrame mainFrame, StatusBarModel statusBarModel) {
        super(mainFrame, statusBarModel);
        Logger.getLoggerHandlerManager().addHandler(this);
        getModel().addObserver(this);

        memoryWatcher = new MemoryWatcher();
        memoryWatcher.addActionObjectListener(new ActionObjectListener() {
            @Override
            public void ActionPerformed(ActionArgument actionArgument) {
                getModel().setRightContentText("Current used memory " + memoryWatcher.getUsedMemory() + "M of " + memoryWatcher.getTotalMemory() + "M");
            }
        });
        new Thread(memoryWatcher).start();
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
