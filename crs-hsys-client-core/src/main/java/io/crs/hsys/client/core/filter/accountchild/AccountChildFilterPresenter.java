/**
 * 
 */
package io.crs.hsys.client.core.filter.accountchild;

import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;

import io.crs.hsys.client.core.filter.AbstractFilterPresenter;
import io.crs.hsys.client.core.filter.AbstractFilterUiHandlers;
import io.crs.hsys.client.core.security.CurrentUser;

/**
 * @author robi
 *
 */
public class AccountChildFilterPresenter extends AbstractFilterPresenter<AccountChildFilterPresenter.MyView> {
	private static Logger logger = Logger.getLogger(AccountChildFilterPresenter.class.getName());

	public interface MyView extends AbstractFilterPresenter.MyView, HasUiHandlers<AbstractFilterUiHandlers> {
		Boolean isOnlyActive();
	}

	@Inject
	AccountChildFilterPresenter(EventBus eventBus, MyView view, CurrentUser currentUser) {
		super(eventBus, view, currentUser);
		logger.info("AccountChildFilterPresenter()");
		getView().setUiHandlers(this);
	}

	@Override
	public void filterChange() {
		// TODO Auto-generated method stub
		
	}
}
