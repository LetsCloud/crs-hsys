/**
 * 
 */
package io.crs.hsys.client.kip.assignments.editor;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

/**
 * @author CR
 *
 */
public class AssignmentEditorPresenter extends PresenterWidget<AssignmentEditorPresenter.MyView> implements AssignmentEditorUiHandlers {
	private static Logger logger = Logger.getLogger(AssignmentEditorPresenter.class.getName());

	interface MyView extends View, HasUiHandlers<AssignmentEditorUiHandlers> {
		void initView();

		void openModal();
	}

	@Inject
	AssignmentEditorPresenter(EventBus eventBus, MyView view) {
		super(eventBus, view);
		logger.log(Level.INFO, "AssignmentEditPresenter()");
		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		getView().initView();
	}

	public void open() {
		getView().openModal();
	}
}