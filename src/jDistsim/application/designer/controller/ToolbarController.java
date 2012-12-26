package jDistsim.application.designer.controller;

import jDistsim.application.designer.model.ToolbarModel;
import jDistsim.application.designer.view.ToolbarView;
import jDistsim.ui.control.button.ImageButton;
import jDistsim.utils.pattern.mvc.AbstractController;
import jDistsim.utils.pattern.mvc.AbstractFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Author: Jirka Pénzeš
 * Date: 3.12.12
 * Time: 23:36
 */
public class ToolbarController extends AbstractController<ToolbarModel> {

    public ToolbarController(AbstractFrame mainFrame, ToolbarModel model) {
        super(mainFrame, model);
        initializeActions();
    }

    private void initializeActions() {
        ToolbarView view = getMainFrame().getView(ToolbarView.class);
        view.addRelationsMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                boolean relations = ((ImageButton) mouseEvent.getSource()).isActive();
                getModel().setRelations(relations);
            }
        });
    }


}
