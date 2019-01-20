/**
 * 
 */
package io.crs.hsys.client.kip.search.roomstatus;

import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

import io.crs.hsys.client.core.security.CurrentUser;

/**
 * @author robi
 *
 */
public class RoomStatusSearchPresenter extends PresenterWidget<RoomStatusSearchPresenter.MyView>
		implements RoomStatusSearchUiHandlers {
	private static Logger logger = Logger.getLogger(RoomStatusSearchPresenter.class.getName());

	public interface MyView extends View, HasUiHandlers<RoomStatusSearchUiHandlers> {
	}

	protected final CurrentUser currentUser;

	@Inject
	RoomStatusSearchPresenter(EventBus eventBus, RoomStatusSearchPresenter.MyView view, CurrentUser currentUser) {
		super(eventBus, view);
		logger.info("RoomStatusSearchPresenter()");
		this.currentUser = currentUser;
		getView().setUiHandlers(this);
	}
}
