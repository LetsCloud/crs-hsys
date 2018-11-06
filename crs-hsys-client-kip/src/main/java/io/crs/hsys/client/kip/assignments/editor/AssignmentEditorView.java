/**
 * 
 */
package io.crs.hsys.client.kip.assignments.editor;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;


import gwt.material.design.client.ui.MaterialDialog;

/**
 * @author CR
 *
 */
public class AssignmentEditorView extends ViewWithUiHandlers<AssignmentEditorUiHandlers> implements AssignmentEditorPresenter.MyView {
	private static Logger logger = Logger.getLogger(AssignmentEditorView.class.getName());

	interface Binder extends UiBinder<HTMLPanel, AssignmentEditorView> {
	}

	@UiField
	MaterialDialog materialModal;

	@Inject
	AssignmentEditorView(Binder binder) {
		logger.log(Level.INFO, "AssignmentEditView()");
		initWidget(binder.createAndBindUi(this));
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void openModal() {
		logger.log(Level.INFO, "AssignmentEditView.openModal()");
		Timer t2 = new Timer() {
			@Override
			public void run() {
				materialModal.open();
			}
		};
		t2.schedule(500);
	}
	
	@UiHandler("cancelButton")
	public void onCancel(ClickEvent event) {
		materialModal.close();
	}
}
