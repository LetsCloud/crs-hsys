/**
 * 
 */
package io.crs.hsys.client.cfg.filter;

import java.util.logging.Logger;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

import io.crs.hsys.client.cfg.filter.FilterChangeEvent.DataTable;
import io.crs.hsys.client.core.security.CurrentUser;

/**
 * @author robi
 *
 */
public abstract class AbstractFilterPresenter<V extends AbstractFilterPresenter.MyView> extends PresenterWidget<V>
		implements AbstractFilterUiHandlers {
	private static Logger logger = Logger.getLogger(AbstractFilterPresenter.class.getName());

	public interface MyView extends View {

		void reset();

		Boolean isOnlyActive();
	}

	protected final CurrentUser currentUser;
	
	public AbstractFilterPresenter(EventBus eventBus, V view, CurrentUser currentUser) {
		super(eventBus, view);
		logger.info("AbstractFilterPresenter()");
		this.currentUser = currentUser;
	}

	@Override
	public void filterChange() {
		FilterChangeEvent.fire(AbstractFilterPresenter.this, DataTable.ROOM_TYPE);
	}

	public Boolean isOnlyActive() {
		return getView().isOnlyActive();
	}
}
