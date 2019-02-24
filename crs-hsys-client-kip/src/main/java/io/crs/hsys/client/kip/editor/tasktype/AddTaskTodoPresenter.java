/**
 * 
 */
package io.crs.hsys.client.kip.editor.tasktype;

import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

import io.crs.hsys.client.kip.browser.tasktodo.TaskTodoBrowserPresenter;

/**
 * @author robi
 *
 */
public class AddTaskTodoPresenter extends PresenterWidget<AddTaskTodoPresenter.MyView>
		implements AddTaskTodoUiHandlers {
	private static Logger logger = Logger.getLogger(TaskTodoBrowserPresenter.class.getName());

	public interface MyView extends View, HasUiHandlers<AddTaskTodoUiHandlers> {
		void open();
	}

	@Inject
	AddTaskTodoPresenter(EventBus eventBus, AddTaskTodoPresenter.MyView view, PlaceManager placeManager) {
		super(eventBus, view);
		logger.info("AddTaskTodoPresenter()");

		getView().setUiHandlers(this);
	}

	public void open() {
		getView().open();
	}
}
