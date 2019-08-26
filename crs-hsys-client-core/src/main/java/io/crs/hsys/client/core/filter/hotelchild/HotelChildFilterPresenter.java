/**
 * 
 */
package io.crs.hsys.client.core.filter.hotelchild;

import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;

import io.crs.hsys.client.core.datasource.HotelDataSource2;
import io.crs.hsys.client.core.filter.AbstractFilterUiHandlers;
import io.crs.hsys.client.core.security.CurrentUser;

/**
 * @author robi
 *
 */
public class HotelChildFilterPresenter extends AbstractHotelChildFilterPresenter<HotelChildFilterPresenter.MyView> {
	private static Logger logger = Logger.getLogger(HotelChildFilterPresenter.class.getName());

	public interface MyView extends AbstractHotelChildFilterPresenter.MyView, HasUiHandlers<AbstractFilterUiHandlers> {
	}

	@Inject
	HotelChildFilterPresenter(EventBus eventBus, MyView view, CurrentUser currentUser,
			HotelDataSource2 hotelDataSource) {
		super(eventBus, view, currentUser, hotelDataSource);
		logger.info("HotelChildFilterPresenter()");

		getView().setUiHandlers(this);
	}

	@Override
	public void filterChange() {
		// TODO Auto-generated method stub
		
	}
}
