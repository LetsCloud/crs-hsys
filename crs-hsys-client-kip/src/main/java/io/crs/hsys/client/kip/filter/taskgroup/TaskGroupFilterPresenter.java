/**
 * 
 */
package io.crs.hsys.client.kip.filter.taskgroup;

import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;

import io.crs.hsys.client.core.filter.AbstractFilterUiHandlers;
import io.crs.hsys.client.core.filter.hotelchild.HotelChildFilterPresenter;
import io.crs.hsys.client.core.security.CurrentUser;

/**
 * @author robi
 *
 */
public class TaskGroupFilterPresenter extends AbstractTaskGroupFilterPresenter<TaskGroupFilterPresenter.MyView> {
	private static Logger logger = Logger.getLogger(HotelChildFilterPresenter.class.getName());

	public interface MyView extends AbstractTaskGroupFilterPresenter.MyView, HasUiHandlers<AbstractFilterUiHandlers> {
	}

	@Inject
	TaskGroupFilterPresenter(EventBus eventBus, TaskGroupFilterPresenter.MyView view,
			CurrentUser currentUser) {
		super(eventBus, view, currentUser);
		logger.info("TaskGroupFilterPresenter()");

		getView().setUiHandlers(this);
	}

	@Override
	public void filterChange() {
		// TODO Auto-generated method stub
		
	}
}
