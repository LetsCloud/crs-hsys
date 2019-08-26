/**
 * 
 */
package io.crs.hsys.client.fro.filter.ratecode;

import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;

import io.crs.hsys.client.core.datasource.HotelDataSource2;
import io.crs.hsys.client.core.filter.AbstractFilterUiHandlers;
import io.crs.hsys.client.core.filter.FilterChangeEvent;
import io.crs.hsys.client.core.filter.FilterChangeEvent.DataTable;
import io.crs.hsys.client.core.filter.hotelchild.AbstractHotelChildFilterPresenter;
import io.crs.hsys.client.core.security.CurrentUser;

/**
 * @author robi
 *
 */
public class RateCodeFilterPresenter extends AbstractHotelChildFilterPresenter<RateCodeFilterPresenter.MyView> {
	private static Logger logger = Logger.getLogger(RateCodeFilterPresenter.class.getName());

	public interface MyView extends AbstractHotelChildFilterPresenter.MyView, HasUiHandlers<AbstractFilterUiHandlers> {
	}

	@Inject
	RateCodeFilterPresenter(EventBus eventBus, MyView view, CurrentUser currentUser, HotelDataSource2 hotelDataSource) {
		super(eventBus, view, currentUser, hotelDataSource);
		logger.info("RateCodeFilterPresenter()");
		getView().setUiHandlers(this);
	}

	@Override
	public void onReveal() {
		super.onReveal();
		logger.info("RateCodeFilterPresenter().onReveal()");
	}

	public Boolean isOnlyActive() {
		return getView().isOnlyActive();
	}

	@Override
	public void filterChange() {
		FilterChangeEvent.fire(RateCodeFilterPresenter.this, DataTable.RATE_CODE);
	}
}
