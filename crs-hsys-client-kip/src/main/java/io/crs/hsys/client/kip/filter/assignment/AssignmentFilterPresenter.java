/**
 * 
 */
package io.crs.hsys.client.kip.filter.assignment;

import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;

import io.crs.hsys.client.core.filter.AbstractFilterPresenter;
import io.crs.hsys.client.core.filter.AbstractFilterUiHandlers;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.shared.cnst.InventoryType;

/**
 * @author robi
 *
 */
public class AssignmentFilterPresenter extends AbstractFilterPresenter<AssignmentFilterPresenter.MyView> {
	private static Logger logger = Logger.getLogger(AssignmentFilterPresenter.class.getName());

	public interface MyView extends AbstractFilterPresenter.MyView, HasUiHandlers<AbstractFilterUiHandlers> {
		InventoryType getSelectedInventoryType();
	}

	@Inject
	AssignmentFilterPresenter(EventBus eventBus, MyView view, CurrentUser currentUser) {
		super(eventBus, view, currentUser);
		logger.info("AssignmentFilterPresenter()");
		getView().setUiHandlers(this);
	}

	@Override
	public void onReveal() {
		super.onReveal();
	}

	public Boolean isOnlyActive() {
		return getView().isOnlyActive();
	}

	@Override
	public void filterChange() {
		// TODO Auto-generated method stub
		
	}
}
